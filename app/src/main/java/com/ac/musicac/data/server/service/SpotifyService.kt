package com.ac.musicac.data.server.service

import com.ac.musicac.data.server.UserResult
import com.ac.musicac.data.server.model.releases.ItemResult
import com.ac.musicac.data.server.model.releases.ReleasesResult
import com.ac.musicac.data.server.model.releases.SearchResult
import retrofit2.http.GET
import retrofit2.http.Header
import retrofit2.http.Path
import retrofit2.http.Query

interface SpotifyService {
    @GET("users/{userId}")
    suspend fun getUser(@Path("userId") userId : String): UserResult

    @GET("browse/new-releases")
    suspend fun getReleases(
        @Header("country") country: String,
        @Header("limit") limit: String,
        @Header("offset") offset: String): ReleasesResult

    @GET("search")
    suspend fun findSearch(
        @Query("type") type: String,
        @Query("q") query: String,
        @Query("limit") limit: Int,
        @Query("offset") offset: Int): SearchResult

    @GET("artists/{artistId}")
    suspend fun getArtist(@Path("artistId") artistId : String): ItemResult

    @GET("artists/{artistId}/top-tracks")
    suspend fun getArtistTopTracks(
        @Path("artistId") artistId : String,
        @Query("market") market: String = "US"): List<ItemResult>

}
