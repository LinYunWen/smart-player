package com.kkbox.yunwenlin.smartPlayer

import android.support.test.InstrumentationRegistry
import org.junit.Assert.assertEquals
import org.junit.Test

class CategoryTest {
    val testCategory = Category()

    init {
        val context = InstrumentationRegistry.getTargetContext()
        MainActivity.moodStation = MoodStation(context)
        MainActivity.genreStation = GenreStation(context)
    }

    @Test
    fun test_matchMood() {
        val expect = "StGZp2ToWq92diPHS7"
        val id = testCategory.moodCategory.matchMood("適合慢跑的電台")
        assertEquals(expect, id)
    }

    @Test
    fun test_checkGenreType() {
        val expectClass = "Mandarin"
        val className = testCategory.genreCategory.checkGenreType("華語新歌")
        assertEquals(expectClass, className)
    }

    @Test
    fun test_matchGenre() {
        testCategory.genreCategory.genreClass = "Mandarin"
        val expect = "TYq3EHFTl-1EOvJM5Y"
        val id = testCategory.genreCategory.matchGenre("華語新歌")
        assertEquals(expect, id)
    }
}