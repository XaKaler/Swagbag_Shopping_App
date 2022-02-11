package com.shopping.swagbag.category

import com.shopping.swagbag.common.base.BaseRepository

class CategoryRepository(private val apiService: CategoryApi): BaseRepository() {
/*
    private val categoryLiveData = MutableLiveData<Resource<CategoryModel>>()

    val category: LiveData<Resource<CategoryModel>>
    get() = categoryLiveData*/

    suspend fun getAllCategories() = safeApiCall { apiService.getAllCategories() }

    suspend fun masterCategory() = safeApiCall { apiService.masterCategory() }
}