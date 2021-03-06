package com.ac.musicac.di

import android.app.Application
import androidx.room.Room
import com.ac.musicac.BuildConfig
import com.ac.musicac.data.Constants
import com.ac.musicac.data.database.MusicAcDatabase
import com.ac.musicac.data.server.APIService
import com.ac.musicac.data.server.interceptor.AuthorizationHeader
import com.ac.musicac.data.server.interceptor.TokenHeader
import com.ac.musicac.data.server.service.SpotifyAuthenticationService
import com.ac.musicac.data.server.service.SpotifyService
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
object AppModule {

    @Provides
    @Singleton
    fun provideDatabase(app: Application) : MusicAcDatabase = Room.databaseBuilder(
        app,
        MusicAcDatabase::class.java,
        Constants.DATABASE_NAME
    ).build()

    @Provides
    @Singleton
    fun providesAuthenticationDao(db: MusicAcDatabase) = db.authenticationDao()

    @Provides
    @Singleton
    @ApiUrl
    fun providesApiUrl(): String = "https://api.spotify.com/v1/"

    @Provides
    @Singleton
    @AuthenticationApiUrl
    fun providesAuthenticationApiUrl(): String = "https://accounts.spotify.com/api/"

    @Provides
    @Singleton
    @ClientId
    fun providesClientId(): String = "88a836718f2640aeb371d3039c982f74"

    @Provides
    @Singleton
    @ClientSecret
    fun providesClientSecret(): String = "236563df9f3740e5be8a30e795590ab0"

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
    fun provideAuthorizationHeader(
        @ClientId clientId: String,
        @ClientSecret clientSecret: String
    ): AuthorizationHeader = AuthorizationHeader(clientId, clientSecret)

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
