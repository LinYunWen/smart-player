package com.kkbox.yunwenlin.smart_player

import android.os.Build
import android.os.Bundle
import android.speech.RecognitionListener
import android.speech.SpeechRecognizer
import android.support.annotation.RequiresApi

class AndroidSpeechRecognizer: RecognitionListener {
    override fun onReadyForSpeech(p0: Bundle?) {
        println("ready")
    }

    override fun onRmsChanged(p0: Float) {
        println("on changed")
    }

    override fun onBufferReceived(p0: ByteArray?) {
        println("on buffer receive")
    }

    override fun onPartialResults(p0: Bundle?) {
        println("on partial result")
    }

    override fun onEvent(p0: Int, p1: Bundle?) {
        println("event")
    }

    override fun onBeginningOfSpeech() {
        println("start")
    }

    override fun onEndOfSpeech() {
        println("end")
    }

    override fun onError(p0: Int) {
        println("some error")
    }

    @RequiresApi(Build.VERSION_CODES.M)
    override fun onResults(result: Bundle?) {
        var matches = result!!.getStringArrayList(SpeechRecognizer.RESULTS_RECOGNITION)
        // set list on main UI
        MainActivity.adapter.clear()
        MainActivity.spinnerFlag = false
        MainActivity.adapter.addAll(matches)
        MainActivity.spinner.setAdapter(MainActivity.adapter)

        // put result in transcript
        MainActivity.recorder.transcript = matches[0]
        MainActivity.recorder.afterRecord()
    }

}
