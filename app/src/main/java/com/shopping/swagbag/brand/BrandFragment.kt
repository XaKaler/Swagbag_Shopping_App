package com.shopping.swagbag.brand

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.Observer
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.shopping.swagbag.common.base.BaseFragment
import com.shopping.swagbag.databinding.FragmentBrandBinding
import com.shopping.swagbag.databinding.ToolbarWithNoMenuBinding
import com.shopping.swagbag.service.apis.ProductApi
import com.shopping.swagbag.products.ProductRepository
import com.shopping.swagbag.products.ProductViewModel
import com.shopping.swagbag.service.Resource


class BrandFragment :
    BaseFragment<FragmentBrandBinding, ProductViewModel, ProductRepository>(FragmentBrandBinding::inflate) {

    private lateinit var toolbarBinding: ToolbarWithNoMenuBinding

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        initViews()
    }

    private fun initViews() {
        toolbarBinding = viewBinding.toolbar

        setToolbar()

        setAllBrands()
    }

    private fun setAllBrands() {
        viewModel.allBrand().observe(viewLifecycleOwner, Observer {

            when(it){

                is Resource.Loading -> showLoading()

                is Resource.Success -> {
                    stopShowingLoading()

                    val allBrands = it.value.result

                    viewBinding.rvBrand.apply{
                        layoutManager = LinearLayoutManager(context)
                        adapter = BrandAdapter(context, allBrands)
                    }
                }

                is Resource.Failure ->{
                    stopShowingLoading()

                    toast(it.errorBody.toString())
                }
            }
        })
    }

    private fun setToolbar() {
        with(toolbarBinding){
            imgBack.setOnClickListener{findNavController().popBackStack()}

            tvTitle.text = "All Brands"
        }
    }

    override fun getFragmentBinding(
        inflater: LayoutInflater,
        container: ViewGroup?
    ) = FragmentBrandBinding.inflate(inflater, container, false)

    override fun getViewModel() = ProductViewModel::class.java

    override fun getFragmentRepository() =
        ProductRepository(remoteDataSource.getBaseUrl().create(ProductApi::class.java))
}