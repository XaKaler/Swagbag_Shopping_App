package com.shopping.swagbag.service
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class RetrofitSingleton {
    companion object{
        private const val baseUrl = "https://api.swagbag.com/api/"

        private fun getBaseUrl():Retrofit =
            Retrofit.Builder()
            .baseUrl(baseUrl)
            .addConverterFactory(GsonConverterFactory.create())
            .build()

        fun getRetroApi(): ApiService = getBaseUrl().create(ApiService::class.java)
    }
}
