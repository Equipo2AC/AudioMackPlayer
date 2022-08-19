package com.ac.musicac.data.server.datasource

import arrow.core.Either
import com.ac.musicac.data.datasource.MusicRemoteDataSource
import com.ac.musicac.data.server.APIService
import com.ac.musicac.data.server.model.artist.*
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

private fun ArtistTopTrackResult.toDomainModel(): ArtistTopTrack =
    ArtistTopTrack(
        tracks.map{ it.toDomainModel() }
    )

private fun TopTracksResult.toDomainModel(): TopTracks =
    TopTracks(
        album.map { it.toDomainModel() },
        artists.map { it.toDomainModel() }
        /* available_markets,
        disc_number,
        duration_ms,
        explicit,
        external_ids.toDomainModel(),
        external_urls.toDomainModel(),
        href,
        id,
        is_local,
        is_playable,
        // "",
        name,
        popularity,
        preview_url,
        restrictions.toDomainModel(),
        track_number,
        type,
        uri*/

    )

private fun TopTrackAlbumResult.toDomainModel(): TopTrackAlbum =
    TopTrackAlbum(
        // album_group,
        album_type,
        artists.map { it.toDomainModel() },
        available_markets,
        external_urls.toDomainModel(),
        href,
        id,
        images.map { it.toDomainModel() },
        name,
        release_date,
        release_date_precision,
        restrictions.toDomainModel(),
        total_tracks,
        type,
        uri
    )

private fun LinkedFromResult.toDomainModel(): LinkedFrom =
    LinkedFrom(
        album.toDomainModel(),
        artists.map { it.toDomainModel() },
        available_markets,
        disc_number,
        duration_ms,
        explicit,
        external_ids.toDomainModel(),
        external_urls.toDomainModel(),
        href,
        id,
        is_local,
        is_playable,
        linked_from?.toDomainModel(),
        name,
        popularity,
        preview_url,
        restrictions.toDomainModel(),
        track_number,
        type,
        uri
    )

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

private fun ArtistViewResult.toDomainModel(): PopularArtist =
    PopularArtist(
        external_urls.toDomainModel(),
        followers.toDomainModel(),
        genres ?: listOf(),
        href,
        id,
        images.map { it.toDomainModel() },
        name,
        popularity ?: 0,
        type ?: "",
        uri
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

private fun ExternalIdsResultX.toDomainModel(): ExternalIds =
    ExternalIds(
        upc ?: ""
    )

private fun RestrictionsResult.toDomainModel(): Restrictions =
    Restrictions(
        reason
    )





