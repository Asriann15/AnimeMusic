package com.example.animemusic.repository

import android.content.Context
import android.util.Log
import androidx.lifecycle.LiveData
import kotlinx.coroutines.*

class MusicRepository(context: Context) {
    private val apiService = RestApi.getApiService(context)
    private val handler = CoroutineExceptionHandler { _, exception ->
        Log.d("Debug", exception.message.toString())
    }

    fun getMusicList(): LiveData<List<MusicItem>> {
        return object : LiveData<List<MusicItem>>() {
            override fun onActive() {
                super.onActive()

                apiService.let {
                    CoroutineScope(Dispatchers.IO).launch(handler) {
                        val music = it.getMusicList()
                        val musicList = music.data

                        withContext(Dispatchers.Main) {
                            value = musicList
                        }
                    }
                }
            }
        }
    }

    fun getMusicDetail(id: Int): LiveData<DetailMusicItem> {
        return object : LiveData<DetailMusicItem>() {
            override fun onActive() {
                super.onActive()

                apiService.let {
                    CoroutineScope(Dispatchers.IO).launch(handler) {
                        val music = it.getMusic(id)
                        val musicDetail = music.data

                        withContext(Dispatchers.Main) {
                            value = musicDetail[0]
                        }
                    }
                }
            }
        }
    }

    fun setInternetConnectionListener(internetConnectionListener: InternetConnectionListener) {
        RestApi.setInternetConnectionListener(internetConnectionListener)
    }
}