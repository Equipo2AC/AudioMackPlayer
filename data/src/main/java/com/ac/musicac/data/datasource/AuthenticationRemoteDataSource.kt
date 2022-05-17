package com.ac.musicac.data.datasource

import arrow.core.Either
import com.ac.musicac.domain.Error
import com.ac.musicac.domain.Token

interface AuthenticationRemoteDataSource {
    suspend fun getToken(): Either<Error, Token>
}
