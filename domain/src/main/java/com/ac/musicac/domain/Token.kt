package com.ac.musicac.domain

import java.util.Date

data class Token(
    val value: String,
    val type: String,
    val expirationDate: Date
)
