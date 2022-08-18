package com.ac.musicac.ui.main.artist

import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.ac.musicac.R
import com.ac.musicac.databinding.ViewPopularTrackBinding
import com.ac.musicac.domain.TopTracks
import com.ac.musicac.ui.common.basicDiffUtil
import com.ac.musicac.ui.common.inflate

class TopTracksAdapter : ListAdapter<TopTracks, TopTracksAdapter.ViewHolder>(basicDiffUtil { old, new -> old.id == new.id }) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = parent.inflate(R.layout.view_popular_track, false)
        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val track = getItem(position)
        holder.bind(track)
    }

    class ViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        private val binding = ViewPopularTrackBinding.bind(view)
        fun bind(track: TopTracks) {
            binding.track = track
        }
    }

}