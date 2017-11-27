package com.kkbox.yunwenlin.smartPlayer

import android.graphics.Bitmap
import android.graphics.BitmapFactory
import android.os.AsyncTask
import android.util.Log
import java.io.IOException
import java.io.InputStream
import java.net.HttpURLConnection
import java.net.URL

class DownloadIamge: AsyncTask<String, Unit, Bitmap?>() {

    override fun doInBackground(vararg urls: String?): Bitmap? {
        val input: InputStream
        try {
            val url = URL(urls[0])
            val connection = url.openConnection() as HttpURLConnection
            connection.doInput = true
            connection.connect()
            input = connection.inputStream
        } catch (e: IOException) {
            Log.e(MainActivity.LOG_TAG, "error: " + e.printStackTrace())
            return null
        }
        return BitmapFactory.decodeStream(input)
    }

    override fun onPostExecute(result: Bitmap?) {
        MainActivity.mImage!!.setImageBitmap(result)
    }
}