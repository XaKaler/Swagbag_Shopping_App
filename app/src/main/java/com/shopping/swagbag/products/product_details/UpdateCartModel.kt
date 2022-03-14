package com.shopping.swagbag.products.product_details


import com.google.gson.annotations.SerializedName

data class UpdateCartModel(
    val message: String, // Cart updated.
    val status: String // success
)