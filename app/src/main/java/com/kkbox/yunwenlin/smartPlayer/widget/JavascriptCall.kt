package com.kkbox.yunwenlin.smartPlayer

import android.os.Build
import android.support.annotation.RequiresApi
import android.util.Log
import android.webkit.JavascriptInterface

class JavascriptCall {

    @RequiresApi(Build.VERSION_CODES.M)
    @JavascriptInterface
    fun playFinish() {
        MainActivity.player.playOther(1)
        Log.i(MainActivity.LOG_TAG, "play end")
    }

    fun stopWidgetPlay() {
        MainActivity.player.pausePlaying()
        Log.i(MainActivity.LOG_TAG, "stop play")
    }
}