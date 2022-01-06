package com.shopping.swagbag.order.without_items

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.fragment.findNavController
import com.shopping.swagbag.R
import com.shopping.swagbag.databinding.FragmentAddUserDetailsBinding
import com.shopping.swagbag.databinding.FragmentOrderWithoutItemsBinding
import com.shopping.swagbag.databinding.ToolbarWithNoMenuWhiteBgBinding

class OrderWithoutItemsFragment : Fragment(R.layout.fragment_order_without_items) {

    private lateinit var viewBinding: FragmentOrderWithoutItemsBinding
    private lateinit var toolbarBinding: ToolbarWithNoMenuWhiteBgBinding

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        viewBinding = FragmentOrderWithoutItemsBinding.bind(view)
        toolbarBinding = viewBinding.include

        initViews()


    }

    private fun initViews() {
        viewBinding.startShopping.setOnClickListener{
            findNavController().navigate(R.id.action_orderWithoutItemsFragment_to_categoryFragment)
        }

        setToolbar()
    }

    private fun setToolbar() {
        with(toolbarBinding){
            // set title
            tvTitle.text = getString(R.string.orders)

            // back button click
            imgBack.setOnClickListener{
                findNavController().popBackStack()
            }
        }
    }
}