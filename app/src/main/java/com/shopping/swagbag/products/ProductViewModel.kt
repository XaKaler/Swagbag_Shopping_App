package com.shopping.swagbag.products

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.shopping.swagbag.brand.BrandModel
import com.shopping.swagbag.products.product_details.ProductDetailModel
import com.shopping.swagbag.service.Resource
import com.shopping.swagbag.wishlist.withproduct.AddToWishlistModel
import com.shopping.swagbag.wishlist.withproduct.DeleteSingleWishModel
import com.shopping.swagbag.wishlist.withproduct.GetWishlistModel
import kotlinx.coroutines.launch

class ProductViewModel(
    private val repository: ProductRepository
    ): ViewModel() {

    fun productDetails(
        slug: String
    ): LiveData<Resource<ProductDetailModel>> {
        val result = MutableLiveData<Resource<ProductDetailModel>>()

        viewModelScope.launch {
            result.value = Resource.Loading
            result.value = repository.productDetails(slug)
        }

        return result
    }

    fun allBrand(): LiveData<Resource<BrandModel>> {

        val brand = MutableLiveData<Resource<BrandModel>>()

        viewModelScope.launch {
            brand.value = Resource.Loading
            brand.value = repository.allBrands()
        }

        return brand
    }

    fun productSearch(
        deal: String,
        brand: String,
        sub_category: String,
        category: String,
        price: String,
        sortby: String,
        master: String,
    ): LiveData<Resource<ProductSearchModel>> {

        Log.e("", "category id: $master", )

        val products = MutableLiveData<Resource<ProductSearchModel>>()

        viewModelScope.launch {
            products.value = Resource.Loading
            products.value = repository.productSearch(
                deal,
                brand,
                sub_category,
                category,
                price,
                sortby,
                master
            )
        }

        return products
    }

    // Wishlist
    fun addToWishList(
        productId: String,
        userId: String
    ): LiveData<Resource<AddToWishlistModel>>{
        val result = MutableLiveData<Resource<AddToWishlistModel>>()

        viewModelScope.launch {
            result.value = Resource.Loading
            result.value = repository.addToWishlist(productId, userId)
        }
        return result
    }

    fun getWish(userId: String): LiveData<Resource<GetWishlistModel>>{
        val result = MutableLiveData<Resource<GetWishlistModel>>()

        viewModelScope.launch {
            result.value = Resource.Loading
            result.value = repository.getWish(userId)
        }

        return result
    }

    fun deleteSingleWish(productId: String, userId: String): LiveData<Resource<DeleteSingleWishModel>>{
        val result = MutableLiveData<Resource<DeleteSingleWishModel>>()

        viewModelScope.launch {
            result.value = Resource.Loading
            result.value = repository.deleteSingleWish(productId, userId)
        }

        return result
    }
}