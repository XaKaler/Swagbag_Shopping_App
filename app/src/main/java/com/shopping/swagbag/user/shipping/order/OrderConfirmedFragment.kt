package com.shopping.swagbag.user.shipping.order

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.View
import androidx.activity.addCallback
import androidx.navigation.fragment.findNavController
import com.shopping.swagbag.R
import com.shopping.swagbag.databinding.FragmentOrderConfirmedBinding
import com.shopping.swagbag.databinding.ToolbarWithNoMenuWhiteBgBinding

class OrderConfirmedFragment : Fragment(R.layout.fragment_order_confirmed) {

    private lateinit var viewBinding: FragmentOrderConfirmedBinding
    private lateinit var toolbarBinding: ToolbarWithNoMenuWhiteBgBinding

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        // handle back pressed
        requireActivity().onBackPressedDispatcher.addCallback(viewLifecycleOwner) {
            findNavController().navigate(R.id.action_global_home2)
        }

        viewBinding = FragmentOrderConfirmedBinding.bind(view)
        toolbarBinding = viewBinding.include

        initViews()
    }

    private fun initViews() {
        with(viewBinding){
            
            viewOrders.setOnClickListener{
                findNavController().navigate(R.id.action_orderConfirmedFragment_to_orderWithItemsFragment)
            }

            continueShopping.setOnClickListener{
                findNavController().navigate(R.id.action_orderConfirmedFragment_to_home2)
            }
        }

        setToolbar()
    }

    private fun setToolbar() {
        with(toolbarBinding){
            // set title
            tvTitle.text = getString(R.string.order_confirmed)

            // back button click
            imgBack.setOnClickListener{
                findNavController().popBackStack()
            }
        }
    }
}