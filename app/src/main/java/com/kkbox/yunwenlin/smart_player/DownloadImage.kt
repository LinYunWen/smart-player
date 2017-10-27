package com.kkbox.yunwenlin.smart_player

import android.graphics.Bitmap
import android.graphics.BitmapFactory
import android.os.AsyncTask
import java.io.IOException
import java.net.HttpURLConnection
import java.net.URL

class DownloadImage: AsyncTask<String, Unit, Bitmap?>() {

    override fun doInBackground(vararg urls: String?): Bitmap? {
        try {
            val url = URL(urls[0])
            val connection = url.openConnection() as HttpURLConnection
            connection.setDoInput(true)
            connection.connect()
            val input = connection.getInputStream()
            return BitmapFactory.decodeStream(input)
        } catch (e: IOException) {
            println("error: " + e.printStackTrace())
            return null
        }
    }

    override fun onPostExecute(result: Bitmap?) {
        MainActivity.mImage!!.setImageBitmap(result)
    }
}