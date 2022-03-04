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
        val slider: List<Slider>
    ) {
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
            val point: Int, // 20
            @SerializedName("point_exp_date")
            val pointExpDate: String, // 2022-01-30T00:00:00.000Z
            val price: Int, // 70
            @SerializedName("product_types")
            val productTypes: List<String>,
            @SerializedName("return_day")
            val returnDay: String, // 15
            val returnable: String, // 1
            @SerializedName("selling_price")
            val sellingPrice: Int, // 65
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
            val backorders: String, // yes
            val batchno: String, // 12123
            val brand: String, // 61fb80663669ad2911a25fb9
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
            val desc: String, // To be used twice a day&nbsp;
            @SerializedName("discounted_price")
            val discountedPrice: Any?, // null
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
            val options: List<Option>,
            @SerializedName("packaging_charge")
            val packagingCharge: String, // 1
            val point: String, // 1
            @SerializedName("point_exp_date")
            val pointExpDate: String, // 2022-04-14T00:00:00.000Z
            val price: Int, // 115
            @SerializedName("product_types")
            val productTypes: Any?, // null
            @SerializedName("return_day")
            val returnDay: String,
            val returnable: String,
            @SerializedName("selling_price")
            val sellingPrice: Int, // 100
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
                val contentDisposition: Any?, // null
                val contentEncoding: Any?, // null
                val contentType: String, // application/octet-stream
                val encoding: String, // 7bit
                val etag: String, // "841c4c44fe124cb8bb90ba6e27a1d9b7"
                val fieldname: String, // upload
                val key: String, // 1645689012867ihzer.png
                val location: String, // https://swagbag.sgp1.digitaloceanspaces.com/1645689012867ihzer.png
                val metadata: Any?, // null
                val mimetype: String, // image/png
                val originalname: String, // 1.png
                val serverSideEncryption: Any?, // null
                val size: Int, // 607861
                val storageClass: String // STANDARD
            )

            data class Option(
                val name: String, // Color
                val value: String // #d41367:500:SKU123:51, #1913d4:1000:SKU456:50, #4dd413:0:SKU4578:50
            )
        }

        data class MasterCategory(
            val active: Int, // 1
            @SerializedName("created_date")
            val createdDate: String, // 2021-09-23T13:53:06.672Z
            val deleted: Int, // 0
            val desc: String, // Men
            val `file`: String, // https://swagbag-space.fra1.digitaloceanspaces.com/1639941989132rg7xa.png
            @SerializedName("_id")
            val id: String, // 615ae18463d6a6435a183dcc
            val name: String, // Men
            @SerializedName("short_desc")
            val shortDesc: String, // Men
            val slug: String, // men
            @SerializedName("slug_history")
            val slugHistory: List<String>,
            val sort: Int, // 1
            @SerializedName("update_date")
            val updateDate: String, // 2021-12-19T19:26:33.000Z
            @SerializedName("__v")
            val v: Int // 0
        )

        data class RandomCategory(
            val active: Int, // 1
            val brands: List<String>,
            @SerializedName("created_date")
            val createdDate: String, // 2021-11-11T16:24:24.827Z
            val deleted: Int, // 0
            val description: String, // Shoes
            @SerializedName("description_after_content")
            val descriptionAfterContent: String?, // Shoes
            val `file`: String, // https://swagbag-space.fra1.digitaloceanspaces.com/16367240429507ra4a.png
            @SerializedName("_id")
            val id: String, // 618e6d4bae7db56d2c44981e
            val master: String, // 615ae18463d6a6435a183dcc
            val name: String, // Shoes
            val parent: Any?, // null
            val slug: String, // shoes-1
            @SerializedName("slug_history")
            val slugHistory: List<String>,
            @SerializedName("update_date")
            val updateDate: String, // 2021-11-11T16:24:24.827Z
            @SerializedName("__v")
            val v: Int // 0
        )

        data class Slider(
            val active: Int, // 1
            @SerializedName("created_date")
            val createdDate: String, // 2022-02-10T10:07:16.497Z
            val deleted: Int, // 0
            val `file`: String, // https://swagbag.sgp1.digitaloceanspaces.com/1644669040751nb7u4.webp
            @SerializedName("_id")
            val id: String, // 6204e934c7d7133ea49853db
            val name: String, // Slider 1
            @SerializedName("update_date")
            val updateDate: String, // 2022-02-12T12:42:00.000Z
            val url: String, // https://uae.swagbag.com/women
            @SerializedName("__v")
            val v: Int // 0
        )
    }
}