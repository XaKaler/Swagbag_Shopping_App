package com.shopping.swagbag.category

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
            val id: String, // 61bb805e66538a1c1f366787
            val name: String, // Baby Bath Essentials
            val subcategory: List<Subcategory>
        ) {
            data class Subcategory(
                val __v: Int, // 0
                val _id: String, // 61bb810866538a1c1f3667c6
                val active: Int, // 1
                val brands: List<String>,
                val created_date: String, // 2021-12-15T14:49:50.542Z
                val deleted: Int, // 0
                val description: String, // baby wash
                val description_after_content: String?,
                val `file`: String,
                val master: String, // 615ae14c63d6a6435a183db7
                val name: String, // Baby Wash
                val parent: String, // 61bb805e66538a1c1f366787
                val slug: String, // baby-wash
                val slug_history: List<String>,
                val update_date: String // 2021-12-18T07:09:32.000Z
            )
        }

        data class Master(
            val id: String, // 615ae14c63d6a6435a183db7
            val name: String // Kids
        )
    }
}