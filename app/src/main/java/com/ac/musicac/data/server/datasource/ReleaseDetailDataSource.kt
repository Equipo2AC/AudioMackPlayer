package com.ac.musicac.data.server.datasource

import arrow.core.Either
import com.ac.musicac.data.datasource.ReleaseDetailRemoteDataSource
import com.ac.musicac.data.server.APIService
import com.ac.musicac.data.server.model.release.*
import com.ac.musicac.data.server.model.release.RemoteExternalIds
import com.ac.musicac.data.server.service.SpotifyService
import com.ac.musicac.data.tryCall
import com.ac.musicac.domain.*
import com.ac.musicac.domain.releases.*
import javax.inject.Inject

class ReleaseDetailDataSource @Inject constructor(
    private val api: APIService<SpotifyService>
) : ReleaseDetailRemoteDataSource {

    override suspend fun getReleaseDetail(
        albumId: String,
        market: String
    ): Either<Error?, Release> = tryCall {
        api.service
            .getReleaseDetail(albumId, market)
            .toDomainModel()
    }
}

private fun RemoteRelease.toDomainModel(): Release =
    Release(
        album_type,
        artists.map { it.toDomainModel() },
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

private fun RemoteArtist.toDomainModel(): Artist =
    Artist(
        id,
        name
    )

private fun RemoteCopyright.toDomainModel(): Copyright =
    Copyright(
        text
    )

private fun RemoteExternalIds.toDomainModel(): ExternalIds =
    ExternalIds(
        upc
    )

private fun RemoteImage.toDomainModel(): Image =
    Image(
        height, url, width
    )

private fun RemoteExternalUrls.toDomainModel(): ExternalUrls =
    ExternalUrls(
        spotify
    )

private fun RemoteTracks.toDomainModel(): Tracks =
    Tracks(
        items.map { it.toDomainModel() },
        total
    )

private fun RemoteTrack.toDomainModel(): Track =
    Track(
        artists.map { it.toDomainModel() },
        disc_number,
        duration_ms,
        id,
        name,
        track_number.toString()
    )

