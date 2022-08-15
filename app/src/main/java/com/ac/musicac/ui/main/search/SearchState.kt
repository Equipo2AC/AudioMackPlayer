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
                navController.navigate(
                    SearchFragmentDirections.actionSearchToAlbum(action.item.id)
                )
            }
            is SearchAction.OnClickArtist -> {
                navController.navigate(
                    SearchFragmentDirections.actionSearchToArtist(action.item.id)
                )
            }

        }
    }

}