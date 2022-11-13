package com.ac.musicac.ui.main.home

import CoroutinesTestRule
import app.cash.turbine.test
import com.ac.musicac.data.database.entity.AlbumEntity
import com.ac.musicac.data.database.entity.ArtistEntity
import com.ac.musicac.data.server.model.main.ArtistViewResult
import com.ac.musicac.domain.SeveralArtist
import com.ac.musicac.ui.buildRepositoryWith
import com.ac.musicac.ui.main.home.HomeArtistsViewModel.*
import com.ac.musicac.usecases.GetSeveralArtistUseCase
import com.ac.musicac.usecases.RequestSeveralArtistUseCase
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.test.runTest
import org.junit.Assert
import org.junit.Assert.assertEquals
import org.junit.Rule
import org.junit.Test

@ExperimentalCoroutinesApi
class HomeArtistsIntegrationTest {

    @get:Rule
    val coroutinesTestRule = CoroutinesTestRule()

    private val artistId = "7ltDVBr6mKbRvohxheJ9h1"

    @Test
    fun `Data is loaded from server when local source is empty`() = runTest {
        val remoteData = buildRemoteArtists(4, 5, 6)

        val vm = buildModelWith()

        vm.onUiReady(artistId)


        vm.state.test {
            assertEquals(UiState(), awaitItem())
            assertEquals(UiState(artists = SeveralArtist(emptyList()), loading = false), awaitItem())

            val artists = awaitItem().artists!!.artists
            assertEquals("Overview 4", artists[0].id)
            assertEquals("Overview 5", artists[1].id)
            assertEquals("Overview 6", artists[2].id)

            cancel()
        }

    }

    private fun buildModelWith(
        localArtistData:List<ArtistEntity> = emptyList(),
        localAlbumData:List<AlbumEntity> = emptyList(),
        remoteData: List<ArtistViewResult> = emptyList()): HomeArtistsViewModel {

        val repo = buildRepositoryWith(localArtistData, localAlbumData, remoteData)

        val getSeveralArtistUseCase = GetSeveralArtistUseCase(repo)
        val requestSeveralArtistUseCase = RequestSeveralArtistUseCase(repo)
        val vm = HomeArtistsViewModel(getSeveralArtistUseCase, requestSeveralArtistUseCase)
        return vm
    }
}