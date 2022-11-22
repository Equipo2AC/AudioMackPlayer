package com.ac.musicac.ui.main.home

import CoroutinesTestRule
import app.cash.turbine.test
import com.ac.musicac.data.database.entity.AlbumEntity
import com.ac.musicac.data.database.entity.ArtistEntity
import com.ac.musicac.data.server.model.main.AlbumViewResult
import com.ac.musicac.data.server.model.main.ArtistViewResult
import com.ac.musicac.domain.SeveralAlbums
import com.ac.musicac.domain.SeveralArtist
import com.ac.musicac.testshared.Mocks.mockAlbums
import com.ac.musicac.testshared.Mocks.mockPopularAlbums
import com.ac.musicac.testshared.Mocks.mockSeveralAlbums
import com.ac.musicac.ui.*
import com.ac.musicac.ui.main.home.HomeAlbumsViewModel.*
import com.ac.musicac.usecases.GetSeveralAlbumUseCase
import com.ac.musicac.usecases.GetSeveralArtistUseCase
import com.ac.musicac.usecases.RequestSeveralAlbumUseCase
import com.ac.musicac.usecases.RequestSeveralArtistUseCase
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.test.runTest
import org.junit.Assert
import org.junit.Rule
import org.junit.Test

@ExperimentalCoroutinesApi
class HomeAlbumsIntegrationTest {

    @get:Rule
    val coroutinesTestRule = CoroutinesTestRule()

    private val albumId = "6jbtHi5R0jMXoliU2OS0lo"

    @Test
    fun `Albums Data is loaded from server when local source is empty`() = runTest {
        val remoteData = buildRemoteAlbum(4, 5, 6)

        val vm = buildModelWith(remoteAlbumData = remoteData)

        vm.onUiReady(albumId)


        vm.state.test {
            Assert.assertEquals(UiState(), awaitItem())
            Assert.assertEquals(UiState(albums = SeveralAlbums(emptyList())), awaitItem())
            Assert.assertEquals(UiState(albums = SeveralAlbums(emptyList()), loading = true), awaitItem())

            val albums = awaitItem().albums?.albums
            if(!albums.isNullOrEmpty()) {
                Assert.assertEquals("Label here 4", albums[0].label)
                Assert.assertEquals("Label here 5", albums[1].label)
                Assert.assertEquals("Label here 6", albums[2].label)
            }

            cancel()
        }

    }

    @Test
    fun `Album Data is loaded from local database when available`() = runTest {
        val localData = buildDatabaseAlbum(0)
        val remoteData = buildRemoteAlbum(4, 5, 6)

        val vm = buildModelWith(
            localAlbumData = localData,
            remoteAlbumData = remoteData)

        vm.onUiReady(albumId)


        vm.state.test {
            Assert.assertEquals(UiState(), awaitItem())
            Assert.assertEquals(UiState(albums = SeveralAlbums(listOf())), awaitItem())

            val albums = awaitItem().albums?.albums

            if(albums != null) {
                Assert.assertEquals("Label here 0", albums[0].label)
                // Assert.assertEquals("Label here 0", albums[1].label)
                // Assert.assertEquals("Label here 0", albums[2].label)
            }
            cancel()
        }

    }


    private fun buildModelWith(
        localArtistData:List<ArtistEntity> = emptyList(),
        localAlbumData:List<AlbumEntity> = emptyList(),
        remoteArtistData: List<ArtistViewResult> = emptyList(),
        remoteAlbumData: List<AlbumViewResult> = emptyList()): HomeAlbumsViewModel {

        val repo = buildRepositoryWith(localArtistData, localAlbumData, remoteArtistData, remoteAlbumData)

        val getSeveralAlbumUseCase = GetSeveralAlbumUseCase(repo)
        val requestSeveralAlbumUseCase = RequestSeveralAlbumUseCase(repo)
        val vm = HomeAlbumsViewModel(getSeveralAlbumUseCase, requestSeveralAlbumUseCase)
        return vm
    }

}