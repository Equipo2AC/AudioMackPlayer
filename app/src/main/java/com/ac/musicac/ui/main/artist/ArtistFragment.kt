package com.ac.musicac.ui.main.artist

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.View
import android.widget.Toast
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.navArgs
import com.ac.musicac.R
import com.ac.musicac.databinding.FragmentArtistBinding
import com.ac.musicac.ui.common.launchAndCollect
import com.ac.musicac.ui.main.releases.list.ReleasesAdapter
import com.ac.musicac.ui.main.releases.list.ReleasesState
import com.ac.musicac.ui.main.releases.list.buildReleasesState
import com.ac.musicac.ui.main.search.SearchState
import com.ac.musicac.ui.main.search.buildSearchState
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class ArtistFragment: Fragment(R.layout.fragment_artist) {

    private val safeArgs: ArtistFragmentArgs by navArgs()
    private lateinit var artistState: ArtistState
    private val viewModel : ArtistViewModel by viewModels()
    private lateinit var binding: FragmentArtistBinding
    private val adapter = ArtistsAlbumsAdapter { artistState.onAlbumClicked(it) }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        artistState = buildArtistState()
        binding = FragmentArtistBinding.bind(view).apply {
            recycler.adapter = adapter
            artistToolbar.setNavigationOnClickListener { requireActivity().onBackPressed() }
        }
        launchArtistCollect()
        viewModel.onUiReady(safeArgs.artistId)
        viewModel.onAlbumsRequest(safeArgs.artistId)

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
            Log.e("Artist ID", it.artistId)
        }
        state.error?.let {
            Toast.makeText(requireContext(), "Error ${artistState.errorToString(it)} ", Toast.LENGTH_SHORT).show()
        }
    }
}