package com.ac.musicac.data.datasource

import arrow.core.Either
import com.ac.musicac.domain.Error
import com.ac.musicac.domain.Releases

interface ReleasesRemoteDataSource {
    suspend fun getReleases(): Either<Error?, Releases>
}