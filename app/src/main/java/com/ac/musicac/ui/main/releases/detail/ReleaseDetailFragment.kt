package com.ac.musicac.ui.main.releases.detail

import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.navArgs
import com.ac.musicac.R
import com.ac.musicac.databinding.FragmentReleaseDetailBinding
import com.ac.musicac.databinding.FragmentReleasesBinding
import com.ac.musicac.ui.common.launchAndCollect
import com.ac.musicac.ui.main.releases.list.ReleasesAdapter
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class ReleaseDetailFragment: Fragment(R.layout.fragment_release_detail) {

    private val viewModel: ReleaseDetailViewModel by viewModels()

    private lateinit var releaseDetailState: ReleaseDetailState

    private val args: ReleaseDetailFragmentArgs by navArgs()

    private val adapter = TracksAdapter()


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        releaseDetailState = buildReleaseDetailState()

        val binding = FragmentReleaseDetailBinding.bind(view).apply {
            recycler.adapter = adapter
        }

        binding.movieDetailToolbar.setNavigationOnClickListener { requireActivity().onBackPressed() }

        viewLifecycleOwner.launchAndCollect(viewModel.state) { state ->
            if (state.album != null) {
                binding.album = state.album
                binding.tracks = state.album.tracks.items
            }
        }

        releaseDetailState.requestLocationPermission {
            viewModel.onUiReady(args.albumId)
        }
    }
}