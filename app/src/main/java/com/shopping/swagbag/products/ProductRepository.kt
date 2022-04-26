package com.shopping.swagbag.products

import android.util.Log
import com.shopping.swagbag.common.base.BaseRepository
import com.shopping.swagbag.service.apis.ProductApi
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

    suspend fun headerSearch(search: String) = safeApiCall { api.headerSearch(search) }

    suspend fun addToWishlist(
        productId: String,
        userId: String
    ) = safeApiCall { api.addToWishlist(productId, userId) }

    suspend fun getWish(userId: String) = safeApiCall { api.getWish(userId) }


    suspend fun deleteSingleWish(productId: String, userId: String) =
        safeApiCall { api.deleteSingleWish(productId, userId) }

    suspend fun getCart(userId: String) = safeApiCall { api.getCart(userId) }

    suspend fun updateCart(
        cartId: String,
    productId: String,
    quantity: String) = safeApiCall { api.updateCart(productId, cartId, quantity) }

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
        //Log.e("TAG", "getHome in product repository: ${api.getHome()}")
        api.getHome()
    }

    suspend fun checkout(
        login: String,
        userId: String,
        address: String,
        finalPrice: String,
        billingAddress: String,
        cartData: String
    ) =
        safeApiCall { api.checkout(login, userId, address, finalPrice, billingAddress, cartData) }

    suspend fun checkoutConfirm(orderId: String, gateway: String, transactionId: String) =
        safeApiCall { api.checkoutConfirm(orderId, gateway, transactionId) }

    suspend fun orderUser(token: String, filterBy: String, userId: String) =
        safeApiCall { api.orderUser(token, filterBy, userId) }

    suspend fun cancelOrder(orderId: String) = safeApiCall { api.cancelOrder(orderId) }

    suspend fun returnOrder(orderId: String, products: String, reason: String) =
        safeApiCall { api.returnOrder(orderId, products, reason) }

    suspend fun addReview(
        userId: String,
        product: String,
        name: String,
        email: String,
        mobile: String,
        rating: String,
        review: String,
    ) = safeApiCall { api.addReview(userId, product, name, email, mobile, rating, review) }
}