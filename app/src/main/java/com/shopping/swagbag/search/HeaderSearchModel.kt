package com.shopping.swagbag.search


import com.google.gson.annotations.SerializedName

data class HeaderSearchModel(
    val result: List<Result>,
    val status: String // success

) {
    data class Result(
        val name: String, // Kids
        val product: List<Product>,
        val slug: String // kids
    ) {
        data class Product(
            val active: Int, // 1
            @SerializedName("added_by")
            val addedBy: String, // 5fe463f5a9e14206002dd63e
            val attribute: String,
            val backorders: String,
            val batchno: String,
            val brand: String, // 6148c87ee40e5da07a1d9eed
            val category: List<String>,
            val cgst: String,
            @SerializedName("combo_products")
            val comboProducts: Any?, // null
            val commission: String,
            @SerializedName("created_date")
            val createdDate: String, // 2022-03-21T13:44:02.881Z
            val cuisine: Any?, // null
            val deal: Any?, // null
            val deleted: Int, // 0
            val desc: String, // test
            @SerializedName("discounted_price")
            val discountedPrice: Any?, // null
            @SerializedName("end_date")
            val endDate: String, // 2022-03-21T13:44:02.881Z
            val express: Boolean, // false
            val featured: Any?, // null
            val `file`: List<File>,
            val height: String,
            @SerializedName("_id")
            val id: String, // 623897ef1eec593385b8e773
            val igst: String,
            val length: String,
            @SerializedName("manage_stock")
            val manageStock: Int, // 0
            @SerializedName("master_category")
            val masterCategory: List<String>,
            val name: String, // Baby Bath Essentials new
            val options: List<Any>,
            @SerializedName("packaging_charge")
            val packagingCharge: String,
            val point: Int, // 0
            @SerializedName("point_exp_date")
            val pointExpDate: String, // 2022-03-21T13:44:02.881Z
            val price: String, // 200
            @SerializedName("product_types")
            val productTypes: List<String>,
            @SerializedName("return_day")
            val returnDay: String,
            val returnable: String,
            @SerializedName("selling_price")
            val sellingPrice: String, // null
            val sgst: String,
            @SerializedName("shelving_location")
            val shelvingLocation: String,
            @SerializedName("short_desc")
            val shortDesc: String,
            val sku: String,
            val slug: String, // baby-bath-essentials-new
            @SerializedName("slug_history")
            val slugHistory: List<String>,
            @SerializedName("start_date")
            val startDate: String, // 2022-03-21T13:44:02.881Z
            @SerializedName("stock_qty")
            val stockQty: String,
            @SerializedName("sub_category")
            val subCategory: List<String>,
            val tags: String,
            @SerializedName("tax_status")
            val taxStatus: String,
            val threshold: String,
            @SerializedName("update_date")
            val updateDate: String, // 2022-03-21T13:44:02.881Z
            @SerializedName("__v")
            val v: Int, // 0
            val vendor: String, // 6217323d60550b55668df986
            @SerializedName("video_url")
            val videoUrl: String,
            val weight: String,
            val width: String
        ) {
            data class File(
                val acl: String, // public-read
                val bucket: String, // swagbag
                val contentDisposition: Any?, // null
                val contentEncoding: Any?, // null
                val contentType: String, // application/octet-stream
                val encoding: String, // 7bit
                val etag: String, // "b4b7a0d7eb034b3925516a143a5302a4"
                val fieldname: String, // upload
                val key: String, // 1647876076411w3h3l.png
                val location: String, // https://swagbag.sgp1.digitaloceanspaces.com/1647876076411w3h3l.png
                val metadata: Any?, // null
                val mimetype: String, // image/png
                val originalname: String, // kids.png
                val serverSideEncryption: Any?, // null
                val size: Int, // 183655
                val storageClass: String // STANDARD
            )
        }
    }
}