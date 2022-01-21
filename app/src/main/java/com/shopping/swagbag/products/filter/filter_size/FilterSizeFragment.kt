package com.shopping.swagbag.products.filter.filter_size

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.View
import androidx.recyclerview.widget.LinearLayoutManager
import com.shopping.swagbag.R
import com.shopping.swagbag.databinding.FragmentFilterSizeBinding
import com.shopping.swagbag.dummy.DummyData

class FilterSizeFragment : Fragment(R.layout.fragment_filter_size) {

    private lateinit var viewBinding: FragmentFilterSizeBinding

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        viewBinding = FragmentFilterSizeBinding.bind(view)

        initViews()

    }

    private fun initViews() {
        setSizeList()
        with(viewBinding){

        }
    }

    private fun setSizeList() {
        with(viewBinding){
            rvSize.apply{
                layoutManager = LinearLayoutManager(context)
                adapter = DummyData().getProductSize().let { FilterSizeAdapter(context, it) }
            }
        }
    }

}