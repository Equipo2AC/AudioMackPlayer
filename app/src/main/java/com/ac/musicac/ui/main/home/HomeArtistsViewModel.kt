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
class HomeArtistsViewModel @Inject constructor(
    getSeveralArtistUseCase: GetSeveralArtistUseCase,
    private val requestSeveralArtistUseCase: RequestSeveralArtistUseCase
) : ViewModel(), Scope by Scope.Impl() {

    private val _state = MutableStateFlow(UiState())
    val state: StateFlow<UiState> = _state.asStateFlow()

    // A comma-separated list of the Spotify IDs for the albums. Maximum: 20 IDs.
    private val artistIds = "7ltDVBr6mKbRvohxheJ9h1,716NhGYqD1jl2wI1Qkgq36,52iwsT98xCoGgiGntTiR7K," +
            "4q3ewBCX7sLwd24euuV69X,1bAftSH8umNcGZ0uyV7LMg,790FomKkXshlbRYZFtlgla," +
            "2R21vXR83lH98kGeO99Y66,1Cs0zKBU1kc0i8ypK3B9ai,1Xyo4u8uXC1ZmMpatF05PJ"

    init {
        viewModelScope.launch {
            getSeveralArtistUseCase()
                .catch { cause -> _state.update { it.copy(error = cause.toError()) }}
                .collect{ artists -> _state.update { UiState( artists = artists) } }
        }
    }

    fun onUiReady() {
        viewModelScope.launch {
            _state.value = _state.value.copy(loading = true)
            val error = requestSeveralArtistUseCase(artistIds)
            _state.value = _state.value.copy(loading = false, error = error)
        }
    }



    data class UiState(
        val loading: Boolean? = null,
        val artists: SeveralArtist? = null,
        val error: Error? = null
    )

}