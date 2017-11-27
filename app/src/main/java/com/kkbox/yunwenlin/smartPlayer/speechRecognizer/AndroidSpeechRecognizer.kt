package com.kkbox.yunwenlin.smartPlayer

import android.os.Build
import android.os.Bundle
import android.speech.RecognitionListener
import android.speech.SpeechRecognizer
import android.support.annotation.RequiresApi
import android.util.Log

class AndroidSpeechRecognizer : RecognitionListener {
    override fun onReadyForSpeech(p0: Bundle?) {
        Log.i(MainActivity.LOG_TAG, "recognizer ready")
    }

    override fun onRmsChanged(p0: Float) {
        Log.i(MainActivity.LOG_TAG, "recognizer on changed")
    }

    override fun onBufferReceived(p0: ByteArray?) {
        Log.i(MainActivity.LOG_TAG, "recognizer on buffer receive")
    }

    override fun onPartialResults(p0: Bundle?) {
        Log.i(MainActivity.LOG_TAG, "recognizer on partial result")
    }

    override fun onEvent(p0: Int, p1: Bundle?) {
        Log.i(MainActivity.LOG_TAG, "recognizer event")
    }

    override fun onBeginningOfSpeech() {
        Log.i(MainActivity.LOG_TAG, "recognizer start")
    }

    override fun onEndOfSpeech() {
        Log.i(MainActivity.LOG_TAG, "recognizer end")
    }

    override fun onError(p0: Int) {
        Log.i(MainActivity.LOG_TAG, "recognizer on error")
    }

    @RequiresApi(Build.VERSION_CODES.M)
    override fun onResults(result: Bundle?) {
        var matches = result!!.getStringArrayList(SpeechRecognizer.RESULTS_RECOGNITION)
        MainActivity.adapter.clear()
        MainActivity.spinnerFlag = false
        MainActivity.adapter.addAll(matches)
        MainActivity.spinner.adapter = MainActivity.adapter

        MainActivity.recorder.transcript = matches[0]
        MainActivity.recorder.afterRecord()
    }
}
