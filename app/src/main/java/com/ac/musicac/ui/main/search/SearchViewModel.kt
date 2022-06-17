package com.ac.musicac.ui.main.search

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.ac.musicac.domain.Error
import com.ac.musicac.domain.Item
import com.ac.musicac.domain.Type
import com.ac.musicac.usecases.SearchUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class SearchViewModel @Inject constructor(private val searchUseCase: SearchUseCase) : ViewModel() {

    private val _state = MutableStateFlow(UiState())
    val state: StateFlow<UiState> = _state.asStateFlow()

    fun onChangeType(type: Type?) {
        type?.let {
            _state.update { state -> state.copy(type = it) }
            search(type, state.value.query)
        }
    }

    fun onQueryTextChange(text: String): Boolean {
        if (text.isNotEmpty()) {
            search(state.value.type, text)
        } else {
            _state.update { state -> state.copy(
                search = null,
                query = ""
            ) }
        }
        return false
    }

    private fun search(type: Type, query: String) {
        _state.update { state -> state.copy(loading = true, search = null) }
        viewModelScope.launch {
            searchUseCase(type, query).fold(
                ifLeft = { cause ->
                    _state.update {
                        it.copy(
                            loading = false,
                            error = cause
                        )
                    }
                },
                ifRight = { result ->
                    _state.update { state ->
                        state.copy(
                            loading = false,
                            query = query,
                            search = when (type) {
                                Type.ARTIST -> result.artists?.items
                                Type.ALBUM -> result.albums?.items
                            }
                        )
                    }
                }
            )
        }
    }

    fun onAction(action: SearchAction) {
        when (action) {
            is SearchAction.OnClickAlbum -> TODO()
            is SearchAction.OnClickArtist -> TODO()
        }
    }

    data class UiState(
        val loading: Boolean? = null,
        val search: List<Item>? = null,
        val query: String = "",
        val type: Type = Type.ALBUM,
        val error: Error? = null
    )
}
