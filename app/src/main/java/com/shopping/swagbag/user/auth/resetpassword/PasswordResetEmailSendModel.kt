package com.shopping.swagbag.user.auth.resetpassword


data class PasswordResetEmailSendModel(
    val message: String, // OTP has been send to your email address
    val status: String // success
)