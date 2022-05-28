package com.ac.musicac.data.datasource

import arrow.core.Either
import com.ac.musicac.domain.Error
import com.ac.musicac.domain.releases.Releases

interface ReleasesRemoteDataSource {
    suspend fun getReleases(region: String, limit: String = "10", offset: String = "5"): Either<Error?, Releases>
}