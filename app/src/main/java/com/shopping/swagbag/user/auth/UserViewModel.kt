package com.shopping.swagbag.user.auth

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.shopping.swagbag.service.Resource
import com.shopping.swagbag.settings.SettingsModel
import com.shopping.swagbag.user.auth.resetpassword.PasswordResetEmailSendModel
import com.shopping.swagbag.user.auth.resetpassword.PasswordResetModel
import com.shopping.swagbag.user.auth.signin.SignInModel
import com.shopping.swagbag.user.auth.signup.SignUpModel
import com.shopping.swagbag.user.address.add_address.AddAddressModel
import com.shopping.swagbag.user.address.address_list.AllAddressModel
import com.shopping.swagbag.user.address.address_list.DeleteAddressModel
import com.shopping.swagbag.user.address.edit_address.EditAddressModel
import com.shopping.swagbag.user.profile.UserUpdateModel
import com.shopping.swagbag.user.wallet.WalletModel
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
        otp: String,
        password: String
    ): LiveData<Resource<PasswordResetModel>> {

        val result = MutableLiveData<Resource<PasswordResetModel>>()

        viewModelScope.launch {
            result.value = Resource.Loading
            result.value = repository.passwordReset(email, otp, password)
        }
        return result
    }

    /*fun userUpdate(
        userId: String,
        fName: String,
        lName: String,
        email: String
    ): LiveData<Resource<>>*/

    fun addAddress(
        userid: String,
        title: String,
        address: String,
        address2: String,
        city: String,
        country: String,
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
                country,
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

    fun userUpdate(userid: String, token: String, fName: String, lName: String, email: String): LiveData<Resource<UserUpdateModel>>{
        val result = MutableLiveData<Resource<UserUpdateModel>>()

        viewModelScope.launch {
            result.value = Resource.Loading
            result.value = repository.userUpdate(userid, token, fName, lName, email)
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

    fun editAddress(
        userid: String,
        title: String,
        address: String,
        address2: String,
        city: String,
        country: String,
        post_office: String,
        pincode: String,
        contact_name: String,
        contact_mobile: String,
        addressId: String,
        lat: String,
        lng: String,
    ): LiveData<Resource<EditAddressModel>> {
        val result = MutableLiveData<Resource<EditAddressModel>>()

        viewModelScope.launch {
            result.value = Resource.Loading
            result.value = repository.editAddress(
                userid,
                title,
                address,
                address2,
                city,
                country,
                post_office,
                pincode,
                contact_name,
                contact_mobile,
                addressId,
                lat,
                lng
            )
        }

        return result
    }

    fun wallet(userid: String, limit: String, page: String): LiveData<Resource<WalletModel>> {
        val result = MutableLiveData<Resource<WalletModel>>()

        viewModelScope.launch {
            result.value = Resource.Loading
            result.value = repository.wallet(userid, limit, page)
        }

        return result

    }

    fun deactivateAccount(userid: String, deactivateReason: String){}
}