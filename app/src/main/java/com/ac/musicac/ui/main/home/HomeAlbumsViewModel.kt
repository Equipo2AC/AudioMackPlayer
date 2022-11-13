package com.ac.musicac.ui.main.home

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.ac.musicac.data.toError
import com.ac.musicac.domain.Error
import com.ac.musicac.domain.SeveralAlbums
import com.ac.musicac.domain.SeveralArtist
import com.ac.musicac.ui.common.Scope
import com.ac.musicac.usecases.GetSeveralAlbumUseCase
import com.ac.musicac.usecases.GetSeveralArtistUseCase
import com.ac.musicac.usecases.RequestSeveralAlbumUseCase
import com.ac.musicac.usecases.RequestSeveralArtistUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.*
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class HomeAlbumsViewModel @Inject constructor(
    getSeveralAlbumUseCase: GetSeveralAlbumUseCase,
    private val requestSeveralAlbumUseCase: RequestSeveralAlbumUseCase
) : ViewModel(), Scope by Scope.Impl() {

    private val _state = MutableStateFlow(UiState())
    val state: StateFlow<UiState> = _state.asStateFlow()

    init {
        viewModelScope.launch {
            getSeveralAlbumUseCase()
                .catch { cause -> _state.update { it.copy(error = cause.toError()) }}
                .collect{ albums -> _state.update { UiState(albums = albums) } }
        }
    }

    fun onUiReady(albumsIds: String) {
        viewModelScope.launch {
            _state.value = _state.value.copy(loading = true)
            val error = requestSeveralAlbumUseCase(albumsIds)
            _state.value = _state.value.copy(loading = false, error = error)
        }
    }

    data class UiState(
        val loading: Boolean? = null,
        val albums: SeveralAlbums? = null,
        val error: Error? = null
    )

}