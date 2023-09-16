package com.ac.musicac.ui.main.home

import CoroutinesTestRule
import app.cash.turbine.test
import com.ac.musicac.testshared.Mocks
import com.ac.musicac.ui.main.home.HomeArtistsViewModel.*
import com.ac.musicac.usecases.GetSeveralArtistUseCase
import com.ac.musicac.usecases.RequestSeveralArtistUseCase
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
class HomeArtistsViewModelTest {

    @get:Rule
    val coroutinesTestRule = CoroutinesTestRule()

    @Mock
    private lateinit var getArtistUseCase: GetSeveralArtistUseCase

    @Mock
    private lateinit var requestArtistUseCase: RequestSeveralArtistUseCase

    private lateinit var  vm: HomeArtistsViewModel

    private val artistId = "7ltDVBr6mKbRvohxheJ9h1"

    private val artistSample = Mocks.mockSeveralArtists()

    @Before
    fun setUp() {
        whenever(getArtistUseCase()).thenReturn(flowOf(artistSample))
        vm = HomeArtistsViewModel(getArtistUseCase, requestArtistUseCase)
    }

    @After
    fun tearDown() {
    }

    @Test
    fun `State is updated with current cached content inmediately`() = runTest {

        vm.state.test {
            assertEquals(UiState(artists = artistSample), awaitItem())
            cancel()
        }
    }

    @Test
    fun `Progress is shown when screen start and hidden when it finishes`() = runTest {

        vm.onUiReady(artistId)

        vm.state.test {
            assertEquals(UiState(artists = artistSample), awaitItem())
            assertEquals(UiState(loading = true, artists = artistSample), awaitItem())
            assertEquals(UiState(loading = false, artists = artistSample), awaitItem())
            cancel()
        }
    }

    @Test
    fun `Artists are requested when UI screen starts`() = runTest {
        vm.onUiReady(artistId)

        runCurrent()

        verify(requestArtistUseCase).invoke(any())
    }
}