package com.shopping.swagbag.user.shipping.order

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
import com.shopping.swagbag.common.adapter.OrderItemDetailAdapter
import com.shopping.swagbag.common.base.BaseFragment
import com.shopping.swagbag.common.base.GeneralFunction
import com.shopping.swagbag.databinding.FragmentViewOrderDetailsBinding
import com.shopping.swagbag.databinding.ToolbarWithNoMenuWhiteBgBinding
import com.shopping.swagbag.service.apis.ProductApi
import com.shopping.swagbag.products.ProductRepository
import com.shopping.swagbag.products.ProductViewModel
import com.shopping.swagbag.user.order.with_items.OrderModel


class ViewOrderDetails : BaseFragment<
        FragmentViewOrderDetailsBinding,
        ProductViewModel,
        ProductRepository>(FragmentViewOrderDetailsBinding::inflate), RecycleViewItemClick {

    private lateinit var toolbarBinding: ToolbarWithNoMenuWhiteBgBinding
    private lateinit var orderItems: OrderModel.OrderModelItem

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        toolbarBinding = viewBinding.include

        initViews()


    }

    private fun initViews() {
        with(viewBinding){
            getInvoice.setOnClickListener{
                findNavController().navigate(R.id.action_viewOrderDetails_to_getInvoiceFragment)
            }
            //val orderModel = OrderModel.fromString()
        }

        getArgument()

        setToolbar()
    }

    private fun getArgument() {
        val args: ViewOrderDetailsArgs by navArgs()
        orderItems = Gson().fromJson(args.stringProducts, OrderModel.OrderModelItem::class.java)

        Log.e("OrderItems", "getArgument in view order details screen: $orderItems", )

        setOrderItems(orderItems)
        setData(orderItems)
    }

    private fun setData(orderItems: OrderModel.OrderModelItem) {
        with(viewBinding) {
            placedOn.text = GeneralFunction.convertServerDateToUserTimeZoneTask(orderItems.createdDate)
            orderNo.text = orderItems.orderid
            totalPrice.text = orderItems.finalprice.toString()
            price.text = orderItems.finalprice.toString()
            mobile.text = orderItems.address.contactMobile
            Username.text = orderItems.address.contactName
            address.text = orderItems.address.address

            if (orderItems.gateway == "COD")
                paymentMode.text = "Cash on delivery"
            else
                paymentMode.text = "Online"
        }
    }

    private fun setOrderItems(products: OrderModel.OrderModelItem) {
        with(viewBinding) {
            rvProductOrderDetails.apply {
                layoutManager = LinearLayoutManager(context)
                adapter = OrderItemDetailAdapter(context, products, this@ViewOrderDetails)
            }
        }
    }

    private fun setToolbar() {
        with(toolbarBinding) {
            // set title
            tvTitle.text = getString(R.string.order_details)

            // back button click
            imgBack.setOnClickListener {
                findNavController().popBackStack()
            }
        }
    }

    override fun onItemClickWithName(name: String, position: Int) {
        when(name){
            "detail" -> {
                val action = ViewOrderDetailsDirections.actionGlobalProductDetailsFragment(orderItems.products[position].productname)
                findNavController().navigate(action)
            }
        }
    }

    override fun getFragmentBinding(
        inflater: LayoutInflater,
        container: ViewGroup?
    ) = FragmentViewOrderDetailsBinding.inflate(inflater, container, false)

    override fun getViewModel() = ProductViewModel::class.java

    override fun getFragmentRepository() =
        ProductRepository(remoteDataSource.getBaseUrl().create(ProductApi::class.java))

}