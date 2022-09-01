package com.ac.musicac.data.datasource

import com.ac.musicac.domain.AlbumView
import com.ac.musicac.domain.Item
import com.ac.musicac.domain.Error
import com.ac.musicac.domain.SeveralAlbums
import kotlinx.coroutines.flow.Flow

interface AlbumLocalDataSource {
    val albums: Flow<SeveralAlbums>
    suspend fun isEmpty(): Boolean
    fun findById(id: Int): Flow<AlbumView>
    suspend fun save(albums: SeveralAlbums): Error?
    suspend fun saveOnly(album: AlbumView): Error?
    suspend fun deleteAll(): Error?
}