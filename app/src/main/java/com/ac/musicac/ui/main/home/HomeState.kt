package com.ac.musicac.ui.main.home

import androidx.fragment.app.Fragment
import androidx.navigation.NavController
import androidx.navigation.fragment.findNavController
import com.ac.musicac.domain.AlbumView
import com.ac.musicac.domain.Item
import com.ac.musicac.domain.PopularArtist


fun Fragment.buildHomeState(
    navController: NavController = findNavController(),
) = HomeState(navController)

class HomeState (private val navController: NavController) {

    fun onAlbumClicked(album: AlbumView) {
        // val action = ArtistFragmentDirections.actionHomeToAlbum(album.itemId)
        // navController.navigate(action)
    }

    fun onArtistClicked(artist: PopularArtist) {
        // val action = ArtistFragmentDirections.actionHomeToArtist(artist.artistId)
        // navController.navigate(action)
    }

}