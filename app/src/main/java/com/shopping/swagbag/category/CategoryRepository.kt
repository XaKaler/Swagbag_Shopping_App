package com.shopping.swagbag.category

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.shopping.swagbag.common.base.BaseRepository
import com.shopping.swagbag.service.ApiService

class CategoryRepository(private val apiService: ApiService): BaseRepository() {

    private val categoryLiveData = MutableLiveData<CategoryModel>()

    val category: LiveData<CategoryModel>
    get() = categoryLiveData

    suspend fun getAllCategories(){
        val result = apiService.getAllCategories()
        if(result.body() != null){
            categoryLiveData.postValue(result.body())
        }
    }
}