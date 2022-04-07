package com.shopping.swagbag.products

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.Observer
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import androidx.recyclerview.widget.GridLayoutManager
import com.shopping.swagbag.R
import com.shopping.swagbag.common.GridSpaceItemDecoration
import com.shopping.swagbag.common.base.BaseFragment
import com.shopping.swagbag.databinding.FragmentProductsBinding
import com.shopping.swagbag.databinding.ToolbarWithThreeMenusBinding
import com.shopping.swagbag.service.Resource
import com.shopping.swagbag.utils.AppUtils


class ProductsFragment : BaseFragment<
        FragmentProductsBinding,
        ProductViewModel,
        ProductRepository
        >(FragmentProductsBinding::inflate), View.OnClickListener {

    private lateinit var toolbarBinding: ToolbarWithThreeMenusBinding
    private lateinit var appUtils: AppUtils

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        toolbarBinding = viewBinding.include

        initViews()
    }

    private fun initViews() {
        setToolbar()

        setProducts()

        appUtils = context?.let { AppUtils(it) }!!

        // click listeners
        with(viewBinding) {
            tvSortBy.setOnClickListener(this@ProductsFragment)
        }
    }

    private fun setProducts() {
        val args: ProductsFragmentArgs by navArgs()
        val masterCategory = args.productId

        viewModel.productSearch("", "", "", "", "", "","",  masterCategory, "")
            .observe(viewLifecycleOwner, Observer {
                when(it){
                    is Resource.Loading -> showLoading()

                    is Resource.Success -> {
                        stopShowingLoading()

                        val products = it.value.result

                        viewBinding.rvProducts.apply{
                            layoutManager = GridLayoutManager(context, 2)
                            addItemDecoration(GridSpaceItemDecoration(5))
                            adapter = ProductAdapter(context, products)
                        }
                    }

                    is Resource.Failure -> {
                        stopShowingLoading()
                        tryAgain()
                        findNavController().popBackStack()
                        Log.e("TAG", "setProducts: $it", )
                    }
                }
            })
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
                if (appUtils.isUserLoggedIn())
                    findNavController().navigate(R.id.action_global_wishlistWithProductFragment)
                else
                    findNavController().navigate(R.id.action_global_signInFragment)
            }

            imgCart.setOnClickListener{
                if (appUtils.isUserLoggedIn())
                findNavController().navigate(R.id.action_global_shoppingBegWithProductFragment)
                else
                    findNavController().navigate(R.id.action_global_signInFragment)
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

    override fun getFragmentBinding(
        inflater: LayoutInflater,
        container: ViewGroup?
    )= FragmentProductsBinding.inflate(inflater, container, false)

    override fun getViewModel() = ProductViewModel::class.java

    override fun getFragmentRepository() = ProductRepository(remoteDataSource.getBaseUrl().create(ProductApi::class.java))
}