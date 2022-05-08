package com.ac.musicac.data.server.datasource

import arrow.core.Either
import com.ac.musicac.data.GranType
import com.ac.musicac.data.datasource.AuthenticationRemoteDataSource
import com.ac.musicac.data.datasource.ReleasesRemoteDataSource
import com.ac.musicac.data.server.APIService
import com.ac.musicac.data.server.model.authentication.AuthenticationResult
import com.ac.musicac.data.server.model.releases.*
import com.ac.musicac.data.server.service.SpotifyAuthenticationService
import com.ac.musicac.data.server.service.SpotifyService
import com.ac.musicac.data.tryCall
import com.ac.musicac.domain.*
import java.util.*
import javax.inject.Inject

class ReleasesDataSource @Inject constructor(
    private val api: APIService<SpotifyService>
) : ReleasesRemoteDataSource {


    override suspend fun getReleases(): Either<Error, Releases> = tryCall {
        api.service
            .getReleases("ES", "10", "5", "BQBYdsfgJcSzjgwUwsdWY7wQW3Waz6nqnY6y9ffmtnDz4o9e1gVZ28RGQfnliUeAHQlQzlkAESBQIf8mMyM")
            .toDomainModel()
    }
}

private fun ReleasesResult.toDomainModel(): Releases =
    Releases(
        albums.toDomainModel()
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

private fun ItemResult.toDomainModel(): Item =
    Item(
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
        total_tracks,
        type,
        uri
    )

private fun ArtistResult.toDomainModel(): Artist =
    Artist(
        external_urls.toDomainModel(),
        href,
        id,
        name, type, uri
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





