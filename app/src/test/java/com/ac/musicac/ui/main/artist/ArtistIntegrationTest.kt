package com.ac.musicac.ui.main.artist

import CoroutinesTestRule
import app.cash.turbine.test
import arrow.core.right
import com.ac.musicac.data.database.entity.AlbumEntity
import com.ac.musicac.data.database.entity.ArtistEntity
import com.ac.musicac.data.server.model.main.AlbumViewResult
import com.ac.musicac.data.server.model.main.ArtistViewResult
import com.ac.musicac.data.server.model.releases.AlbumsReleasesResult
import com.ac.musicac.testshared.Mocks
import com.ac.musicac.ui.buildRemoteAlbum
import com.ac.musicac.ui.buildRemoteArtist
import com.ac.musicac.ui.buildRepositoryWith
import com.ac.musicac.ui.main.artist.ArtistViewModel.UiState
import com.ac.musicac.usecases.GetArtistAlbumsUseCase
import com.ac.musicac.usecases.GetArtistUseCase
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.toList
import kotlinx.coroutines.launch
import kotlinx.coroutines.test.runCurrent
import kotlinx.coroutines.test.runTest
import org.junit.Assert.assertEquals
import org.junit.Assert.assertNotEquals
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith
import org.mockito.Mock
import org.mockito.junit.MockitoJUnitRunner
import org.mockito.kotlin.any
import org.mockito.kotlin.whenever

@ExperimentalCoroutinesApi
@RunWith(MockitoJUnitRunner::class)
class ArtistIntegrationTest {

    @get:Rule
    val coroutinesTestRule = CoroutinesTestRule()

    @Mock
    private lateinit var getArtistUseCase: GetArtistUseCase

    @Mock
    private lateinit var getArtistAlbumsUseCase: GetArtistAlbumsUseCase

    private lateinit var vmTest: ArtistViewModel

    private val artistId = "7ltDVBr6mKbRvohxheJ9h1"
    private val artistSample = Mocks.mockPopularArtist()
    // private val albumSample = Mocks.mockAlbums()

    @Before
    fun setUp() {
        val remoteArtist = buildRemoteArtist(4)
        val remoteAlbum = buildRemoteAlbum(5)
        vmTest = buildModelWith(remoteArtistData = remoteArtist, remoteAlbumData = remoteAlbum)
    }


    @Test
    fun `Artist Data is loaded from server always`() = runTest {
        // val remoteArtist = buildRemoteArtist(4)

        // whenever(getArtistUseCase(any())).thenReturn(artistSample.right())

        // vmTest = buildModelWith(remoteArtistData = remoteArtist)

        vmTest.onUiReady()

        val results = mutableListOf<UiState>()
        val job = launch { vmTest.state.toList(results) }
        runCurrent()
        job.cancel()
        assertEquals(UiState(loading = true), results[0])


        /*vmTest.state.test {
            assertEquals(UiState(), awaitItem())
            assertEquals(UiState(loading = true), awaitItem())
            // assertEquals(UiState(loading = false, artist = artistSample), awaitItem())
            // assertEquals(UiState(loading = false), awaitItem())

            val artist = awaitItem().artist

            assertEquals("https://api.spotify.com/v1/artists/7ltDVBr6mKbRvohxheJ9h1", artist?.href)
            assertEquals("7ltDVBr6mKbRvohxheJ9h1", artist?.artistId)
            assertEquals(0, artist?.id)
            assertEquals("ROSALÍA", artist?.name)


            cancel()
        }*/

    }

    @Test
    fun `Top Albums of artist Data is loaded from server always`() = runTest {
        // val remoteAlbum = buildRemoteAlbum(4)

        // whenever(getArtistAlbumsUseCase(artistId)).thenReturn(albumSample.right())

        // vmTest = buildModelWith(remoteAlbumData = remoteAlbum)

        vmTest.onAlbumsRequest()

        val results = mutableListOf<UiState>()
        val job = launch { vmTest.state.toList(results) }
        runCurrent()
        job.cancel()
        // assertEquals(UiState(artist = artistSample), results[0])
        assertEquals(UiState(loading = true), results[0])


        /*vmTest.state.test {
            assertEquals(UiState(), awaitItem())
            assertEquals(UiState(loading = true), awaitItem())
            // assertNotEquals(UiState(error = any(), loading = false), awaitItem())

            val artist = awaitItem().artist

            assertEquals("Overview 4", artist?.href)
            assertEquals("7ltDVBr6mKbRvohxheJ9h1", artist?.artistId)
            assertEquals(4, artist?.id)
            assertEquals("Artist Name", artist?.name)


            cancel()
        }*/

    }

    private fun buildModelWith(
        localArtistData:List<ArtistEntity> = emptyList(),
        localAlbumData:List<AlbumEntity> = emptyList(),
        remoteArtistData: List<ArtistViewResult> = emptyList(),
        remoteAlbumData: List<AlbumViewResult> = emptyList(),
        remoteReleaseData: List<AlbumsReleasesResult> = emptyList()
    ): ArtistViewModel {

        val repo = buildRepositoryWith(localArtistData, localAlbumData, remoteArtistData, remoteAlbumData, remoteReleaseData)

        getArtistUseCase = GetArtistUseCase(repo)
        getArtistAlbumsUseCase = GetArtistAlbumsUseCase(repo)
        val vm = ArtistViewModel(artistId, getArtistUseCase, getArtistAlbumsUseCase)
        return vm
    }
}