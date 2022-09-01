package com.ac.musicac.data.database.datasource

import com.ac.musicac.data.database.dao.ArtistDao
import com.ac.musicac.data.database.entity.ArtistEntity
import com.ac.musicac.data.datasource.ArtistLocalDataSource
import com.ac.musicac.data.tryCall
import com.ac.musicac.domain.*
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import javax.inject.Inject

class ArtistRoomDataSource @Inject constructor(private val artistDao: ArtistDao): ArtistLocalDataSource {

    override val artists: Flow<SeveralArtist> = artistDao.getAll().map { it.toDomainModel() }

    override suspend fun isEmpty(): Boolean = artistDao.artistCount() == 0

    override fun findById(id: Int): Flow<PopularArtist> = artistDao.findById(id).map { it.toDomainModel() }

    override suspend fun save(artists: SeveralArtist): Error? = tryCall {
        artistDao.insertAllArtist(artists.artists.fromDomainModel())
    }.fold(ifLeft = { it }, ifRight = { null })

    override suspend fun saveOnly(artist: PopularArtist): Error? = tryCall {
        artistDao.insertArtist(artist.fromDomainModel())
    }.fold(ifLeft = { it }, ifRight = { null })

    override suspend fun deleteAll(): Error? = tryCall {
        artistDao.deleteAll()
    }.fold(ifLeft = { it }, ifRight = { null })
}

private fun List<ArtistEntity>.toDomainModel(): SeveralArtist =
    SeveralArtist(
        artists = map { it.toDomainModel() }
    )

private fun ArtistEntity.toDomainModel(): PopularArtist =
    PopularArtist(
        id,
        ExternalUrls(externalUrls),
        Followers("", followers),
        listOf(genres),
        href,
        artistId,
        listOf(Image(0, imageUrl, 0)),
        name,
        popularity,
        type,
        uri
    )

private fun List<PopularArtist>.fromDomainModel(): List<ArtistEntity> = map { it.fromDomainModel() }

private fun PopularArtist.fromDomainModel(): ArtistEntity =
    ArtistEntity(
        0,
        externalUrls.spotify,
        followers.total,
        genres.maxOrNull() ?: "",
        href,
        artistId,
        images.maxOfOrNull { it.url } ?: "",
        name,
        popularity,
        type,
        uri
    )


