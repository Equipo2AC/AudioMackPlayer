package com.ac.musicac.ui.main.releases

import androidx.fragment.app.Fragment
import com.ac.musicac.R
import dagger.hilt.android.AndroidEntryPoint
import androidx.fragment.app.viewModels

@AndroidEntryPoint
class ReleasesFragment: Fragment(R.layout.fragment_releases) {

    private val viewModel: ReleasesViewModel by viewModels()


}