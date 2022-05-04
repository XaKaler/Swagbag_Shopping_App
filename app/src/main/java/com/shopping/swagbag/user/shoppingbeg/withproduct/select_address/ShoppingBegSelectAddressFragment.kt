package com.shopping.swagbag.user.shoppingbeg.withproduct.select_address

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import androidx.recyclerview.widget.LinearLayoutManager
import com.google.gson.Gson
import com.shopping.swagbag.R
import com.shopping.swagbag.common.RecycleViewItemClick
import com.shopping.swagbag.common.base.BaseFragment
import com.shopping.swagbag.databinding.FragmentShoppingBegSelectAddressBinding
import com.shopping.swagbag.databinding.ToolbarWithNoMenuWhiteBgBinding
import com.shopping.swagbag.service.Resource
import com.shopping.swagbag.service.apis.UserApi
import com.shopping.swagbag.user.auth.UserRepository
import com.shopping.swagbag.user.auth.UserViewModel
import com.shopping.swagbag.user.address.address_list.AllAddressModel
import com.shopping.swagbag.user.shoppingbeg.withproduct.GetCartModel
import com.shopping.swagbag.user.shoppingbeg.withproduct.UserAddressAdapter
import com.shopping.swagbag.utils.AppUtils

class ShoppingBegSelectAddressFragment :
    BaseFragment<FragmentShoppingBegSelectAddressBinding,
            UserViewModel,
            UserRepository>(FragmentShoppingBegSelectAddressBinding::inflate),
    View.OnClickListener {

    private lateinit var toolbarBinding: ToolbarWithNoMenuWhiteBgBinding
    private lateinit var addressList: List<AllAddressModel.Result>
    private lateinit var selectedAddress: AllAddressModel.Result

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        toolbarBinding = viewBinding.include

        initViews()
    }

    private fun initViews() {
        with(viewBinding) {
            btnContinue.setOnClickListener{moveToPayment()}
            termsOfUse.setOnClickListener(this@ShoppingBegSelectAddressFragment)
            privacyPolicy.setOnClickListener(this@ShoppingBegSelectAddressFragment)
            btnAddAddress.setOnClickListener { findNavController().navigate(R.id.action_shoppingBegSelectAddressFragment_to_addUserDetailsFragment) }
        }

        getAddresses()

        setToolbar()

        setDeliveryEstimate()
    }

    private fun moveToPayment() {
        // send cart data that we get from cart screen
        val args: ShoppingBegSelectAddressFragmentArgs by navArgs()
        val cartData: GetCartModel = args.cartData

        if (this::selectedAddress.isInitialized){
            val action =
                ShoppingBegSelectAddressFragmentDirections.actionShoppingBegSelectAddressFragmentToPaymentModeFragment(
                    cartData,
                    selectedAddress
                )
            findNavController().navigate(action)
        }
        else
            toast("Select address")

    }

    private fun getAddresses() {
        val appUtils = context?.let { AppUtils(it) }
        val userId = appUtils?.getUserId()

        if (userId != null) {
            viewModel.allAddress(userId).observe(viewLifecycleOwner) {
                when (it) {
                    is Resource.Loading -> showLoading()

                    is Resource.Success -> {
                        stopShowingLoading()

                        addressList = it.value.result!!

                        if (addressList.isEmpty()) {
                            viewBinding.noAddress.visibility = View.VISIBLE
                            viewBinding.rvAddress.visibility = View.GONE
                        } else {
                            viewBinding.noAddress.visibility = View.GONE
                            viewBinding.rvAddress.visibility = View.VISIBLE

                            setAddresses(addressList)
                        }

                        setAddresses(it.value.result)
                    }

                    is Resource.Failure -> {
                        toast("try again")
                        stopShowingLoading()
                        findNavController().popBackStack()
                    }
                }
            }
        }
    }

    private fun setDeliveryEstimate() {
        with(viewBinding) {
            productImage.setImageResource(R.drawable.mens)
        }
    }

    private fun setAddresses(addresses: List<AllAddressModel.Result>) {
        with(viewBinding) {
            rvAddress.apply {
                layoutManager = LinearLayoutManager(context)
                adapter =
                    UserAddressAdapter(context, addresses, object: RecycleViewItemClick{
                        override fun onItemClickWithName(name: String, position: Int) {
                            when (name) {
                                "select" -> {
                                    selectedAddress = addressList[position]
                                    Log.e("TAG", "onItemClickWithName: $selectedAddress")
                                }

                                "edit" -> {
                                    val editAddress = addressList[position]
                                    Log.e("address", "edit address: $editAddress", )

                                    val action =
                                        ShoppingBegSelectAddressFragmentDirections.actionShoppingBegSelectAddressFragmentToAddUserDetailFragment(
                                            Gson().toJson(addressList[position], AllAddressModel.Result::class.java)
                                        )
                                    findNavController().navigate(action)
                                }
                            }
                        }
                    })
            }
        }
    }

    private fun setToolbar() {
        with(toolbarBinding) {
            // set title
            tvTitle.text = getString(R.string.shopping_beg)

            // back button click
            imgBack.setOnClickListener {
                findNavController().popBackStack()
            }
        }
    }

    override fun onClick(v: View?) {
        when (v?.id) {
            R.id.termsOfUse -> findNavController().navigate(R.id.action_shoppingBegSelectAddressFragment_to_termsOfUsesFragment)
            R.id.privacyPolicy -> findNavController().navigate(R.id.action_shoppingBegSelectAddressFragment_to_privacyPolicyFragment)
        }
    }

    override fun getFragmentBinding(
        inflater: LayoutInflater,
        container: ViewGroup?
    ) = FragmentShoppingBegSelectAddressBinding.inflate(inflater, container, false)

    override fun getViewModel() = UserViewModel::class.java

    override fun getFragmentRepository() =
        UserRepository(remoteDataSource.getBaseUrl().create(UserApi::class.java))

}
