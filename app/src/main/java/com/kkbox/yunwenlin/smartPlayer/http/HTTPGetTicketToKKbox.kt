package com.kkbox.yunwenlin.smartPlayer

import android.os.AsyncTask
import android.os.Build
import android.support.annotation.RequiresApi

class HTTPGetTicketToKKbox : AsyncTask<Int, Unit, String>() {

    override fun onPreExecute() {
        MainActivity.uiAction.beforeRequest("Changing...")
    }

    // @p[0]: the index of song
    override fun doInBackground(vararg p: Int?): String? {
        // for widget
        MainActivity.player.musicId = MainActivity.kkboxClient.getId(MainActivity.player.dataInfo.getJSONObject(p[0]!!))

        //get image
        val downloadImage = DownloadIamge()
        downloadImage.execute(MainActivity.kkboxClient.getImage(MainActivity.player.dataInfo.getJSONObject(p[0]!!)))

        return MainActivity.kkboxClient.getName(MainActivity.player.dataInfo.getJSONObject(p[0]!!))
    }

    @RequiresApi(Build.VERSION_CODES.M)
    override fun onPostExecute(result: String) {
        MainActivity.uiAction.finishRequest(result)
        MainActivity.mPlayButton!!.onPlay(true)
    }
}