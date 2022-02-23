package com.shopping.swagbag.category

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.fragment.navArgs
import com.shopping.swagbag.common.base.BaseFragment
import com.shopping.swagbag.databinding.FragmentCategoryBinding
import com.shopping.swagbag.databinding.FragmentParticularCategoryBinding
import com.shopping.swagbag.service.Resource

class ParticularCategoryFragment :
    BaseFragment<FragmentParticularCategoryBinding,
            CategoryViewModel,
            CategoryRepository>(FragmentParticularCategoryBinding::inflate) {

    private lateinit var categoryData: ParticularCategoryModel

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        initViews()
    }

    private fun initViews() {
        getCategoryData()
    }

    private fun getCategoryData() {
        val args: ParticularCategoryFragmentArgs by navArgs()
        val categoryId = args.categoryId

        Log.e("TAG", "getCategoryData: $categoryId", )

        viewModel.particularCategory(categoryId).observe(viewLifecycleOwner){
            when(it){
                is Resource.Loading -> showLoading()

                is Resource.Success -> {
                    stopShowingLoading()

                    Log.e("TAG", "getCategoryData: $it", )
                }

                is Resource.Failure -> Log.e("TAG", "getCategoryData: $it", )
            }
        }

    }

    override fun getFragmentBinding(
        inflater: LayoutInflater,
        container: ViewGroup?
    ) = FragmentParticularCategoryBinding.inflate(inflater, container, false)

    override fun getViewModel() = CategoryViewModel::class.java

    override fun getFragmentRepository() =
        CategoryRepository(remoteDataSource.getBaseUrl().create(CategoryApi::class.java))

}