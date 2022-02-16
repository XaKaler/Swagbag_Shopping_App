package com.shopping.swagbag.wishlist.withproduct

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.Observer
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.shopping.swagbag.R
import com.shopping.swagbag.common.RecycleViewItemClick
import com.shopping.swagbag.common.base.BaseFragment
import com.shopping.swagbag.databinding.FragmentWishlistWithProductBinding
import com.shopping.swagbag.databinding.ToolbarWithTwoMenusDeleteAndCartBinding
import com.shopping.swagbag.dummy.DummyData
import com.shopping.swagbag.products.ProductApi
import com.shopping.swagbag.products.ProductRepository
import com.shopping.swagbag.products.ProductViewModel
import com.shopping.swagbag.service.Resource
import com.shopping.swagbag.utils.AppUtils


class WishlistWithProductFragment : BaseFragment<
        FragmentWishlistWithProductBinding,
        ProductViewModel,
        ProductRepository
        >(FragmentWishlistWithProductBinding::inflate),
    RecycleViewItemClick {

    private lateinit var toolbarBinding: ToolbarWithTwoMenusDeleteAndCartBinding
    private lateinit var wistListProduct: GetWishlistModel
    private lateinit var wishlistProductAdapter: WishlistWithProductAdapter
    private val appUtils = context?.let { AppUtils(it) }
    private val userId: String = if (appUtils!!.isUserLoggedIn()) {
        appUtils.getUserId()
    } else {
        ""
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        toolbarBinding = viewBinding.include

        initViews()
    }

    private fun initViews() {
        setCategory()

        getWishlistProduct()

        toolbar()
    }

    private fun getWishlistProduct() {

        if (appUtils!!.isUserLoggedIn()) {
            val userId = appUtils.getUserId()

            viewModel.getWish(userId).observe(viewLifecycleOwner, Observer {
                when (it) {
                    is Resource.Loading -> showLoading()

                    is Resource.Success -> {
                        stopShowingLoading()

                        wistListProduct = it.value

                        setWishlistProduct(it.value.result)
                    }

                    is Resource.Failure -> {
                        Log.e("TAG", "setWishlistProduct: ${it.toString()}")
                    }
                }
            })
        }
    }

    private fun setWishlistProduct(products: List<GetWishlistModel.Result>) {
        with(viewBinding) {
            rvProducts.apply {
                layoutManager = LinearLayoutManager(context)
                wishlistProductAdapter =
                    WishlistWithProductAdapter(context, products, this@WishlistWithProductFragment)
                adapter = wishlistProductAdapter
            }
        }
    }

    private fun setCategory() {
        with(viewBinding) {
            rvCategory.apply {
                layoutManager = LinearLayoutManager(context, LinearLayoutManager.HORIZONTAL, false)
                adapter = WishlistWithProductSliderAdapter(
                    context,
                    DummyData().getWishListWithItemCategory()
                )
            }
        }
    }

    private fun toolbar() {
        with(toolbarBinding){
            // set title
            tvTitle.text = getString(R.string.wishlist)

            //click listeners
            imgBack.setOnClickListener{
                findNavController().popBackStack()
            }

            delete.setOnClickListener {}

            imgCart.setOnClickListener {
                findNavController().navigate(R.id.action_wishlistWithProductFragment_to_shoppingBegWithoutProductFragment)
            }

        }
    }

    override fun getFragmentBinding(
        inflater: LayoutInflater,
        container: ViewGroup?
    ) = FragmentWishlistWithProductBinding.inflate(inflater, container, false)

    override fun getViewModel() = ProductViewModel::class.java

    override fun getFragmentRepository() =
        ProductRepository(remoteDataSource.getBaseUrl().create(ProductApi::class.java))

    override fun onItemClickWithName(tag: String, position: Int) {
        when (tag) {
            "moveToBeg" -> addToBeg()
            "remove" -> removeFromWishlist(position)
        }
    }

    private fun removeFromWishlist(position: Int) {
        if (userId != "") {
            viewModel.deleteSingleWish(wistListProduct.result[position].product.id, userId)
                .observe(viewLifecycleOwner, Observer {
                    when (it) {
                        is Resource.Loading -> showLoading()

                        is Resource.Success -> {
                            stopShowingLoading()

                            toast(it.value.message)

                            val updateList: List<GetWishlistModel.Result> = wistListProduct.result
                            wishlistProductAdapter.updateData(updateList)
                            //@todo update recycle view
                        }

                        is Resource.Failure -> Log.e("TAG", "removeFromWishlist: $it")
                    }
                })
        }
    }

    private fun addToBeg() {
        if (userId != "") {

        }
    }


}