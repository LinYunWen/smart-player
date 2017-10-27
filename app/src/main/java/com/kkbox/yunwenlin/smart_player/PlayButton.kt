package com.kkbox.yunwenlin.smart_player

import android.content.Context
import android.os.Build
import android.support.annotation.RequiresApi
import android.view.View
import android.widget.Button

class PlayButton(ctx: Context) : Button(ctx) {
    var mStartPlaying = true

    @RequiresApi(Build.VERSION_CODES.M)
    var clicker: View.OnClickListener = OnClickListener {
        onPlay(true)
    }

    @RequiresApi(Build.VERSION_CODES.M)
    fun onPlay(start: Boolean) {
        if (start) {
            if (!MainActivity.mRecordButton!!.mStartRecording) {
                MainActivity.mRecordButton!!.onRecord(false)
            }
            MainActivity.player.startPlaying()
        }
        MainActivity.mPauseButton!!.mPausePlaying = true
        MainActivity.mPauseButton!!.setText("Pause")
    }

    init {
        text = "Replay"
        setOnClickListener(clicker)
    }
}