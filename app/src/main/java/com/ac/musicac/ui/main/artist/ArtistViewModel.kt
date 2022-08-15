package com.ac.musicac.ui.main.artist

import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.ac.musicac.domain.Artist
import com.ac.musicac.domain.Error
import com.ac.musicac.ui.main.releases.ReleasesViewModel
import com.ac.musicac.usecases.GetArtistUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class ArtistViewModel @Inject constructor(
    savedStateHandle: SavedStateHandle,
    getArtistUseCase: GetArtistUseCase
) : ViewModel() {

    private val _state = MutableStateFlow(UiState())
    val state: StateFlow<UiState> = _state.asStateFlow()
    private val artistId = ArtistFragmentArgs.fromSavedStateHandle(savedStateHandle).id

    init {
        viewModelScope.launch {
            getArtistUseCase(artistId).fold(ifLeft = {_state.update { UiState(error = it.error) }}) {
                _state.update { UiState(artist = it.artist) }
            }
        }
    }

    data class UiState(
        val artist: Artist? = null,
        val error: Error? = null
    )

}