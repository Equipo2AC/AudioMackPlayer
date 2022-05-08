package com.ac.musicac.ui.main.releases

import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.ac.musicac.R
import com.ac.musicac.databinding.ViewReleaseBinding
import com.ac.musicac.domain.Albums
import com.ac.musicac.ui.common.basicDiffUtil
import com.ac.musicac.ui.common.inflate

class ReleasesAdapter (private val listener: (Albums) -> Unit) :
    ListAdapter<Albums, ReleasesAdapter.ViewHolder>(basicDiffUtil { old, new -> old.next == new.next }) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = parent.inflate(R.layout.view_release, false)
        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val movie = getItem(position)
        holder.bind(movie)
        holder.itemView.setOnClickListener { listener(movie) }
    }

    class ViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        private val binding = ViewReleaseBinding.bind(view)
        fun bind(album: Albums) {
            binding.album = album
        }
    }
}