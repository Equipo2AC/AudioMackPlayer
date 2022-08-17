package com.ac.musicac.ui.main.artist

import androidx.databinding.BindingAdapter
import androidx.recyclerview.widget.RecyclerView
import com.ac.musicac.domain.Artist
import com.ac.musicac.domain.Item
import com.ac.musicac.domain.PopularArtist
import com.ac.musicac.domain.Track
import com.ac.musicac.ui.main.releases.detail.TracksAdapter

@BindingAdapter("artist")
fun ArtistInfoView.updateReleaseDetails(item: PopularArtist?) {
    if (item != null) {
        setArtist(item)
    }
}

@BindingAdapter("related_tracks")
fun RecyclerView.setTracks(tracks: List<Track>?) {
    if (tracks != null) {
        (adapter as? TracksAdapter)?.submitList(tracks)
    }
}

