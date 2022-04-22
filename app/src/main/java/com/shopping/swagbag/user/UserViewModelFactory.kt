package com.shopping.swagbag.user

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.shopping.swagbag.user.auth.UserRepository
import com.shopping.swagbag.user.auth.UserViewModel

class UserViewModelFactory(private val repository: UserRepository) :
    ViewModelProvider.Factory {
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        return UserViewModel(repository) as T
    }
}