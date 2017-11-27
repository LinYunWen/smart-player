package com.kkbox.yunwenlin.smartPlayer

import android.os.Build
import android.support.annotation.RequiresApi
import android.support.test.InstrumentationRegistry
import org.json.JSONObject
import org.junit.Test
import org.junit.Assert.*

class KKboxOpenAPIClientTest {

    private val context = InstrumentationRegistry.getTargetContext()
    val client = KKboxOpenAPIClient()

    init {
        setParams()
    }

    fun setParams() {
        MainActivity.mainContext = context
        MainActivity.accessToken = client.getAccessToken()
    }

    @Test
    fun test_doSearch() {
        val data = client.doSearch("Hello", "track", "TW")
        val json = JSONObject(data.toString())
        assertNotNull(json)
        assertNotNull(json.has("tracks"))
        assertNotNull(json.getJSONObject("tracks").has("data"))
    }

    @RequiresApi(Build.VERSION_CODES.KITKAT)
    @Test
    fun test_parser_correct() {
        val data = client.parser(client.doSearch("Hello", "track", "TW"), "Hello")
        assertNotNull(data)
        assertNotNull(data.getJSONObject(0))
        assertNotNull(data.getJSONObject(0).getString("id"))
        assertNotNull(data.getJSONObject(0).getString("name"))
    }

    @Test
    fun test_getMoodStationTracks() {
        val expectId = "StGZp2ToWq92diPHS7"
        val expectName = "Work Out"
        val data = client.getMoodStationTracks("StGZp2ToWq92diPHS7", "TW")

        assertEquals(expectId, data.getString("id"))
        assertEquals(expectName, data.getString("name"))
    }

    @Test
    fun test_getGenreStationTracks() {
        val expectId = "TYq3EHFTl-1EOvJM5Y"
        val expectName = "Mandarin New Release"
        val data = client.getGenreStationTracks("TYq3EHFTl-1EOvJM5Y", "TW")

        assertEquals(expectId, data.getString("id"))
        assertEquals(expectName, data.getString("name"))
    }

    @Test
    fun test_getId() {
        val testJson = client.parser(client.doSearch("丹寧執著", "track", "TW"), "丹寧執著").getJSONObject(0)
        val expect = "0rZcEjfB97UdzRVIrh"
        val data = client.getId(testJson)
        assertEquals(expect, data)
    }

    @Test
    fun test_getName_correct() {
        val testJson = client.parser(client.doSearch("丹寧執著", "track", "TW"), "丹寧執著").getJSONObject(0)
        val expect = "丹寧執著 (feat. 消除聯萌) (Own The Day)"
        val data = client.getName(testJson)
        assertEquals(expect, data)
    }

    @Test
    fun test_getImage() {
        val testJson = client.parser(client.doSearch("丹寧執著", "track", "TW"), "丹寧執著").getJSONObject(0)
        val expect = "https://i.kfs.io/album/global/28328108,0v1/fit/1000x1000.jpg"
        val data = client.getImage(testJson)
        assertEquals(expect, data)
    }

    @Test
    fun test_doMatchName_correct() {
        val data = client.doMatchName("林俊傑 (JJ Lin)", "林俊傑")
        assertEquals(true, data)
    }

    @Test
    fun test_doMatchName_uncorrect() {
        val data = client.doMatchName("林俊傑 (JJ Lin)", "林俊")
        assertEquals(false, data)
    }
}