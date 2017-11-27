package com.kkbox.yunwenlin.smartPlayer


class Widget {
    fun getTrackWidgetURL(id: String): String{
        return "https://widget.kkbox.com/v1/?id=$id&type=song&autoplay=true"
    }

    fun getPlaylistWidgetURL(id: String): String {
        return "https://widget.kkbox.com/v1/?id=$id&type=playlist&autoplay=true"
    }
}