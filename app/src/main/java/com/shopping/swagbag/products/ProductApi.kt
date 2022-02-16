package com.shopping.swagbag.products

import com.shopping.swagbag.brand.BrandModel
import com.shopping.swagbag.products.product_details.ProductDetailModel
import com.shopping.swagbag.wishlist.withproduct.AddToWishlistModel
import com.shopping.swagbag.wishlist.withproduct.DeleteSingleWishModel
import com.shopping.swagbag.wishlist.withproduct.GetWishlistModel
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
}