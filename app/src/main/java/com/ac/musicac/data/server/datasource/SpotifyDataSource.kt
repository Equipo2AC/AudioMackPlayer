package com.ac.musicac.data.server.datasource

import arrow.core.Either
import com.ac.musicac.data.datasource.MusicRemoteDataSource
import com.ac.musicac.data.server.APIService
import com.ac.musicac.data.server.model.main.*
import com.ac.musicac.data.server.model.releases.*
import com.ac.musicac.data.server.service.SpotifyService
import com.ac.musicac.data.tryCall
import com.ac.musicac.domain.*
import javax.inject.Inject

class SpotifyDataSource @Inject constructor(
    private val api: APIService<SpotifyService>
) : MusicRemoteDataSource {

    override suspend fun getReleases(
        region: String,
        limit: String,
        offset: String
    ): Either<Error?, Releases> = tryCall {
        api.service
            .getReleases(region, limit, offset)
            .toDomainModel()
    }

    override suspend fun getReleaseDetail(
        albumId: String,
        market: String
    ): Either<Error?, Release> = tryCall {
        api.service
            .getReleaseDetail(albumId, market)
            .toDomainModel()
    }

    override suspend fun findSearch(
        type: String,
        query: String,
        limit: Int,
        offset: Int
    ): Either<Error?, Search> = tryCall {
        api.service
            .findSearch(type, query, limit, offset)
            .toDomainModel()
    }

    override suspend fun getArtist(id: String): Either<Error?, PopularArtist> = tryCall {
        api.service.getArtist(id).toDomainModel()
    }

    override suspend fun getSeveralArtist(ids: String): Either<Error?, SeveralArtist> = tryCall {
        api.service.getSeveralArtist(ids).toDomainModel()
    }

    override suspend fun getSeveralAlbums(ids: String): Either<Error?, SeveralAlbums> = tryCall {
        api.service.getSeveralAlbums(ids).toDomainModel()
    }


    override suspend fun getArtistAlbums(
        id: String,
        limit: Int,
        offset: Int
    ): Either<Error?, Albums> = tryCall {
        api.service
            .getArtistAlbums(id, limit, offset)
            .toDomainModel()
    }
}

private fun ReleasesResult.toDomainModel(): Releases =
    Releases(
        albums.toDomainModel()
    )

private fun RemoteRelease.toDomainModel(): Release =
    Release(
        album_type,
        getArtistsName(artists),
        copyrights.map { it.toDomainModel() },
        id,
        images.maxByOrNull { it.height }?.toDomainModel(),
        label,
        name,
        popularity,
        release_date,
        total_tracks.toString(),
        tracks.toDomainModel()
    )

private fun SearchResult.toDomainModel(): Search =
    Search(
        albums?.toDomainModel(),
        artists?.toDomainModel(),
    )

private fun AlbumsResult.toDomainModel(): Albums =
    Albums(
        href,
        items.map { it.toDomainModel() },
        limit,
        next,
        offset,
        previous,
        total
    )

private fun ArtistsResult.toDomainModel(): Artists =
    Artists(
        href,
        items.map { it.toDomainModel() },
        limit,
        next,
        offset,
        previous,
        total
    )

private fun SeveralArtistsResult.toDomainModel(): SeveralArtist =
    SeveralArtist(
        artists.map { it.toDomainModel() }
    )

private fun SeveralAlbumsResult.toDomainModel(): SeveralAlbums =
    SeveralAlbums (
        albums.map { it.toDomainModel() }
    )

private fun AlbumViewResult.toDomainModel(): AlbumView =
    AlbumView(
        0,
        albumType ?: "",
        artists.map{ it.toDomainModel() } ,
        availableMarkets ?: listOf(),
        externalUrls.toDomainModel(),
        href,
        albumId = id,
        // images.maxByOrNull { it.height }?.toDomainModel(),
        image = images[0].url ?: "",
        name,
        releaseDate ?: "",
        releaseDatePrecision ?: "",
        totalTracks ?: 0,
        tracks.toDomainModel(),
        type,
        uri
    )

private fun ArtistViewResult.toDomainModel(): PopularArtist =
    PopularArtist(
        0,
        external_urls.toDomainModel(),
        followers.toDomainModel(),
        genres ?: listOf(),
        href,
        artistId = id,
        images.map { it.toDomainModel() },
        name,
        popularity ?: 0,
        type ?: "",
        uri
    )

private fun ItemResult.toDomainModel(): Item =
    Item(
        0,
        album_type ?: "",
        getArtistsName(artists) ?: "",
        available_markets ?: listOf(),
        external_urls.toDomainModel(),
        href,
        itemId = id,
        images.maxByOrNull { it.height }?.toDomainModel(),
        name,
        release_date ?: "",
        release_date_precision ?: "",
        total_tracks ?: 0,
        type,
        uri,
        followers?.total ?: 0,
        genres ?: listOf()
    )


fun getArtistsName(artists: List<ArtistResult>?) = artists?.joinToString(", ") { it.name }

private fun ArtistResult.toDomainModel(): Artist =
    Artist(
        external_urls.toDomainModel(),
        href,
        id,
        name, type, uri
    )

private fun FollowersResult.toDomainModel(): Followers =
    Followers(
        href ?: "",
        total
    )

private fun TrackResult.toDomainModel(): Track =
    Track(
        getArtistsName(artists),
        disc_number,
        duration_ms,
        id,
        name,
        track_number.toString()
    )

private fun TracksResult.toDomainModel(): Tracks =
    Tracks(
        items.map { it.toDomainModel() },
        total
    )

private fun ImageResult.toDomainModel(): Image =
    Image(
        height, url, width
    )

private fun ExternalUrlsResult.toDomainModel(): ExternalUrls =
    ExternalUrls(
        spotify
    )


private fun CopyrightResult.toDomainModel(): Copyright =
    Copyright(
        text
    )

private fun ExternalIdsResult.toDomainModel(): ExternalIds =
    ExternalIds(
        upc
    )






