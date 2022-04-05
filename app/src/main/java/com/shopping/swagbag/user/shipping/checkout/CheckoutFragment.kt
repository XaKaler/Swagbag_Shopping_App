package com.shopping.swagbag.user.shipping.checkout

import android.content.Context
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import com.google.gson.Gson
import com.shopping.swagbag.MainActivity
import com.shopping.swagbag.R
import com.shopping.swagbag.common.base.BaseFragment
import com.shopping.swagbag.databinding.FragmentPaymentModeBinding
import com.shopping.swagbag.databinding.ToolbarWithNoMenuWhiteBgBinding
import com.shopping.swagbag.products.ProductApi
import com.shopping.swagbag.products.ProductRepository
import com.shopping.swagbag.products.ProductViewModel
import com.shopping.swagbag.service.Resource
import com.shopping.swagbag.user.order.user_details.AllAddressModel
import com.shopping.swagbag.user.shoppingbeg.withproduct.GetCartModel
import com.shopping.swagbag.utils.AppUtils
import kotlinx.android.synthetic.main.fragment_payment_mode.view.*
import kotlin.properties.Delegates


class CheckoutFragment : BaseFragment<
        FragmentPaymentModeBinding,
        ProductViewModel,
        ProductRepository>(FragmentPaymentModeBinding::inflate) {

    private lateinit var toolbarBinding: ToolbarWithNoMenuWhiteBgBinding
    private var paymentMode: String = ""
    private lateinit var address: AllAddressModel.Result
    private lateinit var cartData: GetCartModel
    private val userId by lazy { context?.let { AppUtils(it).getUserId() } }
    private lateinit var mainActivity: MainActivity

    override fun onAttach(context: Context) {
        super.onAttach(context)

        mainActivity = context as MainActivity

        if (mainActivity !is MainActivity) {
            Log.e("TAG", "onAttach: is instance of main actvity")
        } else {
            Log.e("TAG", "onAttach:not is instance of main actvity")
        }

    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        toolbarBinding = viewBinding.include
/*
        val genId: Int = viewBinding.paymentOptions.checkedRadioButtonId
        val radioButton = view.findViewById(genId) as RadioButton
        paymentMode = radioButton.text.toString()*/

        initViews()
    }

    private fun initViews() {
        with(viewBinding) {

            placeOrder.setOnClickListener {
                placeOrders()
            }

            viewDetails.setOnClickListener {
            }

            paymentOptions.setOnCheckedChangeListener { _, checkedId ->
                when (checkedId) {
                    R.id.cod -> paymentMode = "COD"
                    R.id.online -> paymentMode = "Online"
                }
            }
        }
        setToolbar()

        getArgument()
    }

    private fun placeOrders() {
        when (paymentMode) {
            "COD" -> checkout(paymentMode)
            "Online" -> {}
            else -> toast("Select payment option")
        }
    }

    private fun checkout(gateway: String) {
        val json = Gson().toJson(cartData)

        Log.e("checkout", "userid : $userId\n" +
                "address : $address\n" +
                "cart data : $json", )

        userId?.let { userId ->
            viewModel.checkout("Y", userId, address.id, address.id, json)
                .observe(viewLifecycleOwner) {
                    when (it) {
                        is Resource.Loading -> showLoading()

                        is Resource.Success -> {
                            stopShowingLoading()

                            val orderId = it.value.orderId
                            checkoutConfirm(orderId, gateway, "")
                        }

                    is Resource.Failure -> {
                        stopShowingLoading()
                        toast("try again")
                        Log.e("checkout", "$it")
                    }
                }
            }
        }
    }

    private fun checkoutConfirm(orderId: String, gateway: String, transactionId: String) {
        viewModel.checkoutConfirm(orderId, gateway, transactionId).observe(viewLifecycleOwner) {
            when(it){
                is Resource.Loading -> showLoading()

                is Resource.Success -> {
                    stopShowingLoading()

                    toast(it.value.message)

                    findNavController().navigate(R.id.action_paymentModeFragment_to_orderConfirmedFragment)
                }

                is Resource.Failure ->{
                    stopShowingLoading()
                    toast("try again")
                    Log.e("checkout", "$it")
                }
            }
        }
    }

    private fun getArgument() {
        val args: CheckoutFragmentArgs by navArgs()
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


            discountPrice.text = discountOnMRP.toString()
            subTotal.text = totalMRP.toString()

            /*val tax = mainActivity.getSettingResult("Tax").toInt()
            taxPercentage.text = "Tax($tax%)"
            val taxAmount = totalMRP*tax/100
            taxPrice.text = taxAmount.toString()
*/
            totalAmount = totalMRP - discountOnMRP + deliveryCharge
            totalPrice.text = totalAmount.toString()

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
        with(toolbarBinding) {
            // set title
            tvTitle.text = getString(R.string.payment)

            // back button click
            imgBack.setOnClickListener {
                findNavController().popBackStack()
            }
        }
    }

    override fun getFragmentBinding(
        inflater: LayoutInflater,
        container: ViewGroup?
    ) = FragmentPaymentModeBinding.inflate(inflater, container, false)

    override fun getViewModel() = ProductViewModel::class.java

    override fun getFragmentRepository() =
        ProductRepository(remoteDataSource.getBaseUrl().create(ProductApi::class.java))
}