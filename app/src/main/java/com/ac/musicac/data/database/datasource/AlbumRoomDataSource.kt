package com.ac.musicac.data.database.datasource

import com.ac.musicac.data.database.dao.AlbumDao
import com.ac.musicac.data.database.entity.AlbumEntity
import com.ac.musicac.data.datasource.AlbumLocalDataSource
import com.ac.musicac.data.tryCall
import com.ac.musicac.domain.Error
import com.ac.musicac.domain.ExternalUrls
import com.ac.musicac.domain.Image
import com.ac.musicac.domain.Item
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import javax.inject.Inject

class AlbumRoomDataSource @Inject constructor(private val albumDao: AlbumDao) : AlbumLocalDataSource {
    override val albums: Flow<List<Item>> = albumDao.getAll().map { it.toDomainModel() }

    override suspend fun isEmpty(): Boolean = albumDao.albumCount() == 0

    override fun findById(id: Int): Flow<Item> = albumDao.findById(id).map { it.toDomainModel() }

    override suspend fun save(albums: List<Item>): Error? = tryCall {
        albumDao.insertAllAlbums(albums.fromDomainModel())
    }.fold(ifLeft = { it }, ifRight = { null })

    override suspend fun saveOnly(album: Item): Error? = tryCall {
        albumDao.insertAlbum(album.fromDomainModel())
    }.fold(ifLeft = { it }, ifRight = { null })

    override suspend fun deleteAll(): Error? = tryCall {
        albumDao.deleteAll()
    }.fold(ifLeft = { it }, ifRight = { null })
}

private fun List<AlbumEntity>.toDomainModel(): List<Item> = map { it.toDomainModel() }

private fun AlbumEntity.toDomainModel(): Item =
    Item(
        id,
        albumType,
        artists,
        listOf(),
        ExternalUrls(externalUrls),
        href,
        albumId,
        Image(0, imageUrl, 0),
        name,
        releaseDate,
        releaseDatePrecision,
        totalTracks,
        type,
        uri,
        followers,
        listOf(genres)
    )

private fun List<Item>.fromDomainModel(): List<AlbumEntity> = map { it.fromDomainModel() }

private fun Item.fromDomainModel(): AlbumEntity =
    AlbumEntity(
        0,
        albumType,
        artists,
        externalUrls.spotify,
        href,
        itemId,
        imageUrl = image?.url!!,
        name,
        releaseDate,
        releaseDatePrecision,
        totalTracks,
        type,
        uri,
        followers,
        genres.get(0)
    )