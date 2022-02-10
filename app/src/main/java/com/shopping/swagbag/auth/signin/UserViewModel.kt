package com.shopping.swagbag.auth.signin

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.shopping.swagbag.service.Resource
import kotlinx.coroutines.launch

class UserViewModel(private val repository: UserRepository) : ViewModel() {

    fun login(
        email: String,
        password: String
    ): LiveData<Resource<SignInModel>> {
        val signInResponse = MutableLiveData<Resource<SignInModel>>()
        viewModelScope.launch {
            signInResponse.value = Resource.Loading
            signInResponse.value = repository.getUserLogin(email, password)
        }
        return signInResponse
    }
}