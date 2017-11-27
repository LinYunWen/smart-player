package com.kkbox.yunwenlin.smartPlayer

import android.os.Build
import android.support.annotation.RequiresApi
import android.support.test.InstrumentationRegistry
import android.widget.ImageView
import android.widget.ProgressBar
import android.widget.TextView
import com.kkbox.yunwenlin.smartPlayer.buttons.RecordButton
import com.kkbox.yunwenlin.smartPlayer.network.Network
import org.junit.Test

class RecorderTest {

    val testRecorder = Recorder()
    val context = InstrumentationRegistry.getTargetContext()

    init {
        val client = KKboxOpenAPIClient()
        MainActivity.mainContext = context
        MainActivity.accessToken = client.getAccessToken()
        MainActivity.network = Network(MainActivity.mainContext)

        MainActivity.mPlayButton = PlayButton(context)
        MainActivity.mRecordButton = RecordButton(context)
        MainActivity.mPauseButton = PauseButton(context)
        MainActivity.player.dataInfo = client.parser(client.doSearch("丹寧執著", "track", "TW"), "丹寧執著")

        MainActivity.genreStation = GenreStation(MainActivity.mainContext)
        MainActivity.moodStation = MoodStation(MainActivity.mainContext)

        MainActivity.mText = TextView(context)
        MainActivity.progressBar = ProgressBar(context)
        MainActivity.mImage = ImageView(context)
    }

    @Test
    @RequiresApi(Build.VERSION_CODES.LOLLIPOP)
    fun test_afterRecord_next() {
        testRecorder.transcript = "下一首"
        testRecorder.afterRecord()
    }

    @Test
    @RequiresApi(Build.VERSION_CODES.LOLLIPOP)
    fun test_afterRecord_front() {
        testRecorder.transcript = "上一首"
        testRecorder.afterRecord()
    }

    @Test
    @RequiresApi(Build.VERSION_CODES.LOLLIPOP)
    fun test_afterRecord_moodStation() {
        testRecorder.transcript = "運動的電台"
        testRecorder.afterRecord()
    }

    @Test
    @RequiresApi(Build.VERSION_CODES.LOLLIPOP)
    fun test_afterRecord_genreStation() {
        testRecorder.transcript = "華語新歌"
        testRecorder.afterRecord()
    }

    @Test
    @RequiresApi(Build.VERSION_CODES.LOLLIPOP)
    fun test_afterRecord_song() {
        testRecorder.transcript = "丹寧執著"
        testRecorder.afterRecord()
    }
}