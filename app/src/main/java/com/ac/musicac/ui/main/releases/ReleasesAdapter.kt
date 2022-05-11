package com.ac.musicac.ui.main.releases

import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.ac.musicac.R
import com.ac.musicac.databinding.ViewReleaseBinding
import com.ac.musicac.domain.Item
import com.ac.musicac.ui.common.basicDiffUtil
import com.ac.musicac.ui.common.inflate

class ReleasesAdapter (private val listener: (Item) -> Unit) :
    ListAdapter<Item, ReleasesAdapter.ViewHolder>(basicDiffUtil { old, new -> old.id == new.id }) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = parent.inflate(R.layout.view_release, false)
        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val album = getItem(position)
        holder.bind(album)
        holder.itemView.setOnClickListener { listener(album) }
    }

    class ViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        private val binding = ViewReleaseBinding.bind(view)
        fun bind(album: Item) {
            binding.item = album
        }
    }
}