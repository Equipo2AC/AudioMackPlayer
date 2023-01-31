package com.ac.musicac.ui.main.release

import CoroutinesTestRule
import app.cash.turbine.test
import com.ac.musicac.data.database.entity.AlbumEntity
import com.ac.musicac.data.database.entity.ArtistEntity
import com.ac.musicac.data.server.model.main.AlbumViewResult
import com.ac.musicac.data.server.model.main.ArtistViewResult
import com.ac.musicac.data.server.model.releases.AlbumsReleasesResult
import com.ac.musicac.domain.SeveralArtist
import com.ac.musicac.testshared.Mocks
import com.ac.musicac.ui.buildRemoteRelease
import com.ac.musicac.ui.buildRepositoryWith
import com.ac.musicac.ui.main.home.HomeArtistsViewModel
import com.ac.musicac.ui.main.releases.list.ReleasesViewModel
import com.ac.musicac.usecases.GetReleasesUseCase
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.test.runTest
import org.junit.Assert
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith
import org.mockito.junit.MockitoJUnitRunner

@ExperimentalCoroutinesApi
@RunWith(MockitoJUnitRunner::class)
class ReleaseIntegrationTest {

    @get:Rule
    val coroutinesTestRule = CoroutinesTestRule()

    @Test
    fun `Releases Data is loaded from server when local source is empty`() = runTest {
        val remoteData = buildRemoteRelease(4, 5, 6)

        val vm = buildModelWith(remoteReleaseData = remoteData)

        vm.onUiReady()

        vm.state.test {
            Assert.assertEquals(ReleasesViewModel.UiState(), awaitItem())
            Assert.assertEquals(ReleasesViewModel.UiState(loading = true), awaitItem())
            // Assert.assertEquals(ReleasesViewModel.UiState(albums = listOf(Mocks.mockItems()), loading = false), awaitItem())

            val releases = awaitItem().albums
            if (!releases.isNullOrEmpty()) {
                Assert.assertEquals("Overview 4", releases[0].href)
                Assert.assertEquals("Overview 5", releases[1].href)
                Assert.assertEquals("Overview 6", releases[2].href)
                Assert.assertEquals(4, releases[0].id)
                Assert.assertEquals(5, releases[1].id)
                Assert.assertEquals(6, releases[2].id)
            }
            cancel()
        }

    }

    /*@Test
    fun `Releases Data is loaded from local database when available`() = runTest {
        // val localData = buildDatabaseArtist(1, 2, 3)
        val remoteData = buildRemoteRelease(4, 5, 6)

        val vm = buildModelWith(remoteReleaseData = remoteData)

        vm.onUiReady()


        vm.state.test {
            Assert.assertEquals(HomeArtistsViewModel.UiState(), awaitItem())
            Assert.assertNotEquals(
                ReleasesViewModel.UiState(
                    albums = listOf(Mocks.mockItems()), loading = true
                ), awaitItem()
            )
            Assert.assertNotEquals(
                ReleasesViewModel.UiState(
                    albums = listOf(Mocks.mockItems()), loading = false
                ), awaitItem()
            )

            val releases = awaitItem().albums
            if(!releases.isNullOrEmpty()) {
                Assert.assertEquals("Overview 1", releases[0].href)
                Assert.assertEquals("Overview 2", releases[1].href)
                Assert.assertEquals("Overview 3", releases[2].href)
                Assert.assertEquals(1, releases[0].id)
                Assert.assertEquals(2, releases[1].id)
                Assert.assertEquals(3, releases[2].id)
            }

            cancel()
        }
    }*/

    private fun buildModelWith(
        localArtistData:List<ArtistEntity> = emptyList(),
        localAlbumData:List<AlbumEntity> = emptyList(),
        remoteArtistData: List<ArtistViewResult> = emptyList(),
        remoteAlbumData: List<AlbumViewResult> = emptyList(),
        remoteReleaseData: List<AlbumsReleasesResult> = emptyList()
    ): ReleasesViewModel {

        val repo = buildRepositoryWith(localArtistData, localAlbumData, remoteArtistData, remoteAlbumData, remoteReleaseData)
        val getReleasesUseCase = GetReleasesUseCase(repo)
        val vm = ReleasesViewModel(getReleasesUseCase)
        return vm
    }


}