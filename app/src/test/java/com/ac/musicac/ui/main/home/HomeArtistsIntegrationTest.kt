package com.ac.musicac.ui.main.home

import CoroutinesTestRule
import app.cash.turbine.test
import com.ac.musicac.data.database.entity.AlbumEntity
import com.ac.musicac.data.database.entity.ArtistEntity
import com.ac.musicac.data.server.model.main.AlbumViewResult
import com.ac.musicac.data.server.model.main.ArtistViewResult
import com.ac.musicac.data.server.model.releases.AlbumsReleasesResult
import com.ac.musicac.domain.SeveralArtist
import com.ac.musicac.testshared.Mocks
import com.ac.musicac.ui.buildDatabaseArtist
import com.ac.musicac.ui.buildRemoteArtist
import com.ac.musicac.ui.buildRepositoryWith
import com.ac.musicac.ui.main.home.HomeArtistsViewModel.UiState
import com.ac.musicac.usecases.GetSeveralArtistUseCase
import com.ac.musicac.usecases.RequestSeveralArtistUseCase
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.test.runTest
import org.junit.Assert.assertEquals
import org.junit.Assert.assertNotEquals
import org.junit.Rule
import org.junit.Test

@ExperimentalCoroutinesApi
class HomeArtistsIntegrationTest {

    @get:Rule
    val coroutinesTestRule = CoroutinesTestRule()

    private val artistId = "7ltDVBr6mKbRvohxheJ9h1"

    @Test
    fun `Artist Data is loaded from server when local source is empty`() = runTest {
        val remoteData = buildRemoteArtist(4, 5, 6)

        val vm = buildModelWith(remoteArtistData = remoteData)

        vm.onUiReady(artistId)


        vm.state.test {
            assertEquals(UiState(), awaitItem())
            assertEquals(UiState(artists = SeveralArtist(emptyList())), awaitItem())
            assertEquals(UiState(artists = SeveralArtist(emptyList()), loading = true), awaitItem())
            assertEquals(UiState(artists = SeveralArtist(emptyList()), loading = false), awaitItem())

            val artists = awaitItem().artists?.artists
            if (!artists.isNullOrEmpty()) {
                assertEquals("Overview 4", artists[0].href)
                assertEquals("Overview 5", artists[1].href)
                assertEquals("Overview 6", artists[2].href)
                assertEquals("7ltDVBr6mKbRvohxheJ9h1", artists[0].artistId)
                assertEquals("artist", artists[0].type)
                assertEquals(75, artists[0].popularity)
                assertEquals(0, artists[0].id)
                assertEquals(0, artists[1].id)
                assertEquals(0, artists[2].id)
            }
            cancel()
        }

    }

    @Test
    fun `Artist Data is loaded from local database when available`() = runTest {
        val localData = buildDatabaseArtist(1, 2, 3)
        val remoteData = buildRemoteArtist(4, 5, 6)

        val vm = buildModelWith(
            localArtistData = localData,
            remoteArtistData = remoteData)

        vm.onUiReady(artistId)


        vm.state.test {
            assertEquals(UiState(), awaitItem())
            assertNotEquals(UiState(loading = false, artists = SeveralArtist(listOf(Mocks.mockPopularArtist()))), awaitItem())
            assertNotEquals(UiState(loading = true, artists = SeveralArtist(listOf(Mocks.mockPopularArtist()))), awaitItem())

            val artists = awaitItem().artists?.artists
            if(!artists.isNullOrEmpty()) {
                assertEquals("Overview 1", artists[0].href)
                assertEquals("Overview 2", artists[1].href)
                assertEquals("Overview 3", artists[2].href)
                assertEquals(1, artists[0].id)
                assertEquals(2, artists[1].id)
                assertEquals(3, artists[2].id)
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
    ): HomeArtistsViewModel {

        val repo = buildRepositoryWith(localArtistData, localAlbumData, remoteArtistData, remoteAlbumData, remoteReleaseData)

        val getSeveralArtistUseCase = GetSeveralArtistUseCase(repo)
        val requestSeveralArtistUseCase = RequestSeveralArtistUseCase(repo)
        val vm = HomeArtistsViewModel(getSeveralArtistUseCase, requestSeveralArtistUseCase)
        return vm
    }
}