package com.ac.musicac.ui.main.release

import CoroutinesTestRule
import app.cash.turbine.test
import com.ac.musicac.data.database.entity.AlbumEntity
import com.ac.musicac.data.database.entity.ArtistEntity
import com.ac.musicac.data.server.model.main.AlbumViewResult
import com.ac.musicac.data.server.model.main.ArtistViewResult
import com.ac.musicac.data.server.model.releases.AlbumsReleasesResult
import com.ac.musicac.testshared.Mocks
import com.ac.musicac.ui.buildLocalRelease
import com.ac.musicac.ui.buildRemoteRelease
import com.ac.musicac.ui.buildRepositoryWith
import com.ac.musicac.ui.main.releases.list.ReleasesViewModel
import com.ac.musicac.usecases.GetReleasesUseCase
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.test.runTest
import org.junit.Assert
import org.junit.Rule
import org.junit.Test

@ExperimentalCoroutinesApi
class ReleaseIntegrationTest {

    @get:Rule
    val coroutinesTestRule = CoroutinesTestRule()

    private val releasesSample = Mocks.mockReleases()
    private val itemsSample = Mocks.mockItems()

    @Test
    fun `Releases Data is loaded from server when local source is empty`() = runTest {

        val remoteData = buildRemoteRelease(4, 5, 6)

        val vm = buildModelWith(remoteReleaseData = remoteData)

        vm.onUiReady()

        vm.state.test {
            Assert.assertEquals(ReleasesViewModel.UiState(), awaitItem())
            Assert.assertEquals(ReleasesViewModel.UiState(loading = true), awaitItem())
            Assert.assertEquals(ReleasesViewModel.UiState(loading = false, albums = listOf(itemsSample)), awaitItem())
            Assert.assertEquals(ReleasesViewModel.UiState(loading = true, albums = listOf(itemsSample)), awaitItem())

            val releases = awaitItem().albums
            if (!releases.isNullOrEmpty()) {
                Assert.assertEquals("album", releases[0].albumType)
                Assert.assertEquals("artist", releases[0].type)
                Assert.assertEquals("Estopa", releases[0].name)
                Assert.assertEquals("52iwsT98xCoGgiGntTiR7K", releases[0].id)
                Assert.assertEquals("5ZqnEfVdEGmoPxtELhN7ai", releases[0].itemId)
                Assert.assertEquals("1999-10-18", releases[0].releaseDate)
            }
            cancel()
        }

    }

    @Test
    fun `Releases Data is loaded from local database when available`() = runTest {
        val localData = buildLocalRelease(1, 2, 3)
        val remoteData = buildRemoteRelease(4, 5, 6)

        val vm = buildModelWith(remoteReleaseData = remoteData)

        vm.onUiReady()


        vm.state.test {
            Assert.assertEquals(ReleasesViewModel.UiState(), awaitItem())
            Assert.assertEquals(ReleasesViewModel.UiState(loading = true), awaitItem())
            Assert.assertEquals(ReleasesViewModel.UiState(loading = false, albums = listOf(itemsSample)), awaitItem())
            Assert.assertEquals(ReleasesViewModel.UiState(loading = true, albums = listOf(itemsSample)), awaitItem())
            //Assert.assertEquals(ReleasesViewModel.UiState(albums = listOf(Mocks.mockItems()), loading = false), awaitItem())

            val releases = awaitItem().albums
            if(!releases.isNullOrEmpty()) {
                Assert.assertEquals("album", releases[0].albumType)
                Assert.assertEquals("artist", releases[0].type)
                Assert.assertEquals("Estopa", releases[0].name)
                Assert.assertEquals("52iwsT98xCoGgiGntTiR7K", releases[0].id)
                Assert.assertEquals("5ZqnEfVdEGmoPxtELhN7ai", releases[0].itemId)
                Assert.assertEquals("1999-10-18", releases[0].releaseDate)
            }

            cancel()
        }
    }

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