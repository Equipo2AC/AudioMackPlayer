package com.ac.musicac.ui

import com.ac.musicac.data.RegionRepository
import com.ac.musicac.data.database.datasource.AlbumRoomDataSource
import com.ac.musicac.data.database.datasource.ArtistRoomDataSource
import com.ac.musicac.data.database.entity.AlbumEntity
import com.ac.musicac.data.database.entity.ArtistEntity
import com.ac.musicac.data.repository.MusicRepository
import com.ac.musicac.data.server.datasource.SpotifyDataSource
import com.ac.musicac.data.server.model.main.ArtistViewResult

fun buildRepositoryWith(
    localArtistData: List<ArtistEntity>,
    localAlbumData: List<AlbumEntity>,
    remoteData: List<ArtistViewResult>
): MusicRepository {
    val regionRepository = RegionRepository(FakeLocationDataSource(), FakePermissionChecker())
    val localArtistDataSource = ArtistRoomDataSource(FakeArtistDao(localArtistData))
    val localAlbumsDataSource = AlbumRoomDataSource(FakeAlbumDao(localAlbumData))
    val remoteDataSource = SpotifyDataSource(FakeRemoteService(remoteData))
    return MusicRepository(regionRepository, localArtistDataSource , localAlbumsDataSource, remoteDataSource)
}