package com.shopping.swagbag.products

import android.util.Log
import com.shopping.swagbag.common.base.BaseRepository
import com.shopping.swagbag.user.shoppingbeg.withproduct.GetCartModel
import org.json.JSONArray

class ProductRepository(private val api: ProductApi) : BaseRepository() {

    suspend fun productDetails(slug: String) = safeApiCall { api.productDetails(slug) }

    suspend fun allBrands() = safeApiCall { api.allBrands() }

    suspend fun productSearch(
        deal: String,
        brand: String,
        sub_category: String,
        category: String,
        price: String,
        option: String,
        sortby: String,
        master: String,
        name: String,
    ) = safeApiCall { api.productSearch(deal, brand, sub_category, category, price, option, sortby, master, name) }

    suspend fun addToWishlist(
        productId: String,
        userId: String
    ) = safeApiCall { api.addToWishlist(productId, userId) }

    suspend fun getWish(userId: String) = safeApiCall { api.getWish(userId) }


    suspend fun deleteSingleWish(productId: String, userId: String) =
        safeApiCall { api.deleteSingleWish(productId, userId) }

    suspend fun getCart(userId: String) = safeApiCall { api.getCart(userId) }

    suspend fun updateCart(
        userId: String,
    productId: String,
    quantity: String) = safeApiCall { api.updateCart(productId, userId, quantity) }

    suspend fun addToCart(
        quantity: String,
        productId: String,
        userId: String,
        option: JSONArray
    ) = safeApiCall { api.addToCart(quantity, productId, userId, option) }

    suspend fun deleteSingleCart(
        productId: String,
        userId: String
    ) = safeApiCall { api.deleteSingleCart(productId, userId) }

    suspend fun clearCart(userId: String) = safeApiCall { api.clearCart(userId) }

    suspend fun clearWishlist(userId: String) = safeApiCall { api.clearWishlist(userId) }

    suspend fun getHome() = safeApiCall {
        Log.e("TAG", "getHome in product repository: ${api.getHome()}")
        api.getHome()
    }

    suspend fun checkout(userId: String, address: String, cartData: String) =
        safeApiCall { api.checkout(userId, address, cartData) }

    suspend fun checkoutConfirm(orderId: String, gateway: String, transactionId: String) =
        safeApiCall { api.checkoutConfirm(orderId, gateway, transactionId) }

    suspend fun orderUser(token: String, filterBy: String, userId: String) =
        safeApiCall { api.orderUser(token, filterBy, userId) }
}