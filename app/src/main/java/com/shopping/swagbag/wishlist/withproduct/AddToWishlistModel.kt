package com.shopping.swagbag.wishlist.withproduct


import com.google.gson.annotations.SerializedName

data class AddToWishlistModel(
    val message: String, // product has been added to wish list
    val qty: Int, // 0
    val status: String // success
)