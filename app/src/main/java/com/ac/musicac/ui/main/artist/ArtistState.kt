package com.ac.musicac.ui.main.artist

import android.content.Context
import androidx.fragment.app.Fragment
import androidx.navigation.NavController
import androidx.navigation.fragment.findNavController
import com.ac.musicac.R
import com.ac.musicac.domain.Error
import com.ac.musicac.domain.Item

fun Fragment.buildArtistState(
    context: Context = requireContext(),
    navController: NavController = findNavController()
) = ArtistState(context, navController)

class ArtistState (
    private val context: Context,
    private val navController: NavController) {

    fun onAlbumClicked(album: Item) {
        val action = ArtistFragmentDirections.actionArtistToAlbum(album.itemId)
        navController.navigate(action)
    }

    fun errorToString(error: Error) = when (error) {
        Error.Connectivity -> context.getString(R.string.connectivity_error)
        is Error.Server -> context.getString(R.string.server_error) + error.code
        is Error.Unknown -> context.getString(R.string.unknown_error) + error.message
    }

}