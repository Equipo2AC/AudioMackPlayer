package com.ac.musicac.ui.main.releases.detail

import androidx.databinding.BindingAdapter
import androidx.recyclerview.widget.RecyclerView
import com.ac.musicac.domain.releases.Release
import com.ac.musicac.domain.releases.Track

@BindingAdapter("tracks")
fun RecyclerView.setTracks(tracks: List<Track>?) {
    if (tracks != null) {
        (adapter as? TracksAdapter)?.submitList(tracks)
    }
}

@BindingAdapter("movie")
fun ReleaseDetailInfoView.updateReleaseDetails(release: Release?) {
    if (release != null) {
        setRelease(release)
    }
}