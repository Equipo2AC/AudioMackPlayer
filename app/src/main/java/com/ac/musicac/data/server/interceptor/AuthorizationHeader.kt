package com.ac.musicac.data.server.interceptor

import com.ac.musicac.data.encodeBase64
import okhttp3.Interceptor

class AuthorizationHeader(
    clientId: String,
    clientSecret: String
) : Interceptor {

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
