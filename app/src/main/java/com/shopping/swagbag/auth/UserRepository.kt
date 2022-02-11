package com.shopping.swagbag.auth

import com.shopping.swagbag.common.base.BaseRepository

class UserRepository(private val api: UserApi) : BaseRepository() {

    suspend fun getUserLogin(
        email: String,
        password: String
    ) = safeApiCall { api.getUserLogin(email, password) }

    suspend fun register(
        fName: String,
        lName: String,
        email: String,
        password: String,
        reffer_by: String,
    ) = safeApiCall { api.register(fName, lName, email, password, reffer_by) }

    suspend fun passwordResetEmailSend(
        email: String
    ) = safeApiCall { api.passwordResetEmailSend(email) }

    suspend fun passwordReset(
        email: String,
        otp: String
    ) = safeApiCall { api.passwordReset(email, otp) }
}