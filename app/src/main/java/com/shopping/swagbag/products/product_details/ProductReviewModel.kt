package com.shopping.swagbag.products.product_details


import com.google.gson.annotations.SerializedName

data class ProductReviewModel(
    val message: String, // Review added
    val result: Result,
    val status: String // success
) {
    data class Result(
        val active: Int, // 0
        @SerializedName("created_date")
        val createdDate: String, // 2022-04-23T09:41:44.157Z
        val deleted: Int, // 0
        val email: String, // xakaler@gmail.com
        @SerializedName("_id")
        val id: String, // 6267b7fd90aabd5c4036bd07
        val mobile: String, // 1234567890
        val name: String, // xa
        val product: String, // 625e5f0f3bc36763b7938f58
        val rating: String, // 1.2
        val review: String, // 2
        val user: String, // 61e82a7acf130b2978d05815
        @SerializedName("__v")
        val v: Int // 0
    )
}