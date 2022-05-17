package com.ac.musicac.data.server.service

import com.ac.musicac.data.server.UserResult
import retrofit2.http.GET
import retrofit2.http.Path

import com.ac.musicac.data.server.model.releases.ReleasesResult
import retrofit2.http.*

interface SpotifyService {
    @GET("users/{userId}")
    suspend fun getUser(@Path("userId") userId : String): UserResult

    @GET("browse/new-releases")
    suspend fun getReleases(
        @Header("country") country: String,
        @Header("limit") limit: String,
        @Header("offset") offset: String): ReleasesResult


}
