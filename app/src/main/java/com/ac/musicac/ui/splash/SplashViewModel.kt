package com.ac.musicac.ui.splash

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.ac.musicac.domain.Error
import com.ac.musicac.usecases.RequestAuthenticationUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class SplashViewModel @Inject constructor(
    private val requestAuthenticationUseCase: RequestAuthenticationUseCase
) : ViewModel() {


    private val _state = MutableStateFlow(UiState())
    val state: StateFlow<UiState> = _state.asStateFlow()

    init {
        onUiReady()
    }

    private fun onUiReady() {
        viewModelScope.launch {
            _state.value = _state.value.copy(loading = true)
            val error = requestAuthenticationUseCase()
            // _state.value = _state.value.copy(loading = false, error = error)
            _state.value = _state.value.copy(loading = false, error = error, navigate = error?.let { true } ?: false)
        }
    }

    data class UiState(
        val loading: Boolean = false,
        val navigate: Boolean = false,
        val error: Error? = null
    )
}
