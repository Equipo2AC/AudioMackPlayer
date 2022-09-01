package com.ac.musicac.data.database.entity

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.ac.musicac.domain.ExternalUrls
import com.ac.musicac.domain.Image

@Entity
data class AlbumEntity(
    @PrimaryKey(autoGenerate = true) val id: Int,
    val albumType: String,
    val artists: String,
    // val availableMarkets: List<String>,
    val externalUrls: String,
    val href: String,
    val albumId: String,
    val imageUrl: String,
    val name: String,
    val releaseDate: String,
    val releaseDatePrecision: String,
    val totalTracks: Int,
    val type: String,
    val uri: String,
    val followers: Int,
    val genres: String
)
