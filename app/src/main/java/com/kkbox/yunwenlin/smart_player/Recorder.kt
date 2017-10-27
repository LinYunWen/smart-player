package com.kkbox.yunwenlin.smart_player

import android.media.MediaRecorder
import android.os.Build
import android.support.annotation.RequiresApi
import android.util.Log
import java.io.IOException

class Recorder {
    var mRecorder: MediaRecorder? = null
    lateinit var transcript: String

    internal fun startRecording() {
        mRecorder = MediaRecorder()
        mRecorder!!.setAudioSource(MediaRecorder.AudioSource.MIC)
        mRecorder!!.setOutputFormat(MediaRecorder.OutputFormat.AMR_NB)
        mRecorder!!.setOutputFile(MainActivity.mFileName)
        mRecorder!!.setAudioEncoder(MediaRecorder.AudioEncoder.AMR_NB)

        try {
            mRecorder!!.prepare()
        } catch (e: IOException) {
            Log.e(MainActivity.LOG_TAG, "record prepare() failed")
        }

        mRecorder!!.start()
    }

    internal fun stopRecording() {
        mRecorder!!.stop()
        mRecorder!!.release()
        mRecorder = null
    }


    @RequiresApi(Build.VERSION_CODES.M)
    internal fun afterRecord() {
        if (transcript == "下一首" || transcript == "next song") {
            MainActivity.player.playOther(1)
        } else if (transcript == "上一首" || transcript == "front song") {
            MainActivity.player.playOther(-1)
        } else if (transcript == "暫停播放") {
            MainActivity.player.stopPlaying()
        } else if (transcript == "繼續播放") {
            MainActivity.player.resumePlaying()
        } else if (transcript == "重新播放") {
            MainActivity.player.startPlaying()
        } else if (transcript.contains("電台")) {
            var moodStationId = MainActivity.station.moodCategory.matchMood(transcript)
            println("mood station id: " + moodStationId)
            if (moodStationId == "error") {
                MainActivity.mText!!.setText("Sorry, no match station.")
                return
            }

            val kkboxStation = HTTPGetStationToKKbox()
            kkboxStation.execute(moodStationId, "TW", "mood")
        } else if (MainActivity.station.genreCategory.checkGenreType(transcript) != -1) {
            var genreStationId = MainActivity.station.genreCategory.matchGenre(transcript, MainActivity.station.genreCategory.checkGenreType(transcript))
            println("genre station id: " + genreStationId)
            if (genreStationId == "error") {
                MainActivity.mText!!.setText("Sorry, no match station.")
                return
            }

            val kkboxStation = HTTPGetStationToKKbox()
            kkboxStation.execute(genreStationId, "TW", "genre")
        } else {
            MainActivity.mSearchButton!!.onSearch()
        }
    }
}