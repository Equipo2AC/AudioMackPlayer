package com.ac.musicac.data.database.datasource

import com.ac.musicac.data.database.dao.AlbumDao
import com.ac.musicac.data.database.entity.AlbumEntity
import com.ac.musicac.data.datasource.AlbumLocalDataSource
import com.ac.musicac.data.tryCall
import com.ac.musicac.domain.*
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import javax.inject.Inject

class AlbumRoomDataSource @Inject constructor(private val albumDao: AlbumDao) : AlbumLocalDataSource {

    override val albums: Flow<SeveralAlbums> = albumDao.getAll().map { it.toDomainModel() }

    override suspend fun isEmpty(): Boolean = albumDao.albumCount() == 0

    override fun findById(id: Int): Flow<AlbumView> = albumDao.findById(id).map { it.toDomainModel() }

    override suspend fun save(albums: SeveralAlbums): Error? = tryCall {
        albumDao.insertAllAlbums(albums.albums.fromDomainModel())
    }.fold(ifLeft = { it }, ifRight = { null })

    override suspend fun saveOnly(album: AlbumView): Error? = tryCall {
        albumDao.insertAlbum(album.fromDomainModel())
    }.fold(ifLeft = { it }, ifRight = { null })

    override suspend fun deleteAll(): Error? = tryCall {
        albumDao.deleteAll()
    }.fold(ifLeft = { it }, ifRight = { null })
}

private fun List<AlbumEntity>.toDomainModel(): SeveralAlbums =
    SeveralAlbums(
        albums = map { it.toDomainModel() }
    )

private fun AlbumEntity.toDomainModel(): AlbumView =
    AlbumView(
        id,
        albumType,
        listOf(),
        listOf(),
        ExternalUrls(externalUrls),
        href,
        albumId,
        imageUrl,
        name,
        releaseDate,
        releaseDatePrecision,
        totalTracks,
        Tracks(listOf(), 0),
        type,
        uri
    )


private fun List<AlbumView>.fromDomainModel(): List<AlbumEntity> = map { it.fromDomainModel() }

private fun AlbumView.fromDomainModel(): AlbumEntity =
    AlbumEntity(
        id,
        album_type,
        artists.maxOfOrNull { it.name } ?: "",
        external_urls.spotify,
        href,
        albumId,
        imageUrl = image!!,
        name,
        release_date,
        release_date_precision,
        total_tracks,
        type,
        uri,
    )