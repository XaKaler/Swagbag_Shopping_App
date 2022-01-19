package com.shopping.swagbag.products.filter.filter_color

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.View
import androidx.recyclerview.widget.LinearLayoutManager
import com.shopping.swagbag.R
import com.shopping.swagbag.databinding.FragmentFilterColorBinding
import com.shopping.swagbag.dummy.DummyData
import com.shopping.swagbag.products.filter.filter_size.FilterSizeAdapter

class FilterColorFragment : Fragment(R.layout.fragment_filter_color) {
    private lateinit var viewBinding: FragmentFilterColorBinding

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        viewBinding = FragmentFilterColorBinding.bind(view)

        initViews()

    }

    private fun initViews() {
        setColorList()
        with(viewBinding){

        }
    }

    private fun setColorList() {
        with(viewBinding){
            rvFilterColor.apply{
                layoutManager = LinearLayoutManager(context)
                adapter = DummyData().getDummyData()?.let { FilterColorAdapter(context, it) }
            }
        }
    }

}