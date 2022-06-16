package com.shopping.swagbag.category


import com.google.gson.annotations.SerializedName

data class ParticularCategoryModel(
    val result: Result,
    val status: String // success
) {
    data class Result(
        val brand: List<Brand>,
        val category: List<Category>,
        val deals: List<Deal>,
        val featured: List<Featured>,
        val section: List<Section>
    ) {
        data class Brand(
            val active: Int, // 1
            @SerializedName("created_date")
            val createdDate: String, // 2021-11-13T08:14:09.054Z
            val deleted: Int, // 0
            val desc: String, // Apple
            val `file`: Any?, // null
            @SerializedName("_id")
            val id: String, // 6192d873c80113238e4ac04d
            val name: String, // Apple
            @SerializedName("short_desc")
            val shortDesc: String?,
            val slug: String, // apple
            @SerializedName("slug_history")
            val slugHistory: List<String>,
            @SerializedName("update_date")
            val updateDate: String, // 2021-11-13T08:14:09.054Z
            @SerializedName("__v")
            val v: Int // 0
        )

        data class Category(
            val active: Int, // 1
            val brands: List<String>,
            @SerializedName("created_date")
            val createdDate: String, // 2021-11-12T10:12:31.248Z
            val deleted: Int, // 0
            val description: String,
            @SerializedName("description_after_content")
            val descriptionAfterContent: String?,
            val `file`: String, // https://swagbag-space.fra1.digitaloceanspaces.com/1636718594870igi4m.jpeg
            @SerializedName("_id")
            val id: String, // 618e5775d107e52f281259b2
            val master: String, // 61bd25a466538a1c1f366ac3
            val name: String, // HANDBAGS
            val parent: Any?, // null
            val slug: String, // handbags
            @SerializedName("slug_history")
            val slugHistory: List<String>,
            @SerializedName("update_date")
            val updateDate: String, // 2021-12-18T08:28:32.000Z
            @SerializedName("__v")
            val v: Int // 0
        )

        data class Deal(
            val active: Int, // 1
            @SerializedName("added_by")
            val addedBy: String, // 5fe463f5a9e14206002dd63e
            val attribute: String,
            val backorders: String,
            val batchno: String, // 232323
            val brand: String, // 61bb7f2166538a1c1f36671d
            val category: List<String>,
            val cgst: String,
            @SerializedName("combo_products")
            val comboProducts: Any?, // null
            val commission: String, // 10
            @SerializedName("created_date")
            val createdDate: String, // 2021-12-27T05:54:26.787Z
            val cuisine: Any?, // null
            val deal: Int, // 1
            val deleted: Int, // 0
            val desc: String, // <div>Wrap yourself in the dreamiest spring with this perky beige and pink mini dress. Tailored in a wrap design, the dress features an all-over floral print and cutesy balloon sleeves.</div><div><br></div><div>Style Tip: Pair the dress with a hairband and strappy flats.</div><div><br></div><div><span style="color: rgb(153, 153, 153); font-family: Lato, sans-serif; font-size: 12px;">Dispatch: Within&nbsp;</span><span id="shipHours" style="margin: 0px; padding: 0px; color: rgb(153, 153, 153); font-family: Lato, sans-serif; font-size: 12px;">48-72</span><span style="color: rgb(153, 153, 153); font-family: Lato, sans-serif; font-size: 12px;">&nbsp;Hours</span><br style="margin: 0px; padding: 0px; color: rgb(153, 153, 153); font-family: Lato, sans-serif; font-size: 12px;"><b style="margin: 0px; padding: 0px; color: rgb(153, 153, 153); font-family: Lato, sans-serif; font-size: 12px;">*Note:</b><span style="color: rgb(153, 153, 153); font-family: Lato, sans-serif; font-size: 12px;">&nbsp;Delivery of orders may take longer due to region-wise lockdown and diversion of routes.</span><br style="margin: 0px; padding: 0px; color: rgb(153, 153, 153); font-family: Lato, sans-serif; font-size: 12px;"><span style="color: rgb(153, 153, 153); font-family: Lato, sans-serif; font-size: 12px;">Return/Exchange: If you are not completely satisfied with your purchase, simply select the option of return/exchange within 15 days of receiving your order from your order details page and we will process your return, no questions asked.</span><br></div>
            @SerializedName("discounted_price")
            val discountedPrice: Any?, // null
            @SerializedName("end_date")
            val endDate: String, // 2021-12-27T00:00:00.000Z
            val express: Boolean, // false
            val featured: Int, // 1
            val `file`: List<File>,
            val height: String, // 1
            @SerializedName("_id")
            val id: String, // 61c968b2639b7b51637c5da2
            val igst: String,
            val length: String, // 1
            @SerializedName("manage_stock")
            val manageStock: Int, // 0
            @SerializedName("master_category")
            val masterCategory: List<String>,
            val name: String, // Beige Pink Floral Mini Wrap Dress
            val options: List<Option>,
            @SerializedName("packaging_charge")
            val packagingCharge: String, // 40
            val point: String, // 20
            @SerializedName("point_exp_date")
            val pointExpDate: String, // 2022-01-30T00:00:00.000Z
            val price: Float, // 70
            @SerializedName("product_types")
            val productTypes: List<String>,
            @SerializedName("return_day")
            val returnDay: String, // 15
            val returnable: String, // 1
            @SerializedName("selling_price")
            val sellingPrice: Float, // 65
            val sgst: String,
            @SerializedName("shelving_location")
            val shelvingLocation: String, // Zone 1
            @SerializedName("short_desc")
            val shortDesc: String, // Beige Pink Floral Mini Wrap Dress
            val sku: String, // IT-3699
            val slug: String, // beige-pink-floral-mini-wrap-dress
            @SerializedName("slug_history")
            val slugHistory: List<String>,
            @SerializedName("start_date")
            val startDate: String, // 2021-12-27T00:00:00.000Z
            @SerializedName("stock_qty")
            val stockQty: String, // 1000
            @SerializedName("sub_category")
            val subCategory: List<String>,
            val tags: String, // Cloth, Floral
            @SerializedName("tax_status")
            val taxStatus: String,
            val threshold: String,
            @SerializedName("update_date")
            val updateDate: String, // 2021-12-27T05:54:26.787Z
            @SerializedName("__v")
            val v: Int, // 0
            val vendor: String, // 61fb87943669ad2911a26008
            @SerializedName("video_url")
            val videoUrl: String, // https://www.youtube.com/embed/Sk0dy_wbXSQ
            val weight: String, // 1
            val width: String // 1
        ) {
            data class File(
                val acl: String, // public-read
                val bucket: String, // swagbag-space
                val contentDisposition: Any?, // null
                val contentEncoding: Any?, // null
                val contentType: String, // application/octet-stream
                val encoding: String, // 7bit
                val etag: String, // "f2c83d741aeee55d7685dc63177917d0"
                val fieldname: String, // upload
                val key: String, // 1640589488850j4byg.jpeg
                val location: String, // https://swagbag-space.fra1.digitaloceanspaces.com/1640589488850j4byg.jpeg
                val metadata: Any?, // null
                val mimetype: String, // image/jpeg
                val originalname: String, // 4.4.jpg
                val serverSideEncryption: Any?, // null
                val size: Int, // 22661
                val storageClass: String // STANDARD
            )

            data class Option(
                val name: String, // Color
                val value: String // #d41367:500:SKU123:51, #1913d4:1000:SKU456:50, #4dd413:0:SKU4578:50
            )
        }

        data class Featured(
            val active: Int, // 1
            @SerializedName("added_by")
            val addedBy: String, // 5fe463f5a9e14206002dd63e
            val attribute: String,
            val backorders: String,
            val batchno: String, // 1234567
            val brand: String, // 6211dc2f0fe91d273d2178b9
            val category: List<String>,
            val cgst: String,
            @SerializedName("combo_products")
            val comboProducts: Any?, // null
            val commission: String, // 0
            @SerializedName("created_date")
            val createdDate: String, // 2022-02-22T07:51:16.617Z
            val cuisine: Any?, // null
            val deal: Int, // 0
            val deleted: Int, // 0
            val desc: String, // Effective on skin. Helps in glowing the skin
            @SerializedName("discounted_price")
            val discountedPrice: Any?, // null
            @SerializedName("end_date")
            val endDate: String, // 2022-02-22T00:00:00.000Z
            val express: Boolean, // false
            val featured: Int, // 1
            val `file`: List<File>,
            val height: String,
            @SerializedName("_id")
            val id: String, // 6217661f60550b55668e0599
            val igst: String,
            val length: String,
            @SerializedName("manage_stock")
            val manageStock: Int, // 1
            @SerializedName("master_category")
            val masterCategory: List<String>,
            val name: String, // Face Mask
            val options: List<Option>,
            @SerializedName("packaging_charge")
            val packagingCharge: String, // 1
            val point: String, // 0.1
            @SerializedName("point_exp_date")
            val pointExpDate: String, // 2022-04-06T00:00:00.000Z
            val price: Float, // 70
            @SerializedName("product_types")
            val productTypes: Any?, // null
            @SerializedName("return_day")
            val returnDay: String,
            val returnable: String,
            @SerializedName("selling_price")
            val sellingPrice: Float, // 65
            val sgst: String,
            @SerializedName("shelving_location")
            val shelvingLocation: String,
            @SerializedName("short_desc")
            val shortDesc: String, // Smoothens Face
            val sku: String, // 121232
            val slug: String, // face-mask
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
            val vendor: String, // 621731b360550b55668df97b
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
                val etag: String, // "78ec6aaf39a3dc20811f34f6f34dfe29"
                val fieldname: String, // upload
                val key: String, // 1645700635530lwle7.png
                val location: String, // https://swagbag.sgp1.digitaloceanspaces.com/1645700635530lwle7.png
                val metadata: Any?, // null
                val mimetype: String, // image/png
                val originalname: String, // Green_cosmetics.png
                val serverSideEncryption: Any?, // null
                val size: Int, // 292982
                val storageClass: String // STANDARD
            )

            data class Option(
                val name: String, // Color
                val value: String // #d41367:500:SKU123:51, #1913d4:1000:SKU456:50, #4dd413:0:SKU4578:50
            )
        }

        data class Section(
            val active: Int, // 1
            val brand: String, // 616889f3e0f5f5b576434121
            val category: String, // 618e6d4bae7db56d2c44981e
            @SerializedName("created_date")
            val createdDate: String, // 2022-02-16T14:51:03.693Z
            val deleted: Int, // 0
            val `file`: String, // https://swagbag.sgp1.digitaloceanspaces.com/1645023075012ey61h.webp
            @SerializedName("_id")
            val id: String, // 6210b7f15f5e06bee40138b6
            @SerializedName("master_category")
            val masterCategory: String, // 61bd25a466538a1c1f366ac3
            val product: String, // 6148c9ebaaa0e6b191f8bf8a
            val section: Int, // 1
            @SerializedName("update_date")
            val updateDate: String, // 2022-02-19T09:13:46.000Z
            val url: String,
            @SerializedName("__v")
            val v: Int // 0
        )
    }
}