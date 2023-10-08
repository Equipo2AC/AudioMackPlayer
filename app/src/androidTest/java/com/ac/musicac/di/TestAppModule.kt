package com.ac.musicac.di

import android.app.Application
import androidx.lifecycle.SavedStateHandle
import androidx.room.Room
import com.ac.musicac.BuildConfig
import com.ac.musicac.data.database.MusicAcDatabase
import com.ac.musicac.data.database.dao.AlbumDao
import com.ac.musicac.data.database.dao.ArtistDao
import com.ac.musicac.data.database.dao.AuthenticationDao
import com.ac.musicac.data.server.APIService
import com.ac.musicac.data.server.interceptor.AuthorizationHeader
import com.ac.musicac.data.server.interceptor.TokenHeader
import com.ac.musicac.data.server.service.SpotifyAuthenticationService
import com.ac.musicac.data.server.service.SpotifyService
import com.ac.musicac.di.qualifier.*
import com.ac.musicac.ui.main.artist.ArtistFragmentArgs
import com.ac.musicac.ui.main.releases.detail.ReleaseDetailFragmentArgs
import com.jakewharton.retrofit2.converter.kotlinx.serialization.asConverterFactory
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ViewModelComponent
import dagger.hilt.android.scopes.ViewModelScoped
import dagger.hilt.components.SingletonComponent
import dagger.hilt.testing.TestInstallIn
import kotlinx.serialization.ExperimentalSerializationApi
import kotlinx.serialization.json.Json
import okhttp3.MediaType.Companion.toMediaType
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Converter
import javax.inject.Singleton

@Module
@TestInstallIn(components = [SingletonComponent::class], replaces = [AppModule::class])
object TestAppModule {

    @Provides
    @Singleton
    fun provideDatabase(app: Application) : MusicAcDatabase = Room.inMemoryDatabaseBuilder(
        app,
        MusicAcDatabase::class.java
    ).build()

    @Provides
    @Singleton
    fun providesAuthenticationDao(db: MusicAcDatabase): AuthenticationDao = db.authenticationDao()

    @Provides
    @Singleton
    fun providesArtistDao(db: MusicAcDatabase): ArtistDao = db.artistDao()

    @Provides
    @Singleton
    fun providesAlbumDao(db: MusicAcDatabase): AlbumDao = db.albumDao()

    @Provides
    @Singleton
    @ApiUrl
    fun providesApiUrl(): String = "http://localhost:8080"

    @Provides
    @Singleton
    @AuthenticationApiUrl
    fun providesAuthenticationApiUrl(): String = "http://localhost:8080"

    @Provides
    @Singleton
    @OnlyArtistDummyId
    fun providesOnlyArtistsId(): String = "7ltDVBr6mKbRvohxheJ9h1"

    @Provides
    @Singleton
    @ArtistDummyIds
    fun providesArtistsIds(): String = "7ltDVBr6mKbRvohxheJ9h1,716NhGYqD1jl2wI1Qkgq36,52iwsT98xCoGgiGntTiR7K,4q3ewBCX7sLwd24euuV69X,1bAftSH8umNcGZ0uyV7LMg,790FomKkXshlbRYZFtlgla,2R21vXR83lH98kGeO99Y66,1Cs0zKBU1kc0i8ypK3B9ai"

    @Provides
    @Singleton
    @AlbumDummyIds
    fun providesAlbumsIds(): String = "3RQQmkQEvNCY4prGKE6oc5,6jbtHi5R0jMXoliU2OS0lo,1wLB2bnCl2m5m9M9g8r93Y,7rE2qU0GsiIiNd4VPupV3B,4yNnIoQh8y1uDB6ScOS2vx,4PNqWiJAfjj32hVvlchV5u,6GHUywBU0u92lg0Dhrt40R,6gQKAYf3TJM9sppw3AtbHH"

    @Provides
    @Singleton
    fun provideOkHttpClient(): OkHttpClient = HttpLoggingInterceptor().run {
        level = HttpLoggingInterceptor.Level.BODY
        OkHttpClient.Builder().addInterceptor(this).build()
    }

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
@InstallIn(ViewModelComponent::class)
object ArtistViewModelModule {

    @Provides
    @ViewModelScoped
    @ArtistId
    fun provideArtistId(savedStateHandle: SavedStateHandle) =
        ArtistFragmentArgs.fromSavedStateHandle(savedStateHandle).artistId
}

@Module
@InstallIn(ViewModelComponent::class)
class ReleaseDetailViewModelModule {

    @Provides
    @ViewModelScoped
    @AlbumId
    fun provideAlbumId(savedStateHandle: SavedStateHandle) =
        ReleaseDetailFragmentArgs.fromSavedStateHandle(savedStateHandle).albumId

}