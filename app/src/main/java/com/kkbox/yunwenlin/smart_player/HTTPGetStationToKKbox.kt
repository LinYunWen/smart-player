package com.kkbox.yunwenlin.smart_player

import android.os.AsyncTask
import android.os.Build
import android.support.annotation.RequiresApi
import android.view.View
import org.json.JSONArray
import org.json.JSONObject

class HTTPGetStationToKKbox :AsyncTask<String, Unit, JSONArray>() {
    val kkboxClient = KKboxOpenAPIClient()

    override fun onPreExecute() {
        super.onPreExecute()
        MainActivity.mText!!.setText("Changing...")
        MainActivity.progressBar.setVisibility(View.VISIBLE)
        MainActivity.mImage!!.setVisibility(View.INVISIBLE)
    }

    // @p[0]: id
    // @p[1]: territory
    // @p[2]: genre or mood station
    override fun doInBackground(vararg p: String): JSONArray {
        var result: JSONObject? = null
        if (p[2] == "genre") {
            result = kkboxClient.getGenerStationTracks(p[0], p[1])
        } else if (p[2] == "mood"){
            result = kkboxClient.getMoodStationTracks(p[0], p[1])
        }
        // error
        if (result!!.has("error")) {
            val error = result.getJSONObject("error").getString("message")
            println("error: " + error)
            return JSONArray("[{\"error\":" + error + "}]")
        }
        var info = result!!.getJSONObject("tracks").getJSONArray("data")

        // for widget
        MainActivity.player.musicId = kkboxClient.getId(info.getJSONObject(0))

        // get image
        val downloadImage = DownloadImage()
        downloadImage.execute(kkboxClient.getImage(info.getJSONObject(0)))

        return info
    }

    @RequiresApi(Build.VERSION_CODES.M)
    override fun onPostExecute(result: JSONArray) {
        super.onPostExecute(result)
        MainActivity.progressBar.setVisibility(View.INVISIBLE)

        // error
        if (result.getJSONObject(0).has("error")) {
            MainActivity.mText!!.setText("result: " + result.getJSONObject(0).getString("error"))
        } else {
            MainActivity.mImage!!.setVisibility(View.VISIBLE)
            MainActivity.mText!!.setText(kkboxClient.getName(result.getJSONObject(0)))
        }

        MainActivity.player.dataInfo = result
        MainActivity.mPlayButton!!.onPlay(true)
    }
}