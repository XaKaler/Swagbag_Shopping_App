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
        val slider: List<Slider>
    ) {
        data class Deal(
            val active: Int, // 1
            @SerializedName("added_by")
            val addedBy: String, // 5fe463f5a9e14206002dd63e
            @SerializedName("additional_description")
            val additionalDescription: String, // <p style="caret-color: rgb(0, 0, 0); color: rgb(0, 0, 0); margin-right: 0px; margin-bottom: 0px; margin-left: 0px;"><font face="Times New Roman" color="#000000" style="font-stretch: normal; font-size: 12px; line-height: normal; font-family: &quot;Times New Roman&quot;; font-variant-ligatures: common-ligatures;">Features:</font></p><ul style="caret-color: rgb(0, 0, 0); color: rgb(0, 0, 0);"><li style="margin: 0px;"><font face="Times New Roman" color="#000000" style="font-stretch: normal; font-size: 12px; line-height: normal; font-family: &quot;Times New Roman&quot;;"></font><font face="Times New Roman" color="#000000" style="font-stretch: normal; font-size: 12px; line-height: normal; font-family: &quot;Times New Roman&quot;; font-variant-ligatures: common-ligatures;">Organic, Natural &amp; Vegan product</font></li><li style="margin: 0px;"><font face="Times New Roman" color="#000000" style="font-stretch: normal; font-size: 12px; line-height: normal; font-family: &quot;Times New Roman&quot;;"></font><font face="Times New Roman" color="#000000" style="font-stretch: normal; font-size: 12px; line-height: normal; font-family: &quot;Times New Roman&quot;; font-variant-ligatures: common-ligatures;">Inspired By Wild Animals from Pure Siberia Region</font></li><li style="margin: 0px;"><font face="Times New Roman" color="#000000" style="font-stretch: normal; font-size: 12px; line-height: normal; font-family: &quot;Times New Roman&quot;;"></font><font face="Times New Roman" color="#000000" style="font-stretch: normal; font-size: 12px; line-height: normal; font-family: &quot;Times New Roman&quot;; font-variant-ligatures: common-ligatures;">Contains Valuable Organic Extracts of Siberian Plants from Natura Siberica Organic farms.</font></li><li style="margin: 0px;"><font face="Times New Roman" color="#000000" style="font-stretch: normal; font-size: 12px; line-height: normal; font-family: &quot;Times New Roman&quot;;"></font><font face="Times New Roman" color="#000000" style="font-stretch: normal; font-size: 12px; line-height: normal; font-family: &quot;Times New Roman&quot;; font-variant-ligatures: common-ligatures;">Is A Soothing Remedy For Pets With Itchy Skin.</font></li></ul>
            val attribute: String,
            val backorders: String,
            val batchno: String,
            val brand: String, // 6211fd260fe91d273d217a3f
            val category: List<String>,
            val cgst: String,
            @SerializedName("combo_products")
            val comboProducts: Any?, // null
            val commission: String,
            val createdAt: String, // 2022-06-01T04:27:15.968Z
            @SerializedName("created_date")
            val createdDate: String, // 2022-05-28T06:36:55.438Z
            val cuisine: Any?, // null
            val deal: Int, // 1
            val deleted: Int, // 0
            val desc: String, // <p style="caret-color: rgb(0, 0, 0); color: rgb(0, 0, 0); margin-right: 0px; margin-bottom: 0px; margin-left: 0px;"><font face="Times New Roman" color="#000000" style="font-stretch: normal; font-size: 12px; line-height: normal; font-family: &quot;Times New Roman&quot;; font-variant-ligatures: common-ligatures;">The conditioner contains bearberry that is rich in vitamins and amino acids that counteract itchiness whilst nourishing the fur of both cats and dogs. The conditioner possesses soothing properties.</font></p>
            val details: String, // <ul style="caret-color: rgb(0, 0, 0); color: rgb(0, 0, 0);"><li style="margin: 0px;"><font face="Times New Roman" color="#000000" style="font-stretch: normal; font-size: 12px; line-height: normal; font-family: &quot;Times New Roman&quot;;"></font><font face="Times New Roman" color="#000000" style="font-stretch: normal; font-size: 12px; line-height: normal; font-family: &quot;Times New Roman&quot;; font-variant-ligatures: common-ligatures;">Size : 400 ml</font></li><li style="margin: 0px;"><font face="Times New Roman" color="#000000" style="font-stretch: normal; font-size: 12px; line-height: normal; font-family: &quot;Times New Roman&quot;;"></font><font face="Times New Roman" color="#000000" style="font-stretch: normal; font-size: 12px; line-height: normal; font-family: &quot;Times New Roman&quot;; font-variant-ligatures: common-ligatures;">Brand : Wilda Siberica</font></li></ul>
            @SerializedName("discounted_price")
            val discountedPrice: Any?, // null
            @SerializedName("end_date")
            val endDate: String, // 2022-05-28T06:36:55.438Z
            val express: Boolean, // false
            val featured: Int?, // 1
            val `file`: List<File>,
            val height: String,
            @SerializedName("_id")
            val id: String, // 6296eaa39fab9f2770a8fa9a
            val igst: String,
            val length: String,
            @SerializedName("manage_stock")
            val manageStock: Int, // 1
            @SerializedName("master_category")
            val masterCategory: List<String>,
            val name: String, // Antistress Pet Conditioner
            val options: List<Any>,
            @SerializedName("packaging_charge")
            val packagingCharge: String,
            val point: String, // 0
            @SerializedName("point_exp_date")
            val pointExpDate: String, // 2022-05-28T06:36:55.438Z
            val price: Double, // 60.5
            @SerializedName("product_types")
            val productTypes: List<String>,
            @SerializedName("return_day")
            val returnDay: String, // 7
            val returnable: String, // 1
            @SerializedName("selling_price")
            val sellingPrice: Any?, // null
            val sgst: String,
            @SerializedName("shelving_location")
            val shelvingLocation: String, // Sharjah
            @SerializedName("short_desc")
            val shortDesc: String,
            val sku: String, // 567789
            val slug: String, // antistress-pet-conditioner
            @SerializedName("slug_history")
            val slugHistory: List<String>,
            @SerializedName("start_date")
            val startDate: String, // 2022-05-28T06:36:55.438Z
            @SerializedName("stock_qty")
            val stockQty: String, // 10
            @SerializedName("sub_category")
            val subCategory: List<String>,
            val tags: String, // Pet Care
            @SerializedName("tax_status")
            val taxStatus: String,
            val threshold: String, // 2
            @SerializedName("update_date")
            val updateDate: String, // 2022-05-28T06:36:55.438Z
            val updatedAt: String, // 2022-06-01T04:27:15.968Z
            @SerializedName("__v")
            val v: Int, // 0
            val vendor: String, // 62960cac9fab9f2770a8ee12
            @SerializedName("video_url")
            val videoUrl: String,
            val weight: String,
            val width: String
        ) {
            data class File(
                val acl: String, // public-read
                val bucket: String, // swagbag-files
                val contentDisposition: Any?, // null
                val contentEncoding: Any?, // null
                val contentType: String, // application/octet-stream
                val encoding: String, // 7bit
                val etag: String, // "fe059ad19f67a6692302c2948c7529ed"
                val fieldname: String, // upload
                val key: String, // 1654057635419olhkw.jpeg
                val location: String, // https://swagbag-files.s3.amazonaws.com/1654057635419olhkw.jpeg
                val metadata: Any?, // null
                val mimetype: String, // image/jpeg
                val originalname: String, // 21. Antistress Pet Conditioner.jpg
                val serverSideEncryption: Any?, // null
                val size: Int, // 11154
                val storageClass: String, // STANDARD
                val versionId: Any? // null
            )
        }

        data class Featured(
            val active: Int, // 1
            @SerializedName("added_by")
            val addedBy: String, // 5fe463f5a9e14206002dd63e
            @SerializedName("additional_description")
            val additionalDescription: String, // <p style="caret-color: rgb(0, 0, 0); color: rgb(0, 0, 0); margin-right: 0px; margin-bottom: 0px; margin-left: 0px;"><font face="Times New Roman" color="#000000" style="font-stretch: normal; font-size: 12px; line-height: normal; font-family: &quot;Times New Roman&quot;; font-variant-ligatures: common-ligatures;"><b>Indications:</b></font></p><p style="caret-color: rgb(0, 0, 0); color: rgb(0, 0, 0); margin-right: 0px; margin-bottom: 0px; margin-left: 0px;"><font face="Times New Roman" color="#000000" style="font-stretch: normal; font-size: 12px; line-height: normal; font-family: &quot;Times New Roman&quot;; font-variant-ligatures: common-ligatures;">Pure, rose water, all types of skin, pure fragrance, rose, mist, fresh, Nourishk Essencia, steam distilled , roses, skin health, skin glow, radiant, radiance, glow, smooth, closes pores, reduces pores, smooth skin</font></p><p style="margin-right: 0px; margin-bottom: 0px; margin-left: 0px; font-stretch: normal; font-size: 12px; line-height: normal; font-family: &quot;Times New Roman&quot;; color: rgb(0, 0, 0); min-height: 15px;"><font style="font-variant-ligatures: common-ligatures;"></font><br></p><p style="caret-color: rgb(0, 0, 0); color: rgb(0, 0, 0); margin-right: 0px; margin-bottom: 0px; margin-left: 0px;"><font face="Times New Roman" color="#000000" style="font-stretch: normal; font-size: 12px; line-height: normal; font-family: &quot;Times New Roman&quot;; font-variant-ligatures: common-ligatures;"><b>Ingredients:</b></font></p><p style="caret-color: rgb(0, 0, 0); color: rgb(0, 0, 0); margin-right: 0px; margin-bottom: 0px; margin-left: 0px;"><font face="Times New Roman" color="#000000" style="font-stretch: normal; font-size: 12px; line-height: normal; font-family: &quot;Times New Roman&quot;; font-variant-ligatures: common-ligatures;">Pure Rose Petals</font></p><p style="margin-right: 0px; margin-bottom: 0px; margin-left: 0px; font-stretch: normal; font-size: 12px; line-height: normal; font-family: &quot;Times New Roman&quot;; color: rgb(0, 0, 0); min-height: 15px;"><font style="font-variant-ligatures: common-ligatures;"></font><br></p><p style="caret-color: rgb(0, 0, 0); color: rgb(0, 0, 0); margin-right: 0px; margin-bottom: 0px; margin-left: 0px;"><font face="Times New Roman" color="#000000" style="font-stretch: normal; font-size: 12px; line-height: normal; font-family: &quot;Times New Roman&quot;; font-variant-ligatures: common-ligatures;"><b>Directions:</b></font></p><p style="caret-color: rgb(0, 0, 0); color: rgb(0, 0, 0); margin-right: 0px; margin-bottom: 0px; margin-left: 0px;"><font face="Times New Roman" color="#000000" style="font-stretch: normal; font-size: 12px; line-height: normal; font-family: &quot;Times New Roman&quot;; font-variant-ligatures: common-ligatures;">Apply Directly on Face</font></p>
            val attribute: String,
            val backorders: String,
            val batchno: String,
            val brand: String, // 62a18286f8868663bc642dc6
            val category: List<String>,
            val cgst: String,
            @SerializedName("combo_products")
            val comboProducts: Any?, // null
            val commission: String,
            val createdAt: String, // 2022-06-09T05:47:10.043Z
            @SerializedName("created_date")
            val createdDate: String, // 2022-06-08T13:26:15.714Z
            val cuisine: Any?, // null
            val deal: Any?, // null
            val deleted: Int, // 0
            val desc: String, // <p style="caret-color: rgb(0, 0, 0); color: rgb(0, 0, 0); margin-right: 0px; margin-bottom: 0px; margin-left: 0px;"><font face="Times New Roman" color="#000000" style="font-stretch: normal; font-size: 12px; line-height: normal; font-family: &quot;Times New Roman&quot;; font-variant-ligatures: common-ligatures;">Nourishk Essencia Pure Rose water, for all skin types, made up of steam distilled fresh rose extracts only. Closes open pores, smoothes skin. Great toner for skin. Great to be used as a refreshing face mist.</font></p>
            val details: String, // <ul style="caret-color: rgb(0, 0, 0); color: rgb(0, 0, 0);"><li style="margin: 0px;"><font face="Times New Roman" color="#000000" style="font-stretch: normal; font-size: 4.4px; line-height: normal; font-family: &quot;Times New Roman&quot;;"></font><font face="Times New Roman" color="#000000" style="font-stretch: normal; font-size: 12px; line-height: normal; font-family: &quot;Times New Roman&quot;; font-variant-ligatures: common-ligatures;">Size : 100 ml</font></li><li style="margin: 0px;"><font face="Times New Roman" color="#000000" style="font-stretch: normal; font-size: 4.4px; line-height: normal; font-family: &quot;Times New Roman&quot;;"></font><font face="Times New Roman" color="#000000" style="font-stretch: normal; font-size: 12px; line-height: normal; font-family: &quot;Times New Roman&quot;; font-variant-ligatures: common-ligatures;">Item Form : Spray</font></li><li style="margin: 0px;"><font face="Times New Roman" color="#000000" style="font-stretch: normal; font-size: 4.4px; line-height: normal; font-family: &quot;Times New Roman&quot;;"></font><font face="Times New Roman" color="#000000" style="font-stretch: normal; font-size: 12px; line-height: normal; font-family: &quot;Times New Roman&quot;; font-variant-ligatures: common-ligatures;">Brand Nourishk Essencia</font></li><li style="margin: 0px;"><font face="Times New Roman" color="#000000" style="font-stretch: normal; font-size: 4.4px; line-height: normal; font-family: &quot;Times New Roman&quot;;"></font><font face="Times New Roman" color="#000000" style="font-stretch: normal; font-size: 12px; line-height: normal; font-family: &quot;Times New Roman&quot;; font-variant-ligatures: common-ligatures;">Country Of Origin : India</font></li></ul>
            @SerializedName("discounted_price")
            val discountedPrice: Any?, // null
            @SerializedName("end_date")
            val endDate: String, // 2022-06-08T13:26:15.714Z
            val express: Boolean, // false
            val featured: Int, // 1
            val `file`: List<File>,
            val height: String,
            @SerializedName("_id")
            val id: String, // 62a1895df8868663bc642ffa
            val igst: String,
            val length: String,
            @SerializedName("manage_stock")
            val manageStock: Int, // 1
            @SerializedName("master_category")
            val masterCategory: List<String>,
            val name: String, // Rose Water
            val options: List<Any>,
            @SerializedName("packaging_charge")
            val packagingCharge: String,
            val point: String, // 0
            @SerializedName("point_exp_date")
            val pointExpDate: String, // 2022-06-08T13:26:15.714Z
            val price: Double, // 17.5
            @SerializedName("product_types")
            val productTypes: List<String>,
            @SerializedName("return_day")
            val returnDay: String, // 7
            val returnable: String, // 1
            @SerializedName("selling_price")
            val sellingPrice: Any?, // null
            val sgst: String,
            @SerializedName("shelving_location")
            val shelvingLocation: String, // Sharjah
            @SerializedName("short_desc")
            val shortDesc: String, // Pure and Rich Fragrance of Roses, Steam distilled, Face Toner, Face Mist, Closes open pores
            val sku: String, // NOES002-ROWA
            val slug: String, // rose-water
            @SerializedName("slug_history")
            val slugHistory: List<String>,
            @SerializedName("start_date")
            val startDate: String, // 2022-06-08T13:26:15.714Z
            @SerializedName("stock_qty")
            val stockQty: String, // 10
            @SerializedName("sub_category")
            val subCategory: List<String>,
            val tags: String, // Smoothening, Freshening, Radiance
            @SerializedName("tax_status")
            val taxStatus: String,
            val threshold: String, // 2
            @SerializedName("update_date")
            val updateDate: String, // 2022-06-08T13:26:15.714Z
            val updatedAt: String, // 2022-06-09T05:47:10.043Z
            @SerializedName("__v")
            val v: Int, // 0
            val vendor: String, // 62a18311f8868663bc642e07
            @SerializedName("video_url")
            val videoUrl: String,
            val weight: String,
            val width: String
        ) {
            data class File(
                val acl: String, // public-read
                val bucket: String, // swagbag-files
                val contentDisposition: Any?, // null
                val contentEncoding: Any?, // null
                val contentType: String, // application/octet-stream
                val encoding: String, // 7bit
                val etag: String, // "99bbb8d113b7c411873badc872cede63"
                val fieldname: String, // upload
                val key: String, // 1654753628906xf1sd.jpeg
                val location: String, // https://swagbag-files.s3.amazonaws.com/1654753628906xf1sd.jpeg
                val metadata: Any?, // null
                val mimetype: String, // image/jpeg
                val originalname: String, // 1. Rose Water 1.jpg
                val serverSideEncryption: Any?, // null
                val size: Int, // 50624
                val storageClass: String, // STANDARD
                val versionId: Any? // null
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

        data class Slider(
            val active: Int, // 1
            @SerializedName("created_date")
            val createdDate: String, // 2022-02-10T10:07:16.497Z
            val deleted: Int, // 0
            val `file`: String, // https://swagbag-files.s3.amazonaws.com/1652167744346ywteg.jpeg
            @SerializedName("_id")
            val id: String, // 6204e934c7d7133ea49853db
            val name: String, // Slider 3
            @SerializedName("update_date")
            val updateDate: String, // 2022-05-10T07:29:05.000Z
            val url: String, // https://uae.swagbag.com/men
            @SerializedName("__v")
            val v: Int // 0
        )
    }
}