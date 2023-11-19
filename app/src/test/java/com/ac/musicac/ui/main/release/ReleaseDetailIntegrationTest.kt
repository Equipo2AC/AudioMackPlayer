package com.ac.musicac.ui.main.release

import CoroutinesTestRule
import app.cash.turbine.test
import com.ac.musicac.data.database.entity.AlbumEntity
import com.ac.musicac.data.database.entity.ArtistEntity
import com.ac.musicac.data.server.model.main.AlbumViewResult
import com.ac.musicac.data.server.model.main.ArtistViewResult
import com.ac.musicac.data.server.model.releases.AlbumsReleasesResult
import com.ac.musicac.ui.buildLocalRelease
import com.ac.musicac.ui.buildRemoteRelease
import com.ac.musicac.ui.buildRepositoryWith
import com.ac.musicac.ui.main.releases.detail.ReleaseDetailViewModel
import com.ac.musicac.usecases.GetReleaseDetailUseCase
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.test.runTest
import org.junit.Assert
import org.junit.Rule
import org.junit.Test

@ExperimentalCoroutinesApi
class ReleaseDetailIntegrationTest {

    @get:Rule
    val coroutinesTestRule = CoroutinesTestRule()

    private val albumId = "52iwsT98xCoGgiGntTiR7K"

    @Test
    fun `Releases detail Data is loaded from server when local source is empty`() = runTest {
        val remoteData = buildRemoteRelease(7)

        val vm = buildModelWith(remoteReleaseData = remoteData)

        vm.onUiReady(albumId)

        vm.state.test {
            Assert.assertEquals(ReleaseDetailViewModel.UiState(), awaitItem())
            Assert.assertEquals(ReleaseDetailViewModel.UiState(loading = true), awaitItem())
            // Assert.assertEquals(ReleaseDetailViewModel.UiState(album = Mocks.mockRelease(), loading = false), awaitItem())

            val releases = awaitItem().album
            if (releases != null) {
                Assert.assertEquals("52iwsT98xCoGgiGntTiR7K", releases.id)
                Assert.assertEquals("single", releases.albumType)
                Assert.assertEquals("Playa Del Inglés", releases.name)
                Assert.assertEquals("WM Spain", releases.label)
            }
            cancel()
        }

    }

    @Test
    fun `Releases detail Data is loaded from local database when available`() = runTest {

        val remoteData = buildRemoteRelease(7)
        val localData = buildLocalRelease(4, 5, 6)

        val vm = buildModelWith(
            remoteReleaseData = remoteData)


        vm.onUiReady(albumId)


        vm.state.test {
            Assert.assertEquals(ReleaseDetailViewModel.UiState(), awaitItem())
            Assert.assertEquals(ReleaseDetailViewModel.UiState(loading = true), awaitItem())
            // assertNotEquals(UiState(error = any(), loading = false), awaitItem())

            val albums = awaitItem().album

            Assert.assertEquals("WM Spain", albums?.label)
            Assert.assertEquals("single", albums?.albumType)
            Assert.assertEquals("52iwsT98xCoGgiGntTiR7K", albums?.id)
            Assert.assertEquals("Playa Del Inglés", albums?.name)
            Assert.assertEquals(82, albums?.popularity)

            cancel()
        }

    }

    private fun buildModelWith(
        localArtistData:List<ArtistEntity> = emptyList(),
        localAlbumData:List<AlbumEntity> = emptyList(),
        remoteArtistData: List<ArtistViewResult> = emptyList(),
        remoteAlbumData: List<AlbumViewResult> = emptyList(),
        remoteReleaseData: List<AlbumsReleasesResult> = emptyList()
    ): ReleaseDetailViewModel {

        val repo = buildRepositoryWith(localArtistData, localAlbumData, remoteArtistData, remoteAlbumData, remoteReleaseData)
        val getReleaseDetailUseCase = GetReleaseDetailUseCase(repo)
        val vm = ReleaseDetailViewModel(getReleaseDetailUseCase)
        return vm
    }

}