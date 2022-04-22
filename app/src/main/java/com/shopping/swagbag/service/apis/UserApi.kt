package com.shopping.swagbag.service.apis

import com.shopping.swagbag.user.auth.resetpassword.PasswordResetEmailSendModel
import com.shopping.swagbag.user.auth.resetpassword.PasswordResetModel
import com.shopping.swagbag.user.auth.signin.SignInModel
import com.shopping.swagbag.user.auth.signup.SignUpModel
import com.shopping.swagbag.user.order.user_details.AddAddressModel
import com.shopping.swagbag.user.order.user_details.AllAddressModel
import com.shopping.swagbag.user.order.user_details.DeleteAddressModel
import com.shopping.swagbag.user.order.user_details.EditAddressModel
import com.shopping.swagbag.user.profile.UserUpdateModel
import com.shopping.swagbag.user.wallet.WalletModel
import retrofit2.http.*

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
    ): PasswordResetEmailSendModel


    @FormUrlEncoded
    @POST("passwordreset")
    suspend fun passwordReset(
        @Field("email") email: String,
        @Field("otp") otp: String
    ): PasswordResetModel

    @FormUrlEncoded
    @PUT("user-update/{id}")
    suspend fun userUpdate(
        @Path("id")userId: String,
        @Header("authorization")token: String,
        @Field("fname")fname: String,
        @Field("lname")lname: String,
        @Field("email")emial: String,
    ): UserUpdateModel

    @FormUrlEncoded
    @POST("add-address")
    suspend fun addAddress(
        @Field("userid")userid: String,
        @Field("title")title: String,
        @Field("address")address: String,
        @Field("address2")address2: String,
        @Field("city")city: String,
        @Field("state")state: String,
        @Field("post_office")post_office: String,
        @Field("pincode")pincode: String,
        @Field("contact_name")contact_name: String,
        @Field("contact_mobile")contact_mobile: String,
        @Field("lat")lat: String,
        @Field("lng")lng: String,
    ): AddAddressModel

    @FormUrlEncoded
    @POST("edit-address")
    suspend fun editAddress(
        @Field("userid")userid: String,
        @Field("title")title: String,
        @Field("address")address: String,
        @Field("address2")address2: String,
        @Field("city")city: String,
        @Field("state")state: String,
        @Field("post_office")post_office: String,
        @Field("pincode")pincode: String,
        @Field("contact_name")contact_name: String,
        @Field("contact_mobile")contact_mobile: String,
        @Field("id")addressId: String,
        @Field("lat")lat: String,
        @Field("lng")lng: String
    ): EditAddressModel


    @GET("all-address")
    suspend fun allAddress(
        @Query("id")userId: String
    ): AllAddressModel

    @GET("delete-address")
    suspend fun deleteAddress(
        @Query("id")addressId: String
    ): DeleteAddressModel

    @GET("wallet-transactions")
    suspend fun wallet(
        @Query("id")userId: String,
        @Query("limit")limit: String,
        @Query("page")page: String
    ): WalletModel
}