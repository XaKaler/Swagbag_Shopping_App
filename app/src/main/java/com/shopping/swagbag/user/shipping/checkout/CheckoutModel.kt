package com.shopping.swagbag.user.shipping.checkout


import com.google.gson.annotations.SerializedName

data class CheckoutModel(
    val message: String, // checkout success
    val orderId: String, // SB-1648027211
    val status: String // success
)