package com.ac.musicac.data.datasource

import com.ac.musicac.domain.PopularArtist
import com.ac.musicac.domain.Error
import com.ac.musicac.domain.SeveralArtist
import kotlinx.coroutines.flow.Flow

interface ArtistLocalDataSource {
    val artists: Flow<SeveralArtist>
    suspend fun isEmpty(): Boolean
    fun findById(id: Int): Flow<PopularArtist>
    fun findByArtistId(artistId: String): Flow<PopularArtist>
    suspend fun save(artists: SeveralArtist): Error?
    suspend fun saveOnly(artist: PopularArtist): Error?
    suspend fun deleteAll(): Error?
}