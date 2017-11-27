package com.kkbox.yunwenlin.smartPlayer

import android.content.ContentValues.TAG
import android.content.Context
import android.content.res.Resources
import android.util.Log
import java.io.IOException
import java.io.InputStream
import java.util.*

class ConfigReader {

    fun getConfigValue(context: Context, name: String): String {
        var resources = context.resources
        var rawResources: InputStream
        var properties: Properties

        try {
            rawResources = resources.openRawResource(R.raw.config)
            properties = Properties()
            properties.load(rawResources)
        } catch (e: Resources.NotFoundException) {
            Log.e(TAG, "Unable to find the config file: config.properties")
            return "Unable to find the config file: config.properties"
        } catch (e: IOException) {
            Log.e(TAG, "Failed to open config file.")
            return "Failed to open config file."
        }
        return properties.getProperty(name)
    }
}
