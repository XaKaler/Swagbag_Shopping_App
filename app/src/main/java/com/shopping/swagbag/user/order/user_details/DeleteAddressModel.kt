package com.shopping.swagbag.user.order.user_details


import com.google.gson.annotations.SerializedName

data class DeleteAddressModel(
    val message: String, // Address has been deleted.
    val status: String // success
)