package com.ac.musicac.data.datasource

interface LocationDataSource {
    suspend fun findLastRegion(): String?
}