package com.techjd.itunesapi.adapters

import android.content.ClipData.Item
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Filter
import android.widget.Filterable
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.RequestManager
import com.techjd.itunesapi.R
import com.techjd.itunesapi.data.artists.Artists
import kotlinx.android.synthetic.main.item_artists.view.*
import javax.inject.Inject


class ArtistsAdapter @Inject constructor(
    private val glide: RequestManager,
    private var artistsList: ArrayList<Artists>
) :
    RecyclerView.Adapter<ArtistsAdapter.ArtistsViewHolder>(), Filterable {

    var artistsListFull: ArrayList<Artists> = artistsList

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
        val artist = artistsList[position]
        holder.itemView.apply {
            glide.load(artist.artworkUrl100).into(artistImage)
            artistName.text = artist.artistName
            collectionName.text = artist.collectionName
            trackName.text = artist.trackName
        }
    }

    override fun getItemCount(): Int {
        return artistsList.size
    }

    override fun getFilter(): Filter {
        return artistsFliter
    }

    private val artistsFliter: Filter = object : Filter() {
        override fun performFiltering(constraint: CharSequence): FilterResults {
            val filteredList: MutableList<Artists> = ArrayList()
            if (constraint == null || constraint.length == 0) {
                filteredList.addAll(artistsListFull)
            } else {
                val filterPattern = constraint.toString().toLowerCase().trim { it <= ' ' }
                for (item in artistsListFull) {
                    if (item.trackName?.toLowerCase()?.contains(filterPattern) == true) {
                        filteredList.add(item)
                    }
                }
            }
            val results = FilterResults()
            results.values = filteredList
            return results
        }

        override fun publishResults(constraint: CharSequence, results: FilterResults) {

            artistsList.clear()
            artistsList.addAll(results.values as List<Artists>)
            notifyDataSetChanged()
        }
    }

}