package com.example.animemusic.networking

import com.example.animemusic.model.DetailMusic
import com.example.animemusic.model.Music
import retrofit2.http.GET
import retrofit2.http.Path

interface ApiService {
    @GET("listmusic")
    suspend fun getMusicList(): Music

    @GET("jsongetid/{id}")
    suspend fun getMusic(
        @Path("id") id: Int
    ): DetailMusic
}