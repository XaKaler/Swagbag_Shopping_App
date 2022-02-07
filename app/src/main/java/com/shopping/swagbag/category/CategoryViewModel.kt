package com.shopping.swagbag.category

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class CategoryViewModel(private val categoryRepository: CategoryRepository) : ViewModel() {

    init {
        viewModelScope.launch(Dispatchers.IO) {
            categoryRepository.getAllCategories()
        }
    }

    val category: LiveData<CategoryModel>
    get() = categoryRepository.category

}