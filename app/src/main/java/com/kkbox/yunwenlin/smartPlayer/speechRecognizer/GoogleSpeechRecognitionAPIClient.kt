package com.kkbox.yunwenlin.smartPlayer

import com.squareup.okhttp.*
import org.json.JSONObject
import android.util.Base64
import android.util.Log
import java.io.*

class GoogleSpeechRecognitionAPIClient {

    private var client = OkHttpClient()

    @Throws(IOException::class)
    internal fun doSpeechRecognition(): JSONObject {
        var audioData = openFile()
        val payload = "{\"config\": {\"encoding\": \"AMR\", \"sampleRateHertz\": 8000, \"languageCode\": \"zh-TW\", \"enableWordTimeOffsets\": false }, \"audio\": {\"content\": \"$audioData\" } }"
        val body = RequestBody.create(jsonType, payload)
        val GOOGLE_TOKEN = ""
        val request = Request.Builder()
                .url(url)
                .header("Content-Type", "application/json")
                .addHeader("Authorization", GOOGLE_TOKEN)
                .post(body)
                .build()
        Log.i(MainActivity.LOG_TAG, request.toString())

        val response = client.newCall(request).execute()
        val data = response.body().string()
        val jObject = JSONObject(data)
        Log.i("post response: ", data)
        return jObject
    }

    internal fun parser(data: JSONObject): String {

        if (data.has("error") && !data.isNull("error")) {
            return data.getJSONObject("error")
                    .getString("message")
        }

        if (data.has("results") && !data.isNull("results")) {
            return data.getJSONArray("results")
                    .getJSONObject(0)
                    .getJSONArray("alternatives")
                    .getJSONObject(0)
                    .getString("transcript")
        }

        return "There is not a correct message form in input"
    }

    private fun openFile(): String {
        try {
            var file = File(MainActivity.mFileName)
            val size: Int = file.length().toInt() + 1
            Log.i("size: ", size.toString())
            var bytes = ByteArray(size)

            try {
                var fileInputStream = FileInputStream(file)
                val bufferInputStream = BufferedInputStream(fileInputStream)
                bufferInputStream.read(bytes, 0, size)
                bufferInputStream.close()
                Log.i("bytes: ", bytes.toString())
            } catch (e: FileNotFoundException) {
                Log.e(MainActivity.LOG_TAG, "error: File not found")
            } catch (e: IOException) {
                Log.e(MainActivity.LOG_TAG, "error: Exception while reading file\n" + e.printStackTrace())
            }

            var source = Base64.encodeToString(bytes, Base64.DEFAULT)
            Log.i("Base64: ", source.toString())
            return source
        } catch (e: java.lang.Exception) {
            Log.e(MainActivity.LOG_TAG, e.printStackTrace().toString())
        }
        return "error"
    }

    companion object {
        var jsonType = MediaType.parse("application/json; charset=utf-8")
        var url = "https://speech.googleapis.com/v1/speech:recognize"
    }
}