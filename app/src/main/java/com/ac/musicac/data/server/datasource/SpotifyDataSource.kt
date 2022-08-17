package com.ac.musicac.data.server.datasource

import arrow.core.Either
import com.ac.musicac.data.datasource.MusicRemoteDataSource
import com.ac.musicac.data.server.APIService
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
        api.service.getArtist(id).artistToDomainModel()
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

private fun ItemResult.toDomainModel(): Item =
    Item(
        album_type ?: "",
        getArtistsName(artists) ?: "",
        available_markets ?: listOf(),
        external_urls.toDomainModel(),
        href,
        id,
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

private fun ItemResult.artistToDomainModel(): PopularArtist =
    PopularArtist(
        album_type ?: "",
        artists?.map { it.toDomainModel() } ?: listOf(),
        available_markets ?: listOf(),
        external_urls.toDomainModel(),
        href,
        id,
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

private fun TracksResult.toDomainModel(): Tracks =
    Tracks(
        items.map { it.toDomainModel() },
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

private fun ImageResult.toDomainModel(): Image =
    Image(
        height, url, width
    )

private fun ExternalUrlsResult.toDomainModel(): ExternalUrls =
    ExternalUrls(
        spotify
    )

private fun ExternalUrlsXResult.toDomainModel(): ExternalUrlsX =
    ExternalUrlsX(
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





