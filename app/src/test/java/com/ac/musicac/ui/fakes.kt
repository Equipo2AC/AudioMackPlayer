package com.ac.musicac.ui

import com.ac.musicac.data.datasource.AlbumLocalDataSource
import com.ac.musicac.data.datasource.ArtistLocalDataSource
import com.ac.musicac.data.datasource.LocationDataSource
import com.ac.musicac.domain.*
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.flowOf

class FakeArtistLocalDataSource : ArtistLocalDataSource {

    val inMemoryArtists = MutableStateFlow(SeveralArtist(arrayListOf()))

    override val artists = inMemoryArtists

    override suspend fun isEmpty(): Boolean = artists.value.artists.isEmpty()

    override fun findById(id: Int): Flow<PopularArtist> =
        flowOf(inMemoryArtists.value.artists.first { it.id == id })

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
