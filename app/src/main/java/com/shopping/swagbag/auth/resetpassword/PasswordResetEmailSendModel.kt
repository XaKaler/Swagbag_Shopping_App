package com.shopping.swagbag.auth.resetpassword


import com.google.gson.annotations.SerializedName

data class PasswordResetEmailSendModel(
    val message: String, // OTP has been send to your email address
    val status: String // success
)