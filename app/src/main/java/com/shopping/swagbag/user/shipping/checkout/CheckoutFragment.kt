package com.shopping.swagbag.user.shipping.checkout

import android.content.Context
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import androidx.recyclerview.widget.LinearLayoutManager
import com.google.gson.Gson
import com.google.gson.JsonObject
import com.shopping.swagbag.R
import com.shopping.swagbag.common.base.BaseFragment
import com.shopping.swagbag.databinding.FragmentPaymentModeBinding
import com.shopping.swagbag.databinding.ToolbarWithNoMenuWhiteBgBinding
import com.shopping.swagbag.main_activity.MainActivity
import com.shopping.swagbag.products.ProductRepository
import com.shopping.swagbag.products.ProductViewModel
import com.shopping.swagbag.service.RemoteDataSource
import com.shopping.swagbag.service.Resource
import com.shopping.swagbag.service.apis.PaymentApi
import com.shopping.swagbag.service.apis.PaymentDataSource
import com.shopping.swagbag.service.apis.ProductApi
import com.shopping.swagbag.settings.PaymentViewModelFactory
import com.shopping.swagbag.user.order.user_details.AllAddressModel
import com.shopping.swagbag.user.shipping.checkout.payment.PaymentRepository
import com.shopping.swagbag.user.shipping.checkout.payment.PaymentViewModel
import com.shopping.swagbag.user.shipping.checkout.payment.SampleRequestModel
import com.shopping.swagbag.user.shoppingbeg.withproduct.GetCartModel
import com.shopping.swagbag.user.wallet.WalletModel
import com.shopping.swagbag.utils.AppUtils
import kotlin.properties.Delegates


class CheckoutFragment : BaseFragment<
        FragmentPaymentModeBinding,
        ProductViewModel,
        ProductRepository>(FragmentPaymentModeBinding::inflate) {

    private lateinit var toolbarBinding: ToolbarWithNoMenuWhiteBgBinding
    private lateinit var paymentViewModel: PaymentViewModel
    private var paymentMode: String = ""
    private lateinit var address: AllAddressModel.Result
    private lateinit var cartData: GetCartModel
    private val userId by lazy { context?.let { AppUtils(it).getUserId() } }
    private lateinit var mainActivity: MainActivity
    private lateinit var walletData: WalletModel
    private var tax by Delegates.notNull<Int>()
    private var totalAmount: Float = 0.0F
    private var walletAmount: Int = 0
    private var couponAmount: Int = 0
    private var isCouponsApplied = false

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

        initViews()
    }

    private fun initViews() {
        //initialize payment view model
        val repository =
            PaymentRepository(PaymentDataSource().getBaseUrl().create(PaymentApi::class.java))
        paymentViewModel = ViewModelProvider(
            this@CheckoutFragment,
            PaymentViewModelFactory(repository)
        )[PaymentViewModel::class.java]

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
            "Online" -> {
                //checkout(paymentMode)
            }
            else -> toast("Select payment option")
        }
    }

    private fun checkout(gateway: String) {
        val cartDataJson = Gson().toJson(cartData)
        val finalPrice =
            if (viewBinding.wallet.isChecked && isCouponsApplied)
                totalAmount + walletAmount + couponAmount
            else if (viewBinding.wallet.isChecked && !isCouponsApplied)
                totalAmount + walletAmount
            else if (!viewBinding.wallet.isChecked && isCouponsApplied)
                totalAmount + couponAmount
            else
                totalAmount

        Log.e(
            "checkout",
            "userid : $userId\n" +
                    "address : $address\n" +
                    "cart data : $cartDataJson\n" +
                    "finalPrice : $finalPrice",
        )

        userId?.let { userId ->
            viewModel.checkout("Y", userId, address.id,finalPrice.toString(), address.id, cartDataJson)
                .observe(viewLifecycleOwner) {
                    when (it) {
                        is Resource.Loading -> showLoading()

                        is Resource.Success -> {
                            stopShowingLoading()

                            val orderId = it.value.orderId

                            if (gateway == "COD")
                                checkoutConfirm(orderId, gateway, "")
                            else
                                payment()
                            //checkoutConfirm(orderId, gateway, payment())
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

    private fun payment(): String {
        var transactionId = ""

        val registration = SampleRequestModel.Registration(
            "2.00",
            "Web",
            "AED",
            "Demo Merchant",
            "7210055701315195",
            "Paybill",
            "Comtrust@20182018",
            "https://localhost/callbackURL",
            "0000",
            "0000",
            "CPT:Y;VCC:Y;",
            "Demo_fY9c"
        )

        val sampleRequestModel = SampleRequestModel(registration)

        paymentViewModel.paymentRequest(sampleRequestModel).observe(viewLifecycleOwner) {
            when (it) {
                is Resource.Success -> {
                    Log.e("payment", "payment success: $it")
                    transactionId = it.value.transaction.transactionID
                }

                is Resource.Failure -> {
                    Log.e("payment", "payment fail : $it")
                }
            }
        }

        return transactionId
    }

    private fun checkoutConfirm(orderId: String, gateway: String, transactionId: String) {
        viewModel.checkoutConfirm(orderId, gateway, transactionId).observe(viewLifecycleOwner) {
            when (it) {
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

        walletData = mainActivity.getWalletResult()
        tax = mainActivity.getSettingResult("TAX (Percentage)").toInt()

        setData()
    }

    private fun setData() {
        with(viewBinding) {
            //set products
            rvCheckoutProducts.apply {
                layoutManager = LinearLayoutManager(context)
                adapter = cartData.result?.let { CheckoutProductAdapter(context, it) }
            }

            //set price details
            val totalCartItem = cartData.result?.size
            itemCount.text = totalCartItem.toString()

            var totalMRP = 0.0F
            val discountOnMRP = 0
            val deliveryCharge = 0

            for (cart in cartData.result!!) {
                totalMRP += cart.price * cart.quantity
                //discountOnMRP += cart.product.discountedPrice
            }

            discountPrice.text = discountOnMRP.toString()
            subTotal.text = totalMRP.toString()

            //set tax
            Log.e("tax", "setData: $tax").toString()
            val taxText = "Tax($tax%)"
            taxPercentage.text = taxText
            val taxAmount = totalMRP * tax / 100
            taxPrice.text = taxAmount.toString()

            // set total amount
            totalAmount = totalMRP - discountOnMRP + deliveryCharge + taxAmount
            totalPrice.text = totalAmount.toString()

            //set walletData
            walletAmount = walletData.balance
            val walletText = "Wallet(${context?.getString(R.string.Rs)} $walletAmount)"
            wallet.text = walletText
            //check user want to use wallet balance or not
            wallet.setOnCheckedChangeListener { _, isChecked ->
                if (isChecked) {
                    val totalWithWallet = totalAmount - walletAmount
                    Log.e("wallet", "setData: ${wallet.isChecked}")
                    totalPrice.text = totalWithWallet.toString()

                    val viewDetailsBtnTxt =
                        "${getString(R.string.Rs)}$totalWithWallet \nView Details"
                    viewDetails.text = viewDetailsBtnTxt
                } else {
                    Log.e("wallet", "setData: ${wallet.isChecked}")
                    totalPrice.text = totalAmount.toString()

                    val viewDetailsBtnTxt = "${getString(R.string.Rs)}$totalAmount \nView Details"
                    viewDetails.text = viewDetailsBtnTxt
                }
            }

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

            //view details
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