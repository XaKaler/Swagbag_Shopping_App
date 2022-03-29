package com.shopping.swagbag.search

import android.content.Context
import android.content.pm.PackageManager
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.inputmethod.EditorInfo
import android.view.inputmethod.InputMethodManager
import android.widget.EditText
import android.widget.TextView
import androidx.core.app.ActivityCompat
import androidx.core.content.ContextCompat
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.GridLayoutManager
import com.shopping.swagbag.R
import com.shopping.swagbag.common.GridSpaceItemDecoration
import com.shopping.swagbag.common.base.BaseFragment
import com.shopping.swagbag.databinding.FragmentSearchBinding
import com.shopping.swagbag.databinding.SearchBarBinding
import com.shopping.swagbag.databinding.ToolbarWithNoMenuWhiteBgBinding
import com.shopping.swagbag.products.*
import com.shopping.swagbag.service.Resource


class SearchFragment : BaseFragment<FragmentSearchBinding,
        ProductViewModel,
        ProductRepository>(FragmentSearchBinding::inflate), View.OnClickListener {

    private lateinit var toolbarBinding: ToolbarWithNoMenuWhiteBgBinding
    private lateinit var searchBarBinding: SearchBarBinding
    //private lateinit var searchBar: EditText

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        toolbarBinding = viewBinding.include
        searchBarBinding = viewBinding.searchBar
        // searchBar = view.findViewById(R.id.searchBar) as EditText

        initViews()
    }

    private fun initViews() {

        // click listeners
        with(searchBarBinding) {
            searchByImage.setOnClickListener(this@SearchFragment)
            searchByVoice.setOnClickListener(this@SearchFragment)
        }

        productSearch("face wash")
/*
        searchBar.setOnEditorActionListener(TextView.OnEditorActionListener { _, actionId, _ ->
            if (actionId == EditorInfo.IME_ACTION_DONE) {

                // get text that user enter
                val userSearch = "face wash" //searchBarBinding.searchBar.text.toString()
                productSearch(userSearch)

                return@OnEditorActionListener true
            }
            false
        })*/

        openKeyboard()
        setToolbar()
    }

    private fun openKeyboard() {
        //searchBar.requestFocus()
        val imm = context?.getSystemService(Context.INPUT_METHOD_SERVICE) as InputMethodManager
        imm.showSoftInput(view, InputMethodManager.SHOW_IMPLICIT)
    }

    private fun productSearch(userSearch: String) {
        viewModel.productSearch("", "", "", "", "", "", "", "", userSearch)
            .observe(viewLifecycleOwner) {
                when (it) {
                    is Resource.Loading -> showLoading()

                    is Resource.Success -> {
                        stopShowingLoading()

                        showSearchResult(it.value.result)
                    }

                    is Resource.Failure -> {
                        stopShowingLoading()

                        toast("try again")

                        Log.e("productSearch", "$it", )
                    }
                }
            }
    }

    private fun showSearchResult(products: List<ProductSearchModel.Result>) {
        with(viewBinding) {
            rvSearchProducts.apply {
                layoutManager = GridLayoutManager(context, 2)
                addItemDecoration(GridSpaceItemDecoration(5))
                adapter = ProductAdapter(context, products)
            }
        }
    }

    /* private fun setPopularOnSwagbag() {
         with(viewBinding) {
             rvPopularOnSwagbag.apply {
                 layoutManager = LinearLayoutManager(context, LinearLayoutManager.HORIZONTAL, false)
                 //adapter = DummyData().getDummyData()?.let { AllTimeSliderAdapter(context, it, this@SearchFragment) }
             }
         }
     }

     private fun setNewArrivals() {
         with(viewBinding) {
             rvNewArrivals.apply {
                 layoutManager = GridLayoutManager(context, 2)
                // adapter = DummyData().getTwoDummyData()?.let { BestProductAdapter(context, it, this@SearchFragment) }
             }
         }
     }*/

    private fun setToolbar() {
        with(toolbarBinding) {
            // set title
            tvTitle.text = getString(R.string.search)

            // back button click
            imgBack.setOnClickListener {
                findNavController().popBackStack()
            }
        }
    }

    override fun onClick(v: View?) {
        when (v?.id) {
            R.id.searchByImage -> findNavController().navigate(R.id.action_searchFragment_to_dialogSearchUsing)

            R.id.searchByVoice -> checkMicPermission()
        }
    }

    private fun checkMicPermission() {
        if (context?.let {
                ContextCompat.checkSelfPermission(
                    it,
                    android.Manifest.permission.RECORD_AUDIO
                )
            } == PackageManager.PERMISSION_DENIED) {
            activity?.let {
                ActivityCompat.requestPermissions(
                    it,
                    arrayOf(android.Manifest.permission.RECORD_AUDIO),
                    0
                )
            }
        } else {
            findNavController().navigate(R.id.action_searchFragment_to_search_using_microphone)
        }
    }

    override fun getFragmentBinding(
        inflater: LayoutInflater,
        container: ViewGroup?
    ) = FragmentSearchBinding.inflate(inflater, container, false)

    override fun getViewModel() = ProductViewModel::class.java

    override fun getFragmentRepository() =
        ProductRepository(remoteDataSource.getBaseUrl().create(ProductApi::class.java))
}