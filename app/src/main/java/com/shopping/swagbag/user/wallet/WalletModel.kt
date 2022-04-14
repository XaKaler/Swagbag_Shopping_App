package com.shopping.swagbag.user.wallet


import com.google.gson.annotations.SerializedName

data class WalletModel(
    val balance: Int, // 0
    val result: List<Any>,
    val status: String, // success
    val totalCount: Int // 0
)