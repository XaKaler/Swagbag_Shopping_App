package com.shopping.swagbag.user.shipping.payment_mode

import android.os.Bundle
import android.view.View
import android.widget.RadioButton
import android.widget.RadioGroup
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import com.shopping.swagbag.R
import com.shopping.swagbag.databinding.FragmentPaymentModeBinding
import com.shopping.swagbag.databinding.ToolbarWithNoMenuWhiteBgBinding
import com.shopping.swagbag.user.order.user_details.AllAddressModel
import com.shopping.swagbag.user.shoppingbeg.withproduct.GetCartModel


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
    }

    private fun initViews() {
        val genId: Int = viewBinding.paymentOptions.checkedRadioButtonId
        /*val radioButton = findViewById(genid) as RadioButton
        paymentMode = radioButton.text.toString()*/

        with(viewBinding) {

            placeOrder.setOnClickListener {
                findNavController().navigate(R.id.action_paymentModeFragment_to_orderConfirmedFragment)
            }

            viewDetails.setOnClickListener {
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
            val totalCartItem = cartData.result?.size
            itemCount.text = totalCartItem.toString()

            var totalMRP = 0
            val discountOnMRP = 0
            var totalAmount = 0
            val deliveryCharge = 0

            for (cart in cartData.result!!) {
                totalMRP += cart.product.sellingPrice
                //discountOnMRP += cart.product.discountedPrice
            }

            totalAmount = totalMRP - discountOnMRP + deliveryCharge

            discountPrice.text = discountOnMRP.toString()
            totalPrice.text = totalAmount.toString()
            productPrice.text = totalMRP.toString()

            //delivery charge
            if (deliveryCharge == 0) {
                deliveryRupeeSign.visibility = View.GONE
                deliveryCharges.visibility = View.GONE
                free.visibility = View.VISIBLE
            } else {
                deliveryRupeeSign.visibility = View.VISIBLE
                deliveryCharges.visibility = View.VISIBLE
                deliveryCharges.text = deliveryCharge.toString()
                free.visibility = View.GONE
            }

            val viewDetailsBtnTxt = "${getString(R.string.Rs)}$totalAmount \nView Details"
            viewDetails.text = viewDetailsBtnTxt
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