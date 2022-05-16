package com.ac.musicac.data.server.service

import com.ac.musicac.data.server.UserResult
import retrofit2.http.GET
import retrofit2.http.Path

interface SpotifyService {
    @GET("users/{userId}")
    suspend fun getUser(@Path("userId") userId : String): UserResult
}
