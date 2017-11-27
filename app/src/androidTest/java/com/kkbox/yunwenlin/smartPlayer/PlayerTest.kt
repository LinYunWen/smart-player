package com.kkbox.yunwenlin.smartPlayer

import android.app.Activity
import android.support.test.InstrumentationRegistry
import com.kkbox.yunwenlin.smartPlayer.network.Network
import org.junit.Assert.assertEquals
import org.junit.Test

class PlayerTest {

    private val context = InstrumentationRegistry.getTargetContext()
    val client = KKboxOpenAPIClient()
    val myActivity = Activity()

    init {
        MainActivity.mainContext = context
        MainActivity.mainActivity = myActivity
        MainActivity.accessToken = client.getAccessToken()
        MainActivity.player.dataInfo = client.parser(client.doSearch("林俊傑", "track", "TW"), "林俊傑")
        MainActivity.network = Network(context)
    }

    @Test
    fun test_playOther() {
        MainActivity.player.playOther(1)
        assertEquals(1, MainActivity.player.curPlayIndex)

        MainActivity.player.playOther(-1)
        assertEquals(0, MainActivity.player.curPlayIndex)
    }
}