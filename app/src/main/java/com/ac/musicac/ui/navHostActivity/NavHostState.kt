package com.ac.musicac.ui.navHostActivity

import androidx.appcompat.app.AppCompatActivity
import androidx.navigation.NavController
import androidx.navigation.fragment.NavHostFragment
import com.ac.musicac.R

fun AppCompatActivity.buildNavHostState(
    navController: NavController =  (supportFragmentManager.findFragmentById(R.id.nav_host_splash_fragment) as NavHostFragment).navController
) = NavHostState(navController)


class NavHostState(
    private val navController: NavController,
) {

    fun navigateToReleases() {
        navController.navigate(R.id.nav_graph_releases)
    }

    fun navigateToHome() {
        // TODO SHOW HOME SCREEN.
    }

}
