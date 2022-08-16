package com.ac.musicac.ui.main.artist

import androidx.databinding.BindingAdapter
import com.ac.musicac.domain.Item

@BindingAdapter("artist")
fun ArtistInfoView.updateReleaseDetails(Artist: Item?) {
    if (Artist != null) {
        setArtist(Artist)
    }
}

/*@BindingAdapter("tracks")
fun RecyclerView.setTracks(tracks: List<Artist>?) {
    if (tracks != null) {
        (adapter as? TracksAdapter)?.submitList(tracks)
    }
}*/

