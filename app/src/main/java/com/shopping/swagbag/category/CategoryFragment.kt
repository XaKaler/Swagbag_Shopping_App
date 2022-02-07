package com.shopping.swagbag.category

import android.os.Bundle
import android.util.Log
import android.view.View
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.GridLayoutManager
import com.shopping.swagbag.R
import com.shopping.swagbag.databinding.FragmentCategoryBinding
import com.shopping.swagbag.databinding.ToolbarWithNoMenuWhiteBgBinding
import com.shopping.swagbag.dummy.DummyData
import com.shopping.swagbag.service.RetrofitSingleton

class CategoryFragment : Fragment(R.layout.fragment_category) {

    private lateinit var viewBinding: FragmentCategoryBinding
    private lateinit var toolbarBinding: ToolbarWithNoMenuWhiteBgBinding
    private lateinit var categoryViewModel: CategoryViewModel

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val repository = CategoryRepository(RetrofitSingleton.getRetroApi())

        viewBinding = FragmentCategoryBinding.bind(view)
        toolbarBinding = viewBinding.include

        categoryViewModel =
            ViewModelProvider(
                this,
                CategoryViewModelFactory(repository)
            )[CategoryViewModel::class.java]

        initViews()
    }

    private fun initViews() {
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

}