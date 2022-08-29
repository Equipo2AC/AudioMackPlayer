package com.ac.musicac.ui.main.search

import CoroutinesTestRule
import app.cash.turbine.test
import arrow.core.left
import arrow.core.right
import com.ac.musicac.domain.Error
import com.ac.musicac.domain.Type
import com.ac.musicac.usecases.SearchUseCase
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.test.runTest
import org.junit.Assert
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith
import org.mockito.Mock
import org.mockito.junit.MockitoJUnitRunner
import org.mockito.kotlin.any
import org.mockito.kotlin.whenever

@RunWith(MockitoJUnitRunner::class)
@OptIn(ExperimentalCoroutinesApi::class)
class SearchViewModelTest {

    @get:Rule
    val coroutinesTestRule = CoroutinesTestRule()

    @Mock
    lateinit var searchUseCase: SearchUseCase

    private lateinit var viewModel: SearchViewModel

    @Before
    fun setUp() {
        viewModel = SearchViewModel(searchUseCase)
    }

    @Test
    fun `When query change empty`() {
        //Given
        //When
        viewModel.onQueryTextChange("")
        //Them
        Assert.assertEquals(viewModel.state.value.search, null)
        Assert.assertEquals(viewModel.state.value.query, "")
    }

    @Test
    fun `With mock artist response when Artist change type `() = runTest {
        //Given
        whenever(searchUseCase(any(), any())).thenReturn(SearchMocks.mockSearchArtist().right())
        //When
        viewModel.onChangeType(Type.ARTIST)
        //Them
        viewModel.state.test {
            Assert.assertEquals(
                SearchViewModel.UiState(
                    loading = true,
                    search = null,
                    query = "",
                    type = Type.ARTIST,
                    error = null
                ), awaitItem()
            )
            Assert.assertEquals(
                SearchViewModel.UiState(
                    loading = false,
                    search = SearchMocks.mockSearchArtist().artists?.items,
                    query = "",
                    type = Type.ARTIST,
                    error = null
                ), awaitItem()
            )

            cancel()
        }
    }

    @Test
    fun `With mock search response artist when Album change type`() = runTest {
        //Given
        whenever(searchUseCase(any(), any())).thenReturn(SearchMocks.mockSearchArtist().right())
        //When
        viewModel.onChangeType(Type.ALBUM)
        //Them
        viewModel.state.test {
            Assert.assertEquals(
                SearchViewModel.UiState(
                    loading = true,
                    search = null,
                    query = "",
                    type = Type.ALBUM,
                    error = null
                ), awaitItem()
            )
            Assert.assertEquals(
                SearchViewModel.UiState(
                    loading = false,
                    search = null,
                    query = "",
                    type = Type.ALBUM,
                    error = null
                ), awaitItem()
            )

            cancel()
        }
    }

    @Test
    fun `With mock search response album when Artist change type`() = runTest {
        //Given
        whenever(searchUseCase(any(), any())).thenReturn(SearchMocks.mockSearchAlbum().right())
        //When
        viewModel.onChangeType(Type.ARTIST)
        //Them
        viewModel.state.test {
            Assert.assertEquals(
                SearchViewModel.UiState(
                    loading = true,
                    search = null,
                    query = "",
                    type = Type.ARTIST,
                    error = null
                ), awaitItem()
            )
            Assert.assertEquals(
                SearchViewModel.UiState(
                    loading = false,
                    search = null,
                    query = "",
                    type = Type.ARTIST,
                    error = null
                ), awaitItem()
            )

            cancel()
        }
    }

    @Test
    fun `With mock search album type is update when Album change type`() = runTest {
        //Given
        whenever(searchUseCase(any(), any())).thenReturn(SearchMocks.mockSearchAlbum().right())
        //When
        viewModel.onChangeType(Type.ALBUM)
        //Them
        viewModel.state.test {
            Assert.assertEquals(
                SearchViewModel.UiState(
                    loading = true,
                    search = null,
                    query = "",
                    type = Type.ALBUM,
                    error = null
                ), awaitItem()
            )
            Assert.assertEquals(
                SearchViewModel.UiState(
                    loading = false,
                    search = SearchMocks.mockSearchAlbum().albums?.items,
                    query = "",
                    type = Type.ALBUM,
                    error = null
                ), awaitItem()
            )

            cancel()
        }
    }

    @Test
    fun `When onChangeType return error()`() = runTest {
        //Given
        val error = Error.Unknown("")
        whenever(searchUseCase(any(), any())).thenReturn(error.left())
        //When
        viewModel.onChangeType(Type.ALBUM)
        //Them
        viewModel.state.test {
            Assert.assertEquals(
                SearchViewModel.UiState(
                    loading = true,
                    search = null,
                    query = "",
                    type = Type.ALBUM,
                    error = null
                ), awaitItem()
            )
            Assert.assertEquals(
                SearchViewModel.UiState(
                    loading = false,
                    search = null,
                    query = "",
                    type = Type.ALBUM,
                    error = error
                ), awaitItem()
            )

            cancel()
        }
    }

    @Test
    fun `State type is update when change Album type`() = runTest {
        //Given
        //When
        viewModel.onChangeType(Type.ALBUM)
        //Them
        Assert.assertEquals(viewModel.state.value.type, Type.ALBUM)
    }

    @Test
    fun `State type is update when change Artist type`() = runTest {
        //Given
        //When
        viewModel.onChangeType(Type.ARTIST)
        //Them
        Assert.assertEquals(viewModel.state.value.type, Type.ARTIST)
    }

    @Test
    fun `State type is don't update when change null type`() = runTest {
        //Given
        //When
        viewModel.onChangeType(null)
        //Them
        Assert.assertEquals(viewModel.state.value.type, Type.ALBUM)
    }
}