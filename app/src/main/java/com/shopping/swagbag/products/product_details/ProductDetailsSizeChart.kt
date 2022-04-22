package com.shopping.swagbag.products.product_details

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.content.ContextCompat
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import com.bumptech.glide.Glide
import com.google.gson.Gson
import com.shopping.swagbag.R
import com.shopping.swagbag.common.base.BaseFragment
import com.shopping.swagbag.databinding.FragmentProductDetailsSizeChartBinding
import com.shopping.swagbag.databinding.ProductDetailsButtonBinding
import com.shopping.swagbag.databinding.ToolbarWithNoMenuWhiteBgBinding
import com.shopping.swagbag.service.apis.ProductApi
import com.shopping.swagbag.products.ProductRepository
import com.shopping.swagbag.products.ProductViewModel
import kotlinx.android.synthetic.main.fragment_product_details_size_chart.*


class ProductDetailsSizeChart :
    BaseFragment<FragmentProductDetailsSizeChartBinding, ProductViewModel, ProductRepository>(
        FragmentProductDetailsSizeChartBinding::inflate
    ) {

    private lateinit var toolbarBinding: ToolbarWithNoMenuWhiteBgBinding
    private lateinit var bottomButtonBinding: ProductDetailsButtonBinding
    private lateinit var product: ProductDetailModel

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        toolbarBinding = viewBinding.include
        bottomButtonBinding = viewBinding.bottomButtons

        initViews()


    }

    private fun initViews() {
        setToolbar()

        getArgument()

        handleBottomButtons()

        //when click on size chart and how to measure
        with(viewBinding.sizeChartButtons) {
            sizeChart.setOnClickListener {
                sizeChart.setBackgroundResource(R.drawable.red_rec_outline_5)
                context?.let {
                    sizeChart.setTextColor(
                        ContextCompat.getColor(
                            it,
                            R.color.steel_teal
                        )
                    )
                }
                howToMeasure.setBackgroundResource(0)
                context?.let {
                    howToMeasure.setTextColor(
                        ContextCompat.getColor(
                            it,
                            R.color.sonic_silver
                        )
                    )
                }

                howToMeasureLayout.visibility = View.GONE
                sizeChartList.visibility = View.VISIBLE
            }

            howToMeasure.setOnClickListener{
                howToMeasure.setBackgroundResource(R.drawable.red_rec_outline_5)
                context?.let {
                    howToMeasure.setTextColor(
                        ContextCompat.getColor(
                            it,
                            R.color.steel_teal
                        )
                    )
                }
                sizeChart.setBackgroundResource(0)
                context?.let {
                    sizeChart.setTextColor(
                        ContextCompat.getColor(
                            it,
                            R.color.sonic_silver
                        )
                    )
                }

                howToMeasureLayout.visibility = View.VISIBLE
                sizeChartList.visibility = View.GONE
            }
        }
    }

    private fun handleBottomButtons() {
        with(bottomButtonBinding){
            addToCart.setOnClickListener{
            }
        }
    }

    private fun getArgument() {
        val args: ProductDetailsSizeChartArgs by navArgs()
        product = Gson().fromJson(args.product, ProductDetailModel::class.java)

        setData()
    }

    private fun setData() {
        with(viewBinding) {
            val productResult = product.result

            context?.let { Glide.with(it).load(product.result.file[0].location).into(productImage) }

            productName.text = productResult.name
            productDetails.text = productResult.shortDesc
            productPrice.text = productResult.price.toString()
        }
    }

    private fun setToolbar() {
        with(toolbarBinding) {
            // set title
            tvTitle.text = getString(R.string.products)

            // back button click
            imgBack.setOnClickListener {
                findNavController().popBackStack()
            }
        }
    }

    override fun getFragmentBinding(
        inflater: LayoutInflater,
        container: ViewGroup?
    ) = FragmentProductDetailsSizeChartBinding.inflate(inflater, container, false)

    override fun getViewModel() = ProductViewModel::class.java

    override fun getFragmentRepository() =
        ProductRepository(remoteDataSource.getBaseUrl().create(ProductApi::class.java))
}