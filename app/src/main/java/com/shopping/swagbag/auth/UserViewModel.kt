package com.shopping.swagbag.auth

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.shopping.swagbag.auth.resetpassword.ResetPasswordModel
import com.shopping.swagbag.auth.signin.SignInModel
import com.shopping.swagbag.auth.signup.SignUpModel
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

    fun register(
        fName: String,
        lName: String,
        email: String,
        password: String,
        reffer_by: String,
    ): LiveData<Resource<SignUpModel>> {
        val signUpResponse = MutableLiveData<Resource<SignUpModel>>()
        viewModelScope.launch {
            signUpResponse.value = Resource.Loading
            signUpResponse.value = repository.register(fName, lName, email, password, reffer_by)
        }
        return signUpResponse
    }

    fun passwordResetEmailSend(email: String
    ):LiveData<Resource<ResetPasswordModel>>{

        val resetPasswordResponse = MutableLiveData<Resource<ResetPasswordModel>>()

        viewModelScope.launch {
            resetPasswordResponse.value  = Resource.Loading
            resetPasswordResponse.value = repository.passwordResetEmailSend(email)
        }

        return resetPasswordResponse
    }
}