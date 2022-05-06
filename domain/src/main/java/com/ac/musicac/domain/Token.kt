package com.ac.musicac.domain

import java.util.*

data class Token(
    var value: String,
    var type: String,
    var expirationDate: Date
)