package com.shopping.swagbag.products

import com.shopping.swagbag.common.base.BaseRepository

class ProductRepository(private val api: ProductApi) : BaseRepository() {

    suspend fun productDetails(slug: String) = safeApiCall { api.productDetails(slug) }

    suspend fun allBrands() = safeApiCall { api.allBrands() }

    suspend fun productSearch(
        deal: String,
        brand: String,
        sub_category: String,
        category: String,
        price: String,
        sortby: String,
        master: String,
    ) = safeApiCall { api.productSearch(deal, brand, sub_category, category, price, sortby, master) }

    suspend fun addToWishlist(
        productId: String,
        userId: String
    ) = safeApiCall { api.addToWishlist(productId, userId) }

    suspend fun getWish(userId: String) = safeApiCall { api.getWish(userId) }


    suspend fun deleteSingleWish(productId: String, userId: String) = safeApiCall { api.deleteSingleWish(productId, userId) }

    suspend fun getCart(userId: String) = safeApiCall { api.getCart(userId) }

    suspend fun deleteSingleCart(
        productId: String,
        userId: String
    ) = safeApiCall { api.deleteSingleCart(productId, userId) }
}