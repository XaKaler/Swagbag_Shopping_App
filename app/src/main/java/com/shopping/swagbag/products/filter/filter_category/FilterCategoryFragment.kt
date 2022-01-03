package com.shopping.swagbag.products.filter.filter_category

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.View
import com.shopping.swagbag.R
import com.shopping.swagbag.databinding.FragmentFilterCategoryBinding

class FilterCategoryFragment : Fragment(R.layout.fragment_filter_category) {
    private lateinit var viewBinding: FragmentFilterCategoryBinding

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        viewBinding = FragmentFilterCategoryBinding.bind(view)

    }
}