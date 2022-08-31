package com.ac.musicac.data.server.service

import com.ac.musicac.data.server.UserResult
import com.ac.musicac.data.server.model.artist.ArtistViewResult
import com.ac.musicac.data.server.model.releases.*
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
        @Query("offset") offset: String): ReleasesResult

    @GET("albums/{id}")
    suspend fun getReleaseDetail(
        @Path("id") id : String,
        @Query("market") market: String): RemoteRelease

    @GET("search")
    suspend fun findSearch(
        @Query("type") type: String,
        @Query("q") query: String,
        @Query("limit") limit: Int,
        @Query("offset") offset: Int): SearchResult

    @GET("artists/{id}")
    suspend fun getArtist(@Path("id") id: String): ArtistViewResult

    @GET("artists")
    suspend fun getSeveralArtist(@Query("ids") id: String): List<ArtistViewResult>

    @GET("artists/{id}/albums")
    suspend fun getArtistAlbums(
        @Path("id") id : String,
        @Query("limit") limit: Int,
        @Query("offset") offset: Int): AlbumsResult

}
