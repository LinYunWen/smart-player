package com.kkbox.yunwenlin.smart_player

import com.squareup.okhttp.*
import org.json.JSONObject
import android.util.Base64
import java.io.*

class GoogleSpeechRecognitionAPIClient {

    internal var client = OkHttpClient()

    @Throws(IOException::class)
    internal fun doSpeechRecognization(s: String): JSONObject {
        var audioData = openFile()
        val payload = "{\"config\": {\"encoding\": \"AMR\", \"sampleRateHertz\": 8000, \"languageCode\": \"zh-TW\", \"enableWordTimeOffsets\": false }, \"audio\": {\"content\": \"" + audioData + "\" } }"
        val body = RequestBody.create(jsonType, payload)
        var GOOGLE_API_TOKEN = ""
        val request = Request.Builder()
                .url(url)
                .header("Content-Type", "application/json")
                .addHeader("Authorization", GOOGLE_API_TOKEN)
                .post(body)
                .build()

        val response = client.newCall(request).execute()
        val data = response.body().string()
        val jObject = JSONObject(data)
        return jObject
    }

    internal fun parser(data: JSONObject): String {
        if (data.has("error") && !data.isNull("error")) {
            println("error")
            return data.getJSONObject("error")
                    .getString("message")
        }

        if (data.has("results") && !data.isNull("results")) {
            println("success")
            return data.getJSONArray("results")
                    .getJSONObject(0)
                    .getJSONArray("alternatives")
                    .getJSONObject(0)
                    .getString("transcript")
        }

        return "There is not a correct message form in input"
    }

    internal fun openFile(): String {
        try {
            var file = File(MainActivity.mFileName)
            val size: Int = file.length().toInt()+1
            // println("size: " + size)
            var bytes = ByteArray(size)

            try {
                var fileInputStream = FileInputStream(file)
                val bufferInputStream = BufferedInputStream(fileInputStream)
                bufferInputStream.read(bytes, 0, size)
                bufferInputStream.close()
                // println("bytes: " + bytes)
            } catch (e: FileNotFoundException) {
                println("error: File not found")
            } catch (e: IOException) {
                println("error: Exception while reading file\n" + e.printStackTrace())
            }

            var source = Base64.encodeToString(bytes, Base64.DEFAULT)
            // println("Base64: " + source)
            return source
        } catch (e: java.lang.Exception) {
            println("error: " + e.printStackTrace())
        }
        return "error"
    }

    companion object {
        var jsonType = MediaType.parse("application/json; charset=utf-8")
        var url = "https://speech.googleapis.com/v1/speech:recognize"
    }
}