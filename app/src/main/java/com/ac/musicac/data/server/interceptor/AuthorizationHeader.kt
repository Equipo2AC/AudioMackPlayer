package com.ac.musicac.data.server.interceptor

import com.ac.musicac.data.encodeBase64
import com.ac.musicac.di.qualifier.ClientId
import com.ac.musicac.di.qualifier.ClientSecret
import okhttp3.Interceptor
import javax.inject.Inject

class AuthorizationHeader : Interceptor {
    @Inject
    @ClientId
    lateinit var clientId : String

    @Inject
    @ClientSecret
    lateinit var clientSecret : String

    private val authorization : String = "$clientId:$clientSecret"

    override fun intercept(chain: Interceptor.Chain) = chain.run {
        proceed(
            request()
                .newBuilder()
                .addHeader("Authorization", "Basic ${authorization.encodeBase64()}")
                .addHeader("Content-Type", "application/x-www-form-urlencoded")
                .build()
        )
    }
}
