package com.kkbox.yunwenlin.smartPlayer.network

import android.content.Context
import android.net.ConnectivityManager
import android.os.Build
import android.support.annotation.RequiresApi

class Network(context: Context) {
    val connectivityManager = context.getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager
    var state = false

    @RequiresApi(Build.VERSION_CODES.M)
    fun checkNetwork(): Boolean {
        state = false
        val networkInfo = connectivityManager.activeNetworkInfo
        if (networkInfo != null && networkInfo.isConnected) {
            state = true
            return state
        }
        /*
        val networks = connectivityManager.allNetworks
        for (element in networks) {
            var networkInfo = connectivityManager.getNetworkInfo(element)
            if (networkInfo != null && networkInfo.isConnected) {
                state = true
                return state
            }
        }
        */

        return state
    }
}

