package com.shopping.swagbag.products

import com.shopping.swagbag.brand.BrandModel
import com.shopping.swagbag.home.HomeModel
import com.shopping.swagbag.products.product_details.AddToCartModel
import com.shopping.swagbag.user.shoppingbeg.withproduct.ClearCartModel
import com.shopping.swagbag.user.shoppingbeg.withproduct.DeleteSingleCartModel
import com.shopping.swagbag.user.shoppingbeg.withproduct.GetCartModel
import com.shopping.swagbag.user.wishlist.withproduct.AddToWishlistModel
import com.shopping.swagbag.user.wishlist.withproduct.ClearWishlistModel
import com.shopping.swagbag.user.wishlist.withproduct.DeleteSingleWishModel
import com.shopping.swagbag.user.wishlist.withproduct.GetWishlistModel
import retrofit2.http.*

interface ProductApi {

    @GET("product-details")
    suspend fun productDetails(
        @Query("slug")slug: String
    ): ProductDetailModel

    @GET("all-brands")
    suspend fun allBrands(): BrandModel

    @GET("product-search")
    suspend fun productSearch(
        @Query("deal")deal: String,
        @Query("brand")brand: String,
        @Query("sub_category")sub_category: String,
        @Query("category")category: String,
        @Query("price")option: String,
        @Query("sortby")sortby: String,
        @Query("master")master: String
    ): ProductSearchModel

    @FormUrlEncoded
    @POST("add-to-wish")
    suspend fun addToWishlist(
        @Field("productid") productId: String,
        @Field("id") user: String
    ): AddToWishlistModel

    @FormUrlEncoded
    @POST("get-wish")
    suspend fun getWish(
        @Field("id")userId: String): GetWishlistModel

    @FormUrlEncoded
    @POST("delete-wish")
    suspend fun deleteSingleWish(
        @Field("product") productId: String,
        @Field("userid") userId: String,
    ): DeleteSingleWishModel

    @FormUrlEncoded
    @POST("get-cart")
    suspend fun getCart(
        @Field("id") userId: String
    ): GetCartModel

    @FormUrlEncoded
    @POST("delete-cart")
    suspend fun deleteSingleCart(
        @Field("id") productId: String,
        @Field("userid") userId: String
    ): DeleteSingleCartModel

    @FormUrlEncoded
    @POST("clear-cart")
    suspend fun clearCart(
        @Field("userid") userId: String
    ): ClearCartModel


    @FormUrlEncoded
    @POST("clear-cart")
    suspend fun clearWishlist(
        @Field("userid") userId: String
    ): ClearWishlistModel

    @FormUrlEncoded
    @POST("add-to-cart")
    suspend fun addToCart(
        @Field("quantity")quantity: String,
        @Field("productid")productId: String,
        @Field("id")userId: String,
        @Field("option")option: String,
    ): AddToCartModel

    @GET("mobile-home")
    suspend fun getHome(): HomeModel
}