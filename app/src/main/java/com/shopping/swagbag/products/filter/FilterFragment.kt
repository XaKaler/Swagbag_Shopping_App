package com.shopping.swagbag.products.filter

import android.app.Activity
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.View
import androidx.fragment.app.FragmentTransaction
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.shopping.swagbag.R
import com.shopping.swagbag.common.RecycleItemClick
import com.shopping.swagbag.databinding.FragmentFilterBinding
import com.shopping.swagbag.dummy.DummyData
import com.shopping.swagbag.products.filter.filter_size.FilterSizeFragment


class FilterFragment : Fragment(R.layout.fragment_filter), RecycleItemClick {

    private lateinit var viewBinding: FragmentFilterBinding

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        viewBinding = FragmentFilterBinding.bind(view)

        initViews()
    }

    private fun initViews() {
        setFilterList()
        setFirstFilter()

        with(viewBinding){
            apply.setOnClickListener{
                findNavController().navigate(R.id.action_filterFragment_to_productsFragment)
            }
            close.setOnClickListener{
                findNavController().navigate(R.id.action_filterFragment_to_productsFragment)
            }
        }
    }

    private fun setFirstFilter() {
        val activity: Activity = context as Activity

        val transaction: FragmentTransaction? = fragmentManager?.beginTransaction()
        transaction?.add(R.id.filterFrameLayout, FilterSizeFragment())
        transaction?.commit()

        //fragmentManager.beginTransaction().attach()
    }

    private fun setFilterList() {
        with(viewBinding){
            rvFilterList.apply {
                layoutManager = LinearLayoutManager(context)
                adapter = ProductFilterAdapter(context, DummyData().getProductFilter(), this@FilterFragment)
            }
        }
    }

    override fun onItemClick(name: String, position: Int) {
        when(name){
            "Size" -> {}
            "Color" -> {}
            "Brand" -> {}
            "Category" -> {}
            "Price Range" -> {}
        }
    }

}