package com.example.animemusic.ui

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.Transformations
import com.example.animemusic.repository.MusicRepository

class DetailViewModel(application: Application) : AndroidViewModel(application) {
    private val repository = MusicRepository(application)
    private val _musicId: MutableLiveData<Int> = MutableLiveData()
    fun setInternetConnectionListener(internetConnectionListener: InternetConnectionListener) {
        repository.setInternetConnectionListener(internetConnectionListener)
    }

    val getMusicDetail: LiveData<DetailMusicItem> = Transformations
        .switchMap(_musicId) {
            repository.getMusicDetail(it)
        }

    fun setMusicId(value: Int) {
        if (_musicId.value == value) {
            return
        }
        _musicId.value = value
    }
}