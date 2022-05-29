package com.ac.musicac.data.server.datasource

import arrow.core.Either
import com.ac.musicac.data.datasource.ReleasesRemoteDataSource
import com.ac.musicac.data.server.APIService
import com.ac.musicac.data.server.model.release.*
import com.ac.musicac.data.server.service.SpotifyService
import com.ac.musicac.data.tryCall
import com.ac.musicac.domain.*
import com.ac.musicac.domain.releases.*
import javax.inject.Inject

class ReleasesDataSource @Inject constructor(
    private val api: APIService<SpotifyService>
) : ReleasesRemoteDataSource {

    override suspend fun getReleases(
        region: String,
        limit: String,
        offset: String
    ): Either<Error?, Releases> = tryCall {
        api.service
            .getReleases(region, limit, offset)
            .toDomainModel()
    }
}

private fun RemoteReleases.toDomainModel(): Releases =
    Releases(
        albums.toDomainModel()
    )

private fun RemoteAlbums.toDomainModel(): Albums =
    Albums(
        href,
        items.map { it.toDomainModel() },
        limit,
        next,
        offset,
        previous,
        total
    )

private fun RemoteItem.toDomainModel(): Item =
    Item(
        album_type,
        getArtistsName(artists),
        available_markets,
        external_urls.toDomainModel(),
        href,
        id,
        images.maxByOrNull { it.height }?.toDomainModel(),
        name,
        release_date,
        release_date_precision,
        total_tracks,
        type,
        uri
    )

fun getArtistsName(artists: List<RemoteArtist>): String {

    val names: MutableList<String> = mutableListOf()

    artists.map {
        names.add(it.name)
    }

    return names.joinToString(", ")
}

private fun RemoteArtist.toDomainModel(): Artist =
    Artist(
        id,
        name
    )

private fun RemoteImage.toDomainModel(): Image =
    Image(
        height, url, width
    )

private fun RemoteExternalUrls.toDomainModel(): ExternalUrls =
    ExternalUrls(
        spotify
    )



