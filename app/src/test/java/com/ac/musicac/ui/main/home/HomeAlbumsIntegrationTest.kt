package com.ac.musicac.ui.main.home

import CoroutinesTestRule
import app.cash.turbine.test
import com.ac.musicac.data.database.entity.AlbumEntity
import com.ac.musicac.data.database.entity.ArtistEntity
import com.ac.musicac.data.server.model.main.AlbumViewResult
import com.ac.musicac.data.server.model.main.ArtistViewResult
import com.ac.musicac.domain.SeveralArtist
import com.ac.musicac.ui.buildRemoteAlbum
import com.ac.musicac.ui.buildRemoteArtist
import com.ac.musicac.ui.buildRepositoryWith
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

        val vm = buildModelWith()

        vm.onUiReady(albumId)


        vm.state.test {
            Assert.assertEquals(HomeArtistsViewModel.UiState(), awaitItem())
            Assert.assertEquals(HomeArtistsViewModel.UiState(artists = SeveralArtist(emptyList()), loading = false), awaitItem())

            val albums = awaitItem().albums!!.albums
            Assert.assertEquals("Overview 4", albums[0].href)
            Assert.assertEquals("Overview 5", albums[1].href)
            Assert.assertEquals("Overview 6", albums[2].href)

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