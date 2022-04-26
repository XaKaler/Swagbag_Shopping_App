package com.shopping.swagbag.user.wishlist.withproduct


import com.google.gson.annotations.SerializedName

data class GetWishlistModel(
    val result: List<Result>,
    val status: String // success
) {
    data class Result(
        @SerializedName("created_date")
        val createdDate: String, // 2022-03-11T12:42:47.667Z
        @SerializedName("_id")
        val id: String, // 622b685bc17a1b5a69bf2c88
        val image: String, // https://swagbag.sgp1.digitaloceanspaces.com/1646376399170o7c0g.png
        val name: String, // bag
        val product: Product,
        val slug: String, // bag
        @SerializedName("update_date")
        val updateDate: String, // 2022-03-11T12:42:47.667Z
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
            val batchno: String, // 1234567
            val brand: String, // 61bb7f2166538a1c1f36671d
            val category: List<String>,
            val cgst: String,
            @SerializedName("combo_products")
            val comboProducts: Any?, // null
            val commission: String,
            @SerializedName("created_date")
            val createdDate: String, // 2022-03-03T08:11:30.402Z
            val cuisine: Any?, // null
            val deal: Int, // 0
            val deleted: Int, // 0
            val desc: String, // test
            @SerializedName("discounted_price")
            val discountedPrice: Any?, // null
            @SerializedName("end_date")
            val endDate: String, // 2022-03-03T00:00:00.000Z
            val express: Boolean, // false
            val featured: Int, // 0
            val file: List<File>,
            val height: String, // 1.075
            @SerializedName("_id")
            val id: String, // 6221b5d349ad231a4e67041e
            val igst: String,
            val length: String, // 30
            @SerializedName("manage_stock")
            val manageStock: Int, // 1
            @SerializedName("master_category")
            val masterCategory: List<String>,
            val name: String, // bag
            val options: List<Option>,
            @SerializedName("packaging_charge")
            val packagingCharge: String, // 1
            val point: Double, // 0.1
            @SerializedName("point_exp_date")
            val pointExpDate: String, // 2022-03-03T08:11:30.402Z
            val price: String, // 700
            @SerializedName("product_types")
            val productTypes: List<String>,
            @SerializedName("return_day")
            val returnDay: String, // 7
            val returnable: String, // 1
            @SerializedName("selling_price")
            val sellingPrice: String, // 650
            val sgst: String,
            @SerializedName("shelving_location")
            val shelvingLocation: String,
            @SerializedName("short_desc")
            val shortDesc: String, // test
            val sku: String, // 12129
            val slug: String, // bag
            @SerializedName("slug_history")
            val slugHistory: List<String>,
            @SerializedName("start_date")
            val startDate: String, // 2022-03-03T00:00:00.000Z
            @SerializedName("stock_qty")
            val stockQty: String, // 10
            @SerializedName("sub_category")
            val subCategory: List<String>,
            val tags: String, // test
            @SerializedName("tax_status")
            val taxStatus: String,
            val threshold: String, // 2
            @SerializedName("update_date")
            val updateDate: String, // 2022-03-03T08:11:30.402Z
            @SerializedName("__v")
            val v: Int, // 0
            val vendor: String, // 621731b360550b55668df97b
            @SerializedName("video_url")
            val videoUrl: String,
            val weight: String, // .30grms
            val width: String // 0.804
        ) {
            data class File(
                val acl: String, // public-read
                val bucket: String, // swagbag
                val contentDisposition: Any?, // null
                val contentEncoding: Any?, // null
                val contentType: String, // application/octet-stream
                val encoding: String, // 7bit
                val etag: String, // "7fa3244a35ba6fc2ededcddf44ba02b6"
                val fieldname: String, // upload
                val key: String, // 1646376399170o7c0g.png
                val location: String, // https://swagbag.sgp1.digitaloceanspaces.com/1646376399170o7c0g.png
                val metadata: Any?, // null
                val mimetype: String, // image/png
                val originalname: String, // muslin bag.png
                val serverSideEncryption: Any?, // null
                val size: Int, // 349053
                val storageClass: String // STANDARD
            )

            data class Option(
                val name: String, // Color
                val value: String // #d41367:500:SKU123, #1913d4:1000:SKU456, #4dd413:0:SKU4578
            )
        }
    }
}