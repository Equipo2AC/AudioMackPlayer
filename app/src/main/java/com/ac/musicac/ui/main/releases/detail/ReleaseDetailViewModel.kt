package com.ac.musicac.ui.main.releases.detail

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.ac.musicac.di.AlbumId
import com.ac.musicac.domain.Error
import com.ac.musicac.domain.releases.Release
import com.ac.musicac.usecases.GetReleaseDetailUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class ReleaseDetailViewModel @Inject constructor(
    @AlbumId private val albumId: String,
    private val getReleaseDetailUseCase: GetReleaseDetailUseCase) : ViewModel() {

    private val _state = MutableStateFlow(UiState())
    val state: StateFlow<UiState> = _state.asStateFlow()

    init {
        viewModelScope.launch {
            getReleaseDetailUseCase(albumId).fold(
                ifLeft = { cause -> _state.update { it.copy(error = cause) } },
                ifRight = { album -> _state.update { UiState(album = album) } }
            )
        }
    }

    data class UiState(
        val loading: Boolean = false,
        val album: Release? = null,
        val error: Error? = null
    )

}