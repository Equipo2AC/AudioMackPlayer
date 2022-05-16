package com.ac.musicac.data.datasource

import arrow.core.Either
import com.ac.musicac.domain.Error
import com.ac.musicac.domain.User

interface UserRemoteDataSource {
    suspend fun getUser(userId: String): Either<Error, User>
}
