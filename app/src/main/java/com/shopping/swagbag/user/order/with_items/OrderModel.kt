package com.shopping.swagbag.user.order.with_items


import android.os.Parcelable
import com.google.gson.annotations.SerializedName
import kotlinx.android.parcel.Parcelize
import kotlinx.android.parcel.RawValue

class OrderModel : ArrayList<OrderModel.OrderModelItem>(){
    @Parcelize
    data class OrderModelItem(
        val address: Address,
        val balance: String, // 0
        val billing: String, // 62449ad3eb7e9132609a9f18
        val coupon: String,
        val couponamount: String,
        val coupontype: String,
        @SerializedName("created_date")
        val createdDate: String, // 2022-04-01T13:47:24.557Z
        val deleted: Int, // 0
        val finalprice: String, // 15.75
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
        val gitwrap: List<String>?,
        @SerializedName("_id")
        val id: String, // 6247026c5f5c1f5d94440cb4
        val note: String,
        val orderid: String, // SB-1648820844
        val otp: String,
        val packing: String, // 3
        val paid: Int, // 0
        val price: String, // 15
        val products: List<Product>,
        @SerializedName("return_products")
        val returnProducts: @RawValue List<Product>,
        @SerializedName("return_reason")
        val returnReason: String,
        val shipping: String, // 0
        val status: String, // processing
        val transactionid: String,
        val type: String, // Normal
        @SerializedName("update_date")
        val updateDate: String, // 2022-04-01T13:47:24.557Z
        val user: String, // 61e82a7acf130b2978d05815
        @SerializedName("__v")
        val v: Int // 0
    ): Parcelable {
        @Parcelize
        data class Address(
            val address: String, // Abu Dhabi - United Arab Emirates
            val address2: String, // abu dhabi
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
            val postOffice: String,
            val state: String, // abc
            val title: String, // abv
            @SerializedName("update_date")
            val updateDate: String, // 2022-03-30T08:21:30.932Z
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

        @Parcelize
        data class Product(
            val `final`: String, // 15
            @SerializedName("_id")
            val id: String, // 6247026c5f5c1f5d94440cb5
            val option: @RawValue  List<Any>,
            val price: Int, // 15
            val product: Product,
            val productname: String, // MINT SOAP
            val quantity: Int // 1
        ): Parcelable {
            @Parcelize
            data class Product(
                val active: Int, // 1
                @SerializedName("added_by")
                val addedBy: String, // 5fe463f5a9e14206002dd63e
                val attribute: String,
                val backorders: String,
                val batchno: String, // 1
                val brand: String, // 61fb80663669ad2911a25fb9
                val category: List<String>,
                val cgst: String,
                @SerializedName("combo_products")
                val comboProducts: @RawValue  Any?, // null
                val commission: String,
                @SerializedName("created_date")
                val createdDate: String, // 2022-03-12T08:09:33.432Z
                val cuisine: @RawValue  Any?, // null
                val deal: Int, // 0
                val deleted: Int, // 0
                val desc: String, // <span data-sheets-value="{&quot;1&quot;:2,&quot;2&quot;:&quot; A herbal and natural  Mint Soap rejuvenates your skin with antiseptic properties. Enriched with Peppermint and Menthol Oil provides cooling sensation  in skin and keeps you feeling fresh on day lasting. &quot;}" data-sheets-userformat="{&quot;2&quot;:12799,&quot;3&quot;:{&quot;1&quot;:0},&quot;4&quot;:{&quot;1&quot;:3,&quot;3&quot;:2},&quot;5&quot;:{&quot;1&quot;:[{&quot;1&quot;:2,&quot;2&quot;:0,&quot;5&quot;:{&quot;1&quot;:2,&quot;2&quot;:0}},{&quot;1&quot;:0,&quot;2&quot;:0,&quot;3&quot;:3},{&quot;1&quot;:1,&quot;2&quot;:0,&quot;4&quot;:1}]},&quot;6&quot;:{&quot;1&quot;:[{&quot;1&quot;:2,&quot;2&quot;:0,&quot;5&quot;:{&quot;1&quot;:2,&quot;2&quot;:0}},{&quot;1&quot;:0,&quot;2&quot;:0,&quot;3&quot;:3},{&quot;1&quot;:1,&quot;2&quot;:0,&quot;4&quot;:1}]},&quot;7&quot;:{&quot;1&quot;:[{&quot;1&quot;:2,&quot;2&quot;:0,&quot;5&quot;:{&quot;1&quot;:2,&quot;2&quot;:0}},{&quot;1&quot;:0,&quot;2&quot;:0,&quot;3&quot;:3},{&quot;1&quot;:1,&quot;2&quot;:0,&quot;4&quot;:1}]},&quot;8&quot;:{&quot;1&quot;:[{&quot;1&quot;:2,&quot;2&quot;:0,&quot;5&quot;:{&quot;1&quot;:2,&quot;2&quot;:0}},{&quot;1&quot;:0,&quot;2&quot;:0,&quot;3&quot;:3},{&quot;1&quot;:1,&quot;2&quot;:0,&quot;4&quot;:1}]},&quot;9&quot;:1,&quot;10&quot;:1,&quot;11&quot;:4,&quot;15&quot;:&quot;Arial&quot;,&quot;16&quot;:8}" style="caret-color: rgb(0, 0, 0); color: rgb(0, 0, 0); text-size-adjust: auto; font-size: 8pt; font-family: Arial; text-align: center;">A herbal and natural Mint Soap rejuvenates your skin with antiseptic properties. Enriched with Peppermint and Menthol Oil provides cooling sensation in skin and keeps you feeling fresh on day lasting.<span class="Apple-converted-space">&nbsp;</span></span>
                @SerializedName("discounted_price")
                val discountedPrice: @RawValue  Any?, // null
                @SerializedName("end_date")
                val endDate: String, // 2022-03-12T00:00:00.000Z
                val express: Boolean, // true
                val featured: Int, // 1
                val `file`: List<File>,
                val height: String,
                @SerializedName("_id")
                val id: String, // 623023bba933dd70cf68b147
                val igst: String,
                val length: String,
                @SerializedName("manage_stock")
                val manageStock: Int, // 1
                @SerializedName("master_category")
                val masterCategory: List<String>,
                val name: String, // MINT SOAP
                val options: @RawValue  List<Any>,
                @SerializedName("packaging_charge")
                val packagingCharge: String, // 3
                val point: Int, // 5
                @SerializedName("point_exp_date")
                val pointExpDate: String, // 2022-03-12T08:09:33.432Z
                val price: Int, // 15
                @SerializedName("product_types")
                val productTypes: List<String>,
                @SerializedName("return_day")
                val returnDay: String, // 3
                val returnable: String, // 1
                @SerializedName("selling_price")
                val sellingPrice: Int, // 15
                val sgst: String,
                @SerializedName("shelving_location")
                val shelvingLocation: String, // Sharjah
                @SerializedName("short_desc")
                val shortDesc: String, // Khadi Organique Mint Soap is Powerful yet skin friendly with herbal and antiseptic properties.It has Peppermint incorporated in mild soap base that takes out heat from the skin while keeping it cool even after a refreshing shower.
                val sku: String, // KHOR_DU2107
                val slug: String, // mint-soap
                @SerializedName("slug_history")
                val slugHistory: List<String>,
                @SerializedName("start_date")
                val startDate: String, // 2022-03-12T00:00:00.000Z
                @SerializedName("stock_qty")
                val stockQty: String, // 10
                @SerializedName("sub_category")
                val subCategory: List<String>,
                val tags: String, // Haircare,Hairenrichment
                @SerializedName("tax_status")
                val taxStatus: String,
                val threshold: String, // 5
                @SerializedName("update_date")
                val updateDate: String, // 2022-03-12T08:09:33.432Z
                @SerializedName("__v")
                val v: Int, // 0
                val vendor: String, // 61fb87943669ad2911a26008
                @SerializedName("video_url")
                val videoUrl: String,
                val weight: String, // 125g
                val width: String
            ): Parcelable {
                @Parcelize
                data class File(
                    val acl: String, // public-read
                    val bucket: String, // swagbag
                    val contentDisposition: @RawValue  Any?, // null
                    val contentEncoding:  @RawValue Any?, // null
                    val contentType: String, // application/octet-stream
                    val encoding: String, // 7bit
                    val etag: String, // "19fc0dd7c0526c675cffbbdab61eaa7c"
                    val fieldname: String, // upload
                    val key: String, // 1647322035841hurih.png
                    val location: String, // https://swagbag.sgp1.digitaloceanspaces.com/1647322035841hurih.png
                    val metadata: @RawValue  Any?, // null
                    val mimetype: String, // image/png
                    val originalname: String, // 1.png
                    val serverSideEncryption: @RawValue  Any?, // null
                    val size: Int, // 567266
                    val storageClass: String // STANDARD
                ): Parcelable
            }
        }
    }
}