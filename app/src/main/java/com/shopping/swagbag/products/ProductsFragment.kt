package com.shopping.swagbag.products

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.Observer
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.shopping.swagbag.R
import com.shopping.swagbag.common.GridSpaceItemDecoration
import com.shopping.swagbag.common.base.BaseFragment
import com.shopping.swagbag.databinding.*
import com.shopping.swagbag.dummy.DummyData
import com.shopping.swagbag.service.Resource


class ProductsFragment : BaseFragment<
        FragmentProductsBinding,
        ProductViewModel,
        ProductRepository
        >(FragmentProductsBinding::inflate), View.OnClickListener {

    private lateinit var toolbarBinding: ToolbarWithThreeMenusBinding

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        toolbarBinding = viewBinding.include

        initViews()
    }

    private fun initViews() {
        setToolbar()

        setProducts()

        // click listeners
        with(viewBinding){
            tvSortBy.setOnClickListener(this@ProductsFragment)
        }
    }

    private fun setProducts() {
        val args: ProductsFragmentArgs by navArgs()
        val productId = args.productId

        viewModel.productSearch("", "", "", "", "", "","",  productId, "")
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

                    is Resource.Failure -> Log.e("TAG", "setProducts: $it", )
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
                findNavController().navigate(R.id.action_productsFragment_to_wishlistWithProductFragment)
            }

            imgCart.setOnClickListener{
                findNavController().navigate(R.id.action_productsFragment_to_shoppingBegWithoutProductFragment)
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