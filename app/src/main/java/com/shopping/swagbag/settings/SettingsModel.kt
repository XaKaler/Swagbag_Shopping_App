package com.shopping.swagbag.settings


import com.google.gson.annotations.SerializedName

data class SettingsModel(
    @SerializedName("deactivate_reason")
    val deactivateReason: List<DeactivateReason>,
    val result: List<Result>,
    @SerializedName("return_reason")
    val returnReason: List<ReturnReason>,
    val status: String // success
) {
    data class DeactivateReason(
        val active: Int, // 1
        @SerializedName("created_date")
        val createdDate: String, // 2022-02-10T15:37:46.629Z
        val deleted: Int, // 0
        val `file`: String,
        @SerializedName("_id")
        val id: String, // 62053157d4661f2fc89589b1
        val name: String, // To many emaill
        @SerializedName("update_date")
        val updateDate: String, // 2022-02-10T15:38:30.000Z
        @SerializedName("__v")
        val v: Int // 0
    )

    data class Result(
        @SerializedName("_id")
        val id: String, // 61a37cec653b6b5b30b34255
        val name: String, // Shipping
        val order: Int, // 7
        val value: String // 5
    )

    data class ReturnReason(
        val active: Int, // 1
        @SerializedName("created_date")
        val createdDate: String, // 2022-02-11T07:43:11.535Z
        val deleted: Int, // 0
        val `file`: String,
        @SerializedName("_id")
        val id: String, // 62061959d39da43618b1a257
        val name: String, // Item/ Box is damaged
        @SerializedName("update_date")
        val updateDate: String, // 2022-02-11T07:43:11.535Z
        @SerializedName("__v")
        val v: Int // 0
    )
}