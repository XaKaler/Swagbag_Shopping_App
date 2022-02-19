package com.shopping.swagbag.user.shoppingbeg.withoutproduct

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.View
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.GridLayoutManager
import com.shopping.swagbag.R
import com.shopping.swagbag.common.adapter.FeatureProductAdapter
import com.shopping.swagbag.databinding.FragmentShoppingBegWithoutProductBinding
import com.shopping.swagbag.databinding.ToolbarWithOneMenusBinding
import com.shopping.swagbag.dummy.DummyData


class ShoppingBegWithoutProductFragment : Fragment(R.layout.fragment_shopping_beg_without_product) {

    private lateinit var viewBinding: FragmentShoppingBegWithoutProductBinding
    private lateinit var toolbarBinding: ToolbarWithOneMenusBinding

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        viewBinding = FragmentShoppingBegWithoutProductBinding.bind(view)
        toolbarBinding = viewBinding.include

        initViews()


    }

    private fun initViews() {
        toolbar()

        setItemThatViewedByUser()
    }

    private fun setItemThatViewedByUser() {
        with(viewBinding){
            rvItemViewed.apply {
                layoutManager = GridLayoutManager(context, 2)
                adapter = DummyData().getDummyData()?.let { FeatureProductAdapter(context, it) }
            }
        }
    }

    private fun toolbar() {
        with(toolbarBinding){
            // set title
            tvTitle.text = getString(R.string.shopping_beg)

            // click listeners
            imgBack.setOnClickListener{
                findNavController().popBackStack()
            }

            imgWishlist.setOnClickListener{
                findNavController().navigate(R.id.action_shoppingBegWithoutProductFragment_to_wishlistWithoutProductFragment)
            }

        }
    }

}