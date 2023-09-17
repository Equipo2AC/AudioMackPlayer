package com.ac.musicac.data.repository

import arrow.core.Either
import arrow.core.left
import arrow.core.right
import com.ac.musicac.data.RegionRepository
import com.ac.musicac.data.datasource.AlbumLocalDataSource
import com.ac.musicac.data.datasource.ArtistLocalDataSource
import com.ac.musicac.data.datasource.MusicRemoteDataSource
import com.ac.musicac.domain.*
import javax.inject.Inject

class MusicRepository @Inject constructor(
    private val regionRepository: RegionRepository,
    private val artistLocalDataSource: ArtistLocalDataSource,
    private val albumLocalDataSource: AlbumLocalDataSource,
    private val musicRemoteDataSource: MusicRemoteDataSource
) {

    val savedArtists = artistLocalDataSource.artists

    val savedAlbums = albumLocalDataSource.albums

    suspend fun getReleases(): Either<Error?, Releases> {
        return musicRemoteDataSource.getReleases(regionRepository.findLastRegion()).fold(
            ifLeft = { it.left() },
            ifRight = { it.right() }
        )
    }

    suspend fun getReleaseDetail(albumId: String): Either<Error?, Release> {
        return musicRemoteDataSource.getReleaseDetail(albumId, regionRepository.findLastRegion()).fold(
            ifLeft = { it.left() },
            ifRight = { it.right() }
        )
    }

    suspend fun findSearch(type: Type, query: String): Either<Error?, Search> {
        return musicRemoteDataSource.findSearch(type.value, query).fold(
            ifLeft = { it.left() },
            ifRight = { it.right() }
        )
    }

    suspend fun getArtist(id: String): Either<Error?, PopularArtist> {
        return musicRemoteDataSource.getArtist(id).fold(
            ifLeft = { it.left() },
            ifRight = { it.right() }
        )
    }

    suspend fun getArtistAlbums(id: String): Either<Error?, Albums> {
        return musicRemoteDataSource.getArtistAlbums(id).fold(
            ifLeft = { it.left() },
            ifRight = { it.right() }
        )
    }

    suspend fun getSeveralArtist(ids: String): Error? {
        if(artistLocalDataSource.isEmpty()) {
            val artists = musicRemoteDataSource.getSeveralArtist(ids)
            artists.fold(ifLeft = {return it}) {
                artistLocalDataSource.save(it)
            }
        }
        return null
    }

    suspend fun getSeveralAlbums(ids: String): Error? {
        if(albumLocalDataSource.isEmpty()) {
            val albums = musicRemoteDataSource.getSeveralAlbums(ids)
            albums.fold(ifLeft = {return it}) {
                albumLocalDataSource.save(it)
            }
        }
        return null
    }
}
