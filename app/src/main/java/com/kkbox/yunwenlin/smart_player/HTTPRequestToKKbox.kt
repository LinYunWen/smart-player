package com.kkbox.yunwenlin.smart_player

import android.os.AsyncTask
import android.os.Build
import android.support.annotation.RequiresApi
import android.view.View
import org.json.JSONArray

class HTTPRequestToKKbox: AsyncTask<String, Unit, JSONArray>() {
    var kkboxClient = KKboxOpenAPIClient()

    override fun onPreExecute() {
        MainActivity.mText!!.setText("Searching...")
        MainActivity.progressBar.setVisibility(View.VISIBLE)
        MainActivity.mImage!!.setVisibility(View.INVISIBLE)
    }


    // @p[0]: query
    // @p[1]: type
    // @p[2]: territory
    @RequiresApi(Build.VERSION_CODES.KITKAT)
    override fun doInBackground(vararg p: String): JSONArray {
        val result = kkboxClient.doSearch(p[0], p[1], p[2])
        val info = kkboxClient.parser(result, p[0])
        println("parse: " + info)
        if (info.getJSONObject(0).has("error")) {
            val error = info.getJSONObject(0).getString("error")
            println("error: " + error)
            return info
        }

        // for widget
        MainActivity.player.musicId = kkboxClient.getId(info.getJSONObject(0))

        // get image
        val downloadImage = DownloadImage()
        downloadImage.execute(kkboxClient.getImage(info.getJSONObject(0)))

        return info
    }


    @RequiresApi(Build.VERSION_CODES.M)
    override fun onPostExecute(result: JSONArray) {
        MainActivity.mImage!!.setVisibility(View.VISIBLE)
        MainActivity.progressBar.setVisibility(View.INVISIBLE)

        // error
        if (result.getJSONObject(0).has("error")) {
            MainActivity.mText!!.setText("result: " + result.getJSONObject(0).getString("error"))
        } else {
            MainActivity.mText!!.setText(kkboxClient.getName(result.getJSONObject(0)))
        }

        MainActivity.player.dataInfo = result
        MainActivity.mPlayButton!!.onPlay(true)
    }
}