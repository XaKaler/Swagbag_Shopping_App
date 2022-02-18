package com.shopping.swagbag.user.order.cancel_order

import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import com.shopping.swagbag.R
import com.shopping.swagbag.databinding.FragmentCancellationOrderCancelledBinding
import com.shopping.swagbag.databinding.ToolbarWithNoMenuWhiteBgBinding

class CancellationOrderCancelledFragment : Fragment(R.layout.fragment_cancellation_order_cancelled) {

    private lateinit var viewBinding: FragmentCancellationOrderCancelledBinding
    private lateinit var toolbarBinding: ToolbarWithNoMenuWhiteBgBinding

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        viewBinding = FragmentCancellationOrderCancelledBinding.bind(view)
        toolbarBinding = viewBinding.include

        initViews()


    }

    private fun initViews() {
        with(viewBinding){
            done.setOnClickListener{
                findNavController().navigate(R.id.action_cancellationOrderCancelledFragment_to_home2)
            }
        }
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