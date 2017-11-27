package com.kkbox.yunwenlin.smartPlayer

import android.os.AsyncTask
import android.os.Build
import android.support.annotation.RequiresApi
import org.json.JSONArray
import org.json.JSONObject

class HTTPGetStationToKKbox : AsyncTask<String, Unit, JSONArray>() {

    override fun onPreExecute() {
        super.onPreExecute()
        MainActivity.uiAction.beforeRequest("Changing...")
    }

    // @p[0]: id
    // @p[1]: territory
    // @p[2]: genre or mood station
    override fun doInBackground(vararg p: String): JSONArray {
        var result: JSONObject? = null
        if (p[2] == "genre") {
            result = MainActivity.kkboxClient.getGenreStationTracks(p[0], p[1])
        } else if (p[2] == "mood"){
            result = MainActivity.kkboxClient.getMoodStationTracks(p[0], p[1])
        }

        // on error
        if (result!!.has("error")) {
            val error = result.getJSONObject("error").getString("message")
            return JSONArray("[{\"error\": \"$error\"}]")
        }
        var info = result!!.getJSONObject("tracks").getJSONArray("data")

        // for widget
        MainActivity.player.musicId = MainActivity.kkboxClient.getId(info.getJSONObject(0))

        // get image
        val downloadImage = DownloadIamge()
        downloadImage.execute(MainActivity.kkboxClient.getImage(info.getJSONObject(0)))

        return info
    }

    @RequiresApi(Build.VERSION_CODES.M)
    override fun onPostExecute(result: JSONArray) {
        super.onPostExecute(result)
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