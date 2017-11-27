package com.kkbox.yunwenlin.smartPlayer

import android.os.AsyncTask
import android.os.Build
import android.support.annotation.RequiresApi
import android.util.Log
import org.json.JSONArray

class HTTPRequestToKKbox: AsyncTask<String, Unit, JSONArray>() {

    override fun onPreExecute() {
        MainActivity.uiAction.beforeRequest("Searching...")
    }

    // @p[0]: query
    // @p[1]: type
    // @p[2]: territory
    @RequiresApi(Build.VERSION_CODES.KITKAT)
    override fun doInBackground(vararg p: String): JSONArray {
        val result = MainActivity.kkboxClient.doSearch(p[0], p[1], p[2])

        val info = MainActivity.kkboxClient.parser(result, p[0])

        // on error
        if (info.getJSONObject(0).has("error")) {
            val error = info.getJSONObject(0).getString("error")
            Log.e(MainActivity.LOG_TAG, "error: $error")
            return info
        }

        // for widget
        MainActivity.player.musicId = MainActivity.kkboxClient.getId(info.getJSONObject(0))

        // get image
        val downloadImage = DownloadIamge()
        downloadImage.execute(MainActivity.kkboxClient.getImage(info.getJSONObject(0)))

        return info
    }

    @RequiresApi(Build.VERSION_CODES.M)
    override fun onPostExecute(result: JSONArray) {
        var text: String

        // error on result
        if (result.getJSONObject(0).has("error")) {
            text = "result: " + result.getJSONObject(0).getString("error")
        } else {
            text = MainActivity.kkboxClient.getName(result.getJSONObject(0))
            MainActivity.player.dataInfo = result
            MainActivity.mPlayButton!!.onPlay(true)
        }
        MainActivity.uiAction.finishRequest(text)
    }
}