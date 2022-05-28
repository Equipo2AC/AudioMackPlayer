package com.ac.musicac.data.datasource

import arrow.core.Either
import com.ac.musicac.domain.Error
import com.ac.musicac.domain.releases.Release
import com.ac.musicac.domain.releases.Releases

interface ReleaseDetailRemoteDataSource {
    suspend fun getReleaseDetail(albumId: String, market: String): Either<Error?, Release>
}