package com.ac.musicac.ui.main.artist

import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.ac.musicac.R
import com.ac.musicac.databinding.ViewPopularAlbumBinding
import com.ac.musicac.domain.Item
import com.ac.musicac.ui.common.basicDiffUtil
import com.ac.musicac.ui.common.inflate

class AlbumsAdapter : ListAdapter<Item, AlbumsAdapter.ViewHolder>(basicDiffUtil { old, new -> old.id == new.id }) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = parent.inflate(R.layout.view_popular_album, false)
        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val album = getItem(position)
        holder.bind(album)
    }

    class ViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        private val binding = ViewPopularAlbumBinding.bind(view)
        fun bind(album: Item) {
            binding.album = album
        }
    }

}