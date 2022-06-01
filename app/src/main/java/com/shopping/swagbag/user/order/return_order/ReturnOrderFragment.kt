package com.shopping.swagbag.user.order.return_order

import android.content.Context
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.AdapterView
import android.widget.ArrayAdapter
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
import com.shopping.swagbag.main_activity.MainActivity
import com.shopping.swagbag.products.ProductRepository
import com.shopping.swagbag.products.ProductViewModel
import com.shopping.swagbag.service.Resource
import com.shopping.swagbag.service.apis.ProductApi
import com.shopping.swagbag.user.order.with_items.OrderModel

class ReturnOrderFragment : BaseFragment<FragmentReturnOrderBinding,
        ProductViewModel,
        ProductRepository>(FragmentReturnOrderBinding::inflate),
    AdapterView.OnItemSelectedListener {

    private lateinit var orderItems: OrderModel.OrderModelItem
    private var selectedProductIndex = ArrayList<Int>()
    private lateinit var productAdapter: ReturnOrderItemsAdapter
    private var reason: String = ""
    private lateinit var mainActivity: MainActivity
    private val returnReason = ArrayList<String>()

    override fun onAttach(context: Context) {
        super.onAttach(context)
        mainActivity = context as MainActivity
    }

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
                    toast("Select at least one product")
                else if(reason == "")
                    toast("Select a reason")
                else if(reason == "Others, pls specify"){
                    if(viewBinding.edtOtherReason.text.toString() == "")
                        toast("Please specify other reason")
                    else {
                        reason = viewBinding.edtOtherReason.text.toString()
                        returnProduct()
                    }
                }
                else{
                    returnProduct()
                }
            }
        }

        setToolbar()

        getArgument()
    }

    private fun returnProduct() {
            val orderId = orderItems.id
            val products = ArrayList<String>()

            //collect all product id's
            for (singleIndex in selectedProductIndex) {
                products.add(orderItems.products[singleIndex].id)
            }

            Log.e("return", "orderId : $orderId \n" +
                    "products : $products\n" +
                    "reason : $reason")

            // api call for return product
            viewModel.returnOrder(orderId, products.joinToString(","), reason).observe(viewLifecycleOwner){
                when(it){
                    is Resource.Loading -> showLoading()

                    is Resource.Success -> {
                        stopShowingLoading()
                        toast(it.value.status)
                        findNavController().navigate(R.id.action_global_home2)
                    }

                    is Resource.Failure -> {
                        stopShowingLoading()
                        tryAgain()
                    }
                }
            }
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

        //set spinner with return reasons
        //get reasons from setting api that we call in main activity
        for(singleItem in mainActivity.getReturnReason()){
            returnReason.add(singleItem.name)
        }

        val arrayAdapter =
            context?.let { ArrayAdapter(it, android.R.layout.simple_spinner_item, returnReason) }
        arrayAdapter?.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)

        with(viewBinding){
            reasonSpinner.adapter = arrayAdapter
        }

        viewBinding.reasonSpinner.onItemSelectedListener = this@ReturnOrderFragment
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

    override fun onItemSelected(parent: AdapterView<*>?, view: View?, position: Int, id: Long) {
        reason = returnReason[position]
        if(reason == "Others, pls specify")
            viewBinding.edtOtherReason.visibility = View.VISIBLE
    }

    override fun onNothingSelected(parent: AdapterView<*>?) {
        TODO("Not yet implemented")
    }


}