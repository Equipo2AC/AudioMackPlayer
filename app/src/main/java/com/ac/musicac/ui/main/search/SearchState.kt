package com.ac.musicac.ui.main.search

import androidx.fragment.app.Fragment
import androidx.navigation.NavController
import androidx.navigation.fragment.findNavController
import com.ac.musicac.domain.Item

fun Fragment.buildSearchState(
    navController: NavController = findNavController(),
) = SearchState(navController)


class SearchState(
    private val navController: NavController
) {

    fun onAlbumClicked(album: Item) {

    }

    fun onArtistClicked(artist: Item) {

    }

    fun onSongClicked(artist: Item) {

    }

}