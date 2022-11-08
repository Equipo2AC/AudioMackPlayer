package com.ac.musicac.data.database.dao

import androidx.room.*
import com.ac.musicac.data.database.entity.ArtistEntity
import kotlinx.coroutines.flow.Flow

@Dao
interface ArtistDao {

    @Query("SELECT * FROM ArtistEntity")
    fun getAll(): Flow<List<ArtistEntity>>

    @Query("SELECT * FROM ArtistEntity WHERE id = :id")
    fun findById(id: Int): Flow<ArtistEntity>

    @Query("SELECT * FROM ArtistEntity WHERE artistId = :artistId")
    fun findByArtistId(artistId: String): Flow<ArtistEntity>

    @Query("SELECT COUNT(id) FROM ArtistEntity")
    suspend fun artistCount(): Int

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertArtist(artist: ArtistEntity)

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertAllArtist(artist: List<ArtistEntity>)

    @Update
    suspend fun updateArtist(artist: ArtistEntity)

    @Delete
    suspend fun delete(artist: ArtistEntity)

    @Query("DELETE FROM ArtistEntity")
    suspend fun deleteAll()
}