package com.shopping.swagbag.service

import com.shopping.swagbag.category.CategoryModel
import retrofit2.Response
import retrofit2.http.GET


interface ApiService {

    @GET("all-category")
    suspend fun getAllCategories(): Response<CategoryModel>
}
