package com.kkbox.yunwenlin.smart_player

import android.os.Build
import android.support.annotation.RequiresApi
import java.io.IOException

import com.squareup.okhttp.MediaType
import com.squareup.okhttp.OkHttpClient
import com.squareup.okhttp.Request
import com.squareup.okhttp.Response
import com.squareup.okhttp.HttpUrl
import org.json.JSONArray
import org.json.JSONObject

class KKboxOpenAPIClient {

    internal var client = OkHttpClient()
    var KKBOX_API_ACCESS_TOKEN = ""

    // code request code here
    @Throws(IOException::class)
    internal fun doSearch(query: String, type:String, territory: String): JSONObject {
        println("query: " + query)

        var urlBuilder: HttpUrl.Builder = HttpUrl.parse("https://api.kkbox.com/v1.1/search").newBuilder()
        urlBuilder.addQueryParameter("q", query)
        urlBuilder.addQueryParameter("type", type)
        urlBuilder.addQueryParameter("territory", territory)
        val url = urlBuilder.build().toString()

        println("url: " + url)
        val request = Request.Builder()
                .header("Authorization", KKBOX_API_ACCESS_TOKEN)
                .url(url)
                .build()
        println(request)
        val response: Response = client.newCall(request).execute()
        val data = response.body().string()
        val jObject = JSONObject(data)
        return jObject
    }

    internal fun getMoodStationTracks(id: String, territory: String): JSONObject {
        var urlBuilder: HttpUrl.Builder = HttpUrl.parse("https://api.kkbox.com/v1.1/mood-stations/" + id).newBuilder()
        urlBuilder.addQueryParameter("territory", territory)
        val url = urlBuilder.build().toString()

        println("url :" + url)
        val request = Request.Builder()
                .header("Authorization", KKBOX_API_ACCESS_TOKEN)
                .url(url)
                .build()
        println(request)
        val response: Response = client.newCall(request).execute()
        val data = response.body().string()
        val jObject = JSONObject(data)

        return jObject
    }

    internal fun getGenerStationTracks(id:String, territory: String): JSONObject {
        var urlBuilder: HttpUrl.Builder = HttpUrl.parse("https://api.kkbox.com/v1.1/genre-stations/" + id).newBuilder()
        urlBuilder.addQueryParameter("territory", territory)
        val url = urlBuilder.build().toString()

        println("url :" + url)
        val request = Request.Builder()
                .header("Authorization", KKBOX_API_ACCESS_TOKEN)
                .url(url)
                .build()
        println(request)
        val response: Response = client.newCall(request).execute()
        val data = response.body().string()
        val jObject = JSONObject(data)
        println("data: " + data)
        return jObject
    }

    @RequiresApi(Build.VERSION_CODES.KITKAT)
    internal fun parser(data: JSONObject, keyword: String): JSONArray {

        if (data.has("error")) {
            val errorMessage = data.getJSONObject("error").getString("message")
            return JSONArray("[{\"error\": \"" + errorMessage + "\"}]")
        }

        if (data.has("tracks" ) && !data.isNull("tracks")) {
            val collection = data.getJSONObject("tracks").getJSONArray("data")
            val length = collection.length()
            for (i in 0..length-1) {
                if (doMatchName(getName(collection.getJSONObject(i)), keyword)) {
                    val item = collection[i]
                    collection.remove(i)
                    collection.put(0, item)
                    return collection
                }
            }
            return collection
        }
        return JSONArray("[{\"error\": \"no result\"}]")
    }

    internal fun getId(data: JSONObject): String {
        return data.getString("id")
    }

    internal fun getName(data: JSONObject): String {
        if (data.has("name")) {
            return data.getString("name")
        }
        return "Error: There is no \"name\" key"
    }

    internal fun getImage(data: JSONObject): String {
        if (data.has("album")) {
            // pick 1000x1000 px iamge
            return data.getJSONObject("album")
                    .getJSONArray("images")
                    .getJSONObject(2)
                    .getString("url")
        }
        return "Error: There is no \"album\" key"
    }

    internal fun doMatchName(name: String, expect: String): Boolean {
        var index = name.indexOf("(")
        if (index < 0) {
            if (name.equals(expect, true)) {
                return true
            }
            return false
        }

        var substring = name.substring(0, index-1)
        if (substring.equals(expect)) {
            return true
        }
        return false
    }

    companion object {
        val jsonType = MediaType.parse("application/json; charset=utf-8")
    }
}