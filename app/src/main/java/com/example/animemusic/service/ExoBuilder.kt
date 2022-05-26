package com.example.animemusic.service

import android.content.Context
import com.google.android.exoplayer2.SimpleExoPlayer

object ExoBuilder {
    private var simpleExoPlayer: SimpleExoPlayer? = null

    fun getInstance(context: Context): SimpleExoPlayer {
        simpleExoPlayer ?: SimpleExoPlayer.Builder(context).also { simpleExoPlayer = it.build() }

        return simpleExoPlayer!!
    }
}