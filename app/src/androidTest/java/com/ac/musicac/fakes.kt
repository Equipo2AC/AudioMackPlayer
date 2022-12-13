package com.ac.musicac

import arrow.core.right
import com.ac.musicac.data.PermissionChecker
import com.ac.musicac.data.database.dao.AlbumDao
import com.ac.musicac.data.database.dao.ArtistDao
import com.ac.musicac.data.database.dao.AuthenticationDao
import com.ac.musicac.data.database.entity.AlbumEntity
import com.ac.musicac.data.database.entity.ArtistEntity
import com.ac.musicac.data.database.entity.AuthenticationEntity
import com.ac.musicac.data.datasource.AlbumLocalDataSource
import com.ac.musicac.data.datasource.ArtistLocalDataSource
import com.ac.musicac.data.datasource.LocationDataSource
import com.ac.musicac.data.datasource.MusicRemoteDataSource
import com.ac.musicac.data.server.UserResult
import com.ac.musicac.data.server.model.main.AlbumViewResult
import com.ac.musicac.data.server.model.main.ArtistViewResult
import com.ac.musicac.data.server.model.main.SeveralAlbumsResult
import com.ac.musicac.data.server.model.main.SeveralArtistsResult
import com.ac.musicac.data.server.model.releases.AlbumsReleasesResult
import com.ac.musicac.data.server.model.releases.AlbumsResult
import com.ac.musicac.data.server.model.releases.ReleasesResult
import com.ac.musicac.data.server.model.releases.SearchResult
import com.ac.musicac.data.server.service.SpotifyService
import com.ac.musicac.domain.*
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.flowOf
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.runBlocking
import okhttp3.Interceptor
import javax.inject.Inject

class FakeArtistLocalDataSource : ArtistLocalDataSource {

    val inMemoryArtists = MutableStateFlow(SeveralArtist(arrayListOf()))

    override val artists = inMemoryArtists

    override suspend fun isEmpty(): Boolean = artists.value.artists.isEmpty()

    override fun findById(id: Int): Flow<PopularArtist> =
        flowOf(inMemoryArtists.value.artists.first { it.id == id })

    override fun findByArtistId(artistId: String): Flow<PopularArtist> {
        TODO("Not yet implemented")
    }

    override suspend fun save(artists: SeveralArtist): Error? {
        inMemoryArtists.value = artists
        return null
    }

    override suspend fun saveOnly(artist: PopularArtist): Error? {
        val temp = inMemoryArtists.value.artists.toMutableList().apply {
            add(artist)
        }.toList()
        inMemoryArtists.value = SeveralArtist(temp)
        return null
    }

    override suspend fun deleteAll(): Error? {
        inMemoryArtists.value = SeveralArtist(arrayListOf())
        return null
    }
}

class FakeAlbumLocalDataSource : AlbumLocalDataSource {

    val inMemoryAlbums = MutableStateFlow(SeveralAlbums(arrayListOf()))

    override val albums = inMemoryAlbums

    override suspend fun isEmpty(): Boolean = albums.value.albums.isEmpty()

    override fun findById(id: Int): Flow<AlbumView> =
        flowOf(inMemoryAlbums.value.albums.first { it.id == id })

    override fun findByAlbumId(albumId: String): Flow<AlbumView> {
        TODO("Not yet implemented")
    }

    override suspend fun save(albums: SeveralAlbums): Error? {
        inMemoryAlbums.value = albums
        return null
    }

    override suspend fun saveOnly(album: AlbumView): Error? {
        val temp = inMemoryAlbums.value.albums.toMutableList().apply {
            add(album)
        }.toList()
        inMemoryAlbums.value = SeveralAlbums(temp)
        return null
    }

    override suspend fun deleteAll(): Error? {
        inMemoryAlbums.value = SeveralAlbums(arrayListOf())
        return null
    }
}



class FakeLocationDataSource : LocationDataSource {

    val location = "US"

    override suspend fun findLastRegion() = location

}

class FakePermissionChecker : PermissionChecker {
    var permissionGranted = true

    override fun check(permission: PermissionChecker.Permission) = permissionGranted
}


class FakeArtistDao (artists: List<ArtistEntity> = emptyList()) : ArtistDao {

    private val inMemoryArtists = MutableStateFlow(artists)

    private lateinit var findArtistFlow: MutableStateFlow<ArtistEntity>


    override fun getAll(): Flow<List<ArtistEntity>> = inMemoryArtists

    override fun findById(id: Int): Flow<ArtistEntity> {
        findArtistFlow = MutableStateFlow(inMemoryArtists.value.first { it.id == id })
        return findArtistFlow
    }

    override fun findByArtistId(artistId: String): Flow<ArtistEntity> {
        findArtistFlow = MutableStateFlow(inMemoryArtists.value.first { it.artistId == artistId })
        return findArtistFlow
    }

