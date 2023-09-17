package com.ac.musicac.data.database.dao

import androidx.room.*
import com.ac.musicac.data.database.entity.AlbumEntity
import com.ac.musicac.data.database.entity.ArtistEntity
import kotlinx.coroutines.flow.Flow

@Dao
interface AlbumDao {

    @Query("SELECT * FROM AlbumEntity")
    fun getAll(): Flow<List<AlbumEntity>>

    @Query("SELECT * FROM AlbumEntity WHERE id = :id")
    fun findById(id: Int): Flow<AlbumEntity>

    @Query("SELECT * FROM AlbumEntity WHERE albumId = :albumId")
    fun findByAlbumId(albumId: String): Flow<AlbumEntity>

    @Query("SELECT COUNT(id) FROM AlbumEntity")
    suspend fun albumCount(): Int

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertAlbum(album: AlbumEntity)

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertAllAlbums(album: List<AlbumEntity>)

    @Update
    suspend fun updateAlbum(album: AlbumEntity)

    @Delete
    suspend fun delete(album: AlbumEntity)

    @Query("DELETE FROM AlbumEntity")
    suspend fun deleteAll()
}