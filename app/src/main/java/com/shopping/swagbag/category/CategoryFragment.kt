package com.shopping.swagbag.category

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.GridLayoutManager
import com.shopping.swagbag.R
import com.shopping.swagbag.common.base.BaseFragment
import com.shopping.swagbag.databinding.FragmentCategoryBinding
import com.shopping.swagbag.databinding.ToolbarWithNoMenuWhiteBgBinding

class CategoryFragment :
    BaseFragment<FragmentCategoryBinding,
            CategoryViewModel,
            CategoryRepository>(FragmentCategoryBinding::inflate) {

    private lateinit var toolbarBinding: ToolbarWithNoMenuWhiteBgBinding
    private lateinit var categoryViewModel: CategoryViewModel
    private val repository = CategoryRepository(remoteDataSource.getRetroApi())

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        initViews()
    }

    private fun initViews() {
        toolbarBinding = viewBinding.include

        categoryViewModel =
            ViewModelProvider(
                this,
                CategoryViewModelFactory(repository)
            )[CategoryViewModel::class.java]

        setToolbar()

        setCategoryData()
    }

    private fun setCategoryData() {
        with(viewBinding) {
            rvCategory.apply{
                layoutManager = GridLayoutManager(context, 2)
                categoryViewModel.category.observe(viewLifecycleOwner, Observer {
                    adapter = CategoryAdapter(context, it.result)
                })
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

    override fun getFragmentBinding(
        inflater: LayoutInflater,
        container: ViewGroup?
    ): FragmentCategoryBinding {
        return FragmentCategoryBinding.inflate(layoutInflater, container, false)
    }

    override fun getViewModel() = CategoryViewModel::class.java

    override fun getFragmentRepository() = repository
}