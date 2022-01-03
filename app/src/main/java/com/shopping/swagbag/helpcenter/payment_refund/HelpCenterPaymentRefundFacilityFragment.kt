package com.shopping.swagbag.helpcenter.payment_refund

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.fragment.findNavController
import com.shopping.swagbag.R
import com.shopping.swagbag.databinding.FragmentHelpCenterPaymentRefundFacilityBinding
import com.shopping.swagbag.databinding.FragmentHelpCenterWithoutOrderBinding
import com.shopping.swagbag.databinding.ToolbarWithNoMenuWhiteBgBinding

class HelpCenterPaymentRefundFacilityFragment : Fragment(R.layout.fragment_help_center_payment_refund_facility) {

    private lateinit var viewBinding: FragmentHelpCenterPaymentRefundFacilityBinding
    private lateinit var toolbarBinding: ToolbarWithNoMenuWhiteBgBinding

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        viewBinding = FragmentHelpCenterPaymentRefundFacilityBinding.bind(view)
        toolbarBinding = viewBinding.include

        initViews()


    }

    private fun initViews() {
        setToolbar()
    }

    private fun setToolbar() {
        with(toolbarBinding){
            // set title
            tvTitle.text = getString(R.string.help_center)

            // back button click
            imgBack.setOnClickListener{
                findNavController().popBackStack()
            }
        }
    }
}