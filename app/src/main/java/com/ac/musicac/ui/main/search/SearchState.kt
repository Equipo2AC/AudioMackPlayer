package com.ac.musicac.ui.main.search

import androidx.fragment.app.Fragment
import androidx.navigation.NavController
import androidx.navigation.fragment.findNavController

fun Fragment.buildSearchState(
    navController: NavController = findNavController(),
) = SearchState(navController)


class SearchState(
    private val navController: NavController
) {

    fun onAction(action: SearchAction) {
        when (action) {
            is SearchAction.OnClickAlbum -> {
                val navDirection = SearchFragmentDirections.actionSearchToAlbum(action.item.itemId)
                navController.navigate(navDirection)
            }
            is SearchAction.OnClickArtist -> {
                val navDirection = SearchFragmentDirections.actionSearchToArtist(action.item.itemId)
                navController.navigate(navDirection)
            }

        }
    }

}