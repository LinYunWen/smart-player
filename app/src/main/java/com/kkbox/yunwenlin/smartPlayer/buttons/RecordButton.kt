package com.kkbox.yunwenlin.smartPlayer.buttons

import android.content.Context
import android.os.Build
import android.support.annotation.RequiresApi
import android.util.AttributeSet
import android.view.View
import android.widget.Button
import com.kkbox.yunwenlin.smartPlayer.MainActivity

class RecordButton : Button {
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

    var mStartRecording = false
    @RequiresApi(Build.VERSION_CODES.M)
    var clicker: View.OnClickListener = OnClickListener {
        onRecord(!mStartRecording)
        /*
        // for dialog record speech
        try {
            startActivityForResult(recognizerIntent, REQ_CODE_SPEECH_INPUT)
        } catch (e: ActivityNotFoundException) {
            // Toast.makeText(getApplicationContext(),
            //         getString(R.string.speech_not_supported),
            //         Toast.LENGTH_SHORT).show();
            println("error: " + e.printStackTrace())
        }
        */
    }

    @RequiresApi(Build.VERSION_CODES.M)
    fun onRecord(start: Boolean) {
        mStartRecording = start
        if (mStartRecording) {
            // stop playing when will to record
            if (MainActivity.mPlayButton!!.mStartPlaying) {
                MainActivity.mPauseButton!!.onPause(true)
            }
            // recorder.startRecording()
            MainActivity.mRecordButton!!.text = "Stop Recording"
            MainActivity.speech.startListening(MainActivity.recognizerIntent)
        } else {
            // recorder.stopRecording()
            MainActivity.mRecordButton!!.text = "Start Recording"
            MainActivity.speech.stopListening()
        }

    }

    init {
        text = "Start Recording"
        setOnClickListener(clicker)
    }
}