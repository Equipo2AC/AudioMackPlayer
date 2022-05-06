package com.ac.musicac.data.server.service

import com.ac.musicac.data.server.AuthenticationResult
import retrofit2.http.Field
import retrofit2.http.FormUrlEncoded
import retrofit2.http.POST

interface SpotifyAuthenticationService {

    @FormUrlEncoded
    @POST("token")
    suspend fun authentication(
        @Field("grant_type") grantType: String
    ): AuthenticationResult

}
