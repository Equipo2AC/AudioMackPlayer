package com.ac.musicac.ui.main.home

import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import com.ac.musicac.R
import com.ac.musicac.databinding.FragmentHomeBinding
import com.ac.musicac.ui.common.launchAndCollect
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class HomeFragment : Fragment(R.layout.fragment_home) {

    private val artistViewModel: HomeArtistsViewModel by viewModels()
    private val albumsViewModel: HomeAlbumsViewModel by viewModels()
    private lateinit var binding : FragmentHomeBinding
    private lateinit var homeState: HomeState
    private val albumsAdapter = AlbumsAdapter { homeState.onAlbumClicked(it) }
    private val artistAdapter = ArtistsAdapter { homeState.onArtistClicked(it) }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        homeState = buildHomeState()
        binding = FragmentHomeBinding.bind(view).apply {
            recyclerAlbums.adapter = albumsAdapter
            recyclerArtist.adapter = artistAdapter
        }

        artistViewModel.onUiReady()
        albumsViewModel.onUiReady()

        viewLifecycleOwner.launchAndCollect(albumsViewModel.state) { state->
            // binding.albums = state.albums?.albums

            state.loading?.let {
                binding.loadingAlbums = it
            }

            state.albums?.let {
                binding.albums = it.albums
                Toast.makeText(requireContext(), "Albumes $it ", Toast.LENGTH_SHORT).show()
            }
            state.error?.let {
                Toast.makeText(requireContext(), "Error $it ", Toast.LENGTH_SHORT).show()
            }
        }

        viewLifecycleOwner.launchAndCollect(artistViewModel.state) { state->
            binding.artists = state.artists?.artists

            state.loading?.let {
                binding.loadingArtists = it
            }
            state.error?.let {
                // Toast.makeText(requireContext(), "Habemus error en artistas $it ", Toast.LENGTH_SHORT).show()
            }
        }
    }

}