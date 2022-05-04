package com.shopping.swagbag.settings


import com.google.gson.annotations.SerializedName

data class AllCountryModel(
    val message: String,
    val result: List<Result>,
    val status: String // success
) {
    data class Result(
        val active: Int, // 1
        @SerializedName("country_code")
        val countryCode: String, // +971
        @SerializedName("created_date")
        val createdDate: String, // 2022-01-29T16:32:58.620Z
        val deleted: Int, // 0
        @SerializedName("_id")
        val id: String, // 61f570c3a67cc11f2c51b9d8
        val name: String, // UAE
        @SerializedName("price_cubic_cm")
        val priceCubicCm: String, // 2
        @SerializedName("price_per_kg")
        val pricePerKg: String, // 2
        @SerializedName("__v")
        val v: Int // 0
    )
}