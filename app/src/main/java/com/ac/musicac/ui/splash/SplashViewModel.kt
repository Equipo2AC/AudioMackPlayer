package com.ac.musicac.ui.splash

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.ac.musicac.usecases.RequestAuthenticationUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class SplashViewModel @Inject constructor(
    private val requestAuthenticationUseCase : RequestAuthenticationUseCase
) : ViewModel() {


    fun demo() {
        viewModelScope.launch {
            val error = requestAuthenticationUseCase()
            val result = error
        }
    }
}
