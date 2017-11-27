package com.kkbox.yunwenlin.smartPlayer

import android.os.AsyncTask
import android.util.Log
import android.view.View

class HTTPRequestForRecognition : AsyncTask<String, Unit, String>() {
    var speechRecognize = GoogleSpeechRecognitionAPIClient()

    override fun onPreExecute() {
        MainActivity.mText!!.text = "Recognizing..."
        MainActivity.progressBar.visibility = View.VISIBLE
    }

    override fun doInBackground(vararg p: String): String {
        val transcript = speechRecognize.doSpeechRecognition()
        var message = speechRecognize.parser(transcript)
        Log.i(MainActivity.LOG_TAG, message)
        return message
    }

    override fun onPostExecute(result: String) {
        var searchClient = HTTPRequestToKKbox()
        searchClient.execute(MainActivity.recorder.transcript, "track", "TW")
    }
}