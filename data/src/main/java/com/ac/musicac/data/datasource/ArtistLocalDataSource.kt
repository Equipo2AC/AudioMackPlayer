package com.ac.musicac.data.datasource

import com.ac.musicac.domain.PopularArtist
import com.ac.musicac.domain.Error
import kotlinx.coroutines.flow.Flow

interface ArtistLocalDataSource {
    val artists: Flow<List<PopularArtist>>
    suspend fun isEmpty(): Boolean
    fun findById(id: Int): Flow<PopularArtist>
    suspend fun save(artists: List<PopularArtist>): Error?
    suspend fun saveOnly(artist: PopularArtist): Error?
    suspend fun deleteAll(): Error?
}