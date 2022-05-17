package com.ac.musicac.data.database.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.ac.musicac.data.database.entity.AuthenticationEntity

@Dao
interface AuthenticationDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertToken(token: AuthenticationEntity)

    @Query("SELECT COUNT(id) FROM AuthenticationEntity")
    suspend fun tokenCount(): Int

    @Query("SELECT * FROM AuthenticationEntity ORDER BY expirationDate DESC LIMIT 1")
    suspend fun getToken(): AuthenticationEntity

    @Query("DELETE FROM AuthenticationEntity WHERE expirationDate > :currentDateTime")
    suspend fun deleteTokens(currentDateTime: Long)
}
