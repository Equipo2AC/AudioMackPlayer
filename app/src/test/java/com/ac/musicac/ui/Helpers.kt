package com.ac.musicac.ui

import androidx.room.Room
import com.ac.musicac.data.Constants
import com.ac.musicac.data.RegionRepository
import com.ac.musicac.data.database.MusicAcDatabase
import com.ac.musicac.data.database.dao.AuthenticationDao
import com.ac.musicac.data.database.datasource.AlbumRoomDataSource
import com.ac.musicac.data.database.datasource.ArtistRoomDataSource
import com.ac.musicac.data.database.entity.AlbumEntity
import com.ac.musicac.data.database.entity.ArtistEntity
import com.ac.musicac.data.repository.MusicRepository
import com.ac.musicac.data.server.APIService
import com.ac.musicac.data.server.datasource.SpotifyDataSource
import com.ac.musicac.data.server.interceptor.AuthorizationHeader
import com.ac.musicac.data.server.interceptor.TokenHeader
import com.ac.musicac.data.server.model.main.AlbumViewResult
import com.ac.musicac.data.server.model.main.ArtistViewResult
import com.ac.musicac.data.server.model.main.RestrictionsResult
import com.ac.musicac.data.server.model.releases.*
import com.ac.musicac.data.server.service.SpotifyService
import com.ac.musicac.domain.ExternalIds
import com.ac.musicac.domain.ExternalUrls
import com.ac.musicac.domain.Tracks
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Inject

fun buildRepositoryWith(
    localArtistData: List<ArtistEntity>,
    localAlbumData: List<AlbumEntity>,
    remoteArtistData: List<ArtistViewResult>,
    remoteAlbumData: List<AlbumViewResult>
): MusicRepository {
    val regionRepository = RegionRepository(FakeLocationDataSource(), FakePermissionChecker())
    val localArtistDataSource = ArtistRoomDataSource(FakeArtistDao(localArtistData))
    val localAlbumsDataSource = AlbumRoomDataSource(FakeAlbumDao(localAlbumData))
    val service: SpotifyService = FakeSpotifyService(artists = remoteArtistData, albums = remoteAlbumData)
    val client : HttpLoggingInterceptor = HttpLoggingInterceptor().apply {
        level = HttpLoggingInterceptor.Level.BODY
    }
    val fakeService = APIService(
        service.javaClass ,
        "https://api.spotify.com/v1/",
        GsonConverterFactory.create(),
        arrayOf(TokenHeader(), client)
    )
    val remoteDataSource = SpotifyDataSource(fakeService)
    return MusicRepository(regionRepository, localArtistDataSource , localAlbumsDataSource, remoteDataSource)
}

fun buildDatabaseArtist(vararg id: Int) = id.map {
    ArtistEntity(
        id = it,
        externalUrls = "",
        followers = 0,
        genres = "pop, rock",
        href = "Overview $it",
        artistId = "7ltDVBr6mKbRvohxheJ9h1",
        imageUrl = "https://i.scdn.co/image/ab676161000051743e5de222aa09ea8c106f2bbb",
        name = "Rosalia",
        popularity = 75,
        type = "artist",
        uri = ""
    )
}

fun buildRemoteArtist(vararg id: Int) = id.map {
    ArtistViewResult (
        external_urls = ExternalUrlsResult(""),
        followers = FollowersResult("", 200),
        genres = listOf("pop","rock"),
        href = "Overview $it",
        id = "7ltDVBr6mKbRvohxheJ9h1",
        images = listOf(
            ImageResult(
                200,
                "https://i.scdn.co/image/ab676161000051743e5de222aa09ea8c106f2bbb",
                200)),
        name = "Rosalia",
        popularity = 75,
        type = "artist",
        uri = ""
    )

}

fun buildDatabaseAlbum(vararg id: Int) = id.map {
    AlbumEntity(
        id = 0,
        albumType = "album",
        artists = "Estopa",
        external_ids = "",
        externalUrls = "https://open.spotify.com/artist/5ZqnEfVdEGmoPxtELhN7ai",
        genres = "pop, rock",
        href = "https://open.spotify.com",
        albumId = "6jbtHi5R0jMXoliU2OS0lo",
        imageUrl = "https://i.scdn.co/image/ab676161000051743e5de222aa09ea8c106f2bbb",
        label = "Label here",
        name = "Artist Name",
        popularity = 75,
        releaseDate = "1999-10-18",
        releaseDatePrecision = "1999-10-18",
        totalTracks = 20,
        type = "artist",
        uri = "https://i.scdn.co/image/"
    )
}

fun buildRemoteAlbum(vararg id: Int) = id.map {
    AlbumViewResult (
        album_type = "album",
        artists = listOf(),
        available_markets = listOf("ES","EN"),
        copyrights = listOf(),
        external_ids = ExternalIdsResult(""),
        external_urls = ExternalUrlsResult("https://open.spotify.com/artist/5ZqnEfVdEGmoPxtELhN7ai"),
        genres = listOf("pop","rock"),
        href = "https://open.spotify.com",
        id = "6jbtHi5R0jMXoliU2OS0lo",
        images = listOf(
            ImageResult(
                200,
                "https://i.scdn.co/image/ab676161000051743e5de222aa09ea8c106f2bbb",
                200)),
        label = "Label here",
        name = "Artist Name",
        popularity = 75,
        release_date = "1999-10-18",
        release_date_precision = "1999-10-18",
        restrictions = RestrictionsResult(""),
        total_tracks = 20,
        tracks = TracksResult("", listOf(), null, null, null, null,20),
        type = "artist",
        uri = "https://i.scdn.co/image/"
    )

}