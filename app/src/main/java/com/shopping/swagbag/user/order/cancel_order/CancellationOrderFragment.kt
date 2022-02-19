package com.shopping.swagbag.user.order.cancel_order

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.View
import androidx.navigation.fragment.findNavController
import com.shopping.swagbag.R
import com.shopping.swagbag.databinding.FragmentCancellationOrderBinding
import com.shopping.swagbag.databinding.LytCancelOrderButtonBinding
import com.shopping.swagbag.databinding.ToolbarWithNoMenuWhiteBgBinding


class CancellationOrderFragment : Fragment(R.layout.fragment_cancellation_order) {

    private lateinit var viewBinding: FragmentCancellationOrderBinding
    private lateinit var toolbarBinding: ToolbarWithNoMenuWhiteBgBinding
    private lateinit var buttonBinding: LytCancelOrderButtonBinding

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        viewBinding = FragmentCancellationOrderBinding.bind(view)
        toolbarBinding = viewBinding.include
        buttonBinding = viewBinding.includeBtn

        initViews()


    }

    private fun initViews() {
        buttonClick()

        setToolbar()
    }

    private fun buttonClick() {
        with(buttonBinding){
            cancel.setOnClickListener{
                findNavController().navigate(R.id.action_cancellationOrderFragment_to_cancellationOrderCancelledFragment)
            }
        }
    }

    private fun setToolbar() {
        with(toolbarBinding){
            // set title
            tvTitle.text = getString(R.string.order_details)

            // back button click
            imgBack.setOnClickListener{
                findNavController().popBackStack()
            }
        }
    }
}