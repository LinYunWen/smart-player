package com.kkbox.yunwenlin.smartPlayer

import android.content.res.Resources
import android.support.test.InstrumentationRegistry
import org.json.JSONObject
import org.junit.Assert.assertEquals
import org.junit.Test

class GenreStationTest {

    lateinit var testGenreStation: GenreStation

    init {
        try {
            testGenreStation = GenreStation(InstrumentationRegistry.getContext())
        } catch (e: Resources.NotFoundException) {
            testGenreStation.genreStationIds = JSONObject()
        }
    }

    @Test
    fun test_getGenreStationIds() {
        val expectClass = "Mandarin"
        val expectSubclass = "Mandarin New Release"
        val expectId = "TYq3EHFTl-1EOvJM5Y"

        assertEquals(expectId, testGenreStation.getGenreStationIds(expectClass, expectSubclass))
    }
}