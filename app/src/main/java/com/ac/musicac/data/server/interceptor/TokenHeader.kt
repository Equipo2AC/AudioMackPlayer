package com.ac.musicac.data.server.interceptor

import okhttp3.Interceptor

class TokenHeader : Interceptor {

    override fun intercept(chain: Interceptor.Chain) = chain.run {
        proceed(
            request()
                .newBuilder()
                .addHeader("Authorization", "Bearer BQAfAGv3Lg0BxuoCHWETQwic90yzUwt46MX58MZURV9Goun4HRxsFH2-x4Aalf7hcpH8bn33RuFir9gp-uY")
                .build()
        )
    }
}
