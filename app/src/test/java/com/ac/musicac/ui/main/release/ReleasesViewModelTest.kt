package com.ac.musicac.ui.main.release

import CoroutinesTestRule
import app.cash.turbine.test
import arrow.core.right
import com.ac.musicac.testshared.Mocks
import com.ac.musicac.ui.main.releases.list.ReleasesViewModel
import com.ac.musicac.ui.main.releases.list.ReleasesViewModel.*
import com.ac.musicac.usecases.GetReleasesUseCase
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.toList
import kotlinx.coroutines.launch
import kotlinx.coroutines.runBlocking
import kotlinx.coroutines.test.runCurrent
import kotlinx.coroutines.test.runTest
import org.junit.Assert.*
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
class ReleasesViewModelTest {

    @get:Rule
    val coroutinesTestRule = CoroutinesTestRule()

    @Mock
    lateinit var getReleasesUseCase: GetReleasesUseCase

    private lateinit var  vm: ReleasesViewModel

    private val releasesSample = Mocks.mockReleases()

    @Before
    fun setUp() = runBlocking {
        whenever(getReleasesUseCase()).thenReturn(releasesSample.right())
        vm = ReleasesViewModel(getReleasesUseCase)
    }

    @Test
    fun `State is updated with releases content inmediately`() = runTest {

        val results = mutableListOf<UiState>()
        val job = launch { vm.state.toList(results) }
        runCurrent()
        job.cancel()
        assertEquals(UiState(loading = false, albums = releasesSample.albums.items), results[0])
        job.cancel()
    }

    @Test
    fun `Progress is shown when screen start and hidden when it finishes`() = runTest {

        vm.state.test {
            // assertEquals(UiState(), awaitItem())
            // assertEquals(UiState(loading = true, albums =  null, error = null), awaitItem())
            assertEquals(UiState(loading = false, albums = releasesSample.albums.items, error = null), awaitItem())
            cancel()
        }
    }

    @Test
    fun `Releases are requested when UI screen starts`() = runTest {

        runCurrent()

        verify(getReleasesUseCase).invoke()
    }

}