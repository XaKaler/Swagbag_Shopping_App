package com.shopping.swagbag.service.apis

import com.shopping.swagbag.settings.SettingsModel
import com.shopping.swagbag.coupons.GiftCardModel
import retrofit2.http.GET

interface SettingApi {

    @GET("gift-card")
    suspend fun giftCard(): GiftCardModel

    @GET("settings")
    suspend fun settings(): SettingsModel
}