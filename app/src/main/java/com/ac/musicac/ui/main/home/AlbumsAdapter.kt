package com.ac.musicac.ui.main.home

import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.ac.musicac.R
import com.ac.musicac.databinding.ViewAlbumBinding

import com.ac.musicac.domain.AlbumView
import com.ac.musicac.ui.common.basicDiffUtil
import com.ac.musicac.ui.common.inflate

class AlbumsAdapter (private val listener: (AlbumView) -> Unit) :
    ListAdapter<AlbumView, AlbumsAdapter.ViewHolder>(basicDiffUtil { old, new -> old.id == new.id }) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = parent.inflate(R.layout.view_album, false)
        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val album = getItem(position)
        holder.bind(album)
        holder.itemView.setOnClickListener { listener(album) }
    }

    class ViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        private val binding = ViewAlbumBinding.bind(view)
        fun bind(album: AlbumView) {
            binding.album = album
        }
    }

}