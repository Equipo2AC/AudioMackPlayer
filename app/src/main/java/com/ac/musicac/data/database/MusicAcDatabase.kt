package com.ac.musicac.data.database

import androidx.room.Database
import androidx.room.RoomDatabase
import com.ac.musicac.data.database.dao.AuthenticationDao
import com.ac.musicac.data.database.entity.AuthenticationEntity

@Database(entities = [AuthenticationEntity::class], version = 1, exportSchema = false)
abstract class MusicAcDatabase : RoomDatabase() {

    abstract fun authenticationDao(): AuthenticationDao
}
