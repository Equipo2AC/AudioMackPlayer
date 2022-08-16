package com.ac.musicac.ui.main.artist

import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import arrow.core.Either
import com.ac.musicac.di.qualifier.ArtistId
import com.ac.musicac.domain.Artist
import com.ac.musicac.domain.Error
import com.ac.musicac.domain.Item
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
    // @ArtistId private val artistId: String,
    savedStateHandle: SavedStateHandle,
    private val getArtistUseCase: GetArtistUseCase
) : ViewModel() {

    private val _state = MutableStateFlow(UiState())
    val state: StateFlow<UiState> = _state.asStateFlow()
    // private val artist = ArtistFragmentArgs.fromSavedStateHandle(savedStateHandle)

    init {
        /*viewModelScope.launch {
            getArtistUseCase(artistId).fold(ifLeft = {_state.update { UiState(error = it.error) }}) {
                _state.update { UiState(artist = it.artist) }
            }
        }*/
    }

    fun onUiReady(artistId: String) {
        viewModelScope.launch {
            _state.value = _state.value.copy(loading = true)

            val response = getArtistUseCase(artistId)

            when (response) {
                is Either.Left -> _state.update { it.copy(loading = false, error = response.value) }
                is Either.Right -> _state.update { it.copy(loading = false, artist = response.value) }
            }
        }
    }

    data class UiState(
        val loading: Boolean? = false,
        val artist: Item? = null,
        val error: Error? = null
    )

}