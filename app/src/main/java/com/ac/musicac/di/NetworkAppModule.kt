package com.ac.musicac.di

import com.ac.musicac.BuildConfig
import com.ac.musicac.data.server.APIService
import com.ac.musicac.data.server.interceptor.AuthorizationHeader
import com.ac.musicac.data.server.service.SpotifyAuthenticationService
import com.ac.musicac.data.server.service.SpotifyService
import com.ac.musicac.data.server.interceptor.TokenHeader
import com.ac.musicac.di.qualifier.ApiUrl
import com.ac.musicac.di.qualifier.AuthenticationApiUrl
import com.ac.musicac.di.qualifier.ClientId
import com.ac.musicac.di.qualifier.ClientSecret
import com.ac.musicac.di.qualifier.JsonFactory
import com.jakewharton.retrofit2.converter.kotlinx.serialization.asConverterFactory
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import kotlinx.serialization.ExperimentalSerializationApi
import kotlinx.serialization.json.Json
import okhttp3.MediaType.Companion.toMediaType
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Converter
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object NetworkAppModule {

    @Provides
    @Singleton
    @ApiUrl
    fun providesApiUrl(): String = "https://api.spotify.com/v1/"

    @Provides
    @Singleton
    @ApiUrl
    fun providesAuthenticationApiUrl(): String = "https://accounts.spotify.com/api/"

    @Provides
    @Singleton
    @ClientId
    fun providesClientId(): String = BuildConfig.spotifyClientId

    @Provides
    @Singleton
    @ClientSecret
    fun providesClientSecret(): String = BuildConfig.spotifyClientSecret

    @ExperimentalSerializationApi
    @Provides
    @JsonFactory
    fun provideJsonFactory(): Converter.Factory {
        val contentType = "application/json".toMediaType()
        val json = Json {
            ignoreUnknownKeys = true
            coerceInputValues = true
        }
        return json.asConverterFactory(contentType)
    }

    @Provides
    @Singleton
    fun provideOkHttpLoggingInterceptor(): HttpLoggingInterceptor = HttpLoggingInterceptor().apply {
        level = if (BuildConfig.DEBUG) {
            HttpLoggingInterceptor.Level.BODY
        } else {
            HttpLoggingInterceptor.Level.NONE
        }
    }

    @Provides
    @Singleton
    fun provideAuthorizationHeader(): AuthorizationHeader = AuthorizationHeader()

    @Provides
    @Singleton
    fun provideTokenHeader(): TokenHeader = TokenHeader()

    @Provides
    @Singleton
    fun provideAuthenticationApiService(
        @AuthenticationApiUrl apiUrl: String,
        authorizationHeader: AuthorizationHeader,
        httpLoggingInterceptor: HttpLoggingInterceptor,
        @JsonFactory jsonFactory : Converter.Factory
    ): APIService<SpotifyAuthenticationService> {
        return APIService(
            SpotifyAuthenticationService::class.java,
            apiUrl,
            jsonFactory,
            arrayOf(authorizationHeader, httpLoggingInterceptor)
        )
    }

    @Provides
    @Singleton
    fun provideSpotifyApiService(
        @AuthenticationApiUrl apiUrl: String,
        tokenHeader: TokenHeader,
        httpLoggingInterceptor: HttpLoggingInterceptor,
        @JsonFactory jsonFactory : Converter.Factory
    ): APIService<SpotifyService> {
        return APIService(
            SpotifyService::class.java,
            apiUrl,
            jsonFactory,
            arrayOf(tokenHeader, httpLoggingInterceptor)
        )
    }
}