package com.ac.musicac.data.server.service

import com.ac.musicac.data.server.UserResult
import com.ac.musicac.data.server.model.release.RemoteRelease
import com.ac.musicac.data.server.model.release.RemoteReleases
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface SpotifyService {
    @GET("users/{userId}")
    suspend fun getUser(@Path("userId") userId : String): UserResult

    @GET("browse/new-releases")
    suspend fun getReleases(
        @Query("country") country: String,
        @Query("limit") limit: String,
        @Query("offset") offset: String): RemoteReleases

    @GET("albums/{id}")
    suspend fun getReleaseDetail(
        @Path("id") id : String,
        @Query("market") market: String): RemoteRelease

}
