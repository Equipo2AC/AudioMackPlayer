package com.ac.musicac.ui.main

import androidx.databinding.BindingAdapter
import androidx.recyclerview.widget.RecyclerView
import com.ac.musicac.domain.releases.Item
import com.ac.musicac.domain.releases.Track
import com.ac.musicac.ui.main.releases.detail.TracksAdapter
import com.ac.musicac.ui.main.releases.list.ReleasesAdapter

@BindingAdapter("items")
fun RecyclerView.setItems(albums: List<Item>?) {
    if (albums != null) {
        (adapter as? ReleasesAdapter)?.submitList(albums)
    }
}

@BindingAdapter("tracks")
fun RecyclerView.setTracks(tracks: List<Track>?) {
    if (tracks != null) {
        (adapter as? TracksAdapter)?.submitList(tracks)
    }
}