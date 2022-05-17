package com.ac.musicac.data.server.interceptor

import com.ac.musicac.data.database.dao.AuthenticationDao
import kotlinx.coroutines.runBlocking
import okhttp3.Interceptor
import javax.inject.Inject

class TokenHeader @Inject constructor(private val dao: AuthenticationDao) : Interceptor {

    override fun intercept(chain: Interceptor.Chain) = runBlocking {
        chain.run {
            val token = dao.getToken()
            val request = request()
                .newBuilder()
                .addHeader("Authorization", "Bearer ${token?.let { it.accessToken }}")
                .addHeader("Content-Type", "application/json")
                .build()
            proceed(request)
        }
    }
}
