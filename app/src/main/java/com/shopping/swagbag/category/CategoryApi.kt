package com.shopping.swagbag.category

import com.shopping.swagbag.category.CategoryModel
import retrofit2.Response
import retrofit2.http.*


interface CategoryApi {

    @GET("all-category")
    suspend fun getAllCategories(): CategoryModel

    @GET("master-category")
    suspend fun masterCategory(): MasterCategoryModel

    @FormUrlEncoded
    @POST("mobile-category")
    suspend fun particularCategory(
        @Field("master_category") masterCategory: String
    ): ParticularCategoryModel

}
