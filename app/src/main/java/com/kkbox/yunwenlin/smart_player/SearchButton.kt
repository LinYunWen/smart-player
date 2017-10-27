package com.kkbox.yunwenlin.smart_player

import android.content.Context
import android.view.View
import android.widget.Button

class SearchButton(ctx: Context) : Button(ctx) {
    var clicker: View.OnClickListener = OnClickListener {
        onSearch()
    }

    fun onSearch() {
        startSearching()
    }

    private fun startSearching() {
        var searchClient = HTTPRequestToKKbox()
        searchClient.execute(MainActivity.recorder.transcript, "track", "TW", MainActivity.recorder.transcript)
    }

    init {
        text = "Search"
        setOnClickListener(clicker)
    }
}