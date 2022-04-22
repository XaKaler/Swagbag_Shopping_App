package com.shopping.swagbag.user.order.with_items


import android.os.Parcelable
import com.google.gson.annotations.SerializedName
import kotlinx.android.parcel.Parcelize
import kotlinx.android.parcel.RawValue

class OrderModel : ArrayList<OrderModel.OrderModelItem>(){
    @Parcelize
    data class OrderModelItem(
        val address: Address,
        val balance: String,
        val billing: Billing,
        val coupon: String,
        val couponamount: String,
        val coupontype: String,
        @SerializedName("created_date")
        val createdDate: String, // 2022-04-05T11:31:22.584Z
        val deleted: Int, // 0
        val finalprice: String, // 1
        val gateway: String, // COD
        @SerializedName("gift_code")
        val giftCode: String,
        @SerializedName("gift_email")
        val giftEmail: String,
        @SerializedName("gift_fname")
        val giftFname: String,
        @SerializedName("gift_lname")
        val giftLname: String,
        @SerializedName("gift_message")
        val giftMessage: String,
        @SerializedName("gift_phone")
        val giftPhone: String,
        val gitwrap: @RawValue Any?, // null
        @SerializedName("_id")
        val id: String, // 624c288abd181f127144aef4
        val note: String,
        val orderid: String, // SB-1649158282
        val otp: String,
        val packing: String,
        val paid: Int, // 0
        val price: String,
        val products: List<Product>,
        @SerializedName("return_products")
        val returnProducts: @RawValue List<Any>,
        @SerializedName("return_reason")
        val returnReason: String,
        val shipping: String,
        val status: String, // pending_payment
        val tax: String, // 0
        val transactionid: String?,
        val type: String, // Normal
        @SerializedName("update_date")
        val updateDate: String, // 2022-04-05T11:31:22.584Z
        val user: String, // 61e82a7acf130b2978d05815
        @SerializedName("__v")
        val v: Int // 0
    ): Parcelable {
        @Parcelize
        data class Address(
            val address: String, // Abu Dhabi - United Arab Emirates
            val address2: String,
            val city: String, // Abu Dhabi
            @SerializedName("contact_mobile")
            val contactMobile: String, // 08860639597
            @SerializedName("contact_name")
            val contactName: String, // Pallav Kumar
            val country: String, // UAE
            @SerializedName("created_date")
            val createdDate: String, // 2022-03-30T08:21:30.932Z
            val deleted: Int, // 0
            @SerializedName("_id")
            val id: String, // 62449ad3eb7e9132609a9f18
            val pincode: String, // 123456
            val position: Position,
            @SerializedName("post_office")
            val postOffice: @RawValue Any?, // null
            val state: String, // abc
            val title: String, // Office
            @SerializedName("update_date")
            val updateDate: String, // 2022-03-30T08:21:30.932Z
            val user: String, // 61e82a7acf130b2978d05815
            @SerializedName("__v")
            val v: Int // 0
        ): Parcelable {
            @Parcelize
            data class Position(
                val coordinates: List<Float>,
                val type: String // Point
            ): Parcelable
        }

        @Parcelize
        data class Billing(
            val address: String, // Abu Dhabi - United Arab Emirates
            val address2: String,
            val city: String, // Abu Dhabi
            @SerializedName("contact_mobile")
            val contactMobile: String, // 08860639597
            @SerializedName("contact_name")
            val contactName: String, // Pallav Kumar
            val country: String, // UAE
            @SerializedName("created_date")
            val createdDate: String, // 2022-03-30T08:21:30.932Z
            val deleted: Int, // 0
            @SerializedName("_id")
            val id: String, // 62449ad3eb7e9132609a9f18
            val pincode: String, // 123456
            val position: Position,
            @SerializedName("post_office")
            val postOffice: @RawValue Any?, // null
            val state: String, // abc
            val title: String, // Office
            @SerializedName("update_date")
            val updateDate: String, // 2022-03-30T08:21:30.932Z
            val user: String, // 61e82a7acf130b2978d05815
            @SerializedName("__v")
            val v: Int // 0
        ): Parcelable {
            @Parcelize
            data class Position(
                val coordinates: List<Float>,
                val type: String // Point
            ): Parcelable
        }

        @Parcelize
        data class Product(
            val `final`: String, // 1220
            @SerializedName("_id")
            val id: String, // 624c288abd181f127144aef5
            val option: List<Option>,
            val price: String, // 190
            val product: Product,
            val productname: String, // test product demo test product demo
            val quantity: Int // 10
        ): Parcelable {
            @Parcelize
            data class Option(
                val key: String, // Color
                val price: String, // 1000
                val value: String //  #1913d4
            ): Parcelable

            @Parcelize
            data class Product(
                val active: Int, // 1
                @SerializedName("added_by")
                val addedBy: String, // 5fe463f5a9e14206002dd63e
                @SerializedName("additional_description")
                val additionalDescription: String,
                val attribute: String,
                val backorders: String,
                val batchno: String,
                val brand: String, // 61fb80663669ad2911a25fb9
                val category: List<String>,
                val cgst: String,
                @SerializedName("combo_products")
                val comboProducts: @RawValue Any?, // null
                val commission: String,
                @SerializedName("created_date")
                val createdDate: @RawValue String, // 2022-03-22T13:27:37.114Z
                val cuisine:  @RawValue Any?, // null
                val deal: Int, // 0
                val deleted: Int, // 1
                val desc: String, // <strong style="margin: 0px; padding: 0px; color: rgb(0, 0, 0); font-family: &quot;Open Sans&quot;, Arial, sans-serif; font-size: 14px; text-align: justify;">Lorem Ipsum</strong><span style="color: rgb(0, 0, 0); font-family: &quot;Open Sans&quot;, Arial, sans-serif; font-size: 14px; text-align: justify;">&nbsp;is simply dummy text of the printing and typesetting industry. Lorem Ipsum has been the industry's standard dummy text ever since the 1500s, when an unknown printer took a galley of type and scrambled it to make a type specimen book. It has survived not only five centuries, but also the leap into electronic typesetting, remaining essentially unchanged. It was popularised in the 1960s with the release of Letraset sheets containing Lorem Ipsum passages, and more recently with desktop publishing software like Aldus PageMaker including versions of Lorem Ipsum.</span><div><span style="color: rgb(0, 0, 0); font-family: &quot;Open Sans&quot;, Arial, sans-serif; font-size: 14px; text-align: justify;"><br></span></div><div><span style="color: rgb(0, 0, 0); font-family: &quot;Open Sans&quot;, Arial, sans-serif; font-size: 14px; text-align: justify;"><b>How to use it ?</b></span></div><div><span style="color: rgb(0, 0, 0); font-family: &quot;Open Sans&quot;, Arial, sans-serif; font-size: 14px; text-align: justify;">is simply dummy text of the printing and typesetting industry. Lorem Ipsum has been the industry's standard dummy text ever since the 1500s, when an unknown printer took a galley of type and scrambled it to make a type specimen book. It has survived not only five centuries, but also the leap into electronic typesetting, remaining essentially unchanged. It was popularised in the 1960s with the release of Letraset sheets containing Lorem Ipsum passages, and more recently with desktop pu</span><span style="color: rgb(0, 0, 0); font-family: &quot;Open Sans&quot;, Arial, sans-serif; font-size: 14px; text-align: justify;"><b><br></b></span></div>
                @SerializedName("discounted_price")
                val discountedPrice: @RawValue Any?, // null
                @SerializedName("end_date")
                val endDate: String, // 2022-03-22T00:00:00.000Z
                val express: Boolean, // false
                val featured: Int, // 1
                val `file`: List<File>,
                val height: String, // 33
                @SerializedName("_id")
                val id: String, // 623b5ada2ff8134a95c5a7d3
                val igst: String,
                val length: String, // 15
                @SerializedName("manage_stock")
                val manageStock: Int, // 1
                @SerializedName("master_category")
                val masterCategory: List<String>,
                val name: String, // test product demo test product demo
                val options: List<Option>,
                @SerializedName("packaging_charge")
                val packagingCharge: String, // 10
                val point: Int, // 2
                @SerializedName("point_exp_date")
                val pointExpDate: String, // 2022-03-22T13:27:37.114Z
                val price: String, // 200
                @SerializedName("product_types")
                val productTypes: List<String>,
                @SerializedName("return_day")
                val returnDay: String, // 5
                val returnable: String, // 1
                @SerializedName("selling_price")
                val sellingPrice: String = "0.0", // 190
                val sgst: String,
                @SerializedName("shelving_location")
                val shelvingLocation: String,
                @SerializedName("short_desc")
                val shortDesc: String, // simply dummy text of the printing and typesetting industry.
                val sku: String, // ABCD
                val slug: String, // test-product
                @SerializedName("slug_history")
                val slugHistory: List<String>,
                @SerializedName("start_date")
                val startDate: String, // 2022-03-22T00:00:00.000Z
                @SerializedName("stock_qty")
                val stockQty: String, // 10
                @SerializedName("sub_category")
                val subCategory: List<String>,
                val tags: String,
                @SerializedName("tax_status")
                val taxStatus: String,
                val threshold: String,
                @SerializedName("update_date")
                val updateDate: String, // 2022-03-22T13:27:37.114Z
                @SerializedName("__v")
                val v: Int, // 0
                val vendor: String, // 61fb87943669ad2911a26008
                @SerializedName("video_url")
                val videoUrl: String,
                val weight: String, // .5
                val width: String // 34
            ): Parcelable {
                @Parcelize
                data class File(
                    val acl: String, // public-read
                    val bucket: String, // swagbag
                    val contentDisposition: @RawValue Any?, // null
                    val contentEncoding: @RawValue Any?, // null
                    val contentType: String, // application/octet-stream
                    val encoding: String, // 7bit
                    val etag: String, // "63d8926cc1a253582a9274b2342fe5af"
                    val fieldname: String, // upload
                    val key: String, // 1648061096256jigpt.png
                    val location: String, // https://swagbag.sgp1.digitaloceanspaces.com/1648061096256jigpt.png
                    val metadata: @RawValue Any?, // null
                    val mimetype: String, // image/png
                    val originalname: String, // kids.png
                    val serverSideEncryption: @RawValue Any?, // null
                    val size: Int, // 245348
                    val storageClass: String, // STANDARD
                    val versionId: @RawValue Any? // null
                ): Parcelable

                @Parcelize
                data class Option(
                    val name: String, // Size
                    val value: String // M:20, L:30
                ): Parcelable
            }
        }
    }
}