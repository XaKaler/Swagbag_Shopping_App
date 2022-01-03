package com.shopping.swagbag.products.filter

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TabHost
import com.shopping.swagbag.R
import com.shopping.swagbag.databinding.FragmentFilterBinding


class FilterFragmnet : Fragment(R.layout.fragment_filter) {

    private lateinit var viewBinding: FragmentFilterBinding

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        viewBinding = FragmentFilterBinding.bind(view)

        initViews()
    }

    private fun initViews() {
    }

}