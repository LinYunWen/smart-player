package com.kkbox.yunwenlin.smartPlayer

import android.support.test.InstrumentationRegistry
import org.junit.Test
import org.junit.Assert.*

class DownloadImageTest {
    val kkboxClient = KKboxOpenAPIClient()
    val client = DownloadIamge()

    init {
        setParams()
    }

    fun setParams() {
        MainActivity.mainContext = InstrumentationRegistry.getTargetContext()
        MainActivity.accessToken = kkboxClient.getAccessToken()
    }

    @Test
    fun test_image_download() {
        val testJson = kkboxClient.parser(kkboxClient.doSearch("丹寧執著", "track", "TW"), "丹寧執著").getJSONObject(0)
        val url = testJson.getJSONObject("album").getJSONArray("images").getJSONObject(2).getString("url")
        val expect = "https://i.kfs.io/album/global/28328108,0v1/fit/1000x1000.jpg"

        assertEquals(expect, url)

        client.execute(url)
    }
}