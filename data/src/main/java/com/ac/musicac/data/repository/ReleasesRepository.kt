package com.ac.musicac.data.repository

import arrow.core.Either
import arrow.core.left
import arrow.core.right
import com.ac.musicac.data.RegionRepository
import com.ac.musicac.data.datasource.ReleasesRemoteDataSource
import com.ac.musicac.domain.Error
import com.ac.musicac.domain.Releases
import javax.inject.Inject

class ReleasesRepository @Inject constructor(
    private val regionRepository: RegionRepository,
    private val remoteRemoteDataSource: ReleasesRemoteDataSource
) {

    suspend fun getReleases(): Either<Error?, Releases> {
        return remoteRemoteDataSource.getReleases(regionRepository.findLastRegion()).fold(
            ifLeft = { it.left() },
            ifRight = { it.right() }
        )
    }
}
