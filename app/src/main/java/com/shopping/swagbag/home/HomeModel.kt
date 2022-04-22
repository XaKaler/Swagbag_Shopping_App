package com.shopping.swagbag.home


import com.google.gson.annotations.SerializedName

data class HomeModel(
    val result: Result,
    val status: String // success
) {
    data class Result(
        val deals: List<Deal>,
        val featured: List<Featured>,
        @SerializedName("master_category")
        val masterCategory: List<MasterCategory>,
        @SerializedName("random_category")
        val randomCategory: List<RandomCategory>,
        val section: List<Section>,
        val slider: List<Slider>
    ) {
        data class Deal(
            val active: Int, // 1
            @SerializedName("added_by")
            val addedBy: String, // 5fe463f5a9e14206002dd63e
            @SerializedName("additional_description")
            val additionalDescription: String,
            val attribute: String,
            val backorders: String,
            val batchno: String, // 12345
            val brand: String, // 6211e64f0fe91d273d2178f8
            val category: List<String>,
            val cgst: String,
            @SerializedName("combo_products")
            val comboProducts: Any?, // null
            val commission: String,
            @SerializedName("created_date")
            val createdDate: String, // 2022-04-18T16:04:16.486Z
            val cuisine: Any?, // null
            val deal: Int, // 1
            val deleted: Int, // 0
            val desc: String, // <p style="caret-color: rgb(0, 0, 0); color: rgb(0, 0, 0); margin-right: 0px; margin-bottom: 0px; margin-left: 0px;"><font face="Times New Roman" color="#000000" style="font-stretch: normal; font-size: 12px; line-height: normal; font-family: &quot;Times New Roman&quot;; font-variant-ligatures: common-ligatures;">This eco-friendly and stylish bamboo soap dish has been specially designed to keep your luxurious Rohr Remedy soap fresh and dry. Designed especially for larger soaps.</font></p>
            @SerializedName("discounted_price")
            val discountedPrice: Any?, // null
            @SerializedName("end_date")
            val endDate: String, // 2022-04-18T00:00:00.000Z
            val express: Boolean, // false
            val featured: Int, // 1
            val `file`: List<File>,
            val height: String,
            @SerializedName("_id")
            val id: String, // 625e5f0f3bc36763b7938f58
            val igst: String,
            val length: String,
            @SerializedName("manage_stock")
            val manageStock: Int, // 1
            @SerializedName("master_category")
            val masterCategory: List<String>,
            val name: String, // Bamboo Soap Dish
            val options: List<Any>,
            @SerializedName("packaging_charge")
            val packagingCharge: String,
            val point: Int, // 0
            @SerializedName("point_exp_date")
            val pointExpDate: String, // 2022-04-18T16:04:16.486Z
            val price: Double, // 58.54
            @SerializedName("product_types")
            val productTypes: List<String>,
            @SerializedName("return_day")
            val returnDay: String,
            val returnable: String,
            @SerializedName("selling_price")
            val sellingPrice: Any?, // null
            val sgst: String,
            @SerializedName("shelving_location")
            val shelvingLocation: String, // Sharjah
            @SerializedName("short_desc")
            val shortDesc: String,
            val sku: String, // SBR4567
            val slug: String, // bamboo-soap-dish
            @SerializedName("slug_history")
            val slugHistory: List<String>,
            @SerializedName("start_date")
            val startDate: String, // 2022-04-18T00:00:00.000Z
            @SerializedName("stock_qty")
            val stockQty: String, // 100
            @SerializedName("sub_category")
            val subCategory: List<String>,
            val tags: String, // Travel Accessories for Men and Women
            @SerializedName("tax_status")
            val taxStatus: String,
            val threshold: String, // 20
            @SerializedName("update_date")
            val updateDate: String, // 2022-04-18T16:04:16.486Z
            @SerializedName("__v")
            val v: Int, // 0
            val vendor: String, // 625e3eb23bc36763b793704d
            @SerializedName("video_url")
            val videoUrl: String,
            val weight: String, // 12.5 x 9 x 2.5cm
            val width: String
        ) {
            data class File(
                val acl: String, // public-read
                val bucket: String, // swagbag-files
                val contentDisposition: Any?, // null
                val contentEncoding: Any?, // null
                val contentType: String, // application/octet-stream
                val encoding: String, // 7bit
                val etag: String, // "b6cf7a9d03d4ed284496bded0b9bc7ae"
                val fieldname: String, // upload
                val key: String, // 1650351887558mum5t.jpeg
                val location: String, // https://swagbag-files.s3.amazonaws.com/1650351887558mum5t.jpeg
                val metadata: Any?, // null
                val mimetype: String, // image/jpeg
                val originalname: String, // Bamboo Soap Dish P1.jpg
                val serverSideEncryption: Any?, // null
                val size: Int, // 20553
                val storageClass: String // STANDARD
            )
        }

        data class Featured(
            val active: Int, // 1
            @SerializedName("added_by")
            val addedBy: String, // 5fe463f5a9e14206002dd63e
            @SerializedName("additional_description")
            val additionalDescription: String,
            val attribute: String,
            val backorders: String,
            val batchno: String, // 12345
            val brand: String, // 6211e64f0fe91d273d2178f8
            val category: List<String>,
            val cgst: String,
            @SerializedName("combo_products")
            val comboProducts: Any?, // null
            val commission: String,
            @SerializedName("created_date")
            val createdDate: String, // 2022-04-18T16:04:16.486Z
            val cuisine: Any?, // null
            val deal: Int, // 1
            val deleted: Int, // 0
            val desc: String, // <p style="caret-color: rgb(0, 0, 0); color: rgb(0, 0, 0); margin-right: 0px; margin-bottom: 0px; margin-left: 0px;"><font face="Times New Roman" color="#000000" style="font-stretch: normal; font-size: 12px; line-height: normal; font-family: &quot;Times New Roman&quot;; font-variant-ligatures: common-ligatures;">This eco-friendly and stylish bamboo soap dish has been specially designed to keep your luxurious Rohr Remedy soap fresh and dry. Designed especially for larger soaps.</font></p>
            @SerializedName("discounted_price")
            val discountedPrice: Any?, // null
            @SerializedName("end_date")
            val endDate: String, // 2022-04-18T00:00:00.000Z
            val express: Boolean, // false
            val featured: Int, // 1
            val `file`: List<File>,
            val height: String,
            @SerializedName("_id")
            val id: String, // 625e5f0f3bc36763b7938f58
            val igst: String,
            val length: String,
            @SerializedName("manage_stock")
            val manageStock: Int, // 1
            @SerializedName("master_category")
            val masterCategory: List<String>,
            val name: String, // Bamboo Soap Dish
            val options: List<Any>,
            @SerializedName("packaging_charge")
            val packagingCharge: String,
            val point: Int, // 0
            @SerializedName("point_exp_date")
            val pointExpDate: String, // 2022-04-18T16:04:16.486Z
            val price: Double, // 58.54
            @SerializedName("product_types")
            val productTypes: List<String>,
            @SerializedName("return_day")
            val returnDay: String,
            val returnable: String,
            @SerializedName("selling_price")
            val sellingPrice: Any?, // null
            val sgst: String,
            @SerializedName("shelving_location")
            val shelvingLocation: String, // Sharjah
            @SerializedName("short_desc")
            val shortDesc: String,
            val sku: String, // SBR4567
            val slug: String, // bamboo-soap-dish
            @SerializedName("slug_history")
            val slugHistory: List<String>,
            @SerializedName("start_date")
            val startDate: String, // 2022-04-18T00:00:00.000Z
            @SerializedName("stock_qty")
            val stockQty: String, // 100
            @SerializedName("sub_category")
            val subCategory: List<String>,
            val tags: String, // Travel Accessories for Men and Women
            @SerializedName("tax_status")
            val taxStatus: String,
            val threshold: String, // 20
            @SerializedName("update_date")
            val updateDate: String, // 2022-04-18T16:04:16.486Z
            @SerializedName("__v")
            val v: Int, // 0
            val vendor: String, // 625e3eb23bc36763b793704d
            @SerializedName("video_url")
            val videoUrl: String,
            val weight: String, // 12.5 x 9 x 2.5cm
            val width: String
        ) {
            data class File(
                val acl: String, // public-read
                val bucket: String, // swagbag-files
                val contentDisposition: Any?, // null
                val contentEncoding: Any?, // null
                val contentType: String, // application/octet-stream
                val encoding: String, // 7bit
                val etag: String, // "b6cf7a9d03d4ed284496bded0b9bc7ae"
                val fieldname: String, // upload
                val key: String, // 1650351887558mum5t.jpeg
                val location: String, // https://swagbag-files.s3.amazonaws.com/1650351887558mum5t.jpeg
                val metadata: Any?, // null
                val mimetype: String, // image/jpeg
                val originalname: String, // Bamboo Soap Dish P1.jpg
                val serverSideEncryption: Any?, // null
                val size: Int, // 20553
                val storageClass: String // STANDARD
            )
        }

        data class MasterCategory(
            val active: Int, // 1
            @SerializedName("created_date")
            val createdDate: String, // 2021-09-23T13:53:06.672Z
            val deleted: Int, // 0
            val desc: String, // Kids
            val `file`: String, // https://swagbag-files.s3.amazonaws.com/1649940046666fx2zw.jpeg
            @SerializedName("_id")
            val id: String, // 615ae14c63d6a6435a183db7
            val name: String, // Kids
            @SerializedName("short_desc")
            val shortDesc: String, // Kids
            val slug: String, // kids
            @SerializedName("slug_history")
            val slugHistory: List<String>,
            val sort: Int, // 0
            @SerializedName("update_date")
            val updateDate: String, // 2022-04-14T12:40:47.000Z
            @SerializedName("__v")
            val v: Int // 0
        )

        data class RandomCategory(
            val active: Int, // 1
            val brands: List<String>,
            @SerializedName("created_date")
            val createdDate: String, // 2022-04-15T11:31:58.835Z
            val deleted: Int, // 0
            val description: String,
            @SerializedName("description_after_content")
            val descriptionAfterContent: String,
            val `file`: String,
            @SerializedName("_id")
            val id: String, // 625d1ab5ad10bc2c6a9bc074
            val master: String, // 615ae14c63d6a6435a183db7
            val name: String, // Exclusive
            val parent: Any?, // null
            val slug: String, // exclusive-2
            @SerializedName("slug_history")
            val slugHistory: List<String>,
            @SerializedName("update_date")
            val updateDate: String, // 2022-04-15T11:31:58.835Z
            @SerializedName("__v")
            val v: Int // 0
        )

        data class Section(
            val `data`: List<Data>,
            val id: String, // 615ae14c63d6a6435a183db7
            val name: String // Kids
        ) {
            data class Data(
                val active: Int, // 1
                val brand: String, // 616889f3e0f5f5b576434121
                val category: String, // 618e6d4bae7db56d2c44981e
                @SerializedName("created_date")
                val createdDate: String, // 2022-02-16T14:51:03.693Z
                val deleted: Int, // 0
                val `file`: String, // https://swagbag.sgp1.digitaloceanspaces.com/1645023075012ey61h.webp
                @SerializedName("_id")
                val id: String, // 620d0f6322be9e3768fea0c1
                @SerializedName("master_category")
                val masterCategory: String, // 615ae14c63d6a6435a183db7
                val product: String, // 6148c9ebaaa0e6b191f8bf8a
                val section: Int, // 1
                @SerializedName("update_date")
                val updateDate: String, // 2022-02-19T09:13:46.000Z
                val url: String,
                @SerializedName("__v")
                val v: Int // 0
            )
        }

        data class Slider(
            val active: Int, // 1
            @SerializedName("created_date")
            val createdDate: String, // 2022-02-10T10:07:16.497Z
            val deleted: Int, // 0
            val `file`: String, // https://swagbag-files.s3.amazonaws.com/1650019994406nu6c7.jpeg
            @SerializedName("_id")
            val id: String, // 6204e934c7d7133ea49853db
            val name: String, // Slider 3
            @SerializedName("update_date")
            val updateDate: String, // 2022-04-15T11:35:30.000Z
            val url: String, // https://uae.swagbag.com/men
            @SerializedName("__v")
            val v: Int // 0
        )
    }
}