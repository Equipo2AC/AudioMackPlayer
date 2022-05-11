package com.ac.musicac.data.datasource

import com.ac.musicac.domain.Error
import com.ac.musicac.domain.Token

interface AuthenticationLocalDataSource {
    suspend fun save(token: Token): Error?
}
