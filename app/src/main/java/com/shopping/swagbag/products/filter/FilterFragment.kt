package com.shopping.swagbag.products.filter

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
import com.shopping.swagbag.products.filter.filter_brand.FilterBrandFragment
import com.shopping.swagbag.products.filter.filter_category.FilterCategoryFragment
import com.shopping.swagbag.products.filter.filter_color.FilterColorFragment
import com.shopping.swagbag.products.filter.filter_price.FilterPriceFragment
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

            }
            close.setOnClickListener{
                findNavController().popBackStack()
            }
        }
    }

    private fun setFirstFilter() {

        val transaction: FragmentTransaction? = fragmentManager?.beginTransaction()
        transaction?.add(R.id.filterFrameLayout, FilterSizeFragment())
        transaction?.commit()

        //fragmentManager.beginTransaction().attach()
    }

    private fun setFilterList() {
        with(viewBinding){
            rvFilterList.apply {
                layoutManager = LinearLayoutManager(context)
                //adapter = ProductFilterAdapter(context, DummyData().getProductFilter(), this@FilterFragment)
            }
        }
    }

    override fun onItemClick(name: String, position: Int) {
        when(name){
            "Size" -> {
                val transaction: FragmentTransaction? = fragmentManager?.beginTransaction()
                transaction?.replace(R.id.filterFrameLayout, FilterSizeFragment())
                transaction?.commit()}
            "Color" -> {
                val transaction: FragmentTransaction? = fragmentManager?.beginTransaction()
                transaction?.replace(R.id.filterFrameLayout, FilterColorFragment())
                transaction?.commit()}
            "Brand" -> {
                val transaction: FragmentTransaction? = fragmentManager?.beginTransaction()
                transaction?.replace(R.id.filterFrameLayout, FilterBrandFragment())
                transaction?.commit()}
            "Category" -> {
                val transaction: FragmentTransaction? = fragmentManager?.beginTransaction()
                transaction?.replace(R.id.filterFrameLayout, FilterCategoryFragment())
                transaction?.commit()}
            "Price Range" -> {
                val transaction: FragmentTransaction? = fragmentManager?.beginTransaction()
                transaction?.replace(R.id.filterFrameLayout, FilterPriceFragment())
                transaction?.commit()}
        }
    }

    override fun onItemClickWithView(position: Int, view: View) {
    }

}