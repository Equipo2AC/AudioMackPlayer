package com.ac.musicac.di

import com.ac.musicac.data.database.datasource.AlbumRoomDataSource
import com.ac.musicac.data.database.datasource.ArtistRoomDataSource
import com.ac.musicac.data.database.datasource.AuthenticationRoomDataSource
import com.ac.musicac.data.datasource.*
import com.ac.musicac.data.server.datasource.SpotifyAuthenticationDataSource
import com.ac.musicac.data.server.datasource.SpotifyDataSource
import com.ac.musicac.data.server.datasource.SpotifyUserServerDataSource
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@Module
@InstallIn(SingletonComponent::class)
abstract class DataSourceModule {

    @Binds
    abstract fun bindRemoteSpotifyAuthenticationDataSource(
        remoteDataSource: SpotifyAuthenticationDataSource
    ): AuthenticationRemoteDataSource

    @Binds
    abstract fun bindLocalAuthenticationDataSource(
        localDataSource: AuthenticationRoomDataSource
    ): AuthenticationLocalDataSource

    @Binds
    abstract fun bindRemoteSpotifyUserDataSource(
        remoteDataSource: SpotifyUserServerDataSource
    ): UserRemoteDataSource

    @Binds
    abstract fun bindRemoteSpotifyDataSource(
        remoteDataSource: SpotifyDataSource
    ): MusicRemoteDataSource

    @Binds
    abstract fun bindArtistLocalDataSource(
        localDataSource: ArtistRoomDataSource
    ): ArtistLocalDataSource

    @Binds
    abstract fun bindAlbumLocalDataSource(
        localDataSource: AlbumRoomDataSource
    ): AlbumLocalDataSource

}
