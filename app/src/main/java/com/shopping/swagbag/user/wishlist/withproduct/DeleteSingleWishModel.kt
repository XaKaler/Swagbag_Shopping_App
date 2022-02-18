package com.shopping.swagbag.user.wishlist.withproduct


data class DeleteSingleWishModel(
    val message: String, // Product deleted successfully from wish list
    val result: Any?, // null
    val status: String // success
)