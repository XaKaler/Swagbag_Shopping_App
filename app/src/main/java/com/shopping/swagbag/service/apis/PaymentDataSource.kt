package com.shopping.swagbag.service.apis

import com.google.gson.GsonBuilder
import com.google.gson.JsonObject
import com.shopping.swagbag.user.shipping.checkout.payment.SampleRequestModel
import com.shopping.swagbag.user.shipping.checkout.payment.SampleResponseModel
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.Body
import retrofit2.http.Headers
import retrofit2.http.POST

class PaymentDataSource {

    companion object{
       private const val base_url = "https://demo-ipg.ctdev.comtrust.ae:2443"
    }

    fun getBaseUrl(): Retrofit {
        val gson = GsonBuilder()
            .setLenient()
            .create()

        val retrofit: Retrofit =
            Retrofit.Builder()
                .baseUrl(base_url)
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

interface PaymentApi{
    @Headers(
        "Accept: application/json",
        "Content-Type: application/json"
    )
    @POST(".")
    suspend fun paymentRequest(@Body sampleRequestModel: SampleRequestModel): SampleResponseModel
}