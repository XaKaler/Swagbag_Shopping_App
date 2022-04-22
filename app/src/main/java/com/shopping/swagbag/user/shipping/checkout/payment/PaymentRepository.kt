package com.shopping.swagbag.user.shipping.checkout.payment

import com.google.gson.JsonObject
import com.shopping.swagbag.common.base.BaseRepository
import com.shopping.swagbag.service.apis.PaymentApi

class PaymentRepository(private val api: PaymentApi): BaseRepository() {

    suspend fun paymentRequest(sampleRequestModel: SampleRequestModel) = safeApiCall { api.paymentRequest(sampleRequestModel) }
}