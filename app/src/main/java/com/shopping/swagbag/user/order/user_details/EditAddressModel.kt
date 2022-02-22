package com.shopping.swagbag.user.order.user_details


import com.google.gson.annotations.SerializedName

data class EditAddressModel(
    val message: String, // Address has been updated.
    val status: String // success
)