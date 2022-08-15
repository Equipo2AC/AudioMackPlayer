package com.ac.musicac.ui.main.artist

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.View
import android.widget.Toast
import androidx.fragment.app.viewModels
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.lifecycleScope
import androidx.lifecycle.repeatOnLifecycle
import androidx.navigation.fragment.navArgs
import com.ac.musicac.R
import com.ac.musicac.databinding.FragmentArtistBinding
import com.ac.musicac.ui.common.launchAndCollect
import com.ac.musicac.ui.main.search.SearchState
import com.ac.musicac.ui.main.search.buildSearchState
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.launch

@AndroidEntryPoint
class ArtistFragment: Fragment(R.layout.fragment_artist) {

    private val safeArgs: ArtistFragmentArgs by navArgs()
    private lateinit var searchState: SearchState
    private val viewModel : ArtistViewModel by viewModels()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        searchState = buildSearchState()
        // val artist = safeArgs.id
        val binding = FragmentArtistBinding.bind(view)
        launchArtistCollect()
        viewModel.onUiReady(safeArgs.id)

    }

    private fun launchArtistCollect() {
        viewLifecycleOwner.launchAndCollect(viewModel.state) { state ->
            withArtistUpdateUI(state)
        }
    }

    private fun withArtistUpdateUI(state: ArtistViewModel.UiState) {
        state.error?.let {
            Toast.makeText(requireContext(), "Habemus Error", Toast.LENGTH_SHORT).show()
        }

        state.artist?.let {
            Toast.makeText(requireContext(), "Habemus Artista ${it.name}", Toast.LENGTH_SHORT).show()
        }
    }

}