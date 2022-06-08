package com.ac.musicac.data.datasource

import arrow.core.Either
import com.ac.musicac.domain.Error
import com.ac.musicac.domain.Releases
import com.ac.musicac.domain.Search

interface MusicRemoteDataSource {
    suspend fun getReleases(
        region: String,
        limit: String = "10",
        offset: String = "5"
    ): Either<Error?, Releases>

    suspend fun findSearch(
        type: String,
        query: String,
        limit: Int = 20,
        offset: Int = 0
    ): Either<Error?, Search>
}
