package com.ac.musicac.data.repository

import com.ac.musicac.data.datasource.AuthenticationLocalDataSource
import com.ac.musicac.data.datasource.AuthenticationRemoteDataSource
import com.ac.musicac.domain.Error
import javax.inject.Inject

class AuthenticationRepository @Inject constructor(
    private val remoteDataSource: AuthenticationRemoteDataSource,
    private val localeDataSource: AuthenticationLocalDataSource) {

    suspend fun requestAuthentication(): Error? {
        val token = remoteDataSource.getToken()
        token.fold(ifLeft = { return it }) {
            localeDataSource.save(it)
        }
        return null
    }
}
