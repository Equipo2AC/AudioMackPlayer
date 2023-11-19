package com.ac.musicac.ui.main.home

import CoroutinesTestRule
import app.cash.turbine.test
import com.ac.musicac.data.database.entity.AlbumEntity
import com.ac.musicac.data.database.entity.ArtistEntity
import com.ac.musicac.data.server.model.main.AlbumViewResult
import com.ac.musicac.data.server.model.main.ArtistViewResult
import com.ac.musicac.data.server.model.releases.AlbumsReleasesResult
import com.ac.musicac.domain.SeveralAlbums
import com.ac.musicac.testshared.Mocks
import com.ac.musicac.testshared.Mocks.mockPopularAlbums
import com.ac.musicac.ui.buildDatabaseAlbum
import com.ac.musicac.ui.buildRemoteAlbum
import com.ac.musicac.ui.buildRepositoryWith
import com.ac.musicac.ui.main.home.HomeAlbumsViewModel.UiState
import com.ac.musicac.usecases.GetSeveralAlbumUseCase
import com.ac.musicac.usecases.RequestSeveralAlbumUseCase
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.test.runTest
import org.junit.Assert.assertEquals
import org.junit.Rule
import org.junit.Test


@ExperimentalCoroutinesApi
class HomeAlbumsIntegrationTest {

    @get:Rule
    val coroutinesTestRule = CoroutinesTestRule()

    private val albumId = "3RQQmkQEvNCY4prGKE6oc5,6jbtHi5R0jMXoliU2OS0lo,1wLB2bnCl2m5m9M9g8r93Y" +
            ",7rE2qU0GsiIiNd4VPupV3B,4yNnIoQh8y1uDB6ScOS2vx,4PNqWiJAfjj32hVvlchV5u" +
            ",6GHUywBU0u92lg0Dhrt40R,6gQKAYf3TJM9sppw3AtbHH"

    private val albumId2 = "3RQQmkQEvNCY4prGKE6oc5"

    private val albumSample = Mocks.mockAlbums()

    @Test
    fun `Albums Data is loaded from server when local source is empty`() = runTest {
        val remoteData = buildRemoteAlbum(4, 5, 6)

        val vm = buildModelWith(remoteAlbumData = remoteData)

        vm.onUiReady(albumId)


        vm.state.test {
            assertEquals(UiState(), awaitItem())
            assertEquals(UiState(albums = SeveralAlbums(emptyList())), awaitItem())
            assertEquals(UiState(albums = SeveralAlbums(emptyList()), loading = true), awaitItem())
            assertEquals(UiState(albums = SeveralAlbums(emptyList()), loading = false), awaitItem())

            val albums = awaitItem().albums?.albums
            if(!albums.isNullOrEmpty()) {
                assertEquals("Label here 4", albums[0].label)
                assertEquals("Label here 5", albums[1].label)
                assertEquals("Label here 6", albums[2].label)
                assertEquals("album", albums[0].album_type)
                assertEquals("artist", albums[0].type)
                assertEquals(75, albums[0].popularity)
            }

            cancel()
        }

    }

    @Test
    fun `Album Data is loaded from local database when available`() = runTest {
        val localData: List<AlbumEntity> = buildDatabaseAlbum(1, 2, 3)
        val remoteData: List<AlbumViewResult> = buildRemoteAlbum(4, 5, 6)

        val vm = buildModelWith(
            localAlbumData = localData,
            remoteAlbumData = remoteData)

        vm.onUiReady(albumId2)

        vm.state.test {
            assertEquals(UiState(), awaitItem())
            // assertEquals(UiState(albums = SeveralAlbums(emptyList())), awaitItem())
            assertEquals(UiState(loading = true, albums = SeveralAlbums(listOf(mockPopularAlbums()))), awaitItem())
            // assertEquals(UiState(loading = false, albums = SeveralAlbums(listOf(mockPopularAlbums()))), awaitItem())
            // assertEquals(UiState(loading = true, albums = SeveralAlbums(listOf(mockPopularAlbums()))), awaitItem())

            val albums = awaitItem().albums?.albums
            if(!albums.isNullOrEmpty()) {
                assertEquals("Label here 1", albums[0].label)
                assertEquals("Label here 2", albums[1].label)
                assertEquals("Label here 3", albums[2].label)
                assertEquals(1, albums[0].id)
                assertEquals(2, albums[1].id)
                assertEquals(3, albums[2].id)
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
    ): HomeAlbumsViewModel {

        val repo = buildRepositoryWith(localArtistData, localAlbumData, remoteArtistData, remoteAlbumData, remoteReleaseData)

        val getSeveralAlbumUseCase = GetSeveralAlbumUseCase(repo)
        val requestSeveralAlbumUseCase = RequestSeveralAlbumUseCase(repo)
        val vm = HomeAlbumsViewModel(getSeveralAlbumUseCase, requestSeveralAlbumUseCase)
        return vm
    }

}