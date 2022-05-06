package com.ac.musicac.data.server.datasource

import arrow.core.Either
import com.ac.musicac.data.GranType
import com.ac.musicac.data.datasource.AuthenticationRemoteDataSource
import com.ac.musicac.data.server.APIService
import com.ac.musicac.data.server.AuthenticationResult
import com.ac.musicac.data.server.service.SpotifyAuthenticationService
import com.ac.musicac.data.tryCall
import com.ac.musicac.domain.Error
import com.ac.musicac.domain.Token
import java.util.*
import javax.inject.Inject

class SpotifyAuthenticationDataSource @Inject constructor(
    private val api: APIService<SpotifyAuthenticationService>
) : AuthenticationRemoteDataSource {

    override suspend fun getToken(): Either<Error, Token> = tryCall {
        api.service
            .authentication(GranType.ClientCredentials.type)
            .toDomainModel()
    }

    private fun AuthenticationResult.toDomainModel(): Token =
        Token(
            accessToken,
            tokenType,
            Date(Date().time + expireIn * 1000)
        )
}
