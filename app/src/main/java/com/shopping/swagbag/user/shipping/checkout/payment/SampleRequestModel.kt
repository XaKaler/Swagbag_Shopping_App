package com.shopping.swagbag.user.shipping.checkout.payment


import com.google.gson.annotations.SerializedName

data class SampleRequestModel(
    @SerializedName("Registration")
    val registration: Registration
) {
    data class Registration(
        @SerializedName("Amount")
        val amount: String, // 2.00
        @SerializedName("Channel")
        val channel: String, // Web
        @SerializedName("Currency")
        val currency: String, // AED
        @SerializedName("Customer")
        val customer: String, // Demo Merchant
        @SerializedName("OrderID")
        val orderID: String, // 7210055701315195
        @SerializedName("OrderName")
        val orderName: String, // Paybill
        @SerializedName("Password")
        val password: String, // Comtrust@20182018
        @SerializedName("ReturnPath")
        val returnPath: String, // https://localhost/callbackURL
        @SerializedName("Store")
        val store: String, // 0000
        @SerializedName("Terminal")
        val terminal: String, // 0000
        @SerializedName("TransactionHint")
        val transactionHint: String, // CPT:Y;VCC:Y;
        @SerializedName("UserName")
        val userName: String // Demo_fY9c
    )
}