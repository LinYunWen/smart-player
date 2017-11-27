package com.kkbox.yunwenlin.smartPlayer

import android.Manifest
import android.app.Activity
import android.content.Context
import android.content.Intent
import android.content.pm.PackageManager

import android.os.Build
import android.os.Bundle
import android.speech.RecognizerIntent
import android.speech.SpeechRecognizer
import android.support.annotation.RequiresApi
import android.support.v4.app.ActivityCompat
import android.support.v7.app.AppCompatActivity
import android.util.Log

import android.view.View
import android.webkit.WebView
import android.webkit.WebViewClient
import android.widget.*
import com.kkbox.yunwenlin.smartPlayer.buttons.RecordButton
import com.kkbox.yunwenlin.smartPlayer.network.Network
import com.koushikdutta.ion.Ion

class MainActivity : AppCompatActivity() {

    private val REQ_CODE_SPEECH_INPUT = 100

    // Requesting permission to RECORD_AUDIO
    private var permissionToRecordAccepted = false
    private val permissions = arrayOf(Manifest.permission.RECORD_AUDIO)
    override fun onRequestPermissionsResult(requestCode: Int, permissions: Array<String>, grantResults: IntArray) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults)
        when (requestCode) {
            REQUEST_RECORD_AUDIO_PERMISSION -> permissionToRecordAccepted = grantResults[0] == PackageManager.PERMISSION_GRANTED
        }
        if (!permissionToRecordAccepted) finish()
    }

    @RequiresApi(Build.VERSION_CODES.M)
    public override fun onCreate(icicle: Bundle?) {
        super.onCreate(icicle)
        mainContext = this
        mainActivity = this
        genreStation = GenreStation(this)
        moodStation = MoodStation(this)
        network = Network(this)

        // Record to the external cache directory for visibility
        mFileName = externalCacheDir!!.absolutePath
        mFileName += "/audiorecord.amr"

        Ion.getDefault(this).configure().setLogging("MyLogs", Log.DEBUG)
        ActivityCompat.requestPermissions(this, permissions, REQUEST_RECORD_AUDIO_PERMISSION)

        if (network.checkNetwork()) {
            // get access token
            accessToken = kkboxClient.getAccessToken()
        }

        // speech recognizer
        var listener = AndroidSpeechRecognizer()
        speech = SpeechRecognizer.createSpeechRecognizer(this)
        recognizerIntent = Intent(RecognizerIntent.ACTION_RECOGNIZE_SPEECH)

        speech.setRecognitionListener(listener)
        recognizerIntent.putExtra(RecognizerIntent.EXTRA_LANGUAGE_PREFERENCE, "zh-TW")
        recognizerIntent.putExtra(RecognizerIntent.EXTRA_CALLING_PACKAGE, this.packageName)
        recognizerIntent.putExtra(RecognizerIntent.EXTRA_LANGUAGE_MODEL, RecognizerIntent.LANGUAGE_MODEL_WEB_SEARCH)
        recognizerIntent.putExtra(RecognizerIntent.EXTRA_MAX_RESULTS, 3)

        // elements
        setContentView(R.layout.activity_main)
        mRecordButton = findViewById(R.id.record_button) as RecordButton
        mPlayButton = findViewById(R.id.play_button) as PlayButton
        mPauseButton = findViewById(R.id.pause_button) as PauseButton
        mSearchButton = SearchButton(this)
        mText = findViewById(R.id.title_text) as TextView
        mImage = findViewById(R.id.image) as ImageView
        progressBar = findViewById(R.id.progress) as ProgressBar
        spinner = findViewById(R.id.spinner) as Spinner
        webView = findViewById(R.id.webView) as WebView

        // spinner list element
        var list = ArrayList<String>()
        list.add("華語新歌")
        list.add("西洋新歌")
        list.add("日韓新歌")
        adapter = ArrayAdapter(this, android.R.layout.simple_spinner_item, list)
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
        spinner.adapter = MainActivity.adapter
        spinner.onItemSelectedListener = object : AdapterView.OnItemSelectedListener {
            override fun onItemSelected(adapterView: AdapterView<*>, view: View, position: Int, id: Long) {
                if (spinnerFlag) {
                    val kkboxStation = HTTPGetStationToKKbox()
                    kkboxStation.execute("CqKi7kny7nlTW42w5M", "TW", "mood")
                    Toast.makeText(this@MainActivity, "您選擇" + adapterView.selectedItem.toString(), Toast.LENGTH_LONG).show()
                }
                spinnerFlag = true
            }

            override fun onNothingSelected(arg0: AdapterView<*>) {
                Toast.makeText(this@MainActivity, "您沒有選擇任何項目", Toast.LENGTH_LONG).show()
            }
        }

        // webView
        var webSetting = webView.settings
        webSetting.javaScriptEnabled = true
        webView.addJavascriptInterface(JavascriptCall(), "jsObject")
        webView.webViewClient = object : WebViewClient() {
            override fun onPageFinished(view: WebView, url: String) {
                super.onPageFinished(view, url)
                webView.loadUrl("javascript:(function() { "
                        + "var audio = document.getElementById('audio-player');"
                        + "audio.addEventListener('ended', function(event) {"
                        + "window.jsObject.playFinish();"
                        + "});"
                        + "})();")
            }
        }

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.JELLY_BEAN_MR1) {
            webView.settings.mediaPlaybackRequiresUserGesture = false
        }
    }

    @RequiresApi(Build.VERSION_CODES.M)
    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)

        when (requestCode) {
            REQ_CODE_SPEECH_INPUT -> {
                if (resultCode == Activity.RESULT_OK && null != data) {

                    val result = data.getStringArrayListExtra(RecognizerIntent.EXTRA_RESULTS)
                    recorder.transcript = result[0]
                    recorder.afterRecord()
                    Log.i("result text: ", result[0])
                    // txtSpeechInput.setText(result[0])
                }
            }
        }
    }

    @RequiresApi(Build.VERSION_CODES.M)
    override fun onPause() {
        super.onPause()
        Log.i("app state: ", "on pause")

        if (mPlayButton!!.mStartPlaying) {
            mPauseButton!!.onPause(true)
        }
        if (mRecordButton!!.mStartRecording) {
            mRecordButton!!.onRecord(false)
        }
    }

    override fun onDestroy() {
        super.onDestroy()
        Log.i("app state: ", "on destory")
        speech.cancel()
        speech.destroy()

        if (recorder.mRecorder != null) {
            recorder.mRecorder!!.release()
            recorder.mRecorder = null
        }

        player.stopPlaying()
    }

    companion object {
        val LOG_TAG = "Smart Player"
        private val REQUEST_RECORD_AUDIO_PERMISSION = 200
        lateinit var genreStation: GenreStation
        lateinit var moodStation: MoodStation
        lateinit var mainContext: Context
        lateinit var mainActivity: Activity
        lateinit var speech: SpeechRecognizer
        lateinit var recognizerIntent: Intent
        lateinit var webView: WebView
        lateinit var progressBar: ProgressBar
        lateinit var spinner: Spinner
        lateinit var adapter: ArrayAdapter<String>
        lateinit var network: Network

        var mFileName: String? = null
        var mText: TextView? = null
        var mImage: ImageView? = null

        var mRecordButton: RecordButton? = null
        var mPlayButton: PlayButton? = null
        var mSearchButton: SearchButton? = null
        var mPauseButton: PauseButton? = null

        var recorder = Recorder()
        var player = Player()
        var station = Category()
        var uiAction = UIAction()
        val kkboxClient = KKboxOpenAPIClient()
        var spinnerFlag = false
        var accessToken = ""
    }
}