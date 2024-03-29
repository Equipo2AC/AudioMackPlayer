package com.ac.musicac.data.datasource

import arrow.core.Either
import com.ac.musicac.domain.*

interface MusicRemoteDataSource {
    suspend fun getReleases(
        region: String,
        limit: String = "10",
        offset: String = "5"
    ): Either<Error?, Releases>

    suspend fun getReleaseDetail(
        albumId: String,
        market: String
    ): Either<Error?, Release>

    suspend fun findSearch(
        type: String,
        query: String,
        limit: Int = 20,
        offset: Int = 0
    ): Either<Error?, Search>

    suspend fun getArtist(
        id: String
    ): Either<Error?, PopularArtist>

    suspend fun getSeveralArtist(
        ids: String
    ): Either<Error?, SeveralArtist>

    suspend fun getSeveralAlbums(
        ids: String
    ): Either<Error?, SeveralAlbums>

    suspend fun getArtistAlbums(
        id: String,
        limit: Int = 10,
        offset: Int = 0
    ): Either<Error?, Albums>

    /*suspend fun getArtistTopTracks(
        id: String,
        market: String
    ): Either<Error?, ArtistTopTrack>*/
}
