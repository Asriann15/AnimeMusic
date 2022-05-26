package com.example.animemusic.ui

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import com.example.animemusic.model.MusicItem
import com.example.animemusic.networking.InternetConnectionListener
import com.example.animemusic.repository.MusicRepository

class MainViewModel(application: Application) : AndroidViewModel(application) {
    private val repository = MusicRepository(application)
    fun setInternetConnectionListener(internetConnectionListener: InternetConnectionListener) {
        repository.setInternetConnectionListener(internetConnectionListener)
    }

    val getMusicList: LiveData<List<MusicItem>> = repository.getMusicList()
}