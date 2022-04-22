package com.shopping.swagbag.user.shipping.checkout.payment


import com.google.gson.annotations.SerializedName

data class SampleResponseModel(
    @SerializedName("Transaction")
    val transaction: Transaction
) {
    data class Transaction(
        @SerializedName("Amount")
        val amount: Amount,
        @SerializedName("Balance")
        val balance: Balance,
        @SerializedName("Fees")
        val fees: Fees,
        @SerializedName("PaymentPage")
        val paymentPage: String, // https://demo-ipg.ctdev.comtrust.ae/PaymentEx/MerchantPay/Payment?lang=en&layout=C0STCBVLEI
        @SerializedName("PaymentPortal")
        val paymentPortal: String, // https://demo-ipg.ctdev.comtrust.ae/PaymentEx/MerchantPay/Payment?lang=en&layout=C0STCBVLEI
        @SerializedName("ResponseClass")
        val responseClass: String, // 0
        @SerializedName("ResponseClassDescription")
        val responseClassDescription: String, // Success
        @SerializedName("ResponseCode")
        val responseCode: String, // 0
        @SerializedName("ResponseDescription")
        val responseDescription: String, // Request Processed Successfully
        @SerializedName("TransactionID")
        val transactionID: String, // 238679106667
        @SerializedName("UniqueID")
        val uniqueID: String // 8347c09e-544b-430b-bd09-92edfb66861a
    ) {
        data class Amount(
            @SerializedName("Value")
            val value: String // 0
        )

        data class Balance(
            @SerializedName("Value")
            val value: String // 0
        )

        data class Fees(
            @SerializedName("Value")
            val value: String // 0
        )
    }
}