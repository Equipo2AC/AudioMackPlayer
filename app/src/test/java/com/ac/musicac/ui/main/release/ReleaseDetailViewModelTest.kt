package com.ac.musicac.ui.main.release

import CoroutinesTestRule
import app.cash.turbine.test
import arrow.core.right
import com.ac.musicac.testshared.Mocks
import com.ac.musicac.ui.main.artist.ArtistViewModel
import com.ac.musicac.ui.main.releases.detail.ReleaseDetailViewModel
import com.ac.musicac.ui.main.releases.list.ReleasesViewModel
import com.ac.musicac.usecases.GetReleaseDetailUseCase
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.toList
import kotlinx.coroutines.launch
import kotlinx.coroutines.test.runCurrent
import kotlinx.coroutines.test.runTest
import org.junit.Assert
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith
import org.mockito.Mock
import org.mockito.junit.MockitoJUnitRunner
import org.mockito.kotlin.verify
import org.mockito.kotlin.whenever

@ExperimentalCoroutinesApi
@RunWith(MockitoJUnitRunner::class)
class ReleaseDetailViewModelTest {

    @get:Rule
    val coroutinesTestRule = CoroutinesTestRule()

    @Mock
    private lateinit var getReleaseDetailUseCase: GetReleaseDetailUseCase

    private lateinit var vm: ReleaseDetailViewModel

    private val albumId = "52iwsT98xCoGgiGntTiR7K"
    private val albumSample = Mocks.mockRelease()


    @Before
    fun setUp() {
        vm = ReleaseDetailViewModel(getReleaseDetailUseCase)
    }

    @Test
    fun `State is updated with release content inmediately`() = runTest {

        whenever(getReleaseDetailUseCase(albumId)).thenReturn(albumSample.right())

        vm.onUiReady(albumId)

        val results = mutableListOf<ReleaseDetailViewModel.UiState>()
        val job = launch { vm.state.toList(results) }
        runCurrent()
        job.cancel()
        Assert.assertEquals(ReleaseDetailViewModel.UiState(loading = false, album = albumSample), results[0])

    }

    @Test
    fun `Progress is shown when release start loading`() = runTest {
        whenever(getReleaseDetailUseCase(albumId)).thenReturn(albumSample.right())

        vm.onUiReady(albumId)

        vm.state.test {
            Assert.assertEquals(ReleaseDetailViewModel.UiState(), awaitItem())
            Assert.assertEquals(
                ReleaseDetailViewModel.UiState(
                    loading = true,
                    album = null,
                    error = null
                ), awaitItem()
            )
            Assert.assertEquals(
                ReleaseDetailViewModel.UiState(
                    loading = false,
                    album = albumSample
                ), awaitItem()
            )
            cancel()
        }
    }

    @Test
    fun `Album are requested when UI screen starts`() = runTest {
        whenever(getReleaseDetailUseCase(albumId)).thenReturn(albumSample.right())

        vm.onUiReady(albumId)

        runCurrent()

        verify(getReleaseDetailUseCase).invoke(albumId)
    }
}