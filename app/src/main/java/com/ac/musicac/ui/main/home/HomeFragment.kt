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

    private val viewModel: HomeViewModel by viewModels()
    private lateinit var binding : FragmentHomeBinding
    private lateinit var homeState: HomeState
    private val albumsAdapter = AlbumsAdapter { homeState.onAlbumClicked(it) }
    private val artistAdapter = ArtistsAdapter { homeState.onArtistClicked(it) }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        homeState = buildHomeState()
        binding = FragmentHomeBinding.bind(view).apply {
            // recyclerAlbums.adapter = albumsAdapter
            recyclerArtist.adapter = artistAdapter
        }

        viewModel.onUiReady()

        viewLifecycleOwner.launchAndCollect(viewModel.state) { state->
            state.loading?.let {

                binding.loading = it
            }
            // binding.albums = state.albums
            // binding.artists = state.artists

            state.albums?.let {
                binding.albums = it.albums
                Toast.makeText(requireContext(), "Albumes $it ", Toast.LENGTH_SHORT).show()
            }
            state.artists?.let {
                // val artist = it.get(0).name
                binding.artists = it.artists
                Toast.makeText(requireContext(), "Artistas ${it.artists.size} ", Toast.LENGTH_SHORT).show()
            }
            // binding.error = state.error?.let(releaseState::errorToString)

        }
    }

}