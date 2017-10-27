package com.kkbox.yunwenlin.smart_player

import android.content.Context
import org.json.JSONObject

class GenreStation(context: Context) {

    var genreStationIn = context.getResources().openRawResource(R.raw.genre_station_id)
    lateinit var genreStationIds:JSONObject
    var genreTypeTable = arrayOf("Mandarin", "Western", "Korean",
            "Japanese", "Hokkien", "Cantonese",
            "Background Music", "Electronic", "Hip Hop/R&B",
            "Hip Hop/R&B", "Rock", "Jazz",
            "Religious Music", "Classical", "World Music",
            "Kids Music", "Festival Music")

    init {
        readGenreStationFile()
    }

    internal fun readGenreStationFile() {
        var buffer = ByteArray(genreStationIn.available())
        genreStationIn.read(buffer)
        var str = String(buffer)
        genreStationIds = JSONObject(str)
    }

    internal fun getGenreStationIds() {
        println("id: " + genreStationIds.getJSONObject("Mandarin").getString("Mandopop Male"))
    }

}