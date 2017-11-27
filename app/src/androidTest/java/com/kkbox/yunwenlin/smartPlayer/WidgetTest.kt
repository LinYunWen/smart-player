package com.kkbox.yunwenlin.smartPlayer

import org.junit.Assert.assertEquals
import org.junit.Test

class WidgetTest {
    val test_widget = Widget()

    @Test
    fun test_getTrackWidgetURL() {
        val expect ="https://widget.kkbox.com/v1/?id=OlJ8azjoS497b5mvYl&type=song&autoplay=true"
        val data = test_widget.getTrackWidgetURL("OlJ8azjoS497b5mvYl")
        assertEquals(expect, data)
    }

    @Test
    fun test_getPlaylistWidgetURL() {
        val expect = "https://widget.kkbox.com/v1/?id=SmJgksDqNg04V8Jjag&type=playlist&autoplay=true"
        val data = test_widget.getPlaylistWidgetURL("SmJgksDqNg04V8Jjag")
        assertEquals(expect, data)
    }
}