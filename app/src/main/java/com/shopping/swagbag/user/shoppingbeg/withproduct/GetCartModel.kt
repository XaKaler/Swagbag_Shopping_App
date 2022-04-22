package com.shopping.swagbag.user.shoppingbeg.withproduct


import android.os.Parcelable
import com.google.gson.annotations.SerializedName
import kotlinx.android.parcel.Parcelize
import kotlinx.android.parcel.RawValue

@Parcelize
data class GetCartModel(
    val result: @RawValue List<Result>? = null,
    val status: String // success
): Parcelable {
    data class Result(
        @SerializedName("created_date")
        val createdDate: String, // 2022-03-12T08:09:33.446Z
        @SerializedName("_id")
        val id: String, // 62307202a933dd70cf68d6ae
        val image: String, // https://swagbag.sgp1.digitaloceanspaces.com/1645689012867ihzer.png
        val name: String, // Saffron Soap
        val option: List<Any>,
        val price: Float, // 100
        val product: Product,
        var quantity: Int, // 1
        val uniqueid: String,
        @SerializedName("update_date")
        val updateDate: String, // 2022-03-12T08:09:33.446Z
        val user: String, // 61e82a7acf130b2978d05815
        @SerializedName("__v")
        val v: Int // 0
    ) {
        data class Product(
            val active: Int, // 1
            @SerializedName("added_by")
            val addedBy: String, // 5fe463f5a9e14206002dd63e
            val attribute: String,
            val backorders: String, // yes
            val batchno: String, // 12123
            val brand: String, // 61fb80663669ad2911a25fb9
            val category: List<String>,
            val cgst: String,
            @SerializedName("combo_products")
            val comboProducts: @RawValue Any? = null, // null
            val commission: String, // 0
            @SerializedName("created_date")
            val createdDate: String, // 2022-02-22T07:51:16.617Z
            val cuisine: @RawValue Any? = null, // null
            val deal: Int, // 0
            val deleted: Int, // 0
            val desc: String, // To be used twice a day&nbsp;
            @SerializedName("discounted_price")
            val discountedPrice: @RawValue Any? = null, // null
            @SerializedName("end_date")
            val endDate: String, // 2022-02-22T00:00:00.000Z
            val express: Boolean, // false
            val featured: Int, // 1
            val `file`: List<File>,
            val height: String,
            @SerializedName("_id")
            val id: String, // 621738b860550b55668dfa38
            val igst: String,
            val length: String,
            @SerializedName("manage_stock")
            val manageStock: Int, // 1
            @SerializedName("master_category")
            val masterCategory: List<String>,
            val name: String, // Saffron Soap
            val options: List<Any>,
            @SerializedName("packaging_charge")
            val packagingCharge: String, // 1
            val point: String, // 1
            @SerializedName("point_exp_date")
            val pointExpDate: String, // 2022-04-14T00:00:00.000Z
            val price: Float, // 115
            @SerializedName("product_types")
            val productTypes: @RawValue Any? = null, // null
            @SerializedName("return_day")
            val returnDay: String,
            val returnable: String,
            @SerializedName("selling_price")
            val sellingPrice: Float, // 100
            val sgst: String,
            @SerializedName("shelving_location")
            val shelvingLocation: String,
            @SerializedName("short_desc")
            val shortDesc: String, // Soap for smoothness
            val sku: String, // 12345678
            val slug: String, // saffron-soap
            @SerializedName("slug_history")
            val slugHistory: List<String>,
            @SerializedName("start_date")
            val startDate: String, // 2022-02-22T00:00:00.000Z
            @SerializedName("stock_qty")
            val stockQty: String, // 10
            @SerializedName("sub_category")
            val subCategory: List<String>,
            val tags: String,
            @SerializedName("tax_status")
            val taxStatus: String,
            val threshold: String, // 2
            @SerializedName("update_date")
            val updateDate: String, // 2022-02-22T07:51:16.617Z
            @SerializedName("__v")
            val v: Int, // 0
            val vendor: String, // 61fb87943669ad2911a26008
            @SerializedName("video_url")
            val videoUrl: String,
            val weight: String,
            val width: String
        ) {
            data class File(
                val acl: String, // public-read
                val bucket: String, // swagbag
                val contentDisposition: @RawValue Any? = null, // null
                val contentEncoding: @RawValue Any? = null, // null
                val contentType: String, // application/octet-stream
                val encoding: String, // 7bit
                val etag: String, // "841c4c44fe124cb8bb90ba6e27a1d9b7"
                val fieldname: String, // upload
                val key: String, // 1645689012867ihzer.png
                val location: String, // https://swagbag.sgp1.digitaloceanspaces.com/1645689012867ihzer.png
                val metadata: @RawValue Any? = null, // null
                val mimetype: String, // image/png
                val originalname: String, // 1.png
                val serverSideEncryption: @RawValue Any? = null, // null
                val size: Int, // 607861
                val storageClass: String // STANDARD
            )
        }
    }
}