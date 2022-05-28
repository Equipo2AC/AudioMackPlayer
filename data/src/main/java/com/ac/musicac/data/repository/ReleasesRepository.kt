package com.ac.musicac.data.repository

import arrow.core.Either
import arrow.core.left
import arrow.core.right
import com.ac.musicac.data.RegionRepository
import com.ac.musicac.data.datasource.ReleaseDetailRemoteDataSource
import com.ac.musicac.data.datasource.ReleasesRemoteDataSource
import com.ac.musicac.domain.Error
import com.ac.musicac.domain.releases.Release
import com.ac.musicac.domain.releases.Releases
import javax.inject.Inject

class ReleasesRepository @Inject constructor(
    private val regionRepository: RegionRepository,
    private val releaseRemoteDataSource: ReleasesRemoteDataSource,
    private val getReleaseDetailDataSource: ReleaseDetailRemoteDataSource
) {

    suspend fun getReleases(): Either<Error?, Releases> {
        return releaseRemoteDataSource.getReleases(regionRepository.findLastRegion()).fold(
            ifLeft = { it.left() },
            ifRight = { it.right() }
        )
    }

    suspend fun getReleaseDetail(albumId: String): Either<Error?, Release> {
        return getReleaseDetailDataSource.getReleaseDetail(albumId, regionRepository.findLastRegion()).fold(
            ifLeft = { it.left() },
            ifRight = { it.right() }
        )
    }
}
