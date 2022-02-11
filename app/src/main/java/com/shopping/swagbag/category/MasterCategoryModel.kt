package com.shopping.swagbag.category


import com.google.gson.annotations.SerializedName

data class MasterCategoryModel(
    val message: String,
    val result: List<Result>,
    val status: String // success
) {
    data class Result(
        val active: Int, // 1
        @SerializedName("created_date")
        val createdDate: String, // 2021-12-15T14:49:50.596Z
        val deleted: Int, // 0
        val desc: String, // women
        val `file`: String, // https://swagbag-space.fra1.digitaloceanspaces.com/1639940716133rq5w5.png
        @SerializedName("_id")
        val id: String, // 61bd25a466538a1c1f366ac3
        val name: String, // Women
        @SerializedName("short_desc")
        val shortDesc: String, // women
        val slug: String, // demo
        @SerializedName("slug_history")
        val slugHistory: List<String>,
        val sort: Int, // 2
        @SerializedName("update_date")
        val updateDate: String, // 2021-12-19T19:05:19.000Z
        @SerializedName("__v")
        val v: Int // 0
    )
}