package com.shopping.swagbag.products.filter.filter_price

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.View
import com.shopping.swagbag.R
import com.shopping.swagbag.databinding.FragmentFilterPriceBinding
import com.shopping.swagbag.databinding.FragmentFilterSizeBinding


class FilterPriceFragment : Fragment(R.layout.fragment_filter_price) {

    private lateinit var viewBinding: FragmentFilterPriceBinding

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        viewBinding = FragmentFilterPriceBinding.bind(view)

    }

}