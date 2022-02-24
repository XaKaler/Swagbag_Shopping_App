package com.shopping.swagbag.coupons


import com.google.gson.annotations.SerializedName

data class GiftCardModel(
    val result: List<Result>,
    val status: String // success
) {
    data class Result(
        val active: Int, // 1
        val amount: String, // 1000
        val brand: Any?, // null
        val category: Any?, // null
        val coupon: String, // 1000 USD
        @SerializedName("created_date")
        val createdDate: String, // 2022-01-17T14:31:35.628Z
        val customer: Any?, // null
        val deleted: Int, // 0
        val desc: String,
        @SerializedName("exp_date")
        val expDate: String, // 2022-01-23T00:00:00.000Z
        val `file`: String, // https://swagbag.sgp1.digitaloceanspaces.com/1642430154677hw49j.webp
        @SerializedName("gift_card")
        val giftCard: String, // Yes
        @SerializedName("_id")
        val id: String, // 61e57eca0ee4253f3804c31f
        @SerializedName("max_usage_limit")
        val maxUsageLimit: Int, // 1
        @SerializedName("max_usage_limit_user")
        val maxUsageLimitUser: Int, // 1
        @SerializedName("maximum_amount")
        val maximumAmount: String, // 100000
        @SerializedName("minimum_amount")
        val minimumAmount: String, // 0
        val product: Any?, // null
        @SerializedName("start_date")
        val startDate: String, // 2022-01-17T14:31:35.628Z
        val type: String, // F
        @SerializedName("update_date")
        val updateDate: String, // 2022-01-17T15:11:48.000Z
        @SerializedName("__v")
        val v: Int // 0
    )
}