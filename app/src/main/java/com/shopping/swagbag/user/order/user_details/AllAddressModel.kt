package com.shopping.swagbag.user.order.user_details


import android.os.Parcelable
import com.google.gson.annotations.SerializedName
import kotlinx.android.parcel.Parcelize

@Parcelize
data class AllAddressModel(
    val message: String,
    val result: List<Result>,
    val status: String // success
) : Parcelable {
    @Parcelize
    data class Result(
        val address: String, // Jaitpura
        val address2: String, // Kalera ka bas
        val city: String, // Jhunjhunu
        @SerializedName("contact_mobile")
        val contactMobile: String, // 9876543210
        @SerializedName("contact_name")
        val contactName: String, // Xa kaler
        val country: String,
        @SerializedName("created_date")
        val createdDate: String, // 2022-02-17T12:00:48.752Z
        val deleted: Int, // 0
        @SerializedName("_id")
        val id: String, // 620f57ab1992337485bb0faf
        val pincode: String, // 333001
        val position: Position,
        @SerializedName("post_office")
        val postOffice: String, // Luna
        val state: String, // Rajasthan
        val title: String, // Home
        @SerializedName("update_date")
        val updateDate: String, // 2022-02-17T12:00:48.752Z
        val user: String, // 61e82a7acf130b2978d05815
        @SerializedName("__v")
        val v: Int // 0
    ) : Parcelable {
        @Parcelize
        data class Position(
            val coordinates: List<Int>,
            val type: String // Point
        ) : Parcelable
    }
}