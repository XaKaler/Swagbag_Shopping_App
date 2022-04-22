package com.shopping.swagbag.user.order.return_order

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.NavArgs
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import androidx.recyclerview.widget.LinearLayoutManager
import com.google.gson.Gson
import com.shopping.swagbag.R
import com.shopping.swagbag.common.RecycleItemClick
import com.shopping.swagbag.common.RecycleViewItemClick
import com.shopping.swagbag.common.adapter.ReturnOrderItemsAdapter
import com.shopping.swagbag.common.base.BaseFragment
import com.shopping.swagbag.databinding.FragmentReturnOrderBinding
import com.shopping.swagbag.products.ProductRepository
import com.shopping.swagbag.products.ProductViewModel
import com.shopping.swagbag.service.Resource
import com.shopping.swagbag.service.apis.ProductApi
import com.shopping.swagbag.user.order.with_items.OrderModel

class ReturnOrderFragment : BaseFragment<FragmentReturnOrderBinding,
        ProductViewModel,
        ProductRepository>(FragmentReturnOrderBinding::inflate) {

    private lateinit var orderItems: OrderModel.OrderModelItem
    private var selectedProductIndex = ArrayList<Int>()
    private lateinit var productAdapter: ReturnOrderItemsAdapter
    private var reason: String = ""

    override fun getFragmentBinding(
        inflater: LayoutInflater,
        container: ViewGroup?
    ) = FragmentReturnOrderBinding.inflate(inflater, container, false)

    override fun getViewModel() = ProductViewModel::class.java

    override fun getFragmentRepository() = ProductRepository(remoteDataSource.getBaseUrl().create(
        ProductApi::class.java))

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        initViews()
    }

    private fun initViews() {
        with(viewBinding){
            selectAll.setOnClickListener{
                for(singleIndex in 0..orderItems.products.size){
                    selectedProductIndex.add(singleIndex)
                }
            }

            btnProceed.setOnClickListener {
                if(selectedProductIndex.isEmpty())
                    toast("Select at lease one product")
                else{
                    returnProduct()
                }
            }
        }

        setToolbar()

        getArgument()
    }

    private fun returnProduct() {
        if(reason != "") {
            val orderId = orderItems.id
            val products = ArrayList<String>()

            //collect all product id's
            for (singleIndex in selectedProductIndex) {
                products.add(orderItems.products[singleIndex].id)
            }

            // api call for return product
            viewModel.returnOrder(orderId, products.toString(), reason).observe(viewLifecycleOwner){
                when(it){
                    is Resource.Loading -> showLoading()

                    is Resource.Success -> {
                        stopShowingLoading()
                        toast(it.value.status)
                    }

                    is Resource.Failure -> {
                        stopShowingLoading()
                        tryAgain()
                    }
                }
            }
        }
        else
            toast("Select a reason")
    }

    private fun getArgument() {
       val args: ReturnOrderFragmentArgs by navArgs()
        orderItems = Gson().fromJson(args.orderProducts, OrderModel.OrderModelItem::class.java)

        Log.e("order", "order items in return screen -> \n$orderItems")

        //after getting argument from order screen set data
        setData()
    }

    private fun setData() {
        //set products
        setProducts()
    }

    private fun setProducts() {
        with(viewBinding){
            rvProducts.apply{
                layoutManager = LinearLayoutManager(context)
                productAdapter = ReturnOrderItemsAdapter(context, orderItems, object: RecycleViewItemClick{
                    override fun onItemClickWithName(name: String, position: Int) {
                        when(name){
                            "product" -> {
                                if(selectedProductIndex.isNotEmpty()){
                                    for(singleIndex in selectedProductIndex){
                                        if(singleIndex != position)
                                            selectedProductIndex.add(position)
                                    }
                                }
                                else selectedProductIndex.add(position)
                            }
                        }
                    }
                })

                adapter = productAdapter
            }
        }
    }

    private fun setToolbar() {
        val toolbarBinding = viewBinding.toolbar

        with(toolbarBinding){
            tvTitle.text = "Return Products"
            imgBack.setOnClickListener { findNavController().popBackStack() }
        }
    }


}