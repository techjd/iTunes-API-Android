package com.techjd.itunesapi.adapters

import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.RequestManager
import com.techjd.itunesapi.R
import com.techjd.itunesapi.data.artists.Artists
import kotlinx.android.synthetic.main.item_artists.view.*
import javax.inject.Inject

class ArtistsAdapter @Inject constructor(private val glide: RequestManager,private val artists: List<Artists>) :
    RecyclerView.Adapter<ArtistsAdapter.ArtistsViewHolder>() {


    class ArtistsViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ArtistsViewHolder {
        return ArtistsViewHolder(
            LayoutInflater.from(parent.context).inflate(
                R.layout.item_artists,
                parent,
                false
            )
        )
    }

    override fun onBindViewHolder(holder: ArtistsViewHolder, position: Int) {
        val artist = artists[position]
        holder.itemView.apply {
            glide.load(artist.artworkUrl100).into(artistImage)
            artistName.text = artist.artistName
            collectionName.text = artist.collectionName
            trackName.text = artist.trackName
        }
    }

    override fun getItemCount(): Int {
        return artists.size
    }

}