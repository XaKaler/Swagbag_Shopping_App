package com.shopping.swagbag.auth.signin

import com.shopping.swagbag.auth.UserApi
import com.shopping.swagbag.common.base.BaseRepository

class UserRepository(private val api: UserApi) : BaseRepository() {

    suspend fun getUserLogin(
        email: String,
        password: String
    ) = safeApiCall { api.getUserLogin(email, password) }
}