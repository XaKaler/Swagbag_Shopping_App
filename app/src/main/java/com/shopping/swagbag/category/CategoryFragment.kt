package com.shopping.swagbag.category

import android.content.Context
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.Observer
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.GridLayoutManager
import com.google.gson.Gson
import com.shopping.swagbag.R
import com.shopping.swagbag.common.GridSpaceItemDecoration
import com.shopping.swagbag.common.RecycleItemClick
import com.shopping.swagbag.common.base.BaseFragment
import com.shopping.swagbag.databinding.FragmentCategoryBinding
import com.shopping.swagbag.databinding.ToolbarWithNoMenuWhiteBgBinding
import com.shopping.swagbag.main_activity.MainActivity
import com.shopping.swagbag.products.ProductSearchParameters
import com.shopping.swagbag.service.Resource
import com.shopping.swagbag.service.apis.CategoryApi

class CategoryFragment :
    BaseFragment<FragmentCategoryBinding,
            CategoryViewModel,
            CategoryRepository>(FragmentCategoryBinding::inflate), RecycleItemClick {

    private lateinit var toolbarBinding: ToolbarWithNoMenuWhiteBgBinding
    private lateinit var mainActivity: MainActivity

    override fun onAttach(context: Context) {
        super.onAttach(context)
        mainActivity = context as MainActivity
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initViews()
    }


    override fun getFragmentBinding(
        inflater: LayoutInflater,
        container: ViewGroup?
    ): FragmentCategoryBinding {
        return FragmentCategoryBinding.inflate(layoutInflater, container, false)
    }

    override fun getViewModel() = CategoryViewModel::class.java

    override fun getFragmentRepository() =
        CategoryRepository(remoteDataSource.getBaseUrl().create(CategoryApi::class.java))


    private fun initViews() {
        toolbarBinding = viewBinding.include

        setToolbar()

        setCategoryData()
    }
    private fun setCategoryData() {
        val masterCategories = mainActivity.getMasterCategories()
        with(viewBinding) {
            rvCategory.apply {
                layoutManager = GridLayoutManager(context, 2)
                addItemDecoration(GridSpaceItemDecoration(5))
                adapter = CategoryAdapter(context, masterCategories, this@CategoryFragment)
            }
        }
    }

    private fun setToolbar() {
        with(toolbarBinding) {
            // set title
            tvTitle.text = getString(R.string.category)

            // back button click
            imgBack.setOnClickListener {
                findNavController().popBackStack()
            }
        }
    }

    override fun onItemClick(name: String, position: Int) {
        val productSearchParameters =
            ProductSearchParameters("", "", "", "", "", "", "", name, "")

        val action = CategoryFragmentDirections.actionCategoryFragmentToProductsFragment(
            Gson().toJson(
                productSearchParameters,
                ProductSearchParameters::class.java
            )
        )
        findNavController().navigate(action)
    }

    override fun onItemClickWithView(position: Int, view: View) {
        TODO("Not yet implemented")
    }

}