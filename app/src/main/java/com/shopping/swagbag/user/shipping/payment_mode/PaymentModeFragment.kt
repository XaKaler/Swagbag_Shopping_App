package com.shopping.swagbag.user.shipping.payment_mode

import android.os.Bundle
import android.view.View
import android.widget.RadioButton
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import com.shopping.swagbag.R
import com.shopping.swagbag.databinding.FragmentPaymentModeBinding
import com.shopping.swagbag.databinding.ToolbarWithNoMenuWhiteBgBinding
import com.shopping.swagbag.user.order.user_details.AllAddressModel
import com.shopping.swagbag.user.shoppingbeg.withproduct.GetCartModel
import kotlinx.android.synthetic.*


class PaymentModeFragment : Fragment(R.layout.fragment_payment_mode) {

    private lateinit var viewBinding: FragmentPaymentModeBinding
    private lateinit var toolbarBinding: ToolbarWithNoMenuWhiteBgBinding
    private lateinit var paymentMode: String
    private lateinit var address: AllAddressModel.Result
    private lateinit var cartData: GetCartModel

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        viewBinding = FragmentPaymentModeBinding.bind(view)
        toolbarBinding = viewBinding.include

        initViews()

        //val radioButton = findViewById() as RadioButton


    }

    private fun initViews() {

        val genid: Int = viewBinding.paymentOptions.checkedRadioButtonId
       // val radioButton = findViewById(genid) as RadioButton
        //val paymentMode = radioButton.text.toString()

        with(viewBinding){

            placeOrder.setOnClickListener{
                findNavController().navigate(R.id.action_paymentModeFragment_to_orderConfirmedFragment)
            }

            viewDetails.setOnClickListener{
                //findNavController().navigate(R.id.action_paymentModeFragment_to_viewOrderDetails)
            }

        }
        setToolbar()

        getArgument()
    }

    private fun getArgument() {
        val args: PaymentModeFragmentArgs by navArgs()
        address = args.address
        cartData = args.cartData

        setData(cartData)
    }

    private fun setData(cartData: GetCartModel) {
        with(viewBinding){
            val totalCartItem = cartData.result.size
            itemCount.text = totalCartItem.toString()


        }
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