package com.kkbox.yunwenlin.smart_player

import android.content.Context
import org.json.JSONObject

class MoodStation(context: Context) {
    var moodStationIn = context.getResources().openRawResource(R.raw.mood_station_id)
    lateinit var moodStationIds:JSONObject
    var moodTable = arrayOf("Work Out", "Party Animal", "Relaxing", "Working Time", "Romantic", "Vacation", "Chill Out", "Tipsy Night", "Acoustic Pop", "Hardcore")

    init {
        readMoodStationFile()
    }

    internal fun readMoodStationFile() {
        var buffer = ByteArray(moodStationIn.available())
        moodStationIn.read(buffer)
        var str = String(buffer)
        moodStationIds = JSONObject(str)
    }

    fun getMoodStationId(index: Int): String {
        println("id: " + moodStationIds.getString(moodTable[index]))
        return moodStationIds.getString(moodTable[index])
    }
}