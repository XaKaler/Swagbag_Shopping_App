package com.shopping.swagbag.user.shipping.checkout


import com.google.gson.annotations.SerializedName

data class CheckoutConfirmModel(
    val message: String, // order confirmed
    val result: Result,
    val status: String // success
) {
    data class Result(
        val address: String, // 622a1728bf969b3bd473c650
        val balance: String,
        val coupon: String,
        val couponamount: String,
        val coupontype: String,
        @SerializedName("created_date")
        val createdDate: String, // 2022-03-23T09:20:11.203Z
        val deleted: Int, // 0
        val finalprice: String,
        val gateway: String, // COD
        @SerializedName("gift_code")
        val giftCode: String,
        @SerializedName("gift_email")
        val giftEmail: String,
        @SerializedName("gift_fname")
        val giftFname: String,
        @SerializedName("gift_lname")
        val giftLname: String,
        @SerializedName("gift_message")
        val giftMessage: String,
        @SerializedName("gift_phone")
        val giftPhone: String,
        val gitwrap: Any?, // null
        @SerializedName("_id")
        val id: String, // 623ae64b2ff8134a95c5886b
        val note: String,
        val orderid: String, // SB-1648027211
        val otp: String,
        val packing: String,
        val paid: Int, // 0
        val price: String,
        val products: List<Any>,
        @SerializedName("return_products")
        val returnProducts: List<Any>,
        @SerializedName("return_reason")
        val returnReason: String,
        val shipping: String,
        val status: String, // processing
        val transactionid: String,
        val type: String, // Normal
        @SerializedName("update_date")
        val updateDate: String, // 2022-03-23T09:20:11.203Z
        val user: String, // 61e82a7acf130b2978d05815
        @SerializedName("__v")
        val v: Int // 0
    )
}