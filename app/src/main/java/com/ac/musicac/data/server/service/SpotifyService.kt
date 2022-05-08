package com.ac.musicac.data.server.service

import com.ac.musicac.data.server.model.authentication.AuthenticationResult
import com.ac.musicac.data.server.model.releases.ReleasesResult
import retrofit2.http.Field
import retrofit2.http.FormUrlEncoded
import retrofit2.http.POST

interface SpotifyService {

    @FormUrlEncoded
    @POST("browse/new-releases")
    suspend fun getReleases(
        @Field("country") country: String,
        @Field("limit") limit: String,
        @Field("offset") offset: String,
        @Field("token") token: String
    ): ReleasesResult


}
