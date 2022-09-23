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

        viewLifecycleOwner.launchAndCollect(albumsViewModel.state) { state->
            with(binding) {
                albums = state.albums?.albums
                state.loading?.let {
                    loadingAlbums = it
                }
                state.error?.let {
                    tvError.text = error
                    Toast.makeText(requireContext(), "Error ${homeState.errorToString(it)} ", Toast.LENGTH_LONG).show()
                }
            }
        }

        viewLifecycleOwner.launchAndCollect(artistViewModel.state) { state ->
            with(binding) {
                artists = state.artists?.artists
                state.loading?.let {
                    loadingArtists = it
                }
                state.error?.let {
                    tvError.text = error
                    Toast.makeText(requireContext(), "Error ${homeState.errorToString(it)} ", Toast.LENGTH_LONG).show()
                }
            }
        }

        homeState.requestLocationPermission {
            artistViewModel.onUiReady()
            albumsViewModel.onUiReady()
        }
    }

}