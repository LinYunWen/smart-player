package com.kkbox.yunwenlin.smartPlayer

import android.support.test.InstrumentationRegistry
import android.widget.ImageView
import android.widget.ProgressBar
import android.widget.TextView
import com.kkbox.yunwenlin.smartPlayer.buttons.RecordButton
import com.kkbox.yunwenlin.smartPlayer.network.Network
import org.junit.Assert.assertNotNull
import org.junit.Assert.assertTrue
import org.junit.Test


class HTTPsTest {
    val context = InstrumentationRegistry.getTargetContext()

    init {
        MainActivity.mainContext = context
        MainActivity.accessToken = MainActivity.kkboxClient.getAccessToken()
        MainActivity.moodStation = MoodStation(context)
        MainActivity.genreStation = GenreStation(context)
        MainActivity.network = Network(context)

        MainActivity.mText = TextView(context)
        MainActivity.mImage = ImageView(context)
        MainActivity.progressBar = ProgressBar(context)

        MainActivity.mPlayButton = PlayButton(context)
        MainActivity.mPauseButton = PauseButton(context)
        MainActivity.mRecordButton = RecordButton(context)
    }

    @Test
    fun test_HTTPGetStationToKKbox_genre() {
        val httpGetStationToKKbox = HTTPGetStationToKKbox()
        httpGetStationToKKbox.execute("TYq3EHFTl-1EOvJM5Y", "TW", "genre")

        Thread.sleep(10000)
        assertNotNull(MainActivity.player.dataInfo)
        assertNotNull(MainActivity.player.dataInfo.getJSONObject(0).getString("id"))
        assertTrue(MainActivity.mPlayButton!!.mStartPlaying)
    }

    @Test
    fun test_HTTPGetStationToKKbox_mood() {
        val httpGetStationToKKbox = HTTPGetStationToKKbox()
        httpGetStationToKKbox.execute("StGZp2ToWq92diPHS7", "TW", "mood")

        Thread.sleep(10000)
        assertNotNull(MainActivity.player.dataInfo)
        assertNotNull(MainActivity.player.dataInfo.getJSONObject(0).getString("id"))
        assertTrue(MainActivity.mPlayButton!!.mStartPlaying)
    }

    @Test
    fun test_HTTPGetTicketToKKbox() {
        val httpGetTicketToKKbox = HTTPGetTicketToKKbox()
        httpGetTicketToKKbox.execute(1)

        Thread.sleep(10000)
        assertNotNull(MainActivity.player.dataInfo)
        assertNotNull(MainActivity.player.dataInfo.getJSONObject(1).getString("id"))
        assertTrue(MainActivity.mPlayButton!!.mStartPlaying)
    }

    @Test
    fun test_HTTPRequestForRecognition() {

    }

    @Test
    fun test_HTTPRequestToKKbox() {
        val httpRequestToKKbox = HTTPRequestToKKbox()
        httpRequestToKKbox.execute("林俊傑", "track", "TW")

        Thread.sleep(10000)
        assertNotNull(MainActivity.player.dataInfo)
        assertNotNull(MainActivity.player.dataInfo.getJSONObject(1).getString("id"))
        assertTrue(MainActivity.mPlayButton!!.mStartPlaying)
    }
}