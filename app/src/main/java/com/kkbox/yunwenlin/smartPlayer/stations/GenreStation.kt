package com.kkbox.yunwenlin.smartPlayer

import android.content.Context
import android.util.Log
import org.json.JSONObject

class GenreStation(context: Context) {

    var genreStationIn = context.resources.openRawResource(R.raw.genre_station_id)
    lateinit var genreStationIds: JSONObject

    init {
        readGenreStationFile()
    }

    private fun readGenreStationFile() {
        val buffer = ByteArray(genreStationIn.available())
        genreStationIn.read(buffer)
        val str = String(buffer)
        genreStationIds = JSONObject(str)
    }

    internal fun getGenreStationIds(className: String, subClassName: String): String {
        Log.i("genre station id: ", genreStationIds.getJSONObject(className).getString(subClassName))
        return genreStationIds.getJSONObject(className).getString(subClassName)
    }

}