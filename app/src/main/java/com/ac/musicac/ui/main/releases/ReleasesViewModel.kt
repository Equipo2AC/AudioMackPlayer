package com.ac.musicac.ui.main.releases

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import arrow.core.Either
import com.ac.musicac.domain.Error
import com.ac.musicac.domain.Item
import com.ac.musicac.usecases.GetReleasesUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class ReleasesViewModel @Inject constructor(
    private val getReleasesUseCase: GetReleasesUseCase,
) : ViewModel() {

    private val _state = MutableStateFlow(UiState())
    val state: StateFlow<UiState> = _state.asStateFlow()

    init {
        viewModelScope.launch {
            getReleasesUseCase().fold(
                ifLeft = { cause -> _state.update { it.copy(error = cause) } },
                ifRight = { albums -> _state.update { UiState(albums = albums.albums.items) } }
            )
        }
    }

    fun onUiReady() {
        viewModelScope.launch {
            _state.value = _state.value.copy(loading = true)

            val response = getReleasesUseCase()

            when (response) {
                is Either.Left -> _state.value = _state.value.copy(loading = false, error = response.value)
                is Either.Right -> _state.value = _state.value.copy(loading = false, albums = response.value.albums.items)
            }
        }
    }

    data class UiState(
        val loading: Boolean = false,
        val albums: List<Item>? = null,
        val error: Error? = null
    )
}