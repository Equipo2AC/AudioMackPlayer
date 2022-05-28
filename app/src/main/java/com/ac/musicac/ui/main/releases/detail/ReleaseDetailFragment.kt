package com.ac.musicac.ui.main.releases.detail

import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import com.ac.musicac.R
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class ReleaseDetailFragment: Fragment(R.layout.fragment_releases) {

    private val viewModel: ReleaseDetailViewModel by viewModels()

    private lateinit var releaseState: ReleaseDetailState

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
    }
}