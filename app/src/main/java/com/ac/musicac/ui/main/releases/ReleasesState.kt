package com.ac.musicac.ui.main.releases

import android.content.Context
import androidx.navigation.NavController
import com.ac.musicac.R
import com.ac.musicac.domain.Albums
import kotlinx.coroutines.CoroutineScope
import com.ac.musicac.domain.Error
import com.ac.musicac.domain.Item

class ReleasesState(
    private val context: Context,
    private val scope: CoroutineScope,
    private val navController: NavController,
) {

    fun onMovieClicked(movie: Item) {

    }

    fun errorToString(error: Error) = when (error) {
        Error.Connectivity -> context.getString(R.string.connectivity_error)
        is Error.Server -> context.getString(R.string.server_error) + error.code
        is Error.Unknown -> context.getString(R.string.unknown_error) + error.message
    }
}