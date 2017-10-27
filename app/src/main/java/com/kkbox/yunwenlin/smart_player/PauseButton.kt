package com.kkbox.yunwenlin.smart_player

import android.content.Context
import android.os.Build
import android.support.annotation.RequiresApi
import android.view.View
import android.widget.Button

class PauseButton(ctx: Context) : Button(ctx) {
    var mPausePlaying = true

    @RequiresApi(Build.VERSION_CODES.M)
    var clicker: View.OnClickListener = OnClickListener {
        onPause(mPausePlaying)
    }

    @RequiresApi(Build.VERSION_CODES.M)
    internal fun onPause(start: Boolean) {
        if (start) {
            MainActivity.player.stopPlaying()
            MainActivity.mPauseButton!!.setText("Resume")
        } else {
            if (!MainActivity.mRecordButton!!.mStartRecording) {
                MainActivity.mRecordButton!!.onRecord(false)
            }
            MainActivity.player.resumePlaying()
            MainActivity.mPauseButton!!.setText("Pause")
        }
        mPausePlaying = !mPausePlaying
    }

    init {
        text = "Pause"
        setOnClickListener(clicker)
    }
}