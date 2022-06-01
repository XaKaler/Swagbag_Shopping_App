package com.shopping.swagbag.user.order.with_items

import android.content.Context
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.google.gson.Gson
import com.shopping.swagbag.R
import com.shopping.swagbag.common.RecycleViewItemClick
import com.shopping.swagbag.common.base.BaseFragment
import com.shopping.swagbag.databinding.FragmentOrderWithItemsBinding
import com.shopping.swagbag.databinding.ToolbarWithNoMenuWhiteBgBinding
import com.shopping.swagbag.main_activity.MainActivity
import com.shopping.swagbag.products.ProductRepository
import com.shopping.swagbag.products.ProductViewModel
import com.shopping.swagbag.service.Resource
import com.shopping.swagbag.service.apis.ProductApi
import com.shopping.swagbag.utils.AppUtils

class OrderFragment : BaseFragment<
        FragmentOrderWithItemsBinding,
        ProductViewModel,
        ProductRepository>(FragmentOrderWithItemsBinding::inflate){

    private lateinit var toolbarBinding: ToolbarWithNoMenuWhiteBgBinding
    private lateinit var orderProducts: OrderModel
    private lateinit var orderItemsAdapter: OrderItemsAdapter
    private lateinit var mainActivity: MainActivity

    override fun onAttach(context: Context) {
        super.onAttach(context)
        mainActivity = context as MainActivity
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        toolbarBinding = viewBinding.include

        initViews()

    }

    private fun initViews() {
        //set or get orders
        if (this::orderProducts.isInitialized) {
            if(orderProducts.isEmpty())
                showEmptyOrder()
            else
                setOrderItems()
        } else
            getOrderItems()

        setToolbar()
    }

    private fun getOrderItems() {
        val appUtils = context?.let { AppUtils(it) }
        val token = appUtils?.getUserToken()
        val userId = appUtils?.getUserId()

                viewModel.orderUser(token!!, "", userId!!).observe(viewLifecycleOwner) {
                    when(it){
                        is Resource.Loading -> showLoading()

                        is Resource.Success -> {
                            stopShowingLoading()

                            orderProducts = it.value

                            if (orderProducts.isEmpty()) {
                               showEmptyOrder()
                            } else {
                                Log.e("order", "getOrderItems: ${it.value}")
                                setOrderItems()
                            }

                        }

                        is Resource.Failure -> {
                            stopShowingLoading()
                            toast("try again ")

                            Log.e("order", "getOrderItems: $it", )

                            findNavController().popBackStack()
                        }
                    }
                }
    }

    private fun setOrderItems() {
        with(viewBinding) {
            orderWithItems.visibility = View.VISIBLE
            orderWithoutItems.visibility = View.GONE

            rvOrderItems.apply {
                layoutManager = LinearLayoutManager(context)
                orderItemsAdapter = OrderItemsAdapter(
                    context,
                    orderProducts,
                    mainActivity.getSettingResult("Cancel time (Min.)"),
                    object : RecycleViewItemClick {
                        override fun onItemClickWithName(name: String, position: Int) {
                            when (name) {
                                "view" -> {
                                    val gsonString = Gson().toJson(orderProducts[position])
                                    val action =
                                        OrderFragmentDirections.actionOrderWithItemsFragmentToViewOrderDetails(
                                            gsonString
                                        )
                                    findNavController().navigate(action)
                                }
                                "cancel" -> {
                                    cancelOrder(orderProducts[position].id, position)
                                }
                                "return" -> {
                                    val gsonString = Gson().toJson(orderProducts[position])
                                    val action = OrderFragmentDirections.actionOrderWithItemsFragmentToReturnOrderFragment(
                                        gsonString
                                    )
                                    findNavController().navigate(action)
                                }
                            }
                        }
                    }
                )
                adapter = orderItemsAdapter
            }
        }
    }

    private fun setToolbar() {
        with(toolbarBinding) {
            // set title
            tvTitle.text = getString(R.string.orders)

            // back button click
            imgBack.setOnClickListener {
                findNavController().popBackStack()
            }
        }
    }

    override fun getFragmentBinding(
        inflater: LayoutInflater,
        container: ViewGroup?
    ) = FragmentOrderWithItemsBinding.inflate(inflater, container, false)

    override fun getViewModel() = ProductViewModel::class.java

    override fun getFragmentRepository() =
        ProductRepository(remoteDataSource.getBaseUrl().create(ProductApi::class.java))

    private fun cancelOrder(orderId: String, position: Int) {
        viewModel.cancelOrder(orderId).observe(viewLifecycleOwner) {
            when (it) {
                is Resource.Loading -> showLoading()

                is Resource.Success -> {
                    stopShowingLoading()

                    toast("Order cancel successfully")

                    //update list
                    val orderList: OrderModel =
                        orderProducts
                    orderList.removeAt(position)
                    orderItemsAdapter.updateList(orderList)
                    //if size is 0 then show user to empty cart
                    if (position == 0) {
                        showEmptyOrder()
                    }
                }

                is Resource.Failure -> {
                    stopShowingLoading()

                    tryAgain()

                    Log.e("TAG", "cancelOrder: $it", )
                }
            }
        }
    }

    private fun showEmptyOrder() {
        viewBinding.orderWithItems.visibility = View.GONE
        viewBinding.orderWithoutItems.visibility = View.VISIBLE
    }
}