package com.shopping.swagbag.order.cancel_order

import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import com.shopping.swagbag.R
import com.shopping.swagbag.databinding.FragmentCancellationViewCancelledItemDetailsBinding
import com.shopping.swagbag.databinding.ToolbarWithNoMenuWhiteBgBinding

class CancellationViewCancelledItemDetailsFragment : Fragment(R.layout.fragment_cancellation_view_cancelled_item_details) {

    private lateinit var viewBinding: FragmentCancellationViewCancelledItemDetailsBinding
    private lateinit var toolbarBinding: ToolbarWithNoMenuWhiteBgBinding

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        viewBinding = FragmentCancellationViewCancelledItemDetailsBinding.bind(view)
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