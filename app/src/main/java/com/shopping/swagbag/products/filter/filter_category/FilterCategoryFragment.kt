package com.shopping.swagbag.products.filter.filter_category

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.View
import androidx.recyclerview.widget.LinearLayoutManager
import com.shopping.swagbag.R
import com.shopping.swagbag.databinding.FragmentFilterCategoryBinding
import com.shopping.swagbag.dummy.DummyData
import com.shopping.swagbag.products.filter.filter_size.FilterSizeAdapter

class FilterCategoryFragment : Fragment(R.layout.fragment_filter_category) {
    private lateinit var viewBinding: FragmentFilterCategoryBinding

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        viewBinding = FragmentFilterCategoryBinding.bind(view)
        initViews()
    }


    private fun initViews() {
        setSizeList()
        with(viewBinding){

        }
    }

    private fun setSizeList() {
        with(viewBinding){
            rvFilterCategory.apply{
                layoutManager = LinearLayoutManager(context)
                adapter = DummyData().getDummyData()?.let { FilterCategoryAdapter(context, it) }
            }
        }
    }
}