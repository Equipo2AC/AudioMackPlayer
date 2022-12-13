package com.ac.musicac.ui.main.home

import CoroutinesTestRule
import app.cash.turbine.test
import com.ac.musicac.testshared.Mocks
import com.ac.musicac.ui.main.home.HomeAlbumsViewModel.*
import com.ac.musicac.usecases.GetSeveralAlbumUseCase
import com.ac.musicac.usecases.RequestSeveralAlbumUseCase
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.flowOf
import kotlinx.coroutines.test.runCurrent
import kotlinx.coroutines.test.runTest
import org.junit.Assert.*
import org.junit.After
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith
import org.mockito.Mock
import org.mockito.junit.MockitoJUnitRunner
import org.mockito.kotlin.any
import org.mockito.kotlin.verify
import org.mockito.kotlin.whenever

@ExperimentalCoroutinesApi
@RunWith(MockitoJUnitRunner::class)
class HomeAlbumsViewModelTest {

    @get:Rule
    val coroutinesTestRule = CoroutinesTestRule()

    @Mock
    private lateinit var getAlbumsUseCase: GetSeveralAlbumUseCase

    @Mock
    private lateinit var requestAlbumsUseCase: RequestSeveralAlbumUseCase

    private lateinit var  vm: HomeAlbumsViewModel

    private val albumId = "6jbtHi5R0jMXoliU2OS0lo"

    private val albumSample = Mocks.mockSeveralAlbums()

    @Before
    fun setUp() {
        whenever(getAlbumsUseCase()).thenReturn(flowOf(albumSample))
        vm = HomeAlbumsViewModel(getAlbumsUseCase, requestAlbumsUseCase)
    }

    @After
    fun tearDown() {
    }

    @Test
    fun `State is updated with current cached content inmediately`() = runTest {

        vm.state.test {
            assertEquals(UiState(), awaitItem())
            assertEquals(UiState(albums = albumSample), awaitItem())
            cancel()
        }
    }

    @Test
    fun `Progress is shown when screen start and hidden when it finishes`() = runTest {

        vm.onUiReady(albumId)

        vm.state.test {
            assertEquals(UiState(), awaitItem())
            assertEquals(UiState(albums = albumSample), awaitItem())
            assertEquals(UiState(loading = true, albums = albumSample), awaitItem())
            assertEquals(UiState(loading = false, albums = albumSample), awaitItem())
            cancel()
        }
    }

    @Test
    fun `Artists are requested when UI screen starts`() = runTest {
        vm.onUiReady(albumId)

        runCurrent()

        verify(requestAlbumsUseCase).invoke(any())
    }
}