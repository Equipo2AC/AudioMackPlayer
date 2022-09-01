package com.ac.musicac.data.database.entity

import androidx.room.Entity

@Entity
data class ImageEntity (
    val height: Int,
    val url: String,
    val width: Int
    )