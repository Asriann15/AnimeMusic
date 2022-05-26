package com.example.animemusic.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.bumptech.glide.load.engine.DiskCacheStrategy
import com.example.animemusic.R
import com.example.animemusic.model.MusicItem
import com.google.android.material.card.MaterialCardView
import kotlinx.android.synthetic.main.item_music.view.*

class MusicListAdapter
    : RecyclerView.Adapter<MusicListAdapter.ViewHolder>() {

    var musicItemList: List<MusicItem>? = null
        set(value) {
            field = value
            notifyDataSetChanged()
        }

    private var onSelectedMusic: OnSelectedMusic? = null

    fun setOnSelectedMusic(onSelectedMusic: OnSelectedMusic) {
        this.onSelectedMusic = onSelectedMusic
    }

    class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val materialCard: MaterialCardView = itemView.material_card
        val circularImage: CircularImageView = itemView.circular_image
        val tvBandName: TextView = itemView.tv_band_name
        val tvTitle: TextView = itemView.tv_title
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val layoutInflater = LayoutInflater.from(parent.context)
        val view = layoutInflater.inflate(R.layout.item_music, parent, false)
        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val musicItem = musicItemList?.get(position)

        if (musicItem != null) {
            holder.tvBandName.text = musicItem.bandName
            holder.tvTitle.text = musicItem.title

            Glide.with(holder.itemView.context)
                .load(musicItem.cover)
                .placeholder(R.drawable.icons8_no_image_64)
                .diskCacheStrategy(DiskCacheStrategy.ALL)
                .into(holder.circularImage)

            holder.materialCard.setOnClickListener {
                onSelectedMusic?.onSelectedItem(musicItem.id)
            }
        }
    }

    override fun getItemCount(): Int {
        return musicItemList?.size ?: 0
    }

    fun interface OnSelectedMusic {
        fun onSelectedItem(id: Int)
    }
}