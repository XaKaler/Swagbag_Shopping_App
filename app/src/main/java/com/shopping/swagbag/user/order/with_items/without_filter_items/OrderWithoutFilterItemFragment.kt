package com.shopping.swagbag.user.order.with_items.without_filter_items

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.View
import androidx.navigation.fragment.findNavController
import com.shopping.swagbag.R
import com.shopping.swagbag.databinding.FragmentOrderWithoutFilterItemBinding
import com.shopping.swagbag.databinding.ToolbarWithNoMenuWhiteBgBinding

class OrderWithoutFilterItemFragment : Fragment(R.layout.fragment_order_without_filter_item) {

    private lateinit var viewBinding: FragmentOrderWithoutFilterItemBinding
    private lateinit var toolbarBinding: ToolbarWithNoMenuWhiteBgBinding

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        viewBinding = FragmentOrderWithoutFilterItemBinding.bind(view)
        toolbarBinding = viewBinding.include

        initViews()


    }

    private fun initViews() {
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