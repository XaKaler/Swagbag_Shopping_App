package com.shopping.swagbag.auth

import com.shopping.swagbag.auth.resetpassword.ResetPasswordModel
import com.shopping.swagbag.auth.signin.SignInModel
import com.shopping.swagbag.auth.signup.SignUpModel
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

    @FormUrlEncoded
    @POST("register")
    suspend fun register(
        @Field("fName")fName: String,
        @Field("lName")lName: String,
        @Field("email")email: String,
        @Field("password")password: String,
        @Field("reffer_by")reffer_by: String
    ): SignUpModel


    @FormUrlEncoded
    @POST("passwrodresetemailsend")
    suspend fun passwordResetEmailSend(
        @Field("email") email: String
    ): ResetPasswordModel

}