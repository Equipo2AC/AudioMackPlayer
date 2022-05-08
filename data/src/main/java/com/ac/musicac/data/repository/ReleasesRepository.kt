package com.ac.musicac.data.repository

import com.ac.musicac.data.datasource.ReleasesRemoteDataSource
import com.ac.musicac.domain.Error
import javax.inject.Inject

class ReleasesRepository @Inject constructor(
    private val remoteRemoteDataSource: ReleasesRemoteDataSource
) {

    suspend fun getReleases(): Error? {
        val releases = remoteRemoteDataSource.getReleases()

        releases.fold(
            ifLeft = {return it},
            ifRight = {return@fold})

        return null
    }
}
