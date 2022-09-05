package com.ac.musicac.ui.main.home

import android.content.Context
import androidx.fragment.app.Fragment
import androidx.navigation.NavController
import androidx.navigation.fragment.findNavController
import com.ac.musicac.R
import com.ac.musicac.domain.AlbumView
import com.ac.musicac.domain.Error
import com.ac.musicac.domain.Item
import com.ac.musicac.domain.PopularArtist


fun Fragment.buildHomeState(
    context: Context = requireContext(),
    navController: NavController = findNavController(),
) = HomeState(context, navController)

class HomeState (
    private val context: Context,
    private val navController: NavController) {

    fun onAlbumClicked(album: AlbumView) {
        // val action = ArtistFragmentDirections.actionHomeToAlbum(album.itemId)
        // navController.navigate(action)
    }

    fun onArtistClicked(artist: PopularArtist) {
        // val action = ArtistFragmentDirections.actionHomeToArtist(artist.artistId)
        // navController.navigate(action)
    }

    fun errorToString(error: Error) = when (error) {
        Error.Connectivity -> context.getString(R.string.connectivity_error)
        is Error.Server -> context.getString(R.string.server_error) + error.code
        is Error.Unknown -> context.getString(R.string.unknown_error) + error.message
    }

}