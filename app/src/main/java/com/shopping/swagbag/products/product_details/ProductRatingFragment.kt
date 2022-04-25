package com.shopping.swagbag.products.product_details

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.shopping.swagbag.common.base.BaseFragment
import com.shopping.swagbag.databinding.FragmentProductRatingBinding
import com.shopping.swagbag.products.ProductRepository
import com.shopping.swagbag.products.ProductViewModel
import com.shopping.swagbag.service.apis.ProductApi


class ProductRatingFragment : BaseFragment<
        FragmentProductRatingBinding,
        ProductViewModel,
        ProductRepository>(FragmentProductRatingBinding::inflate) {

    override fun getFragmentBinding(
        inflater: LayoutInflater,
        container: ViewGroup?
    ) = FragmentProductRatingBinding.inflate(inflater, container, false)

    override fun getViewModel() = ProductViewModel::class.java

    override fun getFragmentRepository() = ProductRepository(
        remoteDataSource.getBaseUrl().create(
            ProductApi::class.java
        )
    )

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        initViews()
    }

    private fun initViews() {
        TODO("Not yet implemented")
    }


}