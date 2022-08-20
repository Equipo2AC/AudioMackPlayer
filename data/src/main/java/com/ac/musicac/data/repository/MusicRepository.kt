package com.ac.musicac.data.repository

import arrow.core.Either
import arrow.core.left
import arrow.core.right
import com.ac.musicac.data.RegionRepository
import com.ac.musicac.data.datasource.MusicRemoteDataSource
import com.ac.musicac.domain.*
import javax.inject.Inject

class MusicRepository @Inject constructor(
    private val regionRepository: RegionRepository,
    private val remoteRemoteDataSource: MusicRemoteDataSource
) {

    suspend fun getReleases(): Either<Error?, Releases> {
        return remoteRemoteDataSource.getReleases(regionRepository.findLastRegion()).fold(
            ifLeft = { it.left() },
            ifRight = { it.right() }
        )
    }

    suspend fun getReleaseDetail(albumId: String): Either<Error?, Release> {
        return remoteRemoteDataSource.getReleaseDetail(albumId, regionRepository.findLastRegion()).fold(
            ifLeft = { it.left() },
            ifRight = { it.right() }
        )
    }

    suspend fun findSearch(type: Type, query: String): Either<Error?, Search> {
        return remoteRemoteDataSource.findSearch(type.value, query).fold(
            ifLeft = { it.left() },
            ifRight = { it.right() }
        )
    }

    suspend fun getArtist(id: String): Either<Error?, PopularArtist> {
        return remoteRemoteDataSource.getArtist(id).fold(
            ifLeft = { it.left() },
            ifRight = { it.right() }
        )
    }

    suspend fun getArtistAlbums(id: String): Either<Error?, Albums> {
        return remoteRemoteDataSource.getArtistAlbums(id).fold(
            ifLeft = { it.left() },
            ifRight = { it.right() }
        )
    }

    /*suspend fun getArtistTopTracks(id: String): Either<Error?, ArtistTopTrack> {
        return remoteRemoteDataSource.getArtistTopTracks(id, regionRepository.findLastRegion()).fold(
            ifLeft = { it.left() },
            ifRight = { it.right() }
        )
    }*/
}
