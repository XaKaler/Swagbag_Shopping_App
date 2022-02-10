package com.shopping.swagbag.category

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.shopping.swagbag.common.base.BaseRepository
import com.shopping.swagbag.service.ApiService
import com.shopping.swagbag.service.Resource

class CategoryRepository(private val apiService: ApiService): BaseRepository() {
/*
    private val categoryLiveData = MutableLiveData<Resource<CategoryModel>>()

    val category: LiveData<Resource<CategoryModel>>
    get() = categoryLiveData*/

    suspend fun getAllCategories() = safeApiCall { apiService.getAllCategories() }
}