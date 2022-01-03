package com.shopping.swagbag.products.filter.filter_brand

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.View
import com.shopping.swagbag.R
import com.shopping.swagbag.databinding.FragmentFilterBrandBinding


class FilterBrandFragment : Fragment(R.layout.fragment_filter_brand) {
    private lateinit var viewBinding: FragmentFilterBrandBinding

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        viewBinding = FragmentFilterBrandBinding.bind(view)

    }

}