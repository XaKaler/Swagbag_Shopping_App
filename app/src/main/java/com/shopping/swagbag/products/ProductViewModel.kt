package com.shopping.swagbag.products

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.shopping.swagbag.brand.BrandModel
import com.shopping.swagbag.home.HomeModel
import com.shopping.swagbag.products.product_details.AddToCartModel
import com.shopping.swagbag.products.product_details.ProductDetailModel
import com.shopping.swagbag.service.Resource
import com.shopping.swagbag.user.shoppingbeg.withproduct.ClearCartModel
import com.shopping.swagbag.user.shoppingbeg.withproduct.DeleteSingleCartModel
import com.shopping.swagbag.user.shoppingbeg.withproduct.GetCartModel
import com.shopping.swagbag.user.wishlist.withproduct.AddToWishlistModel
import com.shopping.swagbag.user.wishlist.withproduct.ClearWishlistModel
import com.shopping.swagbag.user.wishlist.withproduct.DeleteSingleWishModel
import com.shopping.swagbag.user.wishlist.withproduct.GetWishlistModel
import kotlinx.coroutines.launch
import org.json.JSONArray

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

    fun deleteSingleWish(
        productId: String,
        userId: String
    ): LiveData<Resource<DeleteSingleWishModel>> {
        val result = MutableLiveData<Resource<DeleteSingleWishModel>>()

        viewModelScope.launch {
            result.value = Resource.Loading
            result.value = repository.deleteSingleWish(productId, userId)
        }

        return result
    }

    fun getCart(userId: String): LiveData<Resource<GetCartModel>> {
        val result = MutableLiveData<Resource<GetCartModel>>()

        viewModelScope.launch {
            result.value = Resource.Loading
            result.value = repository.getCart(userId)
        }
        return result
    }

    fun addToCart(
        quantity: String,
        productId: String,
        userId: String,
        option: JSONArray
    ): LiveData<Resource<AddToCartModel>> {
        val result = MutableLiveData<Resource<AddToCartModel>>()

        viewModelScope.launch {
            result.value = Resource.Loading
            result.value = repository.addToCart(quantity, productId, userId, option)
        }

        return result
    }

    fun deleteSingleCart(
        productId: String,
        userId: String
    ): LiveData<Resource<DeleteSingleCartModel>> {
        val result = MutableLiveData<Resource<DeleteSingleCartModel>>()

        viewModelScope.launch {
            result.value = Resource.Loading
            result.value = repository.deleteSingleCart(productId, userId)
        }

        return result
    }

    fun clearCart(userId: String): LiveData<Resource<ClearCartModel>>{
        val result = MutableLiveData<Resource<ClearCartModel>>()

        viewModelScope.launch {
            result.value = Resource.Loading
            result.value  = repository.clearCart(userId)
        }

        return result
    }


    fun clearWishlist(userId: String): LiveData<Resource<ClearWishlistModel>>{
        val result = MutableLiveData<Resource<ClearWishlistModel>>()

        viewModelScope.launch {
            result.value = Resource.Loading
            result.value  = repository.clearWishlist(userId)
        }

        return result
    }

    fun getHome(): LiveData<Resource<HomeModel>>{
        val result = MutableLiveData<Resource<HomeModel>>()

        viewModelScope.launch {
            result.value = Resource.Loading
            result.value = repository.getHome()
        }

        Log.e("TAG", "getHome in view model: ${result.value}", )

        return result
    }
}