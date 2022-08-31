package com.ac.musicac.ui.main.home

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.ac.musicac.data.toError
import com.ac.musicac.domain.*
import com.ac.musicac.ui.common.Scope
import com.ac.musicac.usecases.*
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.*
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class HomeViewModel @Inject constructor(
    getSeveralArtistUseCase: GetSeveralArtistUseCase,
    getSeveralAlbumUseCase: GetSeveralAlbumUseCase,
    private val requestSeveralAlbumUseCase: RequestSeveralAlbumUseCase,
    private val requestSeveralArtistUseCase: RequestSeveralArtistUseCase
) : ViewModel(), Scope by Scope.Impl() {

    private val _state = MutableStateFlow(UiState())
    val state: StateFlow<UiState> = _state.asStateFlow()

    // A comma-separated list of the Spotify IDs for the albums. Maximum: 20 IDs.
    private val ids = "382ObEPsp2rxGrnsizN5TX,1A2GTWGtFfWp7KSQTwWOyo,2noRn2Aes5aoNVsU6iWThc"

    init {
        viewModelScope.launch {
            getSeveralArtistUseCase()
                .catch { cause -> _state.update { it.copy(error = cause.toError()) }}
                .collect{ artists -> _state.update { UiState( artists = artists) } }

            getSeveralAlbumUseCase()
                .catch { cause -> _state.update { it.copy(error = cause.toError()) }}
                .collect{ albums -> _state.update { UiState( albums = albums) } }
        }
    }

    fun onUiReady() {
        viewModelScope.launch {
            _state.value = _state.value.copy(isLoading = true)
            val error = requestSeveralArtistUseCase(ids)
            val error2 = requestSeveralAlbumUseCase(ids)
            if (error2 != null) {
                _state.value = _state.value.copy(isLoading = false, error = error2)
            }
            _state.value = _state.value.copy(isLoading = false, error = error)
        }
    }



    data class UiState(
        val isLoading: Boolean? = null,
        val artists: List<PopularArtist>? = null,
        val albums: List<Item>? = null,
        val error: Error? = null
    )

}