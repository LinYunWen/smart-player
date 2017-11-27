package com.kkbox.yunwenlin.smartPlayer

import android.support.test.InstrumentationRegistry
import android.view.View
import android.widget.ImageView
import android.widget.ProgressBar
import android.widget.TextView
import org.junit.Assert.assertEquals
import org.junit.Test

class UIActionTest {
    val context = InstrumentationRegistry.getTargetContext()
    val testUIAction = UIAction()

    init {
        MainActivity.mText = TextView(context)
        MainActivity.progressBar = ProgressBar(context)
        MainActivity.mImage = ImageView(context)
    }

    @Test
    fun test_beforeRequest() {
        val expect = "Hello"
        testUIAction.beforeRequest("Hello")

        assertEquals(expect, MainActivity.mText!!.text)
        assertEquals(View.VISIBLE, MainActivity.progressBar.visibility)
        assertEquals(View.INVISIBLE, MainActivity.mImage!!.visibility)
    }

    @Test
    fun test_finishResquest() {
        val expect = "World"
        testUIAction.finishRequest("World")

        assertEquals(expect, MainActivity.mText!!.text)
        assertEquals(View.INVISIBLE, MainActivity.progressBar.visibility)
        assertEquals(View.VISIBLE, MainActivity.mImage!!.visibility)
    }
}