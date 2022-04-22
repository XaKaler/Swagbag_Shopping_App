package com.shopping.swagbag.service.apis

import com.shopping.swagbag.category.CategoryModel
import com.shopping.swagbag.category.MasterCategoryModel
import com.shopping.swagbag.category.ParticularCategoryModel
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
