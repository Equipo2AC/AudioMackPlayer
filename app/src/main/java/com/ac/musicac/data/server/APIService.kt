package com.ac.musicac.data.server

import com.ac.musicac.data.Constants.CONNECT_TIME_OUT
import com.ac.musicac.data.Constants.READ_TIME_OUT
import com.ac.musicac.data.Constants.WRITE_TIME_OUT
import okhttp3.Interceptor
import okhttp3.OkHttpClient
import retrofit2.Converter
import retrofit2.Retrofit
import java.util.concurrent.TimeUnit

open class APIService<T> constructor(
    serviceClass: Class<T>,
    private val baseURL: String,
    private val converterFactory: Converter.Factory,
    private val interceptors: Array<Interceptor>
) {
    val service = initApiService().create(serviceClass)

    private fun initApiService(): Retrofit {
        val client = OkHttpClient.Builder()
        client.readTimeout(READ_TIME_OUT, TimeUnit.SECONDS)
        client.writeTimeout(WRITE_TIME_OUT, TimeUnit.SECONDS)
        client.connectTimeout(CONNECT_TIME_OUT, TimeUnit.SECONDS)

        interceptors.map { client.addInterceptor(it) }

        return Retrofit.Builder()
            .baseUrl(baseURL)
            .addConverterFactory(converterFactory)
            .client(client.build())
            .build()
    }

}
