package com.ac.musicac.ui.main.releases.detail

import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import com.ac.musicac.R
import com.ac.musicac.databinding.FragmentReleaseDetailBinding
import com.ac.musicac.ui.common.launchAndCollect
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class ReleaseDetailFragment: Fragment(R.layout.fragment_release_detail) {

    private val viewModel: ReleaseDetailViewModel by viewModels()

    private lateinit var releaseState: ReleaseDetailState

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val binding = FragmentReleaseDetailBinding.bind(view)

        binding.movieDetailToolbar.setNavigationOnClickListener { requireActivity().onBackPressed() }

        viewLifecycleOwner.launchAndCollect(viewModel.state) { state ->
            if (state.album != null) {
                binding.album = state.album

            }
        }

        releaseState.requestLocationPermission {
            viewModel.onUiReady()
        }
    }
}