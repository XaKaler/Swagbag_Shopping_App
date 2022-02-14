package com.shopping.swagbag.brand


import com.google.gson.annotations.SerializedName

data class BrandModel(
    val message: String,
    val result: List<Result>,
    val status: String // success
) {
    data class Result(
        val active: Int, // 1
        @SerializedName("created_date")
        val createdDate: String, // 2022-02-02T17:39:27.592Z
        val deleted: Int, // 0
        val desc: String, // Make up items
        val `file`: Any?, // null
        @SerializedName("_id")
        val id: String, // 61fb7d193669ad2911a25e72
        val name: String, // Chado
        @SerializedName("short_desc")
        val shortDesc: String,
        val slug: String, // chado
        @SerializedName("slug_history")
        val slugHistory: List<String>,
        @SerializedName("update_date")
        val updateDate: String, // 2022-02-02T17:39:27.592Z
        @SerializedName("__v")
        val v: Int // 0
    )
}