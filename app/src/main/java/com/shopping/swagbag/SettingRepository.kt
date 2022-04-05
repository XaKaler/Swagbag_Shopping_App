package com.shopping.swagbag

import com.shopping.swagbag.common.base.BaseRepository

class SettingRepository(private val api: SettingApi) : BaseRepository() {

    suspend fun giftCard() = safeApiCall { api.giftCard() }

    suspend fun settings(userId: String) = safeApiCall { api.settings(userId) }
}