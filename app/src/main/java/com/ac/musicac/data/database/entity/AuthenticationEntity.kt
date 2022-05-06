package com.ac.musicac.data.database.entity

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class AuthenticationEntity(
    @PrimaryKey(autoGenerate = true) val id: Int,
    val accessToken: String,
    val tokenType: String,
    val expirationDate: Long,
)