package com.shopping.swagbag.order.with_items

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.shopping.swagbag.R
import com.shopping.swagbag.databinding.FragmentAddUserDetailsBinding
import com.shopping.swagbag.databinding.FragmentOrderWithItemsBinding
import com.shopping.swagbag.databinding.ToolbarWithNoMenuWhiteBgBinding
import com.shopping.swagbag.dummy.DummyData

class OrderWithItemsFragment : Fragment(R.layout.fragment_order_with_items) {


    private lateinit var viewBinding: FragmentOrderWithItemsBinding
    private lateinit var toolbarBinding: ToolbarWithNoMenuWhiteBgBinding

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        viewBinding = FragmentOrderWithItemsBinding.bind(view)
        toolbarBinding = viewBinding.include

        initViews()


    }

    private fun initViews() {
        setOrderItems()

        setToolbar()
    }

    private fun setOrderItems() {
        with(viewBinding){
            rvOrderItems.apply{
                layoutManager = LinearLayoutManager(context)
                adapter = DummyData().getDummyData()?.let { OrderItemsAdapter(context, it) }
            }
        }
    }

    private fun setToolbar() {
        with(toolbarBinding){
            // set title
            tvTitle.text = getString(R.string.orders)

            // back button click
            imgBack.setOnClickListener{
                findNavController().popBackStack()
            }
        }
    }
}