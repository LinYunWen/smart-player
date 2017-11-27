package com.kkbox.yunwenlin.smartPlayer

import android.content.Context
import android.util.Log
import org.json.JSONObject

class MoodStation(context: Context) {

    var moodStationIn = context.resources.openRawResource(R.raw.mood_station_id)
    lateinit var moodStationIds: JSONObject

    init {
        readMoodStationFile()
    }

    // read mood station id
    private fun readMoodStationFile() {
        val buffer = ByteArray(moodStationIn.available())
        moodStationIn.read(buffer)
        val str = String(buffer)
        moodStationIds = JSONObject(str)
    }

    fun getMoodStationId(className: String): String {
        Log.i("mood station id: ", moodStationIds.getString(className))
        return moodStationIds.getString(className)
    }
}