package com.ac.musicac.data.database

import androidx.room.Database
import androidx.room.RoomDatabase
import com.ac.musicac.data.database.dao.AlbumDao
import com.ac.musicac.data.database.dao.ArtistDao
import com.ac.musicac.data.database.dao.AuthenticationDao
import com.ac.musicac.data.database.entity.AlbumEntity
import com.ac.musicac.data.database.entity.ArtistEntity
import com.ac.musicac.data.database.entity.AuthenticationEntity

@Database(entities = [AuthenticationEntity::class, ArtistEntity::class, AlbumEntity::class], version = 1, exportSchema = false)
abstract class MusicAcDatabase : RoomDatabase() {

    abstract fun authenticationDao(): AuthenticationDao
    abstract fun artistDao(): ArtistDao
    abstract fun albumDao(): AlbumDao

}
