package com.shopping.swagbag.products

import com.shopping.swagbag.brand.BrandModel
import com.shopping.swagbag.products.product_details.ProductDetailModel
import retrofit2.http.Field
import retrofit2.http.FormUrlEncoded
import retrofit2.http.GET
import retrofit2.http.Query

interface ProductApi {

    @FormUrlEncoded
    @GET("product-details")
    suspend fun productDetails(
        @Field("slug")slug: String
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
        @Query("master")master: String,
    ): ProductSearchModel
}