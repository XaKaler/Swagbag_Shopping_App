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
            val createdDate: String, // 2022-01-31T14:11:28.360Z
            val deleted: Int, // 0
            val desc: String, // Lorem Ipsum is simply dummy text of the printing and typesetting industry. Lorem Ipsum has been the industry's standard dummy text ever since the 1500s, when an unknown printer took a galley of type and scrambled it to make a type specimen book. It has survived not only five centuries, but also the leap into electronic typesetting, remaining essentially unchanged. It was popularised in the 1960s with the release of Letraset sheets containing Lorem Ipsum passages, and more recently with desktop publishing software like Aldus PageMaker including versions of Lorem Ipsum.
            val desc2: String,
            val desc3: String,
            val `file`: String, // https://swagbag-files.s3.amazonaws.com/165277238669094ol7.png
            val file2: String,
            val file3: String,
            val file4: String,
            @SerializedName("_id")
            val id: String, // 61f8ce7d55c8807f6a29e6ee
            val name: String, // Mama Basics
            @SerializedName("seo_desc")
            val seoDesc: String, // Lorem Ipsum is simply dummy text of the printing and typesetting industry. Lorem Ipsum has been the industry's standard dummy text ever since the 1500s, when an unknown printer took a galley of type and scrambled it to make a type specimen book. It has survived not only five centuries, but also the leap into electronic typesetting, remaining essentially unchanged. It was popularised in the 1960s with the release of Letraset sheets containing Lorem Ipsum passages, and more recently with desktop publishing software like Aldus PageMaker including versions of Lorem Ipsum.
            @SerializedName("seo_title")
            val seoTitle: String, // Mama Basics
            @SerializedName("short_desc")
            val shortDesc: String?,
            val slug: String, // mama-basics
            @SerializedName("slug_history")
            val slugHistory: List<String>,
            @SerializedName("update_date")
            val updateDate: String, // 2022-05-17T07:26:26.000Z
            @SerializedName("__v")
            val v: Int // 0
        )

        data class Category(
            val active: Int, // 1
            val brands: List<String>,
            @SerializedName("created_date")
            val createdDate: String, // 2022-04-15T11:31:58.835Z
            val deleted: Int, // 0
            val description: String, // Everything that comes under self care and wellness
            @SerializedName("description_after_content")
            val descriptionAfterContent: Any?, // null
            val `file`: String, // https://swagbag-files.s3.amazonaws.com/1650971844529gj65a.jpeg
            @SerializedName("_id")
            val id: String, // 625d05bfad10bc2c6a9bb90e
            val master: String, // 61bd25a466538a1c1f366ac3
            val name: String, // Self Love
            val parent: Any?, // null
            val slug: String, // self-love-2
            @SerializedName("slug_history")
            val slugHistory: List<String>,
            @SerializedName("update_date")
            val updateDate: String, // 2022-04-26T11:17:25.000Z
            @SerializedName("__v")
            val v: Int // 0
        )

        data class Deal(
            val active: Int, // 1
            @SerializedName("added_by")
            val addedBy: String?, // 5fe463f5a9e14206002dd63e
            @SerializedName("additional_description")
            val additionalDescription: String, // <p style="caret-color: rgb(0, 0, 0); color: rgb(0, 0, 0); margin-right: 0px; margin-bottom: 0px; margin-left: 0px;"><font face="Helvetica" color="#000000" style="font-stretch: normal; line-height: normal; font-family: Helvetica; font-variant-ligatures: common-ligatures;">Our deodorants are a 100% natural, natural hypoallergenic skin care product that does not contain fragrances, parabens and aluminum hydrochloride.</font></p><p style="margin-right: 0px; margin-bottom: 0px; margin-left: 0px; font-stretch: normal; line-height: normal; font-family: Helvetica; color: rgb(0, 0, 0); min-height: 11px;"><font style="font-variant-ligatures: common-ligatures;"></font><br></p><p style="caret-color: rgb(0, 0, 0); color: rgb(0, 0, 0); margin-right: 0px; margin-bottom: 0px; margin-left: 0px;"><font face="Helvetica" color="#000000" style="font-stretch: normal; line-height: normal; font-family: Helvetica; font-variant-ligatures: common-ligatures;">Ingredients</font></p><p style="caret-color: rgb(0, 0, 0); color: rgb(0, 0, 0); margin-right: 0px; margin-bottom: 0px; margin-left: 0px;"><font face="Helvetica" color="#000000" style="font-stretch: normal; line-height: normal; font-family: Helvetica; font-variant-ligatures: common-ligatures;">Potassium alum</font></p><p style="margin-right: 0px; margin-bottom: 0px; margin-left: 0px; font-stretch: normal; line-height: normal; font-family: Helvetica; color: rgb(0, 0, 0); min-height: 11px;"><font style="font-variant-ligatures: common-ligatures;"></font><br></p><p style="caret-color: rgb(0, 0, 0); color: rgb(0, 0, 0); margin-right: 0px; margin-bottom: 0px; margin-left: 0px;"><font face="Helvetica" color="#000000" style="font-stretch: normal; line-height: normal; font-family: Helvetica; font-variant-ligatures: common-ligatures;">Directions</font></p><p style="caret-color: rgb(0, 0, 0); color: rgb(0, 0, 0); margin-right: 0px; margin-bottom: 0px; margin-left: 0px;"><font face="Helvetica" color="#000000" style="font-stretch: normal; line-height: normal; font-family: Helvetica; font-variant-ligatures: common-ligatures;">The best time to apply the deodorant is&nbsp;right after you shower or bathe, when your underarms are freshly cleaned and still slightly damp.&nbsp;</font></p>
            val attribute: String,
            val backorders: String,
            val batchno: String,
            val brand: String, // 6211dc2f0fe91d273d2178b9
            val category: List<String>,
            val cgst: String,
            @SerializedName("combo_products")
            val comboProducts: Any?, // null
            val commission: String,
            val createdAt: String, // 2022-05-24T06:27:52.050Z
            @SerializedName("created_date")
            val createdDate: String, // 2022-05-20T08:44:57.318Z
            val cuisine: Any?, // null
            val deal: Int, // 1
            val deleted: Int, // 0
            val desc: String, // <p style="caret-color: rgb(0, 0, 0); color: rgb(0, 0, 0); margin-right: 0px; margin-bottom: 0px; margin-left: 0px;"><font face="Times New Roman" color="#000000" style="font-stretch: normal; font-size: 12px; line-height: normal; font-family: &quot;Times New Roman&quot;; font-variant-ligatures: common-ligatures;">Safe for any skin type, gently cares for irritated skin even after hair removal. The deodorant is made on the basis of potassium alum, essential oils and plant extracts. Chemical free, sulfate &amp; silicone free.</font></p><p style="margin-right: 0px; margin-bottom: 0px; margin-left: 0px; font-stretch: normal; font-size: 12px; line-height: normal; font-family: &quot;Times New Roman&quot;; color: rgb(0, 0, 0); min-height: 15px;"><font style="font-variant-ligatures: common-ligatures;"></font><br></p><p style="caret-color: rgb(0, 0, 0); color: rgb(0, 0, 0); margin-right: 0px; margin-bottom: 0px; margin-left: 0px;"><font face="Times New Roman" color="#000000" style="font-stretch: normal; font-size: 12px; line-height: normal; font-family: &quot;Times New Roman&quot;; font-variant-ligatures: common-ligatures;">Aluminium-potassium alum is a natural mineral that has an antiseptic and anti-inflammatory effect, making it particularly effective as a hygiene product for skin care. Aluminium-potassium alum absorbs excess moisture, and disinfecting properties relieve the cause of unpleasant odours, reducing the secretion of sweat and sebaceous glands.</font></p><p style="margin-right: 0px; margin-bottom: 0px; margin-left: 0px; font-stretch: normal; font-size: 12px; line-height: normal; font-family: &quot;Times New Roman&quot;; color: rgb(0, 0, 0); min-height: 15px;"><font style="font-variant-ligatures: common-ligatures;"></font><br></p><p style="caret-color: rgb(0, 0, 0); color: rgb(0, 0, 0); margin-right: 0px; margin-bottom: 0px; margin-left: 0px;"><font face="Times New Roman" color="#000000" style="font-stretch: normal; font-size: 12px; line-height: normal; font-family: &quot;Times New Roman&quot;; font-variant-ligatures: common-ligatures;">Unlike aluminium hydrochloride, alum-potassium alum does not have a harmful effect on the skin, does not tighten pores, their effect is safe for health. Aluminium hydrochloride can be the cause of many diseases, including provoking breast cancer.</font></p><p style="margin-right: 0px; margin-bottom: 0px; margin-left: 0px; font-stretch: normal; font-size: 12px; line-height: normal; font-family: &quot;Times New Roman&quot;; color: rgb(0, 0, 0); min-height: 15px;"><font style="font-variant-ligatures: common-ligatures;"></font><br></p><p style="caret-color: rgb(0, 0, 0); color: rgb(0, 0, 0); margin-right: 0px; margin-bottom: 0px; margin-left: 0px;"><font face="Times New Roman" color="#000000" style="font-stretch: normal; font-size: 12px; line-height: normal; font-family: &quot;Times New Roman&quot;; font-variant-ligatures: common-ligatures;">Features:</font></p><ul style="caret-color: rgb(0, 0, 0); color: rgb(0, 0, 0);"><li style="margin: 0px;"><font face="Times New Roman" color="#000000" style="font-stretch: normal; font-size: 12px; line-height: normal; font-family: &quot;Times New Roman&quot;;"></font><font face="Times New Roman" color="#000000" style="font-stretch: normal; font-size: 12px; line-height: normal; font-family: &quot;Times New Roman&quot;; font-variant-ligatures: common-ligatures;">100% natural</font></li><li style="margin: 0px;"><font face="Times New Roman" color="#000000" style="font-stretch: normal; font-size: 12px; line-height: normal; font-family: &quot;Times New Roman&quot;;"></font><font face="Times New Roman" color="#000000" style="font-stretch: normal; font-size: 12px; line-height: normal; font-family: &quot;Times New Roman&quot;; font-variant-ligatures: common-ligatures;">Natural hypoallergenic skin care</font></li><li style="margin: 0px;"><font face="Times New Roman" color="#000000" style="font-stretch: normal; font-size: 12px; line-height: normal; font-family: &quot;Times New Roman&quot;;"></font><font face="Times New Roman" color="#000000" style="font-stretch: normal; font-size: 12px; line-height: normal; font-family: &quot;Times New Roman&quot;; font-variant-ligatures: common-ligatures;">Does not contain fragrances, parabens and aluminium hydrochloride.</font></li><li style="margin: 0px;"><font face="Times New Roman" color="#000000" style="font-stretch: normal; font-size: 12px; line-height: normal; font-family: &quot;Times New Roman&quot;;"></font><font face="Times New Roman" color="#000000" style="font-stretch: normal; font-size: 12px; line-height: normal; font-family: &quot;Times New Roman&quot;; font-variant-ligatures: common-ligatures;">Safe for any skin type, gently cares for irritated skin even after hair removal.</font></li></ul>
            val details: String, // <ul style="caret-color: rgb(0, 0, 0); color: rgb(0, 0, 0);"><li style="margin: 0px;"><font face="Helvetica" color="#000000" style="font-stretch: normal; line-height: normal; font-family: Helvetica;"></font><font face="Helvetica" color="#000000" style="font-stretch: normal; line-height: normal; font-family: Helvetica; font-variant-ligatures: common-ligatures;">Size : 50 ml</font></li><li style="margin: 0px;"><font face="Helvetica" color="#000000" style="font-stretch: normal; line-height: normal; font-family: Helvetica;"></font><font face="Helvetica" color="#000000" style="font-stretch: normal; line-height: normal; font-family: Helvetica; font-variant-ligatures: common-ligatures;">Contents : Fragrance Free</font></li><li style="margin: 0px;"><font face="Helvetica" color="#000000" style="font-stretch: normal; line-height: normal; font-family: Helvetica;"></font><font face="Helvetica" color="#000000" style="font-stretch: normal; line-height: normal; font-family: Helvetica; font-variant-ligatures: common-ligatures;">Contents : Antiseptic</font></li><li style="margin: 0px;"><font face="Helvetica" color="#000000" style="font-stretch: normal; line-height: normal; font-family: Helvetica;"></font><font face="Helvetica" color="#000000" style="font-stretch: normal; line-height: normal; font-family: Helvetica; font-variant-ligatures: common-ligatures;">Target Skin Type : Dry</font></li><li style="margin: 0px;"><font face="Helvetica" color="#000000" style="font-stretch: normal; line-height: normal; font-family: Helvetica;"></font><font face="Helvetica" color="#000000" style="font-stretch: normal; line-height: normal; font-family: Helvetica; font-variant-ligatures: common-ligatures;">Weight:&nbsp;0.08KG</font></li><li style="margin: 0px;"><font face="Helvetica" color="#000000" style="font-stretch: normal; line-height: normal; font-family: Helvetica;"></font><font face="Helvetica" color="#000000" style="font-stretch: normal; line-height: normal; font-family: Helvetica; font-variant-ligatures: common-ligatures;">SKU : 4627089430939</font></li><li style="margin: 0px;"><font face="Helvetica" color="#000000" style="font-stretch: normal; line-height: normal; font-family: Helvetica;"></font><font face="Helvetica" color="#000000" style="font-stretch: normal; line-height: normal; font-family: Helvetica; font-variant-ligatures: common-ligatures;">Manufacturer : EO Laboratorie natural &amp; organic</font></li><li style="margin: 0px;"><font face="Helvetica" color="#000000" style="font-stretch: normal; line-height: normal; font-family: Helvetica;"></font><font face="Helvetica" color="#000000" style="font-stretch: normal; line-height: normal; font-family: Helvetica; font-variant-ligatures: common-ligatures;">Brand : Green Cosmetics</font></li><li style="margin: 0px;"><font face="Helvetica" color="#000000" style="font-stretch: normal; line-height: normal; font-family: Helvetica;"></font><font face="Helvetica" color="#000000" style="font-stretch: normal; line-height: normal; font-family: Helvetica; font-variant-ligatures: common-ligatures;">Country of Origin : Russia</font></li><li style="margin: 0px;"><font face="Helvetica" color="#000000" style="font-stretch: normal; line-height: normal; font-family: Helvetica;"></font><font face="Helvetica" color="#000000" style="font-stretch: normal; line-height: normal; font-family: Helvetica; font-variant-ligatures: common-ligatures;">Cautions : Store at room temperature</font></li></ul>
            @SerializedName("discounted_price")
            val discountedPrice: Any?, // null
            @SerializedName("end_date")
            val endDate: String, // 2022-05-20T08:44:57.318Z
            val express: Boolean, // false
            val featured: Any?, // null
            val `file`: List<File>,
            val height: String,
            @SerializedName("_id")
            val id: String, // 628c7ae7077b7f070a06001a
            val igst: String,
            val length: String,
            @SerializedName("manage_stock")
            val manageStock: Int, // 1
            @SerializedName("master_category")
            val masterCategory: List<String>,
            val name: String, // DEODORANTS DEO CRYSTAL OAK BARQUE AND GREEN TEA UNCENTED & ALUMINIUM FREE
            val options: List<Option>,
            @SerializedName("packaging_charge")
            val packagingCharge: String,
            val point: Double, // 0.5
            @SerializedName("point_exp_date")
            val pointExpDate: String, // 2022-05-20T08:44:57.318Z
            val price: String, // 49.35
            @SerializedName("product_types")
            val productTypes: List<String>,
            @SerializedName("return_day")
            val returnDay: String, // 7
            val returnable: String, // 1
            @SerializedName("selling_price")
            val sellingPrice: String?, // null
            val sgst: String,
            @SerializedName("shelving_location")
            val shelvingLocation: String, // Sharjah
            @SerializedName("short_desc")
            val shortDesc: String, // Potassium alum reduces sweat and oil glands secretion, being an effective antiperspirant thanks to its deodorizing properties.
            val sku: String, // 4627089430939
            val slug: String, // deodorants-deo-crystal-oak-barque-and-green-tea-uncented-and-aluminium-free
            @SerializedName("slug_history")
            val slugHistory: List<String>,
            @SerializedName("start_date")
            val startDate: String, // 2022-05-20T08:44:57.318Z
            @SerializedName("stock_qty")
            val stockQty: String, // 10
            @SerializedName("sub_category")
            val subCategory: List<String>,
            val tags: String, // Women Care, Men Care, Body Care, Skin Care, Silicon Free, Sulfate Free, Chemical Free
            @SerializedName("tax_status")
            val taxStatus: String,
            val threshold: String, // 2
            @SerializedName("update_date")
            val updateDate: String, // 2022-05-20T08:44:57.318Z
            val updatedAt: String, // 2022-05-24T06:27:52.050Z
            @SerializedName("__v")
            val v: Int, // 0
            val vendor: String, // 62750231fc9ddb176c788d22
            @SerializedName("video_url")
            val videoUrl: String,
            val weight: String, // 0.08KG
            val width: String
        ) {
            data class File(
                val acl: String, // public-read
                val bucket: String, // swagbag-files
                val contentDisposition: Any?, // null
                val contentEncoding: Any?, // null
                val contentType: String, // application/octet-stream
                val encoding: String, // 7bit
                val etag: String, // "7b06313316d64efed7bdd4b85a2dce67"
                val fieldname: String, // upload
                val key: String, // 16533736710809si0g.jpeg
                val location: String, // https://swagbag-files.s3.amazonaws.com/16533736710809si0g.jpeg
                val metadata: Any?, // null
                val mimetype: String, // image/jpeg
                val originalname: String, // 98 DEODORANTS DEO CRYSTAL OAK BARQUE AND GREEN TEA UNCENTED _ ALUMINIUM FREE-1.jpg
                val serverSideEncryption: Any?, // null
                val size: Int, // 13190
                val storageClass: String // STANDARD
            )

            data class Option(
                val name: String, // Colour
                val value: String // #d41367:500:SKU123:20, #1913d4:1000:SKU456:25, #4dd413:0:SKU4578:10
            )
        }

        data class Featured(
            val active: Int, // 1
            @SerializedName("added_by")
            val addedBy: String?, // 5fe463f5a9e14206002dd63e
            @SerializedName("additional_description")
            val additionalDescription: String, // <p style="caret-color: rgb(0, 0, 0); color: rgb(0, 0, 0); margin-right: 0px; margin-bottom: 0px; margin-left: 0px;"><font face="Times New Roman" color="#000000" style="font-stretch: normal; font-size: 12px; line-height: normal; font-family: &quot;Times New Roman&quot;; font-variant-ligatures: common-ligatures;">1. 2. Ingredients - Saffron Extract (Crocus sativus)<br>3. Direction to Use - wet skin with warm water, generously massage over face and body to create lather and rinse it off.<br>4. Cautions - Store in a cool and dry place. Protect from direct sunlight. Avoid contact with direct eyes. For External Use Only.<br>5. Special Claim - NO SLS, PARABEN AND MINERAL OIL<br>6. Beneficial - GOOD SKIN HEALTH</font></p>
            val attribute: String,
            val backorders: String,
            val batchno: String, // SBK0001
            val brand: String, // 61fb80663669ad2911a25fb9
            val category: List<String>,
            val cgst: String,
            @SerializedName("combo_products")
            val comboProducts: Any?, // null
            val commission: String,
            val createdAt: String, // 2022-05-27T08:28:51.076Z
            @SerializedName("created_date")
            val createdDate: String, // 2022-04-21T06:20:05.937Z
            val cuisine: Any?, // null
            val deal: Int?, // 0
            val deleted: Int, // 0
            val desc: String, // <p style="caret-color: rgb(0, 0, 0); color: rgb(0, 0, 0); margin-right: 0px; margin-bottom: 0px; margin-left: 0px;"><font face="Times New Roman" color="#000000" style="font-stretch: normal; font-size: 12px; line-height: normal; font-family: &quot;Times New Roman&quot;; font-variant-ligatures: common-ligatures;">As name suggest, saffron soap is handmade which based on saffron Extract. It smoothens the skin, improves it’s texture, tones it and makes it luminous.&nbsp;<span class="Apple-converted-space">&nbsp;</span>It helps heal wounds, makes scars fade and solves skin pigmentation issues. This soap is beneficial for both men and women skin that soften and promotes skin hydration.</font></p><p style="margin-right: 0px; margin-bottom: 0px; margin-left: 0px; font-stretch: normal; font-size: 12px; line-height: normal; font-family: &quot;Times New Roman&quot;; color: rgb(0, 0, 0); min-height: 15px;"><font style="font-variant-ligatures: common-ligatures;"></font><br></p>
            val details: String,
            @SerializedName("discounted_price")
            val discountedPrice: String?, // null
            @SerializedName("end_date")
            val endDate: String, // 2022-04-21T00:00:00.000Z
            val express: Boolean, // false
            val featured: Int, // 1
            val `file`: List<File>,
            val height: String,
            @SerializedName("_id")
            val id: String, // 626264b1ecbe3d2029599e83
            val igst: String,
            val length: String,
            @SerializedName("manage_stock")
            val manageStock: Int, // 1
            @SerializedName("master_category")
            val masterCategory: List<String>,
            val name: String, // SAFFRON SOAP
            val options: List<Option>,
            @SerializedName("packaging_charge")
            val packagingCharge: String,
            val point: Double, // 0.5
            @SerializedName("point_exp_date")
            val pointExpDate: String, // 2022-04-21T06:20:05.937Z
            val price: String, // 15.75
            @SerializedName("product_types")
            val productTypes: List<String>,
            @SerializedName("return_day")
            val returnDay: String, // 3
            val returnable: String, // 1
            @SerializedName("selling_price")
            val sellingPrice: String?, // null
            val sgst: String,
            @SerializedName("shelving_location")
            val shelvingLocation: String, // Sharjah
            @SerializedName("short_desc")
            val shortDesc: String,
            val sku: String, // KHOR_DU2106
            val slug: String, // saffron-soap-1
            @SerializedName("slug_history")
            val slugHistory: List<String>,
            @SerializedName("start_date")
            val startDate: String, // 2022-04-21T00:00:00.000Z
            @SerializedName("stock_qty")
            val stockQty: String, // 10
            @SerializedName("sub_category")
            val subCategory: List<String>,
            val tags: String,
            @SerializedName("tax_status")
            val taxStatus: String,
            val threshold: String, // 2
            @SerializedName("update_date")
            val updateDate: String, // 2022-04-21T06:20:05.937Z
            val updatedAt: String, // 2022-06-08T10:57:03.282Z
            @SerializedName("__v")
            val v: Int, // 0
            val vendor: String, // 61fb87943669ad2911a26008
            @SerializedName("video_url")
            val videoUrl: String,
            val weight: String, // 125 g
            val width: String
        ) {
            data class File(
                val acl: String, // public-read
                val bucket: String, // swagbag-files
                val contentDisposition: Any?, // null
                val contentEncoding: Any?, // null
                val contentType: String, // application/octet-stream
                val encoding: String, // 7bit
                val etag: String, // "fdb9d20d6612bf8c8c4f4e3a5fc57371"
                val fieldname: String, // upload
                val key: String, // 1650615472871d7y9c.jpeg
                val location: String, // https://swagbag-files.s3.amazonaws.com/1650615472871d7y9c.jpeg
                val metadata: Any?, // null
                val mimetype: String, // image/jpeg
                val originalname: String, // 1. Saffron Soap 1.jpg
                val serverSideEncryption: Any?, // null
                val size: Int, // 22992
                val storageClass: String // STANDARD
            )

            data class Option(
                val name: String, // Colour
                val value: String // #d41367:500:SKU123:20, #1913d4:1000:SKU456:25, #4dd413:0:SKU4578:10
            )
        }

        data class Section(
            val active: Int, // 1
            val brand: String, // 616889f3e0f5f5b576434121
            val category: String, // 618e6d4bae7db56d2c44981e
            @SerializedName("created_date")
            val createdDate: String, // 2022-02-16T14:51:03.693Z
            val deleted: Int, // 0
            val `file`: String, // https://swagbag-files.s3.amazonaws.com/1655451879710gvz6o.png
            @SerializedName("_id")
            val id: String, // 6210b7ff5f5e06bee40138b9
            @SerializedName("master_category")
            val masterCategory: MasterCategory,
            val product: Product?,
            val section: Int, // 4
            @SerializedName("update_date")
            val updateDate: String, // 2022-06-17T07:44:39.000Z
            val url: String,
            @SerializedName("__v")
            val v: Int // 0
        ) {
            data class MasterCategory(
                val active: Int, // 1
                @SerializedName("created_date")
                val createdDate: String, // 2021-12-15T14:49:50.596Z
                val deleted: Int, // 0
                val desc: String, // women
                val `file`: String, // https://swagbag-files.s3.amazonaws.com/16499400355203dlpx.jpeg
                @SerializedName("_id")
                val id: String, // 61bd25a466538a1c1f366ac3
                val name: String, // Women
                @SerializedName("short_desc")
                val shortDesc: String, // women
                val slug: String, // women
                @SerializedName("slug_history")
                val slugHistory: List<String>,
                val sort: Int, // 2
                @SerializedName("update_date")
                val updateDate: String, // 2022-04-14T12:40:36.000Z
                @SerializedName("__v")
                val v: Int // 0
            )

            data class Product(
                val active: Int, // 1
                @SerializedName("added_by")
                val addedBy: String, // 5fe463f5a9e14206002dd63e
                @SerializedName("additional_description")
                val additionalDescription: String, // <p style="caret-color: rgb(0, 0, 0); color: rgb(0, 0, 0); margin-right: 0px; margin-bottom: 0px; margin-left: 0px;"><font face="Times New Roman" color="#000000" style="font-stretch: normal; font-size: 12px; line-height: normal; font-family: &quot;Times New Roman&quot;; font-variant-ligatures: common-ligatures;">BENEFITS: Burdock Root extract contains vitamins B complex and E. Burdock Root increases circulation to the skin, is mildly soothing and antioxidant. Mango butter is rich in antioxidants and emollients as well as vitamins A &amp; E. It softens and moisturizes dry skin. Milk proteins, amino acids and lactic acid calm and soothe dry, upset skin. . Rice bran powder is full of vitamins A, B2, B12 &amp; E, as well as naturally moisturizing proteins. It exfoliates and absorbs excess oils and dirt while smoothing skin. U.S. FARM HARVEST: Whole Milk from locally raised cows.</font></p><p style="margin-right: 0px; margin-bottom: 0px; margin-left: 0px; font-stretch: normal; font-size: 12px; line-height: normal; font-family: &quot;Times New Roman&quot;; color: rgb(0, 0, 0); min-height: 15px;"><font style="font-variant-ligatures: common-ligatures;"></font><br></p><p style="caret-color: rgb(0, 0, 0); color: rgb(0, 0, 0); margin-right: 0px; margin-bottom: 0px; margin-left: 0px;"><font face="Times New Roman" color="#000000" style="font-stretch: normal; font-size: 12px; line-height: normal; font-family: &quot;Times New Roman&quot;; font-variant-ligatures: common-ligatures;">INGREDIENTS: Burdock Root, Mango Butter, Rice Powder, Whole Milk. NO PARABENS NO SULFATES NO PHTHALATES. Kaolin, Arctium Lappa Root (Burdock Root) Extract, Sodium Bicarbonate, Oryza Sativa (Rice) Powder, Citric Acid, Milk/Lait, Mangifera Indica (Mango Seed Butter), Vanilla Planifolia (Vanilla) Extract, Parfum*, Sucrose (Sugar). *Natural fragrance derived from 100% natural sources.</font></p><p style="margin-right: 0px; margin-bottom: 0px; margin-left: 0px; font-stretch: normal; font-size: 12px; line-height: normal; font-family: &quot;Times New Roman&quot;; color: rgb(0, 0, 0); min-height: 15px;"><font style="font-variant-ligatures: common-ligatures;"></font><br></p><p style="caret-color: rgb(0, 0, 0); color: rgb(0, 0, 0); margin-right: 0px; margin-bottom: 0px; margin-left: 0px;"><font face="Times New Roman" color="#000000" style="font-stretch: normal; font-size: 12px; line-height: normal; font-family: &quot;Times New Roman&quot;; font-variant-ligatures: common-ligatures;">HOW TO USE: Pour a quarter-sized amount of Facial Buffing Mask into your palm then add about the same amount of drops of warm water to form a paste. Exfoliate your face in circular motions using your fingertips. Leave paste on your face as a treatment mask for 2-3 minutes to allow the muds, vitamins, milks and extracts to be fully absorbed. Then rinse. Recommended for normal to dry skin.</font></p>
                val attribute: String,
                val backorders: String,
                val batchno: String,
                val brand: String, // 62120dea0fe91d273d217b35
                val category: List<String>,
                val cgst: String,
                @SerializedName("combo_products")
                val comboProducts: Any?, // null
                val commission: String,
                val createdAt: String, // 2022-06-17T04:22:15.716Z
                @SerializedName("created_date")
                val createdDate: String, // 2022-06-16T16:03:02.779Z
                val cuisine: Any?, // null
                val deal: Any?, // null
                val deleted: Int, // 0
                val desc: String, // <p style="caret-color: rgb(0, 0, 0); color: rgb(0, 0, 0); margin-right: 0px; margin-bottom: 0px; margin-left: 0px;"><font face="Times New Roman" color="#000000" style="font-stretch: normal; font-size: 12px; line-height: normal; font-family: &quot;Times New Roman&quot;; font-variant-ligatures: common-ligatures;">To buff, balance, and nourish your skin, simply pour the buffing powder into your palm. Yes! A little bit goes a long way. Add a few drops of water to create an oatmeal-like paste, and exfoliate using circular motions. Whether used as a face mask, an exfoliator or both, your skin will feel like silk! Watch our biscuit video for more details. The powder formula is the same as our original biscuits, just in an easy-to-use form. Sold in a zip-seal package with 4-6 facial treatments, depending on whether you also treat your neck area.</font></p>
                val details: String, // <p style="caret-color: rgb(0, 0, 0); color: rgb(0, 0, 0); margin-right: 0px; margin-bottom: 0px; margin-left: 0px;"><font face="Times New Roman" color="#000000" style="font-stretch: normal; font-size: 12px; line-height: normal; font-family: &quot;Times New Roman&quot;; font-variant-ligatures: common-ligatures;">Size : 14.2 g</font></p><p style="caret-color: rgb(0, 0, 0); color: rgb(0, 0, 0); margin-right: 0px; margin-bottom: 0px; margin-left: 0px;"><font face="Times New Roman" color="#000000" style="font-stretch: normal; font-size: 12px; line-height: normal; font-family: &quot;Times New Roman&quot;; font-variant-ligatures: common-ligatures;">Skin Type : Suitable for all skin types.</font></p><p style="caret-color: rgb(0, 0, 0); color: rgb(0, 0, 0); margin-right: 0px; margin-bottom: 0px; margin-left: 0px;"><font face="Times New Roman" color="#000000" style="font-stretch: normal; font-size: 12px; line-height: normal; font-family: &quot;Times New Roman&quot;; font-variant-ligatures: common-ligatures;">Skincare That Saves : Your purchase help us rescue and care for forgotten, neglected and abused farm animals that come to live at our FHF Sanctuary. Follow their transformations&nbsp;here.</font></p><p style="caret-color: rgb(0, 0, 0); color: rgb(0, 0, 0); margin-right: 0px; margin-bottom: 0px; margin-left: 0px;"><font face="Times New Roman" color="#000000" style="font-stretch: normal; font-size: 12px; line-height: normal; font-family: &quot;Times New Roman&quot;; font-variant-ligatures: common-ligatures;">Brand : Farmhouse Fresh</font></p>
                @SerializedName("discounted_price")
                val discountedPrice: String?, // null
                @SerializedName("end_date")
                val endDate: String, // 2022-06-16T16:03:02.779Z
                val express: Boolean, // false
                val featured: Any?, // null
                val `file`: List<File>,
                val height: String,
                @SerializedName("_id")
                val id: String, // 62ac01774342c850bf9b2fa9
                val igst: String,
                val length: String,
                @SerializedName("manage_stock")
                val manageStock: Int, // 1
                @SerializedName("master_category")
                val masterCategory: List<String>,
                val name: String, // Burdock & Butter Facial Buffing Powder Mix
                val options: List<Any>,
                @SerializedName("packaging_charge")
                val packagingCharge: String,
                val point: String, // 0
                @SerializedName("point_exp_date")
                val pointExpDate: String, // 2022-06-16T16:03:02.779Z
                val price: String, // 70
                @SerializedName("product_types")
                val productTypes: List<String>,
                @SerializedName("return_day")
                val returnDay: String,
                val returnable: String,
                @SerializedName("selling_price")
                val sellingPrice: String?, // null
                val sgst: String,
                @SerializedName("shelving_location")
                val shelvingLocation: String, // Sharjah
                @SerializedName("short_desc")
                val shortDesc: String, // Get calm, hydrated skin – even on the go
                val sku: String, // 876087685903
                val slug: String, // burdock-and-butter-facial-buffing-powder-mix
                @SerializedName("slug_history")
                val slugHistory: List<String>,
                @SerializedName("start_date")
                val startDate: String, // 2022-06-16T16:03:02.779Z
                @SerializedName("stock_qty")
                val stockQty: String, // 10
                @SerializedName("sub_category")
                val subCategory: List<String>,
                val tags: String, // 100% Natural, Vegan. Gluten Free, Nut Free, Paraben Free, Sulfate Free, Phthalate Free Fragrance
                @SerializedName("tax_status")
                val taxStatus: String,
                val threshold: String, // 2
                @SerializedName("update_date")
                val updateDate: String, // 2022-06-16T16:03:02.779Z
                val updatedAt: String, // 2022-06-17T04:22:15.716Z
                @SerializedName("__v")
                val v: Int, // 0
                val vendor: String, // 62a8404859f9f60990eb5320
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
                    val etag: String, // "4918cb78ad73e8e997516e0fa3ce1e8a"
                    val fieldname: String, // upload
                    val key: String, // 165543973515789x73.webp
                    val location: String, // https://swagbag-files.s3.amazonaws.com/165543973515789x73.webp
                    val metadata: Any?, // null
                    val mimetype: String, // image/webp
                    val originalname: String, // 76. Burdock & Butter Facial Buffing Powder Mix 1.webp
                    val serverSideEncryption: Any?, // null
                    val size: Int, // 12088
                    val storageClass: String // STANDARD
                )
            }
        }
    }
}