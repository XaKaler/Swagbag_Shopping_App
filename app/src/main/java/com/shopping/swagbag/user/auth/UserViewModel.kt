package com.shopping.swagbag.user.auth

import androidx.activity.result.ActivityResultRegistryOwner
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.shopping.swagbag.service.Resource
import com.shopping.swagbag.user.auth.resetpassword.PasswordResetEmailSendModel
import com.shopping.swagbag.user.auth.resetpassword.PasswordResetModel
import com.shopping.swagbag.user.auth.signin.SignInModel
import com.shopping.swagbag.user.auth.signup.SignUpModel
import com.shopping.swagbag.user.order.user_details.AddAddressModel
import com.shopping.swagbag.user.order.user_details.AllAddressModel
import com.shopping.swagbag.user.order.user_details.DeleteAddressModel
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

    fun passwordResetEmailSend(
        email: String
    ): LiveData<Resource<PasswordResetEmailSendModel>> {

        val resetPasswordResponse = MutableLiveData<Resource<PasswordResetEmailSendModel>>()

        viewModelScope.launch {
            resetPasswordResponse.value = Resource.Loading
            resetPasswordResponse.value = repository.passwordResetEmailSend(email)
        }

        return resetPasswordResponse
    }

    fun passwordReset(
        email: String,
        otp: String
    ): LiveData<Resource<PasswordResetModel>> {

        val result = MutableLiveData<Resource<PasswordResetModel>>()

        viewModelScope.launch {
            result.value = Resource.Loading
            result.value = repository.passwordReset(email, otp)
        }
        return result
    }

    fun addAddress(
        userid: String,
        title: String,
        address: String,
        address2: String,
        city: String,
        state: String,
        post_office: String,
        pincode: String,
        contact_name: String,
        contact_mobile: String,
        lat: String,
        lng: String,
    ): LiveData<Resource<AddAddressModel>> {
        val result = MutableLiveData<Resource<AddAddressModel>>()

        viewModelScope.launch {
            result.value = Resource.Loading
            result.value = repository.addAddress(
                userid,
                title,
                address,
                address2,
                city,
                state,
                post_office,
                pincode,
                contact_name,
                contact_mobile,
                lat,
                lng
            )
        }

        return result
    }

    fun allAddress(userid: String): LiveData<Resource<AllAddressModel>>{
        val result = MutableLiveData<Resource<AllAddressModel>>()

        viewModelScope.launch {
            result.value = Resource.Loading
            result.value = repository.allAddress(userid)
        }

        return result
    }

    fun deleteAddress(addressId: String): LiveData<Resource<DeleteAddressModel>>{
        val result = MutableLiveData<Resource<DeleteAddressModel>>()

        viewModelScope.launch {
            result.value = Resource.Loading
            result.value = repository.deleteAddress(addressId)
        }

        return result
    }
}