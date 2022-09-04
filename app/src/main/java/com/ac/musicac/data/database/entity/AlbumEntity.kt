package com.ac.musicac.data.database.entity

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.ac.musicac.domain.Copyright
import com.ac.musicac.domain.ExternalIds
import com.ac.musicac.domain.ExternalUrls
import com.ac.musicac.domain.Image

@Entity
data class AlbumEntity(
    @PrimaryKey(autoGenerate = true) val id: Int,
    val albumType: String,
    val artists: String,
    val external_ids: String,
    val externalUrls: String,
    val genres: String,
    val href: String,
    val albumId: String,
    val imageUrl: String,
    val label: String,
    val name: String,
    val popularity: Int,
    val releaseDate: String,
    val releaseDatePrecision: String,
    val totalTracks: Int,
    val type: String,
    val uri: String
)
