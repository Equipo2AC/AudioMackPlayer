package com.ac.musicac.ui.navHostActivity

import androidx.appcompat.app.AppCompatActivity
import androidx.navigation.NavController
import androidx.navigation.findNavController
import com.ac.musicac.R

fun AppCompatActivity.buildNavHostState(
    navController: NavController = findNavController(R.id.nav_host_splash_fragment)
) = NavHostState(navController)


class NavHostState(
    private val navController: NavController,
) {

    fun navigateToReleases() {
        navController.navigate(R.id.action_splash_to_releases)
    }

    fun navigateToHome() {
        navController.navigate(R.id.action_splash_to_home)
    }

}
