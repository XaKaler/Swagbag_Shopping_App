package com.shopping.swagbag.user.wishlist.withproduct


import com.google.gson.annotations.SerializedName

data class GetWishlistModel(
    val result: List<Result>,
    val status: String // success
) {
    data class Result(
        @SerializedName("created_date")
        val createdDate: String, // 2022-02-12T12:58:18.916Z
        @SerializedName("_id")
        val id: String, // 620c97cef4e55d0cd2f3acc0
        val image: String, // https://swagbag-space.fra1.digitaloceanspaces.com/1639933886951seijh.webp
        val name: String, // soap
        val product: Product,
        val slug: String, // soap
        @SerializedName("update_date")
        val updateDate: String, // 2022-02-12T12:58:18.916Z
        val user: String, // 61e82a7acf130b2978d05815
        @SerializedName("__v")
        val v: Int // 0
    ) {
        data class Product(
            val active: Int, // 1
            @SerializedName("added_by")
            val addedBy: String, // 5fe463f5a9e14206002dd63e
            val attribute: String,
            val backorders: String,
            val batchno: String,
            val brand: String, // 6148c87ee40e5da07a1d9eed
            val category: String, // 61bb805e66538a1c1f366787
            val cgst: String,
            @SerializedName("combo_products")
            val comboProducts: Any?, // null
            val commission: String,
            @SerializedName("created_date")
            val createdDate: String, // 2021-12-12T08:19:07.248Z
            val cuisine: Any?, // null
            val deal: Int, // 0
            val deleted: Int, // 0
            val desc: String,
            @SerializedName("discounted_price")
            val discountedPrice: Any?, // null
            @SerializedName("end_date")
            val endDate: String, // 2021-12-12T00:00:00.000Z
            val express: Boolean, // false
            val featured: Int, // 0
            val `file`: List<File>,
            val height: String,
            @SerializedName("_id")
            val id: String, // 61b839ef20808a1790d9b1b0
            val igst: String,
            val length: String,
            @SerializedName("manage_stock")
            val manageStock: Int, // 0
            @SerializedName("master_category")
            val masterCategory: String, // 615ae14c63d6a6435a183db7
            val name: String, // soap
            val options: List<Option>,
            @SerializedName("packaging_charge")
            val packagingCharge: String,
            val point: Int, // 0
            @SerializedName("point_exp_date")
            val pointExpDate: String, // 2025-12-14T00:00:00.000Z
            val price: Int, // 500
            @SerializedName("product_types")
            val productTypes: List<Any>,
            @SerializedName("selling_price")
            val sellingPrice: Int, // 455
            val sgst: String,
            @SerializedName("shelving_location")
            val shelvingLocation: String,
            @SerializedName("short_desc")
            val shortDesc: String,
            val sku: String,
            val slug: String, // soap
            @SerializedName("slug_history")
            val slugHistory: List<String>,
            @SerializedName("start_date")
            val startDate: String, // 2021-12-12T00:00:00.000Z
            @SerializedName("stock_qty")
            val stockQty: String, // 12
            @SerializedName("sub_category")
            val subCategory: String, // 61bb810866538a1c1f3667c6
            val tags: String,
            @SerializedName("tax_status")
            val taxStatus: String,
            val threshold: String,
            @SerializedName("update_date")
            val updateDate: String, // 2021-12-12T08:19:07.248Z
            @SerializedName("__v")
            val v: Int, // 0
            val vendor: String, // 618f661be5821a22644f6bd1
            @SerializedName("video_url")
            val videoUrl: String,
            val weight: String,
            val width: String
        ) {
            data class File(
                val acl: String, // public-read
                val bucket: String, // swagbag-space
                val contentDisposition: Any?, // null
                val contentType: String, // application/octet-stream
                val encoding: String, // 7bit
                val etag: String, // "92034dbbef5a465219733d4d5c0f94a1"
                val fieldname: String, // upload
                val key: String, // 1639933886951seijh.webp
                val location: String, // https://swagbag-space.fra1.digitaloceanspaces.com/1639933886951seijh.webp
                val metadata: Any?, // null
                val mimetype: String, // image/webp
                val originalname: String, // product.webp
                val serverSideEncryption: Any?, // null
                val size: Int, // 3396
                val storageClass: String, // STANDARD
                val versionId: Any? // null
            )

            data class Option(
                val name: String, // Size
                val value: String // L:20:A12A3:10, XL:10:B1B23:5
            )
        }
    }
}