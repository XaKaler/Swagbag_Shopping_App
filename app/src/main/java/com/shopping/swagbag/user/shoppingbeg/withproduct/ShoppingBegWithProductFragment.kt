package com.shopping.swagbag.user.shoppingbeg.withproduct

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
import com.shopping.swagbag.databinding.FragmentShoppingBegWithProductBinding
import com.shopping.swagbag.databinding.ToolbarWithTwoMenusDeleteAndWishlistBinding
import com.shopping.swagbag.products.ProductApi
import com.shopping.swagbag.products.ProductRepository
import com.shopping.swagbag.products.ProductViewModel
import com.shopping.swagbag.service.Resource
import com.shopping.swagbag.utils.AppUtils

class ShoppingBegWithProductFragment : BaseFragment<
        FragmentShoppingBegWithProductBinding,
        ProductViewModel,
        ProductRepository>(FragmentShoppingBegWithProductBinding::inflate),
    View.OnClickListener,
    RecycleViewItemClick {

    private lateinit var toolbarBinding: ToolbarWithTwoMenusDeleteAndWishlistBinding
    private lateinit var product: GetCartModel
    private lateinit var appUtils: AppUtils
    private lateinit var shoppingBegProductAdapter: ShoppingBegProductAdapter

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        toolbarBinding = viewBinding.include

        initViews()
    }

    private fun initViews() {
        toolbar()

        getCart()

        handleOnClick()
    }

    private fun getCart() {
        appUtils = context?.let { AppUtils(it) }!!
        val userId = appUtils.getUserId()

        viewModel.getCart(userId).observe(viewLifecycleOwner) {
            when (it) {
                is Resource.Loading -> showLoading()

                is Resource.Success -> {
                    stopShowingLoading()

                    val productCount = it.value.result.size
                    Log.e("TAG", "getCart: $productCount", )

                    if (productCount == 0) {
                        findNavController().navigate(R.id.action_shoppingBegWithProductFragment_to_shoppingBegWithoutProductFragment)
                    } else {
                        product = it.value
                        setItems(it.value.result)
                    }
                }

                is Resource.Failure -> Log.e("TAG", "getCart: $it")
            }
        }

        //viewModel.getCart()
    }

    private fun handleOnClick() {
        with(viewBinding) {
            placeOrder.setOnClickListener(this@ShoppingBegWithProductFragment)
        }
    }

    private fun setItems(product: List<GetCartModel.Result>) {
        with(viewBinding) {
            rvShoppingBegItems.apply {
                layoutManager = LinearLayoutManager(context)
                shoppingBegProductAdapter =
                    ShoppingBegProductAdapter(context, product, this@ShoppingBegWithProductFragment)
                adapter = shoppingBegProductAdapter
            }
        }
    }

    private fun toolbar() {
        with(toolbarBinding) {
            // set title
            tvTitle.text = getString(R.string.shopping_beg)

            //click listeners
            imgBack.setOnClickListener {
                findNavController().popBackStack()
            }

            imgWishlist.setOnClickListener {
                findNavController().navigate(R.id.action_shoppingBegWithProductFragment_to_wishlistWithProductFragment)
            }

            delete.setOnClickListener {
                clearCart()
            }
        }
    }

    private fun clearCart() {
        val userId = appUtils.getUserId()
        viewModel.clearCart(userId).observe(viewLifecycleOwner, Observer {
            when (it) {
                is Resource.Loading -> showLoading()

                is Resource.Success -> {
                    stopShowingLoading()

                    toast(it.value.message)

                    //send user to empty cart
                    findNavController().navigate(R.id.action_shoppingBegWithProductFragment_to_shoppingBegWithoutProductFragment)
                }

                is Resource.Failure -> Log.e("cart", "clearCart: $it")
            }
        })
    }

    override fun onClick(v: View?) {
        when (v?.id) {
            R.id.placeOrder -> findNavController().navigate(R.id.action_shoppingBegWithProductFragment_to_shoppingBegSelectAddressFragment)
        }
    }

    override fun getFragmentBinding(
        inflater: LayoutInflater,
        container: ViewGroup?
    ) = FragmentShoppingBegWithProductBinding.inflate(inflater, container, false)

    override fun getViewModel() = ProductViewModel::class.java

    override fun getFragmentRepository() =
        ProductRepository(remoteDataSource.getBaseUrl().create(ProductApi::class.java))

    override fun onItemClickWithName(tag: String, position: Int) {
        val productId = product.result[position].product.id
        val userId = appUtils.getUserId()

        viewModel.deleteSingleWish(productId, userId).observe(viewLifecycleOwner) {
            when (it) {
                is Resource.Loading -> showLoading()

                is Resource.Success -> {
                    stopShowingLoading()

                    toast(it.value.message)

                    //update list
                    val productList: MutableList<GetCartModel.Result> =
                        product.result as MutableList

                    //if size is 0 then show user to empty cart
                    if (productList.size == 0)
                        findNavController().navigate(R.id.action_shoppingBegWithProductFragment_to_shoppingBegWithoutProductFragment)
                    else {
                        productList.removeAt(position)
                        shoppingBegProductAdapter.updateList(productList)
                    }

                }

                is Resource.Failure -> Log.e("TAG", "onItemClickWithName: $it",)
            }
        }
    }
}