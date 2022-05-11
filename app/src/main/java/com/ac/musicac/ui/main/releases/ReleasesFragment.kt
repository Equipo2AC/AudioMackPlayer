package com.ac.musicac.ui.main.releases

import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import com.ac.musicac.R
import dagger.hilt.android.AndroidEntryPoint
import androidx.fragment.app.viewModels
import com.ac.musicac.databinding.FragmentReleasesBinding
import com.ac.musicac.ui.common.launchAndCollect

@AndroidEntryPoint
class ReleasesFragment: Fragment(R.layout.fragment_releases) {

    private val viewModel: ReleasesViewModel by viewModels()

    private lateinit var releaseState: ReleasesState

    private val adapter = ReleasesAdapter { releaseState.onMovieClicked(it) }


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val binding = FragmentReleasesBinding.bind(view).apply {
            recycler.adapter = adapter
        }

        viewLifecycleOwner.launchAndCollect(viewModel.state) {
            binding.loading = it.loading
            binding.items = it.albums
            binding.error = it.error?.let(releaseState::errorToString)
        }
    }
}