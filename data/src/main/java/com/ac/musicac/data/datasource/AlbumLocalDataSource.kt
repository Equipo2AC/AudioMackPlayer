package com.ac.musicac.data.datasource

import com.ac.musicac.domain.Item
import com.ac.musicac.domain.Error
import kotlinx.coroutines.flow.Flow

interface AlbumLocalDataSource {
    val albums: Flow<List<Item>>
    suspend fun isEmpty(): Boolean
    fun findById(id: Int): Flow<Item>
    suspend fun save(albums: List<Item>): Error?
    suspend fun saveOnly(album: Item): Error?
    suspend fun deleteAll(): Error?
}