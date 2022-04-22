package com.shopping.swagbag.common.model

import com.google.gson.annotations.SerializedName

data class TopTrendingModel(
    val active: Int, // 1
    val brand: String, // 616889f3e0f5f5b576434121
    val category: String, // 618e6d4bae7db56d2c44981e
    @SerializedName("created_date")
    val createdDate: String, // 2022-02-16T14:51:03.693Z
    val deleted: Int, // 0
    val `file`: String, // https://swagbag.sgp1.digitaloceanspaces.com/1645023075012ey61h.webp
    @SerializedName("_id")
    val id: String, // 6210b7f15f5e06bee40138b6
    @SerializedName("master_category")
    val masterCategory: String, // 61bd25a466538a1c1f366ac3
    val product: String, // 6148c9ebaaa0e6b191f8bf8a
    val section: Int, // 1
    @SerializedName("update_date")
    val updateDate: String, // 2022-02-19T09:13:46.000Z
    val url: String,
    @SerializedName("__v")
    val v: Int // 0
)
