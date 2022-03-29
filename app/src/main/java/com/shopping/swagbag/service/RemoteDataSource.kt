package com.shopping.swagbag.service

import com.google.gson.GsonBuilder
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory


class RemoteDataSource {
    companion object {
        private const val baseUrl = "https://api.swagbag.com/api/"
    }

    fun getBaseUrl(): Retrofit {
        val gson = GsonBuilder()
            .setLenient()
            .create()

        val retrofit: Retrofit =
            Retrofit.Builder()
                .baseUrl(baseUrl)
                .client(OkHttpClient.Builder().also { client ->
                    val logging = HttpLoggingInterceptor()
                    logging.setLevel(HttpLoggingInterceptor.Level.BODY)
                    client.addInterceptor(logging)
                }.build())
                .addConverterFactory(GsonConverterFactory.create(gson))
                .build()

        return retrofit
    }
}
