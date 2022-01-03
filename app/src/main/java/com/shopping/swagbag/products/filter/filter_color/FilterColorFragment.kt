package com.shopping.swagbag.products.filter.filter_color

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.View
import com.shopping.swagbag.R
import com.shopping.swagbag.databinding.FragmentFilterColorBinding

class FilterColorFragment : Fragment(R.layout.fragment_filter_color) {
    private lateinit var viewBinding: FragmentFilterColorBinding

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        viewBinding = FragmentFilterColorBinding.bind(view)

    }

}