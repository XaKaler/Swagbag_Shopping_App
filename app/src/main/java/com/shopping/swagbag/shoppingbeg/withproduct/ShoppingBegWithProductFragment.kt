package com.shopping.swagbag.shoppingbeg.withproduct

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.View
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.shopping.swagbag.R
import com.shopping.swagbag.databinding.FragmentShoppingBegWithProductBinding
import com.shopping.swagbag.databinding.ToolbarWithOneMenusBinding
import com.shopping.swagbag.dummy.DummyData

class ShoppingBegWithProductFragment : Fragment(R.layout.fragment_shopping_beg_with_product), View.OnClickListener {

    private lateinit var viewBinding: FragmentShoppingBegWithProductBinding
    private lateinit var toolbarBinding: ToolbarWithOneMenusBinding

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        viewBinding = FragmentShoppingBegWithProductBinding.bind(view)
        toolbarBinding = viewBinding.include

        initViews()


    }

    private fun initViews() {
        toolbar()

        setItems()

        handleOnClick()
    }

    private fun handleOnClick() {
        with(viewBinding){
            placeOrder.setOnClickListener(this@ShoppingBegWithProductFragment)
        }
    }

    private fun setItems() {
        with(viewBinding){
            rvShoppingBegItems.apply{
                layoutManager = LinearLayoutManager(context)
                adapter = DummyData().getDummyData()?.let { ShoppingBegProductAdapter(context, it) }
            }
        }
    }

    private fun toolbar() {
        with(toolbarBinding){
            // set title
            tvTitle.text = getString(R.string.shopping_beg)

            //click listeners
            imgBack.setOnClickListener{
                findNavController().popBackStack()
            }

            imgWishlist.setOnClickListener{
                findNavController().navigate(R.id.action_shoppingBegWithProductFragment_to_wishlistWithProductFragment)
            }
        }
    }

    override fun onClick(v: View?) {
        when(v?.id){
            R.id.placeOrder -> findNavController().navigate(R.id.action_shoppingBegWithProductFragment_to_shoppingBegSelectAddressFragment)
        }
    }
}