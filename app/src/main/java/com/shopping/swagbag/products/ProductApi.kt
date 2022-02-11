package com.shopping.swagbag.products

import com.shopping.swagbag.products.product_details.ProductDetailModel
import retrofit2.http.Field
import retrofit2.http.FormUrlEncoded
import retrofit2.http.GET

interface ProductApi {

    @FormUrlEncoded
    @GET("product-details")
    suspend fun productDetails(
        @Field("slug")slug: String
    ): ProductDetailModel

}