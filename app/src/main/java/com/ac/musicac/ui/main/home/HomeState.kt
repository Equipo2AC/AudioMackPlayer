package com.ac.musicac.ui.main.home

import android.Manifest
import android.content.Context
import androidx.fragment.app.Fragment
import androidx.lifecycle.lifecycleScope
import androidx.navigation.NavController
import androidx.navigation.fragment.findNavController
import com.ac.musicac.R
import com.ac.musicac.domain.AlbumView
import com.ac.musicac.domain.Error
import com.ac.musicac.domain.Item
import com.ac.musicac.domain.PopularArtist
import com.ac.musicac.ui.common.PermissionRequester
import com.ac.musicac.ui.main.releases.list.buildReleasesState
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.launch


fun Fragment.buildHomeState(
    context: Context = requireContext(),
    scope: CoroutineScope = viewLifecycleOwner.lifecycleScope,
    navController: NavController = findNavController(),
    locationPermissionRequester: PermissionRequester = PermissionRequester(
        this,
        Manifest.permission.ACCESS_COARSE_LOCATION
    )
) = HomeState(context,scope, navController, locationPermissionRequester)

class HomeState (
    private val context: Context,
    private val scope: CoroutineScope,
    private val navController: NavController,
    private val locationPermissionRequester: PermissionRequester
    ) {

    fun onAlbumClicked(album: AlbumView) {
        val action = HomeFragmentDirections.actionHomeToAlbum(album.albumId)
        navController.navigate(action)
    }

    fun onArtistClicked(artist: PopularArtist) {
        val action = HomeFragmentDirections.actionHomeToArtist(artist.artistId)
        navController.navigate(action)
    }

    fun requestLocationPermission(afterRequest: (Boolean) -> Unit) {
        scope.launch {
            val result = locationPermissionRequester.request()
            afterRequest(result)
        }
    }

    fun errorToString(error: Error) = when (error) {
        Error.Connectivity -> context.getString(R.string.connectivity_error)
        is Error.Server -> context.getString(R.string.server_error) + error.code
        is Error.Unknown -> context.getString(R.string.unknown_error) + error.message
    }

}