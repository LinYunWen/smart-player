package com.kkbox.yunwenlin.smartPlayer

import android.content.Context
import android.os.Build
import android.support.annotation.RequiresApi
import android.util.AttributeSet
import android.view.View
import android.widget.Button

class PlayButton : Button {
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

    var mStartPlaying = false
    @RequiresApi(Build.VERSION_CODES.M)
    var clicker: View.OnClickListener = OnClickListener {
        onPlay(true)
    }

    @RequiresApi(Build.VERSION_CODES.M)
    fun onPlay(start: Boolean) {
        mStartPlaying = start
        if (mStartPlaying) {
            if (MainActivity.mRecordButton!!.mStartRecording) {
                MainActivity.mRecordButton!!.onRecord(false)
            }
            MainActivity.player.stopPlaying()
            MainActivity.player.startPlaying()

            // set pause button
            MainActivity.mPauseButton!!.mPausePlaying = false
            MainActivity.mPauseButton!!.text = "Pause"
        }
    }

    init {
        text = "Replay"
        setOnClickListener(clicker)
    }
}