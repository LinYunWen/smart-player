package com.kkbox.yunwenlin.smartPlayer

import android.view.View

class UIAction {
    internal fun beforeRequest(str: String) {
        MainActivity.mText!!.text = str
        MainActivity.progressBar.visibility = View.VISIBLE
        MainActivity.mImage!!.visibility = View.INVISIBLE
    }

    internal fun finishRequest(str: String) {
        MainActivity.progressBar.visibility = View.INVISIBLE
        MainActivity.mImage!!.visibility = View.VISIBLE
        MainActivity.mText!!.text = str
    }
}