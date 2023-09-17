package com.ac.musicac.ui.main.releases.detail

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import arrow.core.Either
import com.ac.musicac.di.qualifier.AlbumId
import com.ac.musicac.domain.Error
import com.ac.musicac.domain.Release
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
    private val getReleaseDetailUseCase: GetReleaseDetailUseCase) : ViewModel() {

    private val _state = MutableStateFlow(UiState())
    val state: StateFlow<UiState> = _state.asStateFlow()

    fun onUiReady(albumId: String) {
        viewModelScope.launch {
            _state.value = _state.value.copy(loading = true, album = null)

            when (val response = getReleaseDetailUseCase(albumId)) {
                is Either.Left -> _state.value = _state.value.copy(loading = false, error = response.value)
                is Either.Right -> _state.value = _state.value.copy(loading = false, album = response.value)
            }
        }
    }

    data class UiState(
        val loading: Boolean? = null,
        val album: Release? = null,
        val error: Error? = null
    )

}