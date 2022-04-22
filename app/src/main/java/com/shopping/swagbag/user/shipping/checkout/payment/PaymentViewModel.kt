package com.shopping.swagbag.user.shipping.checkout.payment

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.google.gson.JsonObject
import com.shopping.swagbag.service.Resource
import kotlinx.coroutines.launch

class PaymentViewModel(private val repository: PaymentRepository): ViewModel() {

    fun paymentRequest(sampleRequestModel: SampleRequestModel): LiveData<Resource<SampleResponseModel>>{
        val result = MutableLiveData<Resource<SampleResponseModel>>()

        viewModelScope.launch {
            result.value = Resource.Loading
            result.value = repository.paymentRequest(sampleRequestModel)
        }

        return result
    }
}