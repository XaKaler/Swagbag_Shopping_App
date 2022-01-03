package com.shopping.swagbag.service

import com.shopping.swagbag.common.FreeData
import retrofit2.Call
import retrofit2.http.GET

interface ApiService {

    @GET("photos")
    fun getFreeData(): Call<List<FreeData.FreeDataItem>>

}