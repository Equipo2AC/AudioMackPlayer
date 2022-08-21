package com.ac.musicac.data.repository

import com.ac.musicac.data.datasource.UserRemoteDataSource
import com.ac.musicac.domain.Error
import javax.inject.Inject

class UserRepository @Inject constructor(
    private val remoteDataSource: UserRemoteDataSource) {

    suspend fun getUser(userId : String): Error? {
        val user = remoteDataSource.getUser(userId)
        user.fold(ifLeft = { return it }) {
            //Nothing
        }
        return null
    }
}
