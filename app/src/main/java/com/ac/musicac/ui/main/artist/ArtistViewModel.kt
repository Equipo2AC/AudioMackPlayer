package com.ac.musicac.ui.main.artist

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import arrow.core.Either
import com.ac.musicac.domain.Albums
import com.ac.musicac.domain.Error
import com.ac.musicac.domain.PopularArtist
import com.ac.musicac.usecases.GetArtistAlbumsUseCase
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
    private val getArtistUseCase: GetArtistUseCase,
    private val getArtistAlbumsUseCase: GetArtistAlbumsUseCase
) : ViewModel() {

    private val _state = MutableStateFlow(UiState())
    val state: StateFlow<UiState> = _state.asStateFlow()

    fun onUiReady(artistId: String) {
        viewModelScope.launch {
            val response = getArtistUseCase(artistId)
            when (response) {
                is Either.Left -> _state.update { it.copy(loading = false, error = response.value) }
                is Either.Right -> _state.update { it.copy(loading = false, artist = response.value) }
            }
        }
    }

    fun onAlbumsRequest(artistId: String) {
        viewModelScope.launch {
            _state.value = _state.value.copy(loading = true)
            val response = getArtistAlbumsUseCase(artistId)
            when (response) {
                is Either.Left -> _state.update { it.copy(loading = false, error = response.value) }
                is Either.Right -> _state.value = _state.value.copy(loading = false, topAlbums = response.value)
            }
        }
    }

    data class UiState(
        val loading: Boolean = false,
        val artist: PopularArtist? = null,
        val topAlbums: Albums? = null,
        val error: Error? = null
    )

}