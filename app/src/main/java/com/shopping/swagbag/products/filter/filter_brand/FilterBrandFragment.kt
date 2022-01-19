package com.shopping.swagbag.products.filter.filter_brand

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.View
import androidx.recyclerview.widget.LinearLayoutManager
import com.shopping.swagbag.R
import com.shopping.swagbag.databinding.FragmentFilterBrandBinding
import com.shopping.swagbag.dummy.DummyData
import com.shopping.swagbag.products.filter.filter_size.FilterSizeAdapter


class FilterBrandFragment : Fragment(R.layout.fragment_filter_brand) {
    private lateinit var viewBinding: FragmentFilterBrandBinding

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        viewBinding = FragmentFilterBrandBinding.bind(view)
        initViews()
    }

    private fun initViews() {
        setSizeList()
        with(viewBinding){

        }
    }

    private fun setSizeList() {
        with(viewBinding){
            rvFilterBrand.apply{
                layoutManager = LinearLayoutManager(context)
                adapter = DummyData().getDummyData()?.let { FilterBrandAdapter(context, it) }
            }
        }
    }

}