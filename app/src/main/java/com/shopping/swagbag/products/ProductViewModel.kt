package com.shopping.swagbag.products

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.shopping.swagbag.products.product_details.ProductDetailModel
import com.shopping.swagbag.service.Resource
import kotlinx.coroutines.launch

class ProductViewModel(
    private val repository: ProductRepository
    ): ViewModel() {

    fun productDetails(
        slug: String
    ): LiveData<Resource<ProductDetailModel>>{
        val result = MutableLiveData<Resource<ProductDetailModel>>()

        viewModelScope.launch {
            result.value = Resource.Loading
            result.value = repository.productDetails(slug)
        }

        return result
    }
}