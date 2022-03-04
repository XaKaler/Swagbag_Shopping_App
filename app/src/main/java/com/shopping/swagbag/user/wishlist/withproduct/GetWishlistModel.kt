package com.shopping.swagbag.user.wishlist.withproduct


import com.google.gson.annotations.SerializedName

data class GetWishlistModel(
    val result: List<Result>,
    val status: String // success
) {
    data class Result(
        @SerializedName("created_date")
        val createdDate: String, // 2022-03-03T08:11:30.416Z
        @SerializedName("_id")
        val id: String, // 62218a8a49ad231a4e66f443
        val image: String, // https://swagbag-space.fra1.digitaloceanspaces.com/1639933803507muv20.webp
        val name: String, // (s) iffalcon 108 cm (43 inches) 4k ultra hd certified android smart led tv 43u61 (black) (2021 model)
        val product: Product,
        val slug: String, // iffalcon-108-cm-43-inches-4k-ultra-hd-certified-android-smart-led-tv-43u61-black-2021-model
        @SerializedName("update_date")
        val updateDate: String, // 2022-03-03T08:11:30.416Z
        val user: String, // 61e82a7acf130b2978d05815
        @SerializedName("__v")
        val v: Int // 0
    ) {
        data class Product(
            val active: Int, // 1
            @SerializedName("added_by")
            val addedBy: String, // 5fe463f5a9e14206002dd63e
            val attribute: String, // Color: RED, Black, Green | RAM: 1GB, 2GB
            val backorders: String,
            val batchno: String,
            val brand: String, // 6148c87ee40e5da07a1d9eed
            val category: List<String>,
            val cgst: String,
            @SerializedName("combo_products")
            val comboProducts: Any?, // null
            val commission: String,
            @SerializedName("created_date")
            val createdDate: String, // 2021-09-20T17:50:32.475Z
            val cuisine: Any?, // null
            val deal: Int, // 0
            val deleted: Int, // 0
            val desc: String, // <ul class="a-unordered-list a-vertical a-spacing-mini" style="margin-right: 0px; margin-bottom: 0px; margin-left: 18px; color: rgb(15, 17, 17); padding: 0px; font-family: &quot;Amazon Ember&quot;, Arial, sans-serif; font-size: 14px;"><li style="list-style: disc; overflow-wrap: break-word; margin: 0px;"><span class="a-list-item">Resolution: 4K Ultra HD (3840 x 2160) | Refresh Rate: 60 Hertz</span></li><li style="list-style: disc; overflow-wrap: break-word; margin: 0px;"><span class="a-list-item">Connectivity : 3 HDMI ports to connect set top box, Blue Ray players | 1 USB ports to connect hard drives and other USB devices</span></li><li style="list-style: disc; overflow-wrap: break-word; margin: 0px;"><span class="a-list-item">Sound: 24 Watts Output | Dolby Audio Power Speakers | Surround Virtualizer</span></li><li style="list-style: disc; overflow-wrap: break-word; margin: 0px;"><span class="a-list-item">Smart TV Features : Google Assistant | 4K Android TV + HDR 10 | AI-IN | T-cast | Bluetooth 5.0</span></li><li style="list-style: disc; overflow-wrap: break-word; margin: 0px;"><span class="a-list-item">Display : A+ Grade Panel | UHD | HDR 10 | Full Screen | Slim Design</span></li><li style="list-style: disc; overflow-wrap: break-word; margin: 0px;"><span class="a-list-item">Warranty Information : 1 Year comprehensive warranty provided by TCL from date of purchase</span></li><li style="list-style: disc; overflow-wrap: break-word; margin: 0px;"><span class="a-list-item">Easy returns: This product is eligible for replacement within 10 days of delivery in case of any product defects, damage or features not matching the description provided</span></li></ul>
            @SerializedName("discounted_price")
            val discountedPrice: Any?, // null
            @SerializedName("end_date")
            val endDate: String, // 2021-09-20T00:00:00.000Z
            val express: Boolean, // false
            val featured: Int, // 0
            val `file`: List<File>,
            val height: String,
            @SerializedName("_id")
            val id: String, // 6148c9ebaaa0e6b191f8bf8a
            val igst: String,
            val length: String,
            @SerializedName("manage_stock")
            val manageStock: Int, // 0
            @SerializedName("master_category")
            val masterCategory: List<String>,
            val name: String, // (s) iffalcon 108 cm (43 inches) 4k ultra hd certified android smart led tv 43u61 (black) (2021 model)
            val options: List<Option>,
            @SerializedName("packaging_charge")
            val packagingCharge: String,
            val point: Int, // 0
            @SerializedName("point_exp_date")
            val pointExpDate: String, // 2021-09-20T00:00:00.000Z
            val price: Int, // 3990
            @SerializedName("product_types")
            val productTypes: List<Any>,
            @SerializedName("return_day")
            val returnDay: String,
            val returnable: String,
            @SerializedName("selling_price")
            val sellingPrice: Int, // 23990
            val sgst: String,
            @SerializedName("shelving_location")
            val shelvingLocation: String,
            @SerializedName("short_desc")
            val shortDesc: String, // iFFALCON 4K HDR TV reproduce in stunning details all shades of light, natural colors for a truly immersive viewing experience via 3,840 x 2,160 pixels (4 x the number of pixels on Full HD TVs). Also HDR 10 adjusts the content in dynamic scene-by- scene way for the optimum representation of contrast with wider color gamut. The TV will present you a perfect picture quality in every scene.
            val sku: String,
            val slug: String, // iffalcon-108-cm-43-inches-4k-ultra-hd-certified-android-smart-led-tv-43u61-black-2021-model
            @SerializedName("slug_history")
            val slugHistory: List<String>,
            @SerializedName("start_date")
            val startDate: String, // 2021-09-20T00:00:00.000Z
            @SerializedName("stock_qty")
            val stockQty: String, // 12
            @SerializedName("sub_category")
            val subCategory: List<String>,
            val tags: String, // 4K, HDR, Dolby, Android
            @SerializedName("tax_status")
            val taxStatus: String,
            val threshold: String,
            @SerializedName("update_date")
            val updateDate: String, // 2021-09-20T17:50:32.475Z
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
                val key: String, // 1639933803507muv20.webp
                val location: String, // https://swagbag-space.fra1.digitaloceanspaces.com/1639933803507muv20.webp
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