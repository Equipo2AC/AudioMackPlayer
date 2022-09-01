package com.ac.musicac.ui.main.artist

import androidx.fragment.app.Fragment
import androidx.navigation.NavController
import androidx.navigation.fragment.findNavController
import com.ac.musicac.domain.Item

fun Fragment.buildArtistState(
    navController: NavController = findNavController(),
) = ArtistState(navController)

class ArtistState (private val navController: NavController) {

    fun onAlbumClicked(album: Item) {
        val action = ArtistFragmentDirections.actionArtistToAlbum(album.itemId)
        navController.navigate(action)
    }

}