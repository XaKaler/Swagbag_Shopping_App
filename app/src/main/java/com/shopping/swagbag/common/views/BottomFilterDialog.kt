package com.shopping.swagbag.common.views

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.FragmentTransaction
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.google.android.material.bottomsheet.BottomSheetBehavior
import com.google.android.material.bottomsheet.BottomSheetDialog
import com.google.android.material.bottomsheet.BottomSheetDialogFragment
import com.shopping.swagbag.R
import com.shopping.swagbag.common.RecycleViewItemClick
import com.shopping.swagbag.databinding.FragmentFilterBinding
import com.shopping.swagbag.dummy.DummyData
import com.shopping.swagbag.products.ProductRepository
import com.shopping.swagbag.products.ProductViewModel
import com.shopping.swagbag.products.ProductViewModelFactory
import com.shopping.swagbag.products.filter.ProductFilterAdapter
import com.shopping.swagbag.products.filter.filter_size.FilterSizeFragment
import com.shopping.swagbag.service.RemoteDataSource
import com.shopping.swagbag.service.Resource
import com.shopping.swagbag.service.apis.ProductApi

class BottomFilterDialog(private val categoryName: String) : BottomSheetDialogFragment() {

    private lateinit var viewBinding: FragmentFilterBinding
    private lateinit var productViewModel: ProductViewModel

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {

        viewBinding = FragmentFilterBinding.inflate(inflater, container, false)
        return viewBinding.root
    }

    override fun onStart() {
        super.onStart()
        val dialog = dialog as BottomSheetDialog
        val behavior: BottomSheetBehavior<*> = dialog.behavior
        behavior.state = BottomSheetBehavior.STATE_EXPANDED
        behavior.peekHeight = 0
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        // product repository
        val productRepository =
            ProductRepository(RemoteDataSource().getBaseUrl().create(ProductApi::class.java))
        productViewModel = ViewModelProvider(
            this,
            ProductViewModelFactory(productRepository)
        )[ProductViewModel::class.java]

        getCategoryFilter()

        with(viewBinding) {
            apply.setOnClickListener {
                dismiss()
            }
            close.setOnClickListener {
                dismiss()
            }
        }
    }

    private fun getCategoryFilter() {
        productViewModel.getFilter(categoryName).observe(viewLifecycleOwner){
            when(it){
                is Resource.Success -> {
                    //Log.e("filter", it.value.toString())
                }
                is Resource.Failure -> {
                    Log.e("filter", it.errorBody.toString())
                }
            }
        }
    }

    private fun setFirstFilter() {
        val transaction: FragmentTransaction? = fragmentManager?.beginTransaction()
        transaction?.replace(R.id.filterFrameLayout, FilterSizeFragment())
        transaction?.addToBackStack(null)
        transaction?.commit()

        //fragmentManager.beginTransaction().attach()
    }

    private fun setFilterList() {
        with(viewBinding) {
            rvFilterList.apply {
                layoutManager = LinearLayoutManager(context, LinearLayoutManager.VERTICAL, false)
                adapter = ProductFilterAdapter(
                    context,
                    DummyData().getProductFilter(),
                    object : RecycleViewItemClick {
                        override fun onItemClickWithName(name: String, position: Int) {
                            Log.e("filter selected item", "item name is : $name")
                        }
                    })
            }
        }
    }
}
