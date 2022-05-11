package com.ac.musicac.ui.main.releases

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import arrow.core.left
import arrow.core.right
import com.ac.musicac.domain.Albums
import com.ac.musicac.domain.Releases
import com.ac.musicac.usecases.GetReleasesUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import com.ac.musicac.domain.Error
import com.ac.musicac.domain.Item
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class ReleasesViewModel @Inject constructor(
    getReleasesUseCase: GetReleasesUseCase,
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

    data class UiState(
        val loading: Boolean = false,
        val albums: List<Item>? = null,
        val error: Error? = null
    )
}