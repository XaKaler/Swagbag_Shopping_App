package com.shopping.swagbag.settings

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.shopping.swagbag.user.shipping.checkout.payment.PaymentRepository
import com.shopping.swagbag.user.shipping.checkout.payment.PaymentViewModel
import com.shopping.swagbag.utils.SettingViewModel

class PaymentViewModelFactory(private val repository: PaymentRepository) :
    ViewModelProvider.Factory {
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        return PaymentViewModel(repository) as T
    }
}