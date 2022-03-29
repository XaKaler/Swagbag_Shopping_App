package com.shopping.swagbag.user.order.with_items

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.shopping.swagbag.R
import com.shopping.swagbag.common.base.BaseFragment
import com.shopping.swagbag.databinding.FragmentOrderWithItemsBinding
import com.shopping.swagbag.databinding.ToolbarWithNoMenuWhiteBgBinding
import com.shopping.swagbag.dummy.DummyData
import com.shopping.swagbag.products.ProductApi
import com.shopping.swagbag.products.ProductRepository
import com.shopping.swagbag.products.ProductViewModel
import com.shopping.swagbag.service.Resource
import com.shopping.swagbag.utils.AppUtils

class OrderWithItemsFragment : BaseFragment<
        FragmentOrderWithItemsBinding,
        ProductViewModel,
        ProductRepository>(FragmentOrderWithItemsBinding::inflate) {


    private lateinit var toolbarBinding: ToolbarWithNoMenuWhiteBgBinding

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        toolbarBinding = viewBinding.include

        initViews()


    }

    private fun initViews() {
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

                            //setOrderItems(it)
                            Log.e("order", "getOrderItems: ${it.value}", )
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

    private fun setOrderItems(orderList: OrderModel.OrderModelItem) {
        with(viewBinding) {
            rvOrderItems.apply {
                layoutManager = LinearLayoutManager(context)
                adapter = OrderItemsAdapter(context, orderList.products)
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
}