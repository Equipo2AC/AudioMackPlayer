package com.ac.musicac.ui.main.home

import androidx.databinding.BindingAdapter
import androidx.recyclerview.widget.RecyclerView
import com.ac.musicac.domain.AlbumView
import com.ac.musicac.domain.PopularArtist
import com.ac.musicac.ui.main.artist.AlbumsAdapter
import com.ac.musicac.ui.main.artist.ArtistsAdapter

@BindingAdapter("albums_items")
fun RecyclerView.setAlbums(albums: List<AlbumView>?) {
    if (albums != null) {
        (adapter as? AlbumsAdapter)?.submitList(albums)
    }
}

@BindingAdapter("artists_items")
fun RecyclerView.setArtists(artists: List<PopularArtist>?) {
    if (artists != null) {
        (adapter as? ArtistsAdapter)?.submitList(artists)
    }
}