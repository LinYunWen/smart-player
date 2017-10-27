package com.kkbox.yunwenlin.smart_player

import android.os.AsyncTask
import android.os.Build
import android.support.annotation.RequiresApi
import android.view.View

class HTTPGetTicketToKKbox : AsyncTask<Int, Unit, String>() {
    val kkboxClient = KKboxOpenAPIClient()
    override fun onPreExecute() {
        MainActivity.mText!!.setText("Changing...")
        MainActivity.progressBar.setVisibility(View.VISIBLE)
        MainActivity.mImage!!.setVisibility(View.INVISIBLE)
    }

    override fun doInBackground(vararg p: Int?): String? {
        // for widget
        MainActivity.player.musicId = kkboxClient.getId(MainActivity.player.dataInfo.getJSONObject(p[0]!!))

        //get image
        val downloadImage = DownloadImage()
        downloadImage.execute(kkboxClient.getImage(MainActivity.player.dataInfo.getJSONObject(p[0]!!)))

        return kkboxClient.getName(MainActivity.player.dataInfo.getJSONObject(p[0]!!))
    }

    @RequiresApi(Build.VERSION_CODES.M)
    override fun onPostExecute(result: String) {
        MainActivity.progressBar.setVisibility(View.INVISIBLE)
        MainActivity.mImage!!.setVisibility(View.VISIBLE)
        MainActivity.mText!!.setText(result)
        MainActivity.mPlayButton!!.onPlay(true)
    }
}