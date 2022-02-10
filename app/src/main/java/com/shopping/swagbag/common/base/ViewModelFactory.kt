package com.shopping.swagbag.common.base

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.shopping.swagbag.auth.signin.UserRepository
import com.shopping.swagbag.auth.signin.UserViewModel
import com.shopping.swagbag.category.CategoryRepository
import com.shopping.swagbag.category.CategoryViewModel

@Suppress("UNCHECKED_CAST")
class ViewModelFactory(private val repository: BaseRepository) :
    ViewModelProvider.NewInstanceFactory() {

    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        return when{
            modelClass.isAssignableFrom(CategoryViewModel::class.java) -> CategoryViewModel(repository as CategoryRepository) as T
            modelClass.isAssignableFrom(UserViewModel::class.java) -> UserViewModel(repository as UserRepository) as T
            else -> throw IllegalArgumentException("ViewModel class not found")
        }
    }
}