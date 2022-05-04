package com.shopping.swagbag.service.apis

import com.shopping.swagbag.settings.SettingsModel
import com.shopping.swagbag.coupons.GiftCardModel
import com.shopping.swagbag.settings.AllCityModel
import com.shopping.swagbag.settings.AllCountryModel
import retrofit2.http.GET
import retrofit2.http.Query

interface SettingApi {

    @GET("gift-card")
    suspend fun giftCard(): GiftCardModel

    @GET("settings")
    suspend fun settings(): SettingsModel

    @GET("all-country")
    suspend fun allCountry(): AllCountryModel

    @GET("all-city")
    suspend fun allCity(
        @Query("id")cityId: String
    ): AllCityModel
}