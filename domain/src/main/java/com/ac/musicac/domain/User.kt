package com.ac.musicac.domain

data class User(
    val id: String,
    val displayName: String?,
    val followers : Int,
    val imageUrl: String
)
