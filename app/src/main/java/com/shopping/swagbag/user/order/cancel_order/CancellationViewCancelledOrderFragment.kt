package com.shopping.swagbag.user.order.cancel_order

import android.os.Bundle
import android.view.View
import androidx.navigation.fragment.findNavController
import androidx.fragment.app.Fragment
import com.shopping.swagbag.R
import com.shopping.swagbag.databinding.FragmentCancellationViewCancelledOrderBinding
import com.shopping.swagbag.databinding.ToolbarWithNoMenuWhiteBgBinding


class CancellationViewCancelledOrderFragment : Fragment(R.layout.fragment_cancellation_view_cancelled_order) {

    private lateinit var viewBinding: FragmentCancellationViewCancelledOrderBinding
    private lateinit var toolbarBinding: ToolbarWithNoMenuWhiteBgBinding

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        viewBinding = FragmentCancellationViewCancelledOrderBinding.bind(view)
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