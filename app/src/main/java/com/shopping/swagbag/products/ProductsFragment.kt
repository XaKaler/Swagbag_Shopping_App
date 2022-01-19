package com.shopping.swagbag.products

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import com.shopping.swagbag.R
import com.shopping.swagbag.databinding.FragmentCreatePasswordBinding
import com.shopping.swagbag.databinding.FragmentProductsBinding
import com.shopping.swagbag.databinding.ToolbarWithNoMenuBinding
import com.shopping.swagbag.databinding.ToolbarWithThreeMenusBinding
import com.shopping.swagbag.dummy.DummyData


class ProductsFragment : Fragment(R.layout.fragment_products), View.OnClickListener {

    private lateinit var viewBinding: FragmentProductsBinding
    private lateinit var toolbarBinding: ToolbarWithThreeMenusBinding

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        viewBinding = FragmentProductsBinding.bind(view)
        toolbarBinding = viewBinding.include

        initViews()


    }

    private fun initViews() {
        setToolbar()

        setProducts()

        // click listeners
        with(viewBinding){
            tvSortBy.setOnClickListener(this@ProductsFragment)
        }
    }

    private fun setProducts() {
        with(viewBinding){
            rvProducts.apply{
                layoutManager = GridLayoutManager(context, 2)
                adapter = DummyData().getDummyData()?.let { ProductAdapter(context, it) }
            }
        }
    }

    private fun setToolbar() {
        with(toolbarBinding){
            // set title
            tvTitle.text = getString(R.string.products)

            // back button click
            imgBack.setOnClickListener{
                findNavController().popBackStack()
            }

            imgSearch.setOnClickListener{
                findNavController().navigate(R.id.action_productsFragment_to_searchFragment)
            }

            imgWishlist.setOnClickListener{
                findNavController().navigate(R.id.action_productsFragment_to_wishlistWithProductFragment)
            }

            imgCart.setOnClickListener{
                findNavController().navigate(R.id.action_productsFragment_to_shoppingBegWithoutProductFragment)
            }

        }
    }

    override fun onClick(v: View?) {
        when(v?.id){
            R.id.tvSortBy->{
                findNavController().navigate(R.id.action_productsFragment_to_filterFragment)
            }
        }
    }
}