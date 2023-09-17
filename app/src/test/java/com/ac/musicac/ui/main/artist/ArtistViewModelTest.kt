package com.ac.musicac.ui.main.artist

import CoroutinesTestRule
import app.cash.turbine.test
import arrow.core.right
import com.ac.musicac.testshared.Mocks
import com.ac.musicac.ui.main.artist.ArtistViewModel.*
import com.ac.musicac.usecases.GetArtistAlbumsUseCase
import com.ac.musicac.usecases.GetArtistUseCase
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.toList
import kotlinx.coroutines.launch
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
class ArtistViewModelTest {

    @get:Rule
    val coroutinesTestRule = CoroutinesTestRule()

    @Mock
    private lateinit var getArtistUseCase: GetArtistUseCase

    @Mock
    private lateinit var getArtistAlbumsUseCase: GetArtistAlbumsUseCase

    private lateinit var  vm: ArtistViewModel

    private val artistId = "7ltDVBr6mKbRvohxheJ9h1"
    private val artistSample = Mocks.mockPopularArtist()
    private val albumSample = Mocks.mockAlbums()

    @Before
    fun setUp() {
        vm = ArtistViewModel(artistId, getArtistUseCase, getArtistAlbumsUseCase)
    }

    @Test
    fun `State is updated with artist content inmediately`() = runTest {

        whenever(getArtistUseCase(artistId)).thenReturn(artistSample.right())

        vm.onUiReady()

        val results = mutableListOf<UiState>()
        val job = launch { vm.state.toList(results) }
        runCurrent()
        job.cancel()
        assertEquals(UiState(artist = artistSample), results[0])

    }

    @Test
    fun `State is updated with artist albums content inmediately`() = runTest {

        whenever(getArtistAlbumsUseCase(artistId)).thenReturn(albumSample.right())

        vm.onAlbumsRequest()

        val results = mutableListOf<UiState>()
        val job = launch { vm.state.toList(results) }
        runCurrent()
        job.cancel()
        assertEquals(UiState(topAlbums = albumSample), results[0])
    }

    @Test
    fun `Progress is shown when artist start loading`() = runTest {

        whenever(getArtistUseCase(artistId)).thenReturn(artistSample.right())

        vm.onUiReady()

        vm.state.test {
            assertEquals(UiState(), awaitItem())
            assertEquals(UiState(loading = true), awaitItem())
            assertEquals(UiState(loading = false, artist = artistSample), awaitItem())
            cancel()
        }
    }

    @Test
    fun `Progress is shown when album start loading`() = runTest {

        whenever(getArtistAlbumsUseCase(artistId)).thenReturn(albumSample.right())

        vm.onAlbumsRequest()

        vm.state.test {
            assertEquals(UiState(), awaitItem())
            assertEquals(UiState(loading = true), awaitItem())
            assertEquals(UiState(loading = false, topAlbums = albumSample), awaitItem())
            cancel()
        }
    }

    @Test
    fun `Artists are requested when UI screen starts`() = runTest {
        vm.onUiReady()

        runCurrent()

        verify(getArtistUseCase).invoke(artistId)
    }

    @Test
    fun `Albums are requested when UI screen starts`() = runTest {
        vm.onAlbumsRequest()

        runCurrent()

        verify(getArtistAlbumsUseCase).invoke(artistId)
    }

}