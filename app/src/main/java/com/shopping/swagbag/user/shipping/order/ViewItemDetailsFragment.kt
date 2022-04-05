package com.shopping.swagbag.user.shipping.order

import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import com.shopping.swagbag.R
import com.shopping.swagbag.databinding.FragmentViewItemDetailsBinding
import com.shopping.swagbag.databinding.ToolbarWithNoMenuWhiteBgBinding
import com.shopping.swagbag.user.order.with_items.OrderModel

class ViewItemDetailsFragment : Fragment(R.layout.fragment_view_item_details) {

    private lateinit var viewBinding: FragmentViewItemDetailsBinding
    private lateinit var toolbarBinding: ToolbarWithNoMenuWhiteBgBinding
    private lateinit var product: OrderModel.OrderModelItem

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        viewBinding = FragmentViewItemDetailsBinding.bind(view)
        toolbarBinding = viewBinding.include
        initViews()

    }

    private fun initViews() {
        with(viewBinding){
            help.setOnClickListener{
                val action = ViewItemDetailsFragmentDirections.actionViewItemDetailsFragmentToHelpCenterWithOrderFragment(product)
                findNavController().navigate(action)
            }
        }

        getArgument()

        setToolbar()
    }

    private fun getArgument() {
        val args : ViewItemDetailsFragmentArgs by navArgs()
        product = args.product
        setProductDetails(product)
    }

    private fun setProductDetails(product: OrderModel.OrderModelItem) {
        with(viewBinding){
            context?.let {
                /*Glide.with(it)
                    .load(product.products?.get(0)?.product?.file!![0].location)
                    .error(R.drawable.ic_launcher_foreground)
                    .placeholder(R.drawable.ic_swagbug_logo)
                    .into(productImage)*/
            }

            productName.text = product.products!![0].productname
            productName2.text = product.products[0].productname/*
            productDetails.text = product.products[0].product.shortDesc
            productDetails2.text = product.products[0].product.desc
            productPrice.text = product.products[0].product.price.toString()*/
            userName.text = product.address?.contactName
            userMobile.text = product.address?.contactMobile
            userAddress.text = product.address?.address
            orderId.text = product.orderid

        }
    }

    private fun setToolbar() {
        with(toolbarBinding) {
            // set title
            tvTitle.text = getString(R.string.item_details)

            // back button click
            imgBack.setOnClickListener {
                findNavController().popBackStack()
            }
        }
    }

}