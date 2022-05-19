package com.ac.musicac.ui.splash

import android.content.Context
import androidx.fragment.app.Fragment
import androidx.navigation.NavController
import androidx.navigation.fragment.findNavController
import com.ac.musicac.R
import com.ac.musicac.domain.Error

fun Fragment.buildSplashState(
    context: Context = requireContext(),
    navController: NavController = findNavController()
) = SplashState(context, navController)

class SplashState(
    private val context: Context,
    private val navController: NavController
) {

    fun errorToString(error: Error) = when (error) {
        Error.Connectivity -> context.getString(R.string.connectivity_error)
        is Error.Server -> context.getString(R.string.server_error) + error.code
        is Error.Unknown -> context.getString(R.string.unknown_error) + error.message
    }

    fun navigate(){
        navController.navigate(R.id.action_splash_to_home)

    }

}