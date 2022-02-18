package com.shopping.swagbag.user.shoppingbeg.withproduct


import com.google.gson.annotations.SerializedName

data class DeleteSingleCartModel(
    val message: String, // Cart deleted successfully
    val result: Result,
    val status: String // success
) {
    data class Result(
        @SerializedName("created_date")
        val createdDate: String, // 2022-02-12T12:58:18.915Z
        @SerializedName("_id")
        val id: String, // 620e1a3ef4e55d0cd2f3ba3b
        val image: String, // https://swagbag-space.fra1.digitaloceanspaces.com/1639933803507muv20.webp
        val name: String, // (s) iffalcon 108 cm (43 inches) 4k ultra hd certified android smart led tv 43u61 (black) (2021 model)
        val option: List<Option>,
        val price: Int, // 23990
        val product: String, // 6148c9ebaaa0e6b191f8bf8a
        val quantity: Int, // 1
        @SerializedName("update_date")
        val updateDate: String, // 2022-02-12T12:58:18.915Z
        val user: String, // 61e82a7acf130b2978d05815
        @SerializedName("__v")
        val v: Int // 0
    ) {
        data class Option(
            val key: String, // Color
            val price: String, // 0
            val value: String //  #4dd413
        )
    }
}