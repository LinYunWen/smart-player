package com.kkbox.yunwenlin.smart_player

import android.os.Build
import android.support.annotation.RequiresApi
import android.webkit.JavascriptInterface

class JavascriptCall {

    @RequiresApi(Build.VERSION_CODES.M)
    @JavascriptInterface
    fun playFinish() {
        MainActivity.player.playOther(1)
        println("play end")
    }

    fun stopWidgetPlay() {
        MainActivity.player.stopPlaying()
        println("stop play")
    }
}