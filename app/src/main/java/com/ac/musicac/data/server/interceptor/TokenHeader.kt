package com.ac.musicac.data.server.interceptor

import okhttp3.Interceptor

class TokenHeader : Interceptor {

    override fun intercept(chain: Interceptor.Chain) = chain.run {
        proceed(
            request()
                .newBuilder()
                .addHeader("Authorization", "Bearer BQBKtPXPJnlEeXXcA9IpDEvUhOJi15HvXLbIMk_5Q7uGz2cJaagzkIuOzq-sOWzat8FDx2ozkgqB4RKyMUI")
                .build()
        )
    }
}
