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
            val albums = getReleasesUseCase().fold(
                ifLeft = { it },
                ifRight = {
                    it.albums
                }
            )

            println(albums)

        }
    }

    data class UiState(
        val loading: Boolean = false,
        val albums: List<Albums>? = null,
        val error: Error? = null
    )
}