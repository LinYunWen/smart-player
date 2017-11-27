package com.kkbox.yunwenlin.smartPlayer

import android.content.Context
import android.os.Build
import android.support.annotation.RequiresApi
import android.util.AttributeSet
import android.view.View
import android.widget.Button

class PauseButton : Button {
    // ----- constructor
    internal val context: Context

    constructor(context: Context) : super(context) {
        this.context = context
    }

    constructor(context: Context, attrs: AttributeSet) : super(context, attrs) {
        this.context = context
    }

    constructor(context: Context, attrs: AttributeSet, defStyle: Int) : super(context, attrs, defStyle) {
        this.context = context
    }
    // ------ constructor

    var mPausePlaying = false
    @RequiresApi(Build.VERSION_CODES.M)
    var clicker: View.OnClickListener = OnClickListener {
        onPause(!mPausePlaying)
    }

    @RequiresApi(Build.VERSION_CODES.M)
    internal fun onPause(start: Boolean) {
        mPausePlaying = start
        if (mPausePlaying) {
            MainActivity.mPlayButton!!.mStartPlaying = false
            MainActivity.player.pausePlaying()
            MainActivity.mPauseButton!!.text = "Resume"
        } else {
            if (MainActivity.mRecordButton!!.mStartRecording) {
                MainActivity.mRecordButton!!.onRecord(false)
            }
            MainActivity.mPlayButton!!.mStartPlaying = true
            MainActivity.player.resumePlaying()
            MainActivity.mPauseButton!!.text = "Pause"
        }
    }

    init {
        text = "Pause"
        setOnClickListener(clicker)
    }
}