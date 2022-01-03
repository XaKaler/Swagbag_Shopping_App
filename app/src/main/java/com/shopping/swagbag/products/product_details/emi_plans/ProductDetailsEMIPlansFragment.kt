package com.shopping.swagbag.products.product_details.emi_plans

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.fragment.findNavController
import com.shopping.swagbag.R
import com.shopping.swagbag.databinding.FragmentProductDetailsEMIPlansBinding
import com.shopping.swagbag.databinding.FragmentProductDetailsSizeChartBinding
import com.shopping.swagbag.databinding.ToolbarWithNoMenuWhiteBgBinding


class ProductDetailsEMIPlansFragment : Fragment(R.layout.fragment_product_details_e_m_i_plans) {

    private lateinit var viewBinding: FragmentProductDetailsEMIPlansBinding
    private lateinit var toolbarBinding: ToolbarWithNoMenuWhiteBgBinding

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        viewBinding = FragmentProductDetailsEMIPlansBinding.bind(view)
        toolbarBinding = viewBinding.include

        initViews()


    }

    private fun initViews() {
        setToolbar()

    }


    private fun setToolbar() {
        with(toolbarBinding){
            // set title
            tvTitle.text = getString(R.string.emi_plans)

            // back button click
            imgBack.setOnClickListener{
                findNavController().popBackStack()
            }
        }
    }

}