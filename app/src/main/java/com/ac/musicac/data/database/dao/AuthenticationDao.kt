package com.ac.musicac.data.database.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import com.ac.musicac.data.database.entity.AuthenticationEntity

@Dao
interface AuthenticationDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertToken(token: AuthenticationEntity)
}