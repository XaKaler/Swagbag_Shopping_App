package com.shopping.swagbag

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.shopping.swagbag.category.CategoryRepository
import com.shopping.swagbag.category.CategoryViewModel
import com.shopping.swagbag.utils.SettingViewModel

class SettingViewModelFactory(private val repository: SettingRepository) :
    ViewModelProvider.Factory {
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        return SettingViewModel(repository) as T
    }
}