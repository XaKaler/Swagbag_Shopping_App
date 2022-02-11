package com.shopping.swagbag.products

import com.shopping.swagbag.common.base.BaseRepository

class ProductRepository(private val api: ProductApi) : BaseRepository() {

    suspend fun productDetails(slug: String) = safeApiCall { api.productDetails(slug) }
}