package com.shopping.swagbag.user.wishlist.withproduct


data class AddToWishlistModel(
    val message: String, // product has been added to wish list
    val qty: Int, // 0
    val status: String // success
)