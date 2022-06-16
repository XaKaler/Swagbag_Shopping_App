package com.shopping.swagbag.products.filter


import com.google.gson.annotations.SerializedName

data class FilterModel(
    val brands: List<Brand>,
    val category: List<Category>,
    val menu: List<Menu>,
    val message: String,
    val result: List<Any>,
    val status: String // success
) {
    data class Brand(
        val active: Int, // 1
        @SerializedName("created_date")
        val createdDate: String, // 2022-01-31T14:11:28.360Z
        val deleted: Int, // 0
        val desc: String, // Lorem Ipsum is simply dummy text of the printing and typesetting industry. Lorem Ipsum has been the industry's standard dummy text ever since the 1500s, when an unknown printer took a galley of type and scrambled it to make a type specimen book. It has survived not only five centuries, but also the leap into electronic typesetting, remaining essentially unchanged. It was popularised in the 1960s with the release of Letraset sheets containing Lorem Ipsum passages, and more recently with desktop publishing software like Aldus PageMaker including versions of Lorem Ipsum.
        val desc2: String,
        val desc3: String,
        val `file`: String?, // https://swagbag-files.s3.amazonaws.com/165277238669094ol7.png
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
        val description: String, // All products used for Men's Grooming
        @SerializedName("description_after_content")
        val descriptionAfterContent: String,
        val `file`: String,
        @SerializedName("_id")
        val id: String, // 625d0e11ad10bc2c6a9bbb6e
        val master: String, // 615ae18463d6a6435a183dcc
        val name: String, // Bath and Body
        val parent: Any?, // null
        val slug: String, // bath-and-body
        @SerializedName("slug_history")
        val slugHistory: List<String>,
        @SerializedName("sub_category")
        val subCategory: List<SubCategory>,
        @SerializedName("update_date")
        val updateDate: String, // 2022-04-15T11:31:58.835Z
        @SerializedName("__v")
        val v: Int // 0
    ) {
        data class SubCategory(
            val active: Int, // 1
            val brands: List<String>,
            @SerializedName("created_date")
            val createdDate: String, // 2022-04-15T11:31:58.835Z
            val deleted: Int, // 0
            val description: String,
            @SerializedName("description_after_content")
            val descriptionAfterContent: String?,
            val `file`: String,
            @SerializedName("_id")
            val id: String, // 625d0e78ad10bc2c6a9bbb86
            val master: String, // 615ae18463d6a6435a183dcc
            val name: String, // Dental Care
            val parent: String, // 625d0e11ad10bc2c6a9bbb6e
            val slug: String, // dental-care
            @SerializedName("slug_history")
            val slugHistory: List<String>,
            @SerializedName("update_date")
            val updateDate: String, // 2022-04-15T11:31:58.835Z
            @SerializedName("__v")
            val v: Int // 0
        )
    }

    data class Menu(
        val active: Int, // 1
        @SerializedName("all_brands")
        val allBrands: List<AllBrand>,
        val brands: List<String>,
        @SerializedName("created_date")
        val createdDate: String, // 2021-11-11T16:24:24.827Z
        val deleted: Int, // 0
        val description: String, // Kurta
        @SerializedName("description_after_content")
        val descriptionAfterContent: String?, // Kurta
        val `file`: String,
        @SerializedName("_id")
        val id: String, // 618e6deeae7db56d2c44986c
        val master: String, // 615ae18463d6a6435a183dcc
        val name: String, // Kurta
        val parent: String?, // 618e6d31ae7db56d2c449812
        val slug: String, // kurta-1
        @SerializedName("slug_history")
        val slugHistory: List<String>,
        @SerializedName("sub_category")
        val subCategory: List<SubCategory>,
        @SerializedName("update_date")
        val updateDate: String, // 2021-11-11T16:24:24.827Z
        @SerializedName("__v")
        val v: Int // 0
    ) {
        data class AllBrand(
            val active: Int, // 1
            @SerializedName("created_date")
            val createdDate: String, // 2022-02-19T09:53:51.911Z
            val deleted: Int, // 0
            val desc: String, // Peerless grooming products by Daimon Barber. Innovative formulation, unrivalled quality and beautiful design for Men
            val `file`: Any?, // null
            @SerializedName("_id")
            val id: String, // 62120d390fe91d273d217b2b
            val name: String, // Daimon Barber
            @SerializedName("seo_desc")
            val seoDesc: String,
            @SerializedName("seo_title")
            val seoTitle: String,
            @SerializedName("short_desc")
            val shortDesc: String?,
            val slug: String, // daimon-barber
            @SerializedName("slug_history")
            val slugHistory: List<String>,
            @SerializedName("update_date")
            val updateDate: String, // 2022-02-19T09:53:51.911Z
            @SerializedName("__v")
            val v: Int // 0
        )

        data class SubCategory(
            val active: Int, // 1
            val brands: List<String>,
            @SerializedName("created_date")
            val createdDate: String, // 2022-04-15T11:31:58.835Z
            val deleted: Int, // 0
            val description: String,
            @SerializedName("description_after_content")
            val descriptionAfterContent: String?,
            val `file`: String,
            @SerializedName("_id")
            val id: String, // 625d0e78ad10bc2c6a9bbb86
            val master: String, // 615ae18463d6a6435a183dcc
            val name: String, // Dental Care
            val parent: String, // 625d0e11ad10bc2c6a9bbb6e
            val slug: String, // dental-care
            @SerializedName("slug_history")
            val slugHistory: List<String>,
            @SerializedName("update_date")
            val updateDate: String, // 2022-04-15T11:31:58.835Z
            @SerializedName("__v")
            val v: Int // 0
        )
    }
}