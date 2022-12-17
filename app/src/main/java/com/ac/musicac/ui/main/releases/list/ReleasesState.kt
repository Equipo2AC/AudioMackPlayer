package com.ac.musicac.ui.main.releases.list

import android.content.Context
import androidx.fragment.app.Fragment
import androidx.lifecycle.lifecycleScope
import androidx.navigation.NavController
import androidx.navigation.fragment.findNavController
import com.ac.musicac.R
import com.ac.musicac.domain.Error
import com.ac.musicac.domain.Item
import kotlinx.coroutines.CoroutineScope

fun Fragment.buildReleasesState(
    context: Context = requireContext(),
    navController: NavController = findNavController()
) = ReleasesState(context, navController)

class ReleasesState(
    private val context: Context,
    private val navController: NavController
) {

    fun onAlbumClicked(album: Item) {
        val action = ReleasesFragmentDirections.actionReleasesToDetail(album.itemId)
        navController.navigate(action)
    }

    fun errorToString(error: Error) = when (error) {
        Error.Connectivity -> context.getString(R.string.connectivity_error)
        is Error.Server -> context.getString(R.string.server_error) + error.code
        is Error.Unknown -> context.getString(R.string.unknown_error) + error.message
    }
}