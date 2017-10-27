package com.kkbox.yunwenlin.smart_player

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

import android.view.View
import android.view.ViewGroup
import android.webkit.WebView
import android.webkit.WebViewClient
import android.widget.*

class MainActivity : AppCompatActivity() {

    // create speech recognizer
    var speech = SpeechRecognizer.createSpeechRecognizer(this)
    var listener = AndroidSpeechRecognizer()
    val recognizerIntent = Intent(RecognizerIntent.ACTION_RECOGNIZE_SPEECH)
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

    inner class RecordButton(ctx: Context) : Button(ctx) {
        var mStartRecording = true
        @RequiresApi(Build.VERSION_CODES.M)
        var clicker: View.OnClickListener = OnClickListener {
            onRecord(mStartRecording)
        }

        @RequiresApi(Build.VERSION_CODES.M)
        fun onRecord(start: Boolean) {
            // stop playing when will to record
            if (mPauseButton!!.mPausePlaying) {
                mPauseButton!!.onPause(true)
            }
            if (start) {
                mRecordButton!!.setText("Stop Recording")
                speech.startListening(recognizerIntent)
            } else {
                mRecordButton!!.setText("Start Recording")
                speech.stopListening()
            }
            mRecordButton!!.mStartRecording = !mRecordButton!!.mStartRecording
        }

        init {
            text = "Start Recording"
            setOnClickListener(clicker)
        }
    }

