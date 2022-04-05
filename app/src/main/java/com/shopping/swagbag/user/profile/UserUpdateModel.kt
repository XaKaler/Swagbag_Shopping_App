package com.shopping.swagbag.user.profile


import com.google.gson.annotations.SerializedName

data class UserUpdateModel(
    val about: String,
    @SerializedName("acc_holder_name")
    val accHolderName: Any?, // null
    @SerializedName("acc_number")
    val accNumber: Any?, // null
    val active: Int, // 1
    @SerializedName("additional_cost1")
    val additionalCost1: Int, // 0
    @SerializedName("additional_cost2")
    val additionalCost2: Int, // 0
    val address: String,
    @SerializedName("bank_address")
    val bankAddress: Any?, // null
    @SerializedName("bank_name")
    val bankName: Any?, // null
    @SerializedName("branch_code")
    val branchCode: Any?, // null
    @SerializedName("cargo_position")
    val cargoPosition: CargoPosition,
    val city: Any?, // null
    val cod: Int, // 1
    @SerializedName("cod_order_cost")
    val codOrderCost: Int, // 0
    val commission: String,
    @SerializedName("commission_type")
    val commissionType: String,
    @SerializedName("communication_zipcode")
    val communicationZipcode: String,
    @SerializedName("created_date")
    val createdDate: String, // 2022-01-19T15:12:20.917Z
    @SerializedName("customer_support_city")
    val customerSupportCity: List<Any>,
    @SerializedName("deactivation_reason")
    val deactivationReason: String,
    val deleted: Int, // 0
    @SerializedName("delivery_boy")
    val deliveryBoy: List<Any>,
    @SerializedName("delivery_city")
    val deliveryCity: List<Any>,
    @SerializedName("delivery_partner_position")
    val deliveryPartnerPosition: DeliveryPartnerPosition,
    @SerializedName("device_token")
    val deviceToken: String,
    @SerializedName("device_type")
    val deviceType: String,
    val doc1: String,
    @SerializedName("doc1_type")
    val doc1Type: String,
    val doc2: String,
    @SerializedName("doc2_type")
    val doc2Type: String,
    val doc3: String,
    @SerializedName("doc3_type")
    val doc3Type: String,
    val email: String, // test@gmail.com
    @SerializedName("email_otp")
    val emailOtp: Int, // 408167
    val `file`: String,
    @SerializedName("first_time")
    val firstTime: Int, // 0
    val fname: String, // Super
    @SerializedName("full_name")
    val fullName: String, // User Name
    @SerializedName("_id")
    val id: String, // 61e82a7acf130b2978d05815
    val ifsc: Any?, // null
    val lname: String, // Admin
    @SerializedName("login_active")
    val loginActive: Int, // 1
    @SerializedName("lp_manager")
    val lpManager: List<Any>,
    val master: Any?, // null
    val mobile: String,
    @SerializedName("monthly_fixed_cost")
    val monthlyFixedCost: String, // 0
    val note: String,
    val office: Any?, // null
    val otp: Int, // 0
    val password: String, // $2a$10$7.bt1lfHeka7cUM1wKogy.pJRjYcInKHGUwjCVj1f79Tz84F00Cdq
    @SerializedName("price_per_kg")
    val pricePerKg: String, // 0
    @SerializedName("price_per_pack")
    val pricePerPack: String, // 0
    @SerializedName("profile_image")
    val profileImage: String,
    @SerializedName("rate_per_km")
    val ratePerKm: String, // 0
    @SerializedName("reffer_by")
    val refferBy: String, // 12345678900
    @SerializedName("reffer_code")
    val refferCode: String,
    val role: Any?, // null
    val salary: String,
    @SerializedName("seo_desc")
    val seoDesc: String,
    @SerializedName("seo_title")
    val seoTitle: String,
    @SerializedName("service_zipcode")
    val serviceZipcode: String,
    val slug: String,
    val subscription: Subscription,
    @SerializedName("update_date")
    val updateDate: String, // 2022-03-01T16:51:01.000Z
    @SerializedName("user_type")
    val userType: String, // customer
    @SerializedName("__v")
    val v: Int, // 0
    @SerializedName("vendor_position")
    val vendorPosition: VendorPosition,
    val website: String
) {
    data class CargoPosition(
        val coordinates: List<Int>,
        val type: String // Point
    )

    data class DeliveryPartnerPosition(
        val coordinates: List<Int>,
        val type: String // Point
    )

    data class Subscription(
        @SerializedName("exp_date")
        val expDate: Any?, // null
        val key: String,
        val plan: Any?, // null
        val point: Int, // 95
        val updated: Any? // null
    )

    data class VendorPosition(
        val coordinates: List<Int>,
        val type: String // Point
    )
}