package com.ac.musicac.data.server.interceptor

import okhttp3.Interceptor

class TokenHeader : Interceptor {

    override fun intercept(chain: Interceptor.Chain) = chain.run {
        proceed(
            request()
                .newBuilder()
                .addHeader("Authorization", "Bearer BQD39cM_hvpJC9LpXvC2da5Ywc18P6Mme2o1JGEW0nBe-Hw6Zgu9mbcSM68L-7FO6bmjPmcNDjwxPdjilGY")
                .build()
        )
    }
}
