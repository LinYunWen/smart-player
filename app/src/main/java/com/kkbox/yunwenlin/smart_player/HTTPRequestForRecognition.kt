package com.kkbox.yunwenlin.smart_player

import android.os.AsyncTask
import android.view.View

class HTTPRequestForRecognition: AsyncTask<String, Unit, String>() {
    var speechRecognize = GoogleSpeechRecognitionAPIClient()

    override fun onPreExecute() {
        MainActivity.mText!!.setText("Recognizing...")
        MainActivity.progressBar.setVisibility(View.VISIBLE)
    }

    override fun doInBackground(vararg p: String): String {
        val transcript = speechRecognize.doSpeechRecognization(p[0])
        var message = speechRecognize.parser(transcript)
        return message
    }

    override fun onPostExecute(result: String) {
        var searchClient = HTTPRequestToKKbox()
        searchClient.execute(MainActivity.recorder.transcript, "track", "TW")
    }
}