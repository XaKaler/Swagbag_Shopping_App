package com.shopping.swagbag.wishlist.withproduct


import com.google.gson.annotations.SerializedName

data class DeleteSingleWishModel(
    val message: String, // Product deleted successfully from wish list
    val result: Any?, // null
    val status: String // success
)