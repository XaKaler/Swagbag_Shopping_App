package com.shopping.swagbag.category

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.shopping.swagbag.service.Resource
import kotlinx.coroutines.launch

class CategoryViewModel(private val categoryRepository: CategoryRepository) : ViewModel() {

    fun getAllCategories(): LiveData<Resource<CategoryModel>>{
        val result = MutableLiveData<Resource<CategoryModel>>()

        viewModelScope.launch {
            result.value = Resource.Loading
            result.value = categoryRepository.getAllCategories()
        }

        return result
    }

    fun masterCategory(): LiveData<Resource<MasterCategoryModel>>{
        val result = MutableLiveData<Resource<MasterCategoryModel>>()

        viewModelScope.launch {
            result.value = Resource.Loading
            result.value = categoryRepository.masterCategory()
        }

        return result
    }

    fun particularCategory(categoryId: String): LiveData<Resource<ParticularCategoryModel>>{
        val result = MutableLiveData<Resource<ParticularCategoryModel>>()

        viewModelScope.launch {
            result.value = Resource.Loading
            result.value = categoryRepository.particularCategory(categoryId)
        }

        return result
    }
}