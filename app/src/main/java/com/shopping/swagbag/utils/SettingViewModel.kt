package com.shopping.swagbag.utils

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.shopping.swagbag.SettingRepository
import com.shopping.swagbag.SettingsModel
import com.shopping.swagbag.category.CategoryRepository
import com.shopping.swagbag.coupons.GiftCardModel
import com.shopping.swagbag.service.Resource
import kotlinx.coroutines.launch

class SettingViewModel(private val repository: SettingRepository): ViewModel() {

    fun giftCard(): LiveData<Resource<GiftCardModel>>{
        val result = MutableLiveData<Resource<GiftCardModel>>()

        viewModelScope.launch {
            result.value = Resource.Loading
            result.value = repository.giftCard()
        }

        return result
    }

    fun settings(): LiveData<Resource<SettingsModel>>{
        val result = MutableLiveData<Resource<SettingsModel>>()

        viewModelScope.launch {
            result.value = Resource.Loading
            result.value = repository.settings()
        }

        return result
    }
}