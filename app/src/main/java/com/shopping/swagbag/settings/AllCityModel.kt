package com.shopping.swagbag.settings


import com.google.gson.annotations.SerializedName

data class AllCityModel(
    val message: String,
    val result: List<Result>,
    val status: String // success
) {
    data class Result(
        val active: Int, // 1
        @SerializedName("additional_cost")
        val additionalCost: String, // 10
        val cod: Boolean, // true
        val country: String, // 61f570c3a67cc11f2c51b9d8
        @SerializedName("created_date")
        val createdDate: String, // 2022-01-29T17:42:59.304Z
        val deleted: Int, // 0
        val description: String,
        @SerializedName("description_after_content")
        val descriptionAfterContent: String,
        val `file`: Any?, // null
        val file2: Any?, // null
        val footer: Int, // 1
        @SerializedName("footer_content")
        val footerContent: String,
        val heading: String,
        @SerializedName("_id")
        val id: String, // 61f57d43c99c3d17d8126c09
        val name: String, // Abu Dhabi
        val ps: String, // both
        val slug: String, // abu-dhabi
        @SerializedName("slug_history")
        val slugHistory: List<String>,
        val state: Any?, // null
        @SerializedName("sub_heading")
        val subHeading: String,
        @SerializedName("update_date")
        val updateDate: String, // 2022-01-29T17:52:59.000Z
        @SerializedName("__v")
        val v: Int // 0
    )
}