package com.shopping.swagbag.auth

import com.shopping.swagbag.auth.signin.SignInModel
import com.shopping.swagbag.category.CategoryModel
import retrofit2.http.Field
import retrofit2.http.FormUrlEncoded
import retrofit2.http.GET
import retrofit2.http.POST

interface UserApi {

    @FormUrlEncoded
    @POST("login")
    suspend fun getUserLogin(
        @Field("email")email: String,
        @Field("password")password: String
    ): SignInModel
}