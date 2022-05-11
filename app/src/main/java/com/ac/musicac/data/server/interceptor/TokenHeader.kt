package com.ac.musicac.data.server.interceptor

import okhttp3.Interceptor

class TokenHeader : Interceptor {

    override fun intercept(chain: Interceptor.Chain) = chain.run {
        proceed(
            request()
                .newBuilder()
                .addHeader("Authorization", "Bearer Token")
                .build()
        )
    }
}
