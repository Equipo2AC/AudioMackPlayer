package com.ac.musicac.ui.main.releases.list

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import arrow.core.Either
import com.ac.musicac.data.toError
import com.ac.musicac.domain.Error
import com.ac.musicac.domain.Item
import com.ac.musicac.ui.main.home.HomeAlbumsViewModel
import com.ac.musicac.usecases.GetReleasesUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.*
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class ReleasesViewModel @Inject constructor(
    private val getReleasesUseCase: GetReleasesUseCase,
) : ViewModel() {

    private val _state = MutableStateFlow(UiState())
    val state: StateFlow<UiState> = _state.asStateFlow()

    fun onUiReady() {
        viewModelScope.launch {
            _state.update { state -> state.copy(loading = true, albums = null, error = null) }
            when (val response = getReleasesUseCase()) {
                is Either.Left -> _state.update { it.copy(loading = false, albums = null, error = response.value) }
                is Either.Right -> _state.update { it.copy(loading = false, albums = response.value.albums.items, error = null) }
            }
        }
    }

    data class UiState(
        val loading: Boolean = false,
        val albums: List<Item>? = null,
        val error: Error? = null
    )
}