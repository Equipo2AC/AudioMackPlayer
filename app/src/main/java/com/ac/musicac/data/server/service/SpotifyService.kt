package com.ac.musicac.data.server.service

import com.ac.musicac.data.server.model.releases.ReleasesResult
import retrofit2.http.*

interface SpotifyService {

    @GET("browse/new-releases")
    suspend fun getReleases(
        @Header("country") country: String,
        @Header("limit") limit: String,
        @Header("offset") offset: String): ReleasesResult


}
