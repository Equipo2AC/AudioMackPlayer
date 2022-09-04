package com.ac.musicac.data.database.datasource

import com.ac.musicac.data.database.dao.AlbumDao
import com.ac.musicac.data.database.entity.AlbumEntity
import com.ac.musicac.data.database.entity.ArtistEntity
import com.ac.musicac.data.datasource.AlbumLocalDataSource
import com.ac.musicac.data.server.datasource.getArtistsName
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
        ExternalIds(external_ids),
        ExternalUrls(externalUrls),
        listOf(genres),
        href,
        albumId,
        imageUrl,
        label,
        name,
        popularity,
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
        getArtists(artists),
        external_ids.upc,
        external_urls.spotify,
        genres.toString(),
        href,
        albumId,
        imageUrl = image ?: "",
        label,
        name,
        popularity,
        release_date,
        release_date_precision,
        total_tracks,
        type,
        uri,
    )

private fun getArtists(artists: List<Artist>?): String {
    val names: MutableList<String> = mutableListOf()
    artists?.map {
        names.add(it.name)
    }
    return names.joinToString(", ")
}