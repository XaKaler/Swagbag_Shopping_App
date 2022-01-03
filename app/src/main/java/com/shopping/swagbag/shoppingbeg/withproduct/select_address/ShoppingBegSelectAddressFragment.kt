package com.shopping.swagbag.shoppingbeg.withproduct.select_address

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.bumptech.glide.Glide
import com.shopping.swagbag.R
import com.shopping.swagbag.databinding.FragmentShoppingBegSelectAddressBinding
import com.shopping.swagbag.databinding.FragmentShoppingBegWithProductBinding
import com.shopping.swagbag.databinding.ToolbarWithNoMenuWhiteBgBinding
import com.shopping.swagbag.databinding.ToolbarWithOneMenusBinding
import com.shopping.swagbag.dummy.DummyData
import com.shopping.swagbag.shoppingbeg.withproduct.UserAddressAdapter

class ShoppingBegSelectAddressFragment : Fragment(R.layout.fragment_shopping_beg_select_address) {

    private lateinit var viewBinding: FragmentShoppingBegSelectAddressBinding
    private lateinit var toolbarBinding: ToolbarWithNoMenuWhiteBgBinding

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        viewBinding = FragmentShoppingBegSelectAddressBinding.bind(view)
        toolbarBinding = viewBinding.include

        initViews()


    }

    private fun initViews() {
        setToolbar()

        setAddresses()

        setDeliveryEstimate()
    }

    private fun setDeliveryEstimate() {
        with(viewBinding){
            productImage.setImageResource(R.drawable.mens)
        }
    }

    private fun setAddresses() {

        with(viewBinding){


            rvAddress.apply {
                layoutManager = LinearLayoutManager(context)
                adapter = UserAddressAdapter(context, DummyData().getUserAddress())
            }
        }
    }

    private fun setToolbar() {
        with(toolbarBinding){
            // set title
            tvTitle.text = getString(R.string.shopping_beg)

            // back button click
            imgBack.setOnClickListener{
                findNavController().popBackStack()
            }
        }
    }
}