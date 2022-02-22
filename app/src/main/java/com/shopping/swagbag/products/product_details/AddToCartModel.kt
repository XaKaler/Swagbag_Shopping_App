package com.shopping.swagbag.products.product_details


import com.google.gson.annotations.SerializedName

data class AddToCartModel(
    val message: String, // cart added
    val qty: Int, // 4
    val status: String // success
)