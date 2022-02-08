package com.shopping.swagbag.category


import com.google.gson.annotations.SerializedName

data class CategoryModel(
    val message: String,
    val result: List<Result>,
    val status: String // success
) {
    data class Result(
        val category: List<Category>,
        val master: Master
    ) {
        data class Category(
            val `file`: String, // https://swagbag-space.fra1.digitaloceanspaces.com/16399423877561lqex.png
            val id: String, // 61bb805e66538a1c1f366787
            val name: String, // Baby Bath Essentials
            val subcategory: List<Subcategory>
        ) {
            data class Subcategory(
                val active: Int, // 1
                val brands: List<String>,
                @SerializedName("created_date")
                val createdDate: String, // 2021-12-15T14:49:50.542Z
                val deleted: Int, // 0
                val description: String, // baby wash
                @SerializedName("description_after_content")
                val descriptionAfterContent: String?,
                val `file`: String,
                @SerializedName("_id")
                val id: String, // 61bb810866538a1c1f3667c6
                val master: String, // 615ae14c63d6a6435a183db7
                val name: String, // Baby Wash
                val parent: String, // 61bb805e66538a1c1f366787
                val slug: String, // baby-wash
                @SerializedName("slug_history")
                val slugHistory: List<String>,
                @SerializedName("update_date")
                val updateDate: String, // 2021-12-18T07:09:32.000Z
                @SerializedName("__v")
                val v: Int // 0
            )
        }

        data class Master(
            val `file`: String, // https://swagbag-space.fra1.digitaloceanspaces.com/16399423877561lqex.png
            val id: String, // 615ae14c63d6a6435a183db7
            val name: String // Kids
        )
    }
}