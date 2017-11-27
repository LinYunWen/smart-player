package com.kkbox.yunwenlin.smartPlayer

import org.junit.Assert.assertEquals
import org.junit.Assert.assertNotNull
import org.junit.Test

class GoogleSpeechRecognitionAPIClientTest {
    val testGoogleSpeechClient = GoogleSpeechRecognitionAPIClient()

    @Test
    fun test_doSpeechRecognition() {
        val data = testGoogleSpeechClient.doSpeechRecognition()
        assertNotNull(data)
        assertNotNull(data.getJSONArray("result"))
    }

    @Test
    fun test_parser() {
        val expect = "how old is the Brooklyn Bridge"
        val data = testGoogleSpeechClient.parser(testGoogleSpeechClient.doSpeechRecognition())
        assertEquals(expect, data)
    }


}