    @RequiresApi(Build.VERSION_CODES.JELLY_BEAN_MR1)
    public override fun onCreate(icicle: Bundle?) {
        super.onCreate(icicle)
        mainContext = this
        mainActivity = this

        // Record to the external cache directory for visibility
        mFileName = externalCacheDir!!.absolutePath
        mFileName += "/audiorecord.amr"
        val scale = this.getResources().getDisplayMetrics().density

        genreStation = GenreStation(this)
        moodStation = MoodStation(this)

        ActivityCompat.requestPermissions(this, permissions, REQUEST_RECORD_AUDIO_PERMISSION)

        // set speech params
        speech.setRecognitionListener(listener)
        recognizerIntent.putExtra(RecognizerIntent.EXTRA_LANGUAGE_PREFERENCE, "zh-TW")
        recognizerIntent.putExtra(RecognizerIntent.EXTRA_CALLING_PACKAGE, this.getPackageName())
        recognizerIntent.putExtra(RecognizerIntent.EXTRA_LANGUAGE_MODEL,
                RecognizerIntent.LANGUAGE_MODEL_WEB_SEARCH)
        recognizerIntent.putExtra(RecognizerIntent.EXTRA_MAX_RESULTS, 3)

        val rl = RelativeLayout(this)
        // record button
        var layoutParams = RelativeLayout.LayoutParams(
                ViewGroup.LayoutParams.WRAP_CONTENT,
                ViewGroup.LayoutParams.WRAP_CONTENT
        )
        layoutParams.addRule(RelativeLayout.ALIGN_PARENT_LEFT)
        layoutParams.addRule(RelativeLayout.ALIGN_PARENT_BOTTOM)
        layoutParams.setMargins(30, 0, 30, 30)
        mRecordButton = RecordButton(this)
        rl.addView(mRecordButton,layoutParams)

        // play button
        var layoutParams2 = RelativeLayout.LayoutParams(
                ViewGroup.LayoutParams.WRAP_CONTENT,
                ViewGroup.LayoutParams.WRAP_CONTENT
        )
        layoutParams2.addRule(RelativeLayout.ALIGN_PARENT_RIGHT)
        layoutParams2.addRule(RelativeLayout.ALIGN_PARENT_BOTTOM)
        layoutParams2.setMargins(30, 0, 30, 30)
        mPlayButton = PlayButton(this)
        rl.addView(mPlayButton,layoutParams2)

        mSearchButton = SearchButton(this)

        mPauseButton = PauseButton(this)
        var layoutParams4 = RelativeLayout.LayoutParams(
                ViewGroup.LayoutParams.WRAP_CONTENT,
                ViewGroup.LayoutParams.WRAP_CONTENT
        )
        layoutParams4.addRule(RelativeLayout.ALIGN_PARENT_RIGHT)
        layoutParams4.addRule(RelativeLayout.ALIGN_PARENT_BOTTOM)
        layoutParams4.setMargins(30, 0, 30, 200)
        mPlayButton = PlayButton(this)
        rl.addView(mPauseButton,layoutParams4)

        // text
        var layoutParamsText = RelativeLayout.LayoutParams(
                ViewGroup.LayoutParams.WRAP_CONTENT,
                ViewGroup.LayoutParams.WRAP_CONTENT
        )
        layoutParamsText.addRule(RelativeLayout.CENTER_HORIZONTAL)
        layoutParamsText.setMargins(30, (80 * scale).toInt(), 30, 0)
        mText = TextView(this)
        mText!!.setText("Please speek")
        rl.addView(mText,layoutParamsText)

        // progressBar
        var layoutParamsPB = RelativeLayout.LayoutParams(
                ViewGroup.LayoutParams.WRAP_CONTENT,
                ViewGroup.LayoutParams.WRAP_CONTENT
        )
        layoutParamsPB.addRule(RelativeLayout.CENTER_IN_PARENT)
        progressBar = ProgressBar(this, null, android.R.attr.progressBarStyleLarge)
        progressBar.setVisibility(View.INVISIBLE)
        rl.addView(progressBar, layoutParamsPB)

        // image
        var layoutParamsImage = RelativeLayout.LayoutParams(
                (150 * scale).toInt(),
                (150 * scale).toInt()
        )
        layoutParamsImage.addRule(RelativeLayout.CENTER_HORIZONTAL)
        layoutParamsImage.setMargins(0, (230 * scale).toInt(), 0, 0)
        mImage = ImageView(this)
        rl.addView(mImage, layoutParamsImage)


        // spinner
        var layoutParamsSp = RelativeLayout.LayoutParams(
                ViewGroup.LayoutParams.WRAP_CONTENT,
                ViewGroup.LayoutParams.WRAP_CONTENT
        )
        layoutParamsSp.addRule(RelativeLayout.CENTER_HORIZONTAL)
        layoutParamsSp.setMargins(30, (40 * scale).toInt(), 30, 0)
        spinner = Spinner(this)
        rl.addView(spinner, layoutParamsSp)
        var list = ArrayList<String>()
        list.add("華語新歌")
        list.add("西洋新歌")
        list.add("日韓新歌")
        adapter = ArrayAdapter(this, android.R.layout.simple_spinner_item, list)
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
        spinner.setAdapter(MainActivity.adapter)
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
        var layoutParamsWeb = RelativeLayout.LayoutParams(
                (300 * scale).toInt(),
                (100 * scale).toInt()
        )
        layoutParamsWeb.addRule(RelativeLayout.CENTER_HORIZONTAL)
        layoutParamsWeb.setMargins(0, (120 * scale).toInt(), 0, (80 * scale).toInt())
        webView = WebView(this)
        var webSetting = webView.getSettings()
        webSetting.setJavaScriptEnabled(true)
        rl.addView(webView, layoutParamsWeb)
        // rl.addView(webView)
        // webView.setWebViewClient(WebViewClient())
        /*
        webView.webViewClient = object : WebViewClient() {
            override fun onPageFinished(view: WebView, url: String) {
                // webView.loadUrl("javascript:(function() {var audio = document.getElementById(\"audio-player\");audio.addEventListener(\"ended\", function(event) {alert(\"end\");});})();")
                webView.loadUrl("javascript:")
                super.onPageFinished(view, url)
            }
        }*/
        webView.addJavascriptInterface(JavascriptCall(), "jsObject")
        webView.setWebViewClient(object : WebViewClient() {
            override fun onPageFinished(view: WebView, url: String) {
                super.onPageFinished(view, url)
                webView.loadUrl("javascript:(function() { " +
                        "var audio = document.getElementById('audio-player');"
                        + "audio.addEventListener('ended', function(event) {"
                        + "window.jsObject.playFinish();"
                        + "});"
                        + "})();")
                println("finish")
            }
        })


        // enable auto-play on webView
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.JELLY_BEAN_MR1) {
            webView.getSettings().setMediaPlaybackRequiresUserGesture(false)
        }

        setContentView(rl)
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
                    // println("result text: " + result[0])
                }
            }
        }
    }

    override fun onStop() {
        super.onStop()
        speech.cancel()
        speech.destroy()
        if (recorder.mRecorder != null) {
            recorder.mRecorder!!.release()
            recorder.mRecorder = null
        }

        if (player.mPlayer != null) {
            player.mPlayer!!.release()
            player.mPlayer = null
        }
    }

    companion object {
        val LOG_TAG = "AudioRecord"
        private val REQUEST_RECORD_AUDIO_PERMISSION = 200
        lateinit var genreStation: GenreStation
        lateinit var moodStation: MoodStation
        var station = Category()
        lateinit var mainContext: Context
        lateinit var mainActivity: Activity

        var mFileName: String? = null
        var mText: TextView? = null
        var mImage: ImageView? = null
        lateinit var webView: WebView

        var spinnerFlag = false
        lateinit var progressBar: ProgressBar
        lateinit var spinner: Spinner
        lateinit var adapter: ArrayAdapter<String>

        var mRecordButton: RecordButton? = null
        var mPlayButton: PlayButton? = null
        var mSearchButton: SearchButton? = null
        var mPauseButton: PauseButton? = null

        var recorder = Recorder()
        var player = Player()
    }
}