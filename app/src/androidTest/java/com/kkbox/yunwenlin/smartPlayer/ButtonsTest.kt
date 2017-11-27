package com.kkbox.yunwenlin.smartPlayer

import android.speech.SpeechRecognizer
import android.support.test.InstrumentationRegistry
import com.kkbox.yunwenlin.smartPlayer.buttons.RecordButton
import com.kkbox.yunwenlin.smartPlayer.network.Network
import org.junit.Assert.*
import org.junit.Test

class ButtonsTest {
    val context = InstrumentationRegistry.getTargetContext()

    init {
        MainActivity.mainContext = context
        // MainActivity.mainActivity = activity
        MainActivity.accessToken = MainActivity.kkboxClient.getAccessToken()
        MainActivity.moodStation = MoodStation(context)
        MainActivity.genreStation = GenreStation(context)
        MainActivity.network = Network(context)

        MainActivity.mPlayButton = PlayButton(context)
        MainActivity.mPauseButton = PauseButton(context)
        MainActivity.mRecordButton = RecordButton(context)
        MainActivity.mSearchButton = SearchButton(context)

        MainActivity.speech = SpeechRecognizer.createSpeechRecognizer(context)
        MainActivity.recorder.transcript = "林俊傑"
    }

    @Test
    fun test_PlayButton() {
        assertEquals("Replay", MainActivity.mPlayButton!!.text)
        assertFalse(MainActivity.mPlayButton!!.mStartPlaying)
        assertEquals("Pause", MainActivity.mPauseButton!!.text)
        assertFalse(MainActivity.mPauseButton!!.mPausePlaying)
        assertEquals("Start Recording", MainActivity.mRecordButton!!.text)
        assertFalse(MainActivity.mRecordButton!!.mStartRecording)

        // MainActivity.webView = WebView(context)
        MainActivity.mPlayButton!!.onPlay(true)
        assertEquals("Replay", MainActivity.mPlayButton!!.text)
        assertFalse(MainActivity.mPlayButton!!.mStartPlaying)
    }

    @Test
    fun test_PauseButton() {
        MainActivity.mPauseButton!!.onPause(true)
        assertEquals("Resume", MainActivity.mPlayButton!!.text)
        assertTrue(MainActivity.mPauseButton!!.mPausePlaying)

        MainActivity.mPauseButton!!.onPause(false)
        assertEquals("Pause", MainActivity.mPlayButton!!.text)
        assertFalse(MainActivity.mPauseButton!!.mPausePlaying)
    }

    @Test
    fun test_RecordButton() {
        MainActivity.mRecordButton!!.onRecord(true)
        assertEquals("Stop Recording", MainActivity.mRecordButton!!.text)
        assertTrue(MainActivity.mRecordButton!!.mStartRecording)

        MainActivity.mRecordButton!!.onRecord(false)
        assertEquals("Start Recording", MainActivity.mRecordButton!!.text)
        assertFalse(MainActivity.mRecordButton!!.mStartRecording)
    }

    @Test
    fun test_SearchButton() {
        MainActivity.mSearchButton!!.onSearch()
        assertEquals("Search", MainActivity.mSearchButton!!.text)
    }
}