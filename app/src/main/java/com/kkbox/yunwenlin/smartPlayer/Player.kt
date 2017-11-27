package com.kkbox.yunwenlin.smartPlayer

import android.media.MediaPlayer
import android.os.Build
import android.support.annotation.RequiresApi
import android.util.Log
import org.json.JSONArray

class Player {
    var mPlayer: MediaPlayer? = null
    var curPlayIndex = 0
    lateinit var dataInfo: JSONArray
    var musicId = "0rZcEjfB97UdzRVIrh"
    val widget = Widget()

    @RequiresApi(Build.VERSION_CODES.M)
    internal fun startPlaying() {
        // for widget
        if (MainActivity.network.checkNetwork()) {
            MainActivity.webView.post(Runnable {
                MainActivity.webView.loadUrl(widget.getTrackWidgetURL(musicId))
            })
        }
        Log.i("widget load url: ", musicId)
    }

    internal fun stopPlaying() {
        if (mPlayer != null) {
            mPlayer!!.release()
            mPlayer = null
        }
    }

    internal fun pausePlaying() {
        // for widget
        Log.i(MainActivity.LOG_TAG, "widget do pause")
        MainActivity.webView.post(Runnable {
            MainActivity.webView.loadUrl("javascript:(function() {"
                    + "document.getElementsByClassName('clickable control icon pause')[0].click();"
                    + "})();")
        })
    }

    internal fun resumePlaying() {
        // for widget
        Log.i(MainActivity.LOG_TAG, "widget do resume")
        MainActivity.webView.post(Runnable {
            MainActivity.webView.loadUrl("javascript:(function() {"
                    + "document.getElementsByClassName('clickable control icon play')[0].click();"
                    + "})();")
        })
    }

    @RequiresApi(Build.VERSION_CODES.M)
    internal fun playOther(lambda: Int) {
        curPlayIndex += lambda
        if (curPlayIndex >= 0) {
            curPlayIndex %= dataInfo.length()
        } else {
            curPlayIndex += dataInfo.length()
        }
        Log.i("cur play index: ", curPlayIndex.toString())
        // for widget
        musicId = dataInfo.getJSONObject(curPlayIndex).getString("id")
        if (MainActivity.network.checkNetwork()) {
            MainActivity.mainActivity.runOnUiThread(Runnable {
                val kkboxClient = HTTPGetTicketToKKbox()
                kkboxClient.execute(curPlayIndex)
            })
        } else {
            MainActivity.player.pausePlaying()
        }
    }
}