    override suspend fun artistCount(): Int = inMemoryArtists.value.size

    override suspend fun insertArtist(artist: ArtistEntity) {
        inMemoryArtists.update { it }
    }

    override suspend fun insertAllArtist(artist: List<ArtistEntity>) {
        inMemoryArtists.value = artist

        if (::findArtistFlow.isInitialized) {
            artist.firstOrNull() { it.id == findArtistFlow.value.id }
                ?.let { findArtistFlow.value = it }
        }
    }

    override suspend fun updateArtist(artist: ArtistEntity) {
        inMemoryArtists.update { it }
    }

    override suspend fun delete(artist: ArtistEntity) {
        inMemoryArtists.update { it }
    }

    override suspend fun deleteAll() {
        inMemoryArtists.update { it }
    }

}

class FakeAlbumDao (albums: List<AlbumEntity> = emptyList()) : AlbumDao {

    private val inMemoryAlbums = MutableStateFlow(albums)

    private lateinit var findAlbumFlow: MutableStateFlow<AlbumEntity>


    override fun getAll(): Flow<List<AlbumEntity>> = inMemoryAlbums

    override fun findById(id: Int): Flow<AlbumEntity> {
        findAlbumFlow = MutableStateFlow(inMemoryAlbums.value.first { it.id == id })
        return findAlbumFlow
    }

    override fun findByAlbumId(albumId: String): Flow<AlbumEntity> {
        findAlbumFlow = MutableStateFlow(inMemoryAlbums.value.first { it.albumId == albumId })
        return findAlbumFlow
    }

    override suspend fun albumCount(): Int = inMemoryAlbums.value.size

    override suspend fun insertAlbum(album: AlbumEntity) {
        inMemoryAlbums.update { it }
    }

    override suspend fun insertAllAlbums(album: List<AlbumEntity>) {
        inMemoryAlbums.value = album

        if (::findAlbumFlow.isInitialized) {
            album.firstOrNull() { it.id == findAlbumFlow.value.id }
                ?.let { findAlbumFlow.value = it }
        }
    }

    override suspend fun updateAlbum(album: AlbumEntity) {
        inMemoryAlbums.update { it }
    }

    override suspend fun delete(album: AlbumEntity) {
        inMemoryAlbums.update { it }
    }

    override suspend fun deleteAll() {
        inMemoryAlbums.update { it }
    }

}

class FakeAuthenticationDao(token: AuthenticationEntity): AuthenticationDao {

    private val inMemoryToken = MutableStateFlow(token)

    override suspend fun insertToken(token: AuthenticationEntity) = inMemoryToken.update { it }

    override suspend fun tokenCount(): Int {
        TODO("Not yet implemented")
    }

    override suspend fun getToken(): AuthenticationEntity = inMemoryToken.value

    override suspend fun deleteTokens(currentDateTime: Long) = inMemoryToken.update { it }

}

class FakeSpotifyService(
    private val artists: List<ArtistViewResult> = emptyList(),
    private val albums: List<AlbumViewResult> = emptyList()
) : SpotifyService {
    override suspend fun getUser(userId: String): UserResult {
        TODO("Not yet implemented")
    }

    override suspend fun getReleases(
        country: String,
        limit: String,
        offset: String
    ): ReleasesResult {
        TODO("Not yet implemented")
    }

    override suspend fun getReleaseDetail(id: String, market: String): AlbumsReleasesResult {
        TODO("Not yet implemented")
    }

    override suspend fun getSeveralAlbums(id: String) = SeveralAlbumsResult(albums)

    override suspend fun findSearch(
        type: String,
        query: String,
        limit: Int,
        offset: Int
    ): SearchResult {
        TODO("Not yet implemented")
    }

    override suspend fun getArtist(id: String): ArtistViewResult {
        TODO("Not yet implemented")
    }

    override suspend fun getSeveralArtist(id: String) = SeveralArtistsResult(artists)

    override suspend fun getArtistAlbums(id: String, limit: Int, offset: Int): AlbumsResult {
        TODO("Not yet implemented")
    }

}


/*class FakeMusicRemoteDataSource : MusicRemoteDataSource {

    val releases = mutableListOf<Releases>()

    override suspend fun getReleases(region: String, limit: String, offset: String) =
        Releases().right()


    override suspend fun getReleaseDetail(albumId: String, market: String) = Release().right()

    override suspend fun findSearch(type: String, query: String, limit: Int, offset: Int) =
        Search().right()

    override suspend fun getArtist(id: String) = PopularArtist().right()

    override suspend fun getSeveralArtist(ids: String) = SeveralArtist().right()

    override suspend fun getSeveralAlbums(ids: String) = SeveralAlbums().right()

    override suspend fun getArtistAlbums(id: String, limit: Int, offset: Int) =
        Albums().right()

}*/
