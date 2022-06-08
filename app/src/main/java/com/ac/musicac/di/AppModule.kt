package com.ac.musicac.di

import android.app.Application
import androidx.room.Room
import com.ac.musicac.BuildConfig
import com.ac.musicac.data.Constants
import com.ac.musicac.data.PermissionChecker
import com.ac.musicac.data.database.MusicAcDatabase
import com.ac.musicac.data.database.dao.AuthenticationDao
import com.ac.musicac.data.datasource.LocationDataSource
import com.ac.musicac.data.server.APIService
import com.ac.musicac.data.server.AndroidPermissionChecker
import com.ac.musicac.data.server.PlayServicesLocationDataSource
import com.ac.musicac.data.server.interceptor.AuthorizationHeader
import com.ac.musicac.data.server.interceptor.TokenHeader
import com.ac.musicac.data.server.service.SpotifyAuthenticationService
import com.ac.musicac.data.server.service.SpotifyService
import com.ac.musicac.di.qualifier.*
import com.jakewharton.retrofit2.converter.kotlinx.serialization.asConverterFactory
import dagger.Binds
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
            explicitNulls = false
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
    fun provideTokenHeader(dao: AuthenticationDao): TokenHeader = TokenHeader(dao)

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
        @ApiUrl apiUrl: String,
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

@Module
@InstallIn(SingletonComponent::class)
abstract class AppDataModule {

    @Binds
    abstract fun bindLocationDataSource(locationDataSource: PlayServicesLocationDataSource): LocationDataSource

    @Binds
    abstract fun bindPermissionChecker(permissionChecker: AndroidPermissionChecker): PermissionChecker

}
