package com.ac.musicac.ui.main.artist

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.View
import android.widget.Toast
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.navArgs
import com.ac.musicac.R
import com.ac.musicac.databinding.FragmentArtistBinding
import com.ac.musicac.ui.common.launchAndCollect
import com.ac.musicac.ui.main.search.SearchState
import com.ac.musicac.ui.main.search.buildSearchState
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class ArtistFragment: Fragment(R.layout.fragment_artist) {

    private val safeArgs: ArtistFragmentArgs by navArgs()
    private lateinit var searchState: SearchState
    private val viewModel : ArtistViewModel by viewModels()
    private lateinit var binding: FragmentArtistBinding
    private val adapter = AlbumsAdapter()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        searchState = buildSearchState()
        binding = FragmentArtistBinding.bind(view).apply {
            recycler.adapter = adapter
            artistToolbar.setNavigationOnClickListener { requireActivity().onBackPressed() }
        }
        launchArtistCollect()
        viewModel.onUiReady(safeArgs.artistId)
        viewModel.onTracksRequest(safeArgs.artistId)

    }

    private fun launchArtistCollect() {
        viewLifecycleOwner.launchAndCollect(viewModel.state) { state ->
            withArtistUpdateUI(state)
        }
    }

    private fun withArtistUpdateUI(state: ArtistViewModel.UiState) = with(binding) {
        loading = state.loading
        item = state.artist
        albumlist = state.topAlbums?.items

        state.error?.let {
            Toast.makeText(requireContext(), "Habemus Error $it ", Toast.LENGTH_SHORT).show()
        }

        /*state.artist?.let {
            binding.item = it
        }*/
        /*state.topAlbums?.let {
            binding.albumlist = it.items
        }*/

    }

}