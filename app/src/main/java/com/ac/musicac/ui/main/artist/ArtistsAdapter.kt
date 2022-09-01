package com.ac.musicac.ui.main.artist

import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.ac.musicac.R
import com.ac.musicac.databinding.ViewArtistBinding
import com.ac.musicac.domain.PopularArtist
import com.ac.musicac.ui.common.basicDiffUtil
import com.ac.musicac.ui.common.inflate

class ArtistsAdapter (private val listener: (PopularArtist) -> Unit) :
    ListAdapter<PopularArtist, ArtistsAdapter.ViewHolder>(basicDiffUtil { old, new -> old.id == new.id }) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = parent.inflate(R.layout.view_artist, false)
        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val artist = getItem(position)
        holder.bind(artist)
        holder.itemView.setOnClickListener { listener(artist) }
    }

    class ViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        private val binding = ViewArtistBinding.bind(view)
        fun bind(artist: PopularArtist) {
            binding.artist = artist
        }
    }
}