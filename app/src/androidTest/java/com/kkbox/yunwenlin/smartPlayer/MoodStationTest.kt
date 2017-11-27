package com.kkbox.yunwenlin.smartPlayer

import android.support.test.InstrumentationRegistry
import org.junit.Assert.assertEquals
import org.junit.Test

class MoodStationTest {

    val context = InstrumentationRegistry.getContext()
    var testMoodStation: MoodStation

    init {
        testMoodStation = MoodStation(context)
    }

    @Test
    fun test_getMoodStaionIds() {
        val expectClass = "Work Out"
        val expectId = "StGZp2ToWq92diPHS7"

        assertEquals(expectId, testMoodStation.getMoodStationId(expectClass))
    }
}