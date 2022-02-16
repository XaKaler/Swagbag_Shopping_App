package com.shopping.swagbag.products.product_details

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import androidx.navigation.fragment.findNavController
import android.view.ViewGroup
import androidx.core.content.ContextCompat
import androidx.recyclerview.widget.GridLayoutManager
import com.shopping.swagbag.R
import com.shopping.swagbag.databinding.FragmentProductDetailsSizeChartBinding
import com.shopping.swagbag.databinding.ToolbarWithNoMenuWhiteBgBinding
import com.shopping.swagbag.databinding.ToolbarWithThreeMenusBinding
import com.shopping.swagbag.dummy.DummyData
import com.shopping.swagbag.products.ProductAdapter
import kotlinx.android.synthetic.main.fragment_product_details_size_chart.*


class ProductDetailsSizeChart : Fragment(R.layout.fragment_product_details_size_chart) {

    private lateinit var viewBinding: FragmentProductDetailsSizeChartBinding
    private lateinit var toolbarBinding: ToolbarWithNoMenuWhiteBgBinding

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        viewBinding = FragmentProductDetailsSizeChartBinding.bind(view)
        toolbarBinding = viewBinding.include

        initViews()


    }

    private fun initViews() {
        setToolbar()

        //when click on size chart and how to measure
        with(viewBinding.sizeChartButtons){
            sizeChart.setOnClickListener{
                sizeChart.setBackgroundResource(R.drawable.red_rec_outline_5)
                context?.let { sizeChart.setTextColor(ContextCompat.getColor(it, R.color.steel_teal)) }
                howToMeasure.setBackgroundResource(0)
                context?.let { howToMeasure.setTextColor(ContextCompat.getColor(it, R.color.sonic_silver)) }

                howToMeasureLayout.visibility = View.GONE
                sizeChartList.visibility = View.VISIBLE
            }

            howToMeasure.setOnClickListener{
                howToMeasure.setBackgroundResource(R.drawable.red_rec_outline_5)
                context?.let { howToMeasure.setTextColor(ContextCompat.getColor(it, R.color.steel_teal)) }
                sizeChart.setBackgroundResource(0)
                context?.let { sizeChart.setTextColor(ContextCompat.getColor(it, R.color.sonic_silver)) }

                howToMeasureLayout.visibility = View.VISIBLE
                sizeChartList.visibility = View.GONE
            }
        }
    }

    private fun setToolbar() {
        with(toolbarBinding){
            // set title
            tvTitle.text = getString(R.string.products)

            // back button click
            imgBack.setOnClickListener{
                findNavController().popBackStack()
            }
        }
    }
}