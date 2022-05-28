package com.ac.musicac.data.server.service

import com.ac.musicac.data.server.UserResult
import com.ac.musicac.data.server.model.release.RemoteRelease
import com.ac.musicac.data.server.model.release.RemoteReleases
import retrofit2.http.GET
import retrofit2.http.Header
import retrofit2.http.Path

interface SpotifyService {
    @GET("users/{userId}")
    suspend fun getUser(@Path("userId") userId : String): UserResult

    @GET("browse/new-releases")
    suspend fun getReleases(
        @Header("country") country: String,
        @Header("limit") limit: String,
        @Header("offset") offset: String): RemoteReleases

    @GET("albums")
    suspend fun getReleaseDetail(
        @Header("market") market: String,
        @Header("id") id: String): RemoteRelease

}
