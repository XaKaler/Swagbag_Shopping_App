package com.shopping.swagbag.shipping.payment_mode

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.fragment.findNavController
import com.shopping.swagbag.R
import com.shopping.swagbag.databinding.FragmentAddUserDetailsBinding
import com.shopping.swagbag.databinding.FragmentPaymentModeBinding
import com.shopping.swagbag.databinding.ToolbarWithNoMenuWhiteBgBinding

class PaymentModeFragment : Fragment(R.layout.fragment_payment_mode) {

    private lateinit var viewBinding: FragmentPaymentModeBinding
    private lateinit var toolbarBinding: ToolbarWithNoMenuWhiteBgBinding

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        viewBinding = FragmentPaymentModeBinding.bind(view)
        toolbarBinding = viewBinding.include

        initViews()


    }

    private fun initViews() {
        var codVisible = false
        var cardVisible = false
        var upiVisible = false

        with(viewBinding){

            // click listeners
            cashOnDelivery.setOnClickListener{
                if(!codVisible){
                    codVisible = true
                    viewCOD.root.visibility = View.VISIBLE
                }
                else {
                    codVisible = false
                    viewCOD.root.visibility = View.GONE
                }
            }

            creditDebitCard.setOnClickListener{
                if(!codVisible){
                    cardVisible = true
                    includeDebeitCreditCard.root.visibility = View.VISIBLE
                }
                else {
                    cardVisible = false
                    includeDebeitCreditCard.root.visibility = View.GONE
                }
            }

            upiOptions.setOnClickListener{
                if(!codVisible){
                    upiVisible = true
                    includeUPI.root.visibility = View.VISIBLE
                }
                else {
                    upiVisible = false
                    includeUPI.root.visibility = View.GONE
                }
            }

            payNow.setOnClickListener{

            }
        }
        setToolbar()
    }

    private fun setToolbar() {
        with(toolbarBinding){
            // set title
            tvTitle.text = getString(R.string.payment)

            // back button click
            imgBack.setOnClickListener{
                findNavController().popBackStack()
            }
        }
    }
}