package com.shopping.swagbag.user.shipping.order

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.View
import androidx.navigation.fragment.findNavController
import com.shopping.swagbag.R
import com.shopping.swagbag.common.RecycleItemClickListener
import com.shopping.swagbag.databinding.FragmentViewOrderDetailsBinding
import com.shopping.swagbag.databinding.ToolbarWithNoMenuWhiteBgBinding


class ViewOrderDetails : Fragment(R.layout.fragment_view_order_details), RecycleItemClickListener {


    private lateinit var viewBinding: FragmentViewOrderDetailsBinding
    private lateinit var toolbarBinding: ToolbarWithNoMenuWhiteBgBinding

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        viewBinding = FragmentViewOrderDetailsBinding.bind(view)
        toolbarBinding = viewBinding.include

        initViews()


    }

    private fun initViews() {
        with(viewBinding){
            getInvoice.setOnClickListener{
                findNavController().navigate(R.id.action_viewOrderDetails_to_getInvoiceFragment)
            }

            getInvoice.setOnClickListener{
                findNavController().navigate(R.id.action_viewOrderDetails_to_getInvoiceFragment)
            }
        }

        setOrderItems()

        setToolbar()
    }

    private fun setOrderItems() {
        with(viewBinding){
            rvProductOrderDetails.apply{
                layoutManager = LinearLayoutManager(context)
                adapter = DummyData().getDummyData()
                    ?.let { OrderItemDetailAdapter(context, it, this@ViewOrderDetails) }
            }
        }
    }

    private fun setToolbar() {
        with(toolbarBinding){
            // set title
            tvTitle.text = getString(R.string.order_details)

            // back button click
            imgBack.setOnClickListener{
                findNavController().popBackStack()
            }
        }
    }

    override fun onSingleItemClickListener(position: Int) {
        findNavController().navigate(R.id.action_viewOrderDetails_to_viewItemDetailsFragment)
    }

    override fun itemClickWithName(name: String) {
        TODO("Not yet implemented")
    }

}