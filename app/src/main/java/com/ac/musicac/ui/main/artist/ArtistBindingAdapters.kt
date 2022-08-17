package com.ac.musicac.ui.main.artist

import androidx.databinding.BindingAdapter
import androidx.recyclerview.widget.RecyclerView
import com.ac.musicac.domain.Artist
import com.ac.musicac.domain.Item

@BindingAdapter("artist")
fun ArtistInfoView.updateReleaseDetails(item: Item?) {
    if (item != null) {
        setArtist(item)
    }
}

/*@BindingAdapter("related_artist")
fun RecyclerView.setTracks(artists: List<Artist>?) {
    if (artists != null) {
        (adapter as? ArtistsAdapter)?.submitList(artists)
    }
}*/

