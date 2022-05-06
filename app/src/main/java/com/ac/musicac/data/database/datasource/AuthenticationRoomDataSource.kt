package com.ac.musicac.data.database.datasource

import com.ac.musicac.data.database.dao.AuthenticationDao
import com.ac.musicac.data.database.entity.AuthenticationEntity
import com.ac.musicac.data.datasource.AuthenticationLocalDataSource
import com.ac.musicac.data.tryCall
import com.ac.musicac.domain.Error
import com.ac.musicac.domain.Token
import javax.inject.Inject

class AuthenticationRoomDataSource @Inject constructor(
    private val authenticationDao: AuthenticationDao) : AuthenticationLocalDataSource{

    override suspend fun save(token: Token): Error? = tryCall{
       authenticationDao.insertToken(token.fromDomainModel())
    }.fold(
        ifLeft = { it },
        ifRight = { null }
    )

    private fun Token.fromDomainModel(): AuthenticationEntity = AuthenticationEntity(
        0,
        value,
        type,
        expirationDate.time
    )
}
