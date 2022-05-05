package com.ac.musicac.data.server.service

import com.ac.musicac.data.server.AuthenticationResult
import retrofit2.http.Field
import retrofit2.http.POST

interface SpotifyAuthenticationService {

    @POST("token")
    fun authentication(
        @Field("grant_type") grantType: String
    ): AuthenticationResult

}