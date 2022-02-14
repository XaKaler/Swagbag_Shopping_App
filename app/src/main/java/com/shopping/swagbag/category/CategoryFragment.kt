package com.shopping.swagbag.category

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.GridLayoutManager
import com.shopping.swagbag.R
import com.shopping.swagbag.common.GridSpaceItemDecoration
import com.shopping.swagbag.common.RecycleItemClick
import com.shopping.swagbag.common.base.BaseFragment
import com.shopping.swagbag.common.base.ViewModelFactory
import com.shopping.swagbag.databinding.FragmentCategoryBinding
import com.shopping.swagbag.databinding.ToolbarWithNoMenuWhiteBgBinding
import com.shopping.swagbag.products.ProductApi
import com.shopping.swagbag.products.ProductRepository
import com.shopping.swagbag.products.ProductViewModel
import com.shopping.swagbag.service.Resource

class CategoryFragment :
    BaseFragment<FragmentCategoryBinding,
            CategoryViewModel,
            CategoryRepository>(FragmentCategoryBinding::inflate), RecycleItemClick {

    private lateinit var toolbarBinding: ToolbarWithNoMenuWhiteBgBinding


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
        viewModel.masterCategory().observe(viewLifecycleOwner, Observer {
            when (it) {
                is Resource.Loading -> showLoading()

                is Resource.Success -> {

                    stopShowingLoading()

                    with(viewBinding) {
                        rvCategory.apply {
                            layoutManager = GridLayoutManager(context, 2)
                            addItemDecoration(GridSpaceItemDecoration(5))
                            adapter =
                                CategoryAdapter(context, it.value.result, this@CategoryFragment)
                        }
                    }
                }

                is Resource.Failure -> {
                    Log.e("TAG", "onViewCreated: ${it.errorBody}")
                }

                else -> {}
            }
        })

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
        val action = CategoryFragmentDirections.actionCategoryFragmentToProductsFragment(name)
        findNavController().navigate(action)
    }

    override fun onItemClickWithView(position: Int, view: View) {
        TODO("Not yet implemented")
    }

}