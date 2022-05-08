package com.ac.musicac.ui.splash

import android.os.Bundle
import android.view.View
import androidx.activity.viewModels
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import com.ac.musicac.R
import com.ac.musicac.databinding.FragmentReleasesBinding
import com.ac.musicac.ui.common.launchAndCollect
import com.ac.musicac.ui.main.releases.ReleasesAdapter
import com.ac.musicac.ui.main.releases.ReleasesState
import com.ac.musicac.ui.main.releases.ReleasesViewModel
import dagger.hilt.android.AndroidEntryPoint


@AndroidEntryPoint
class SplashFragment: Fragment(R.layout.fragment_splash) {

    private val viewModel: SplashViewModel by viewModels()

    private lateinit var releaseState: ReleasesState

    private val adapter = ReleasesAdapter { releaseState.onMovieClicked(it) }


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)



    }

}