package com.shopping.swagbag.auth.resetpassword


import com.google.gson.annotations.SerializedName

data class PasswordResetModel(
    val message: String, // Invalid OTP
    val status: String // error
)