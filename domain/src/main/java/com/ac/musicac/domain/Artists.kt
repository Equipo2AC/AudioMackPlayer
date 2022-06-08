package com.ac.musicac.domain

data class Artists(
    val href: String,
    val items: List<Item>,
    val limit: Int,
    val next: String,
    val offset: Int,
    val previous: String? = "",
    val total: Int
)