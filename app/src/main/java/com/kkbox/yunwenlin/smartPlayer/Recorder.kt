package com.kkbox.yunwenlin.smartPlayer

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
        if (mRecorder != null) {
            mRecorder!!.stop()
            mRecorder!!.release()
            mRecorder = null
        }
    }

    @RequiresApi(Build.VERSION_CODES.M)
    internal fun afterRecord() {
        if (!MainActivity.network.checkNetwork()) {
            MainActivity.mText!!.text = "Please turn on your network"
            return
        }
        if (MainActivity.accessToken.isEmpty()) {
            MainActivity.accessToken = MainActivity.kkboxClient.getAccessToken()
        }

        when (transcript) {
            "下一首", "next song" -> MainActivity.player.playOther(1)
            "上一首", "front song" -> MainActivity.player.playOther(-1)
            "暫停播放" -> MainActivity.player.pausePlaying()
            "繼續播放" -> MainActivity.player.resumePlaying()
            "重新播放" -> MainActivity.player.startPlaying()
            else -> {
                when {
                    transcript.contains("電台") -> {
                        val moodStationId = MainActivity.station.moodCategory.matchMood(transcript)
                        Log.i("Mood station id: ", moodStationId)

                        // no this mood station
                        if (moodStationId == "error") {
                            MainActivity.mText!!.text = "Sorry, no match station."
                            return
                        }
                        // to get station tracks
                        val kkboxStation = HTTPGetStationToKKbox()
                        kkboxStation.execute(moodStationId, "TW", "mood")
                    }
                    MainActivity.station.genreCategory.checkGenreType(transcript) != "error" -> {
                        val genreStationId = MainActivity.station.genreCategory.matchGenre(transcript)

                        // no this genre station
                        if (genreStationId == "error") {
                            MainActivity.mText!!.text = "Sorry, no match station."
                            return
                        }

                        // to get station tracks
                        val kkboxStation = HTTPGetStationToKKbox()
                        kkboxStation.execute(genreStationId, "TW", "genre")
                    }
                    else -> {
                        MainActivity.mSearchButton!!.onSearch()
                    }
                }
            }
        }
    }
}