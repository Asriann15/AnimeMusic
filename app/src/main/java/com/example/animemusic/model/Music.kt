package com.example.animemusic.model

import com.google.gson.annotations.SerializedName

data class Music(
    @SerializedName("post")
    var data: List<MusicItem>
)

data class MusicItem(
    @SerializedName("id")
    var id: Int,
    @SerializedName("coverartikel")
    var cover: String,
    @SerializedName("judulmusic")
    var title: String,
    @SerializedName("namaband")
    var bandName: String
)