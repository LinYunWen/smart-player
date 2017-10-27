package com.kkbox.yunwenlin.smart_player

import android.media.MediaPlayer
import android.os.Build
import android.support.annotation.RequiresApi
import org.json.JSONArray
import android.media.MediaPlayer.OnCompletionListener

class Player {
    var mPlayer: MediaPlayer? = null
    var curPlayIndex = 0
    lateinit var dataInfo: JSONArray
    lateinit var musicUrl: String
    var musicId = "0rZcEjfB97UdzRVIrh"
    val widget = Widget()

    @RequiresApi(Build.VERSION_CODES.M)
    internal fun startPlaying() {
        // for widget
        MainActivity.webView.post(Runnable { MainActivity.webView.loadUrl(widget.getTrackWidgetURL(musicId)) })
        println("load url: " + musicId)
    }

    internal fun stopPlaying() {
        println("do pause widget")
        MainActivity.webView.post(Runnable { MainActivity.webView.loadUrl("javascript:(function() {"
                + "document.getElementsByClassName('clickable control icon pause')[0].click();"
                + "})();") })
    }

    internal fun resumePlaying() {
        println("do resume widget")
        MainActivity.webView.post(Runnable { MainActivity.webView.loadUrl("javascript:(function() {"
                + "document.getElementsByClassName('clickable control icon play')[0].click();"
                + "})();") })
    }

    @RequiresApi(Build.VERSION_CODES.M)
    internal fun playOther(lamda: Int) {
        // for widget
        curPlayIndex += lamda
        if (curPlayIndex >= 0) {
            curPlayIndex %= dataInfo.length()
        } else {
            curPlayIndex += dataInfo.length()
        }

        println("cur play: " + curPlayIndex)
        musicId = dataInfo.getJSONObject(curPlayIndex).getString("id")

        MainActivity.mainActivity.runOnUiThread(Runnable {
            val kkboxClient = HTTPGetTicketToKKbox()
            kkboxClient.execute(curPlayIndex)
        })
    }

    @RequiresApi(Build.VERSION_CODES.M)
    internal fun autoPlayNextOnCompletion(mediaPlayer: MediaPlayer) {
        mediaPlayer.setOnCompletionListener(OnCompletionListener {
            println("on completion")
            MainActivity.mPlayButton!!.onPlay(false)
            MainActivity.player.playOther(1)
        })
    }
}