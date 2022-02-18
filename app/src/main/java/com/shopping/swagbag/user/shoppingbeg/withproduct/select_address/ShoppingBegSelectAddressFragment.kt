package com.shopping.swagbag.user.shoppingbeg.withproduct.select_address

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.View
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.shopping.swagbag.R
import com.shopping.swagbag.databinding.FragmentShoppingBegSelectAddressBinding
import com.shopping.swagbag.databinding.ToolbarWithNoMenuWhiteBgBinding
import com.shopping.swagbag.dummy.DummyData
import com.shopping.swagbag.user.shoppingbeg.withproduct.UserAddressAdapter

class ShoppingBegSelectAddressFragment : Fragment(R.layout.fragment_shopping_beg_select_address), View.OnClickListener {

    private lateinit var viewBinding: FragmentShoppingBegSelectAddressBinding
    private lateinit var toolbarBinding: ToolbarWithNoMenuWhiteBgBinding

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        viewBinding = FragmentShoppingBegSelectAddressBinding.bind(view)
        toolbarBinding = viewBinding.include

        initViews()


    }

    private fun initViews() {
        with(viewBinding){
            btnContinue.setOnClickListener(this@ShoppingBegSelectAddressFragment)
            termsOfUse.setOnClickListener(this@ShoppingBegSelectAddressFragment)
            privacyPolicy.setOnClickListener(this@ShoppingBegSelectAddressFragment)
        }

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

    override fun onClick(v: View?) {
        when(v?.id){
            R.id.termsOfUse -> findNavController().navigate(R.id.action_shoppingBegSelectAddressFragment_to_termsOfUsesFragment)
            R.id.btnContinue -> findNavController().navigate(R.id.action_shoppingBegSelectAddressFragment_to_paymentModeFragment)
            R.id.privacyPolicy -> findNavController().navigate(R.id.action_shoppingBegSelectAddressFragment_to_privacyPolicyFragment)
        }
    }
}