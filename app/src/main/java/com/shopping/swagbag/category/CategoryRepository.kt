package com.shopping.swagbag.category

import com.shopping.swagbag.common.base.BaseRepository

class CategoryRepository(private val apiService: CategoryApi): BaseRepository() {

    suspend fun getAllCategories() = safeApiCall { apiService.getAllCategories() }

    suspend fun masterCategory() = safeApiCall { apiService.masterCategory() }

    suspend fun particularCategory(categoryId: String) = safeApiCall { apiService.particularCategory(categoryId) }

}