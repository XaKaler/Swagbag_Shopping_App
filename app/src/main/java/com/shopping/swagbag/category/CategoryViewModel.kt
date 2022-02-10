package com.shopping.swagbag.category

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.shopping.swagbag.service.Resource
import kotlinx.coroutines.launch

class CategoryViewModel(private val categoryRepository: CategoryRepository) : ViewModel() {

    private val categoryLiveData = MutableLiveData<Resource<CategoryModel>>()

    val category: LiveData<Resource<CategoryModel>>
        get() = categoryLiveData

    init {
        viewModelScope.launch {
            categoryLiveData.value = Resource.Loading
            categoryLiveData.value = categoryRepository.getAllCategories()
        }

    }
}