package com.ac.musicac.ui.main.search

import CoroutinesTestRule
import app.cash.turbine.test
import com.ac.musicac.data.database.entity.AlbumEntity
import com.ac.musicac.data.database.entity.ArtistEntity
import com.ac.musicac.data.server.model.main.AlbumViewResult
import com.ac.musicac.data.server.model.main.ArtistViewResult
import com.ac.musicac.data.server.model.releases.AlbumsReleasesResult
import com.ac.musicac.domain.Type
import com.ac.musicac.testshared.Mocks
import com.ac.musicac.ui.buildRemoteAlbum
import com.ac.musicac.ui.buildRemoteArtist
import com.ac.musicac.ui.buildRepositoryWith
import com.ac.musicac.usecases.SearchUseCase
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.test.runTest
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.mockito.Mock

@ExperimentalCoroutinesApi
class SearchIntegrationTest {

    @get:Rule
    val coroutinesTestRule = CoroutinesTestRule()

    @Mock
    private lateinit var searchUseCase: SearchUseCase

    private lateinit var vmTest: SearchViewModel

    private val artistSample = Mocks.mockPopularArtist()
    private val albumSample = Mocks.mockAlbums()
    // private val searchArtistSample = Mocks.mockSearchArtist()
    // private val searchAlbumSample = Mocks.mockSearchAlbum()

    @Before
    fun setUp() {
        val remoteArtist = buildRemoteArtist(4)
        val remoteAlbum = buildRemoteAlbum(5)
        vmTest = buildModelWith(remoteArtistData = remoteArtist, remoteAlbumData = remoteAlbum)
    }

    @Test
    fun `Search Fragment shows Artist`() = runTest {

        // GIVEN
        // whenever(searchUseCase(any(), any())).thenReturn(Mocks.mockSearchArtist().right())

        // WHEN
        vmTest.onQueryTextChange("test")

        // THEN
        vmTest.state.test {
            val state = awaitItem()
            assert(state.search != null)
            cancel()
        }
    }

    @Test
    fun `Search Fragment shows Albums`() = runTest {

        // GIVEN
        // whenever(searchUseCase(any(), any())).thenReturn(Mocks.mockSearchAlbum().right())

        // WHEN
        vmTest.onQueryTextChange("test")

        // THEN
        vmTest.state.test {
            // val state = awaitItem()
            val albums = awaitItem().search?.firstOrNull()
            assert(albums != null)
            // assert(state.search != null)
            cancel()
        }

    }

    @Test
    fun `Search Fragment change the type for Albums correctly`() = runTest {

        // WHEN
        vmTest.onChangeType(Type.ALBUM)

        // THEN
        vmTest.state.test {
            val state = awaitItem()
            assert(state.type ==  Type.ALBUM)
            // assertEquals(state.type, Type.ALBUM)
            cancel()
        }

    }

    @Test
    fun `Search Fragment change the type for Artists correctly`() = runTest {

        // WHEN
        vmTest.onChangeType(Type.ARTIST)

        // THEN
        vmTest.state.test {
            val state = awaitItem()
            assert(state.type ==  Type.ARTIST)
            // assertEquals(state.type, Type.ARTIST)
            cancel()
        }

    }

    private fun buildModelWith(
        localArtistData:List<ArtistEntity> = emptyList(),
        localAlbumData:List<AlbumEntity> = emptyList(),
        remoteArtistData: List<ArtistViewResult> = emptyList(),
        remoteAlbumData: List<AlbumViewResult> = emptyList(),
        remoteReleaseData: List<AlbumsReleasesResult> = emptyList()
    ): SearchViewModel {

        val repo = buildRepositoryWith(localArtistData, localAlbumData, remoteArtistData, remoteAlbumData, remoteReleaseData)
        searchUseCase = SearchUseCase(repo)
        val vm = SearchViewModel(searchUseCase)
        return vm
    }


}