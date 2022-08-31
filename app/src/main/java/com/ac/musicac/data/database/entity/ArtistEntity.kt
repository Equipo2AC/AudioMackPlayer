package com.ac.musicac.data.database.entity

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.ac.musicac.domain.ExternalUrls
import com.ac.musicac.domain.Followers
import com.ac.musicac.domain.Image

@Entity
data class ArtistEntity(
    @PrimaryKey(autoGenerate = true) val id: Int,
    val externalUrls: ExternalUrls,
    val followers: Followers,
    val genres: List<String>,
    val href: String,
    val artistId: String,
    val images: List<Image>,
    val name: String,
    val popularity: Int,
    val type: String,
    val uri: String
)
