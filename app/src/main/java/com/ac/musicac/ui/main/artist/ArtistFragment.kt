package com.ac.musicac.ui.main.artist

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.View
import android.widget.Toast
import androidx.fragment.app.viewModels
import com.ac.musicac.R
import com.ac.musicac.databinding.FragmentArtistBinding
import com.ac.musicac.ui.common.launchAndCollect
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class ArtistFragment: Fragment(R.layout.fragment_artist) {

    private lateinit var artistState: ArtistState
    private val viewModel : ArtistViewModel by viewModels()
    private lateinit var binding: FragmentArtistBinding
    private val adapter = ArtistsAlbumsAdapter { artistState.onAlbumClicked(it) }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        artistState = buildArtistState()
        binding = FragmentArtistBinding.bind(view).apply {
            recyclerArtistAlbums.adapter = adapter
            artistToolbar.setNavigationOnClickListener {
                requireActivity().onBackPressedDispatcher.onBackPressed()
            }
        }
        launchArtistCollect()
        viewModel.onUiReady()
        viewModel.onAlbumsRequest()

    }

    private fun launchArtistCollect() {
        viewLifecycleOwner.launchAndCollect(viewModel.state) { state ->
            withArtistUpdateUI(state)
        }
    }


    private fun withArtistUpdateUI(state: ArtistViewModel.UiState) = with(binding) {
        loading = state.loading
        albumlist = state.topAlbums?.items?.sortedBy { it.releaseDate }?.reversed()
        state.artist?.let {
            item = state.artist
            popularityBar.progress = state.artist.popularity
            "${state.artist.popularity}%".also { popularityPercent.text = it }
        }
        state.error?.let {
            Toast.makeText(requireContext(), "Error ${artistState.errorToString(it)} ", Toast.LENGTH_SHORT).show()
            Log.e("Artist Fragment ERROR", "Error ${artistState.errorToString(it)} ")
        }
    }
}