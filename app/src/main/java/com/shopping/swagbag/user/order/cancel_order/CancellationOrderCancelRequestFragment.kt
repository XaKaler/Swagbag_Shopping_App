package com.shopping.swagbag.user.order.cancel_order

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.View
import com.shopping.swagbag.R
import androidx.navigation.fragment.findNavController
import com.shopping.swagbag.databinding.FragmentCancellationOrderCancelRequestBinding
import com.shopping.swagbag.databinding.ToolbarWithNoMenuWhiteBgBinding

class CancellationOrderCancelRequestFragment : Fragment(R.layout.fragment_cancellation_order_cancel_request) {

    private lateinit var viewBinding: FragmentCancellationOrderCancelRequestBinding
    private lateinit var toolbarBinding: ToolbarWithNoMenuWhiteBgBinding

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        viewBinding = FragmentCancellationOrderCancelRequestBinding.bind(view)
        toolbarBinding = viewBinding.include

        initViews()


    }

    private fun initViews() {
        setToolbar()
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