package com.shopping.swagbag

import com.shopping.swagbag.coupons.GiftCardModel
import retrofit2.http.Field
import retrofit2.http.FormUrlEncoded
import retrofit2.http.GET

interface SettingApi {

    @GET("gift-card")
    suspend fun giftCard(): GiftCardModel

    @FormUrlEncoded
    @GET("settings")
    suspend fun settings(@Field("id")userId: String): SettingsModel
}