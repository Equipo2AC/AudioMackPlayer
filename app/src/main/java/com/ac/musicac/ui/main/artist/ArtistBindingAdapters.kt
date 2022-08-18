package com.ac.musicac.ui.main.artist

import androidx.databinding.BindingAdapter
import androidx.recyclerview.widget.RecyclerView
import com.ac.musicac.domain.*

@BindingAdapter("artist")
fun ArtistInfoView.updateReleaseDetails(item: PopularArtist?) {
    if (item != null) {
        setArtist(item)
    }
}

@BindingAdapter("related_tracks")
fun RecyclerView.setTracks(tracks: List<TopTracks>?) {
    if (tracks != null) {
        (adapter as? TopTracksAdapter)?.submitList(tracks)
    }
}

