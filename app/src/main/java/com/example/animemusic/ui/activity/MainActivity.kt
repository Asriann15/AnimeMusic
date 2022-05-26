package com.example.animemusic.ui.activity

import android.content.Intent
import android.os.Bundle
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProvider
import com.example.animemusic.R
import com.example.animemusic.adapter.MusicListAdapter
import com.example.animemusic.networking.InternetConnectionListener
import com.example.animemusic.ui.MainViewModel
import com.google.android.material.snackbar.Snackbar
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity(), MusicListAdapter.OnSelectedMusic,
    InternetConnectionListener {
    private lateinit var mainViewModel: MainViewModel
    private lateinit var musicListAdapter: MusicListAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        // adapter
        musicListAdapter = MusicListAdapter()
        musicListAdapter.setOnSelectedMusic(this)

        // view model
        mainViewModel = ViewModelProvider(
            this, ViewModelProvider.AndroidViewModelFactory(application)
        ).get(MainViewModel::class.java)

        mainViewModel.getMusicList.observe(this) {
            musicListAdapter.musicItemList = it
            pb_list_music.visibility = View.GONE
        }

        mainViewModel.setInternetConnectionListener(this)

        // recycler view
        rv_list_music.adapter = musicListAdapter
    }

    override fun onSelectedItem(id: Int) {
        val intent = Intent(this, DetailMusicActivity::class.java)
        intent.putExtra(DetailMusicActivity.EXTRA_ID, id)
        startActivity(intent)
    }

    override fun onInternetUnavailable() {
        Snackbar.make(linear_layout, R.string.no_internet, Snackbar.LENGTH_INDEFINITE)
            .setAnimationMode(Snackbar.ANIMATION_MODE_SLIDE)
            .setDuration(20000)
            .show()
    }
}