package com.ac.musicac.ui.main.releases.list

import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import com.ac.musicac.R
import com.ac.musicac.databinding.FragmentReleasesBinding
import com.ac.musicac.ui.common.launchAndCollect
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class ReleasesFragment: Fragment(R.layout.fragment_releases) {

    private val viewModel: ReleasesViewModel by viewModels()

    private lateinit var releaseState: ReleasesState

    private val adapter = ReleasesAdapter { releaseState.onAlbumClicked(it) }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        releaseState = buildReleasesState()

        val binding = FragmentReleasesBinding.bind(view).apply {
            recycler.adapter = adapter
        }

        viewLifecycleOwner.launchAndCollect(viewModel.state) { state->
            binding.loading = state.loading
            binding.items = state.albums
            binding.error = state.error?.let(releaseState::errorToString)
        }

        releaseState.requestLocationPermission {
            viewModel.onUiReady()
        }
    }
}