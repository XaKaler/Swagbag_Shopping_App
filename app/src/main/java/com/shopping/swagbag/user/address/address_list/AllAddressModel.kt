package com.shopping.swagbag.user.address.address_list


import android.os.Parcelable
import com.google.gson.annotations.SerializedName
import kotlinx.android.parcel.Parcelize
import kotlinx.android.parcel.RawValue

@Parcelize
data class AllAddressModel(
    val message: String,
    val result: @RawValue List<Result>? = null,
    val status: String // success
): Parcelable {
    @Parcelize
    data class Result(
        val address: String, // Mahad, Maharashtra, India
        val address2: String, // TESt
        val city: String, // Umm Al Quwain
        @SerializedName("contact_mobile")
        val contactMobile: String, // 123456
        @SerializedName("contact_name")
        val contactName: String, // TETS
        val country: String, // UAE
        @SerializedName("created_date")
        val createdDate: String, // 2022-03-10T15:16:10.505Z
        val deleted: Int, // 0
        @SerializedName("_id")
        val id: String, // 622a1728bf969b3bd473c650
        val pincode: String, // dsad
        val position: Position,
        @SerializedName("post_office")
        val postOffice: String?,
        val state: String, // dsad
        val title: String, // dasd
        @SerializedName("update_date")
        val updateDate: String, // 2022-03-10T15:16:10.505Z
        val user: String, // 61e82a7acf130b2978d05815
        @SerializedName("__v")
        val v: Int // 0
    ): Parcelable {
        @Parcelize
        data class Position(
            val coordinates: List<Double>,
            val type: String // Point
        ): Parcelable
    }
}