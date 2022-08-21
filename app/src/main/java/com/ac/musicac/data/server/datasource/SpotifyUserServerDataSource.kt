package com.ac.musicac.data.server.datasource

import arrow.core.Either
import com.ac.musicac.data.datasource.UserRemoteDataSource
import com.ac.musicac.data.server.APIService
import com.ac.musicac.data.server.UserResult
import com.ac.musicac.data.server.service.SpotifyService
import com.ac.musicac.data.tryCall
import com.ac.musicac.domain.Error
import com.ac.musicac.domain.User
import javax.inject.Inject

class SpotifyUserServerDataSource @Inject constructor(
    private val api: APIService<SpotifyService>
) : UserRemoteDataSource {
    override suspend fun getUser(userId: String): Either<Error, User> = tryCall {
        api.service
            .getUser(userId)
            .toDomainModel()
    }

    private fun UserResult.toDomainModel(): User =
        User(
            id,
            displayName,
            followers.total,
            images.lastOrNull()?.url ?: "",
        )
}
