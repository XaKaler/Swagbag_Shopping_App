package com.shopping.swagbag.dummy

data class DummyCategory(
    val category: List<Category>,
    val status: String // success
) {
    data class Category(
        val __v: Int, // 0
        val _id: String, // 61c08c97d428ae651dd71e51
        val active: Int, // 1
        val all_brands: List<AllBrand>,
        val brands: List<String>,
        val created_date: String, // 2021-12-20T08:53:56.536Z
        val deleted: Int, // 0
        val description: String,
        val description_after_content: String,
        val `file`: String,
        val master: String, // 615ae16f63d6a6435a183dc6
        val name: String, // Pet Menu
        val parent: Any?, // null
        val slug: String, // pet-menu
        val slug_history: List<String>,
        val sub_category: List<Any>,
        val update_date: String // 2021-12-20T08:53:56.536Z
    ) {
        data class AllBrand(
            val __v: Int, // 0
            val _id: String, // 6148c87ee40e5da07a1d9eed
            val active: Int, // 1
            val created_date: String, // 2021-09-20T17:27:28.238Z
            val deleted: Int, // 0
            val desc: String,
            val `file`: Any?, // null
            val name: String, // Brand
            val short_desc: String,
            val slug: String, // brand
            val slug_history: List<String>,
            val update_date: String // 2021-09-20T17:27:28.238Z
        )
    }
}