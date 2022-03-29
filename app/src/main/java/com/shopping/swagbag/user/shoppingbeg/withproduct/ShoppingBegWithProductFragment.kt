package com.shopping.swagbag.user.shoppingbeg.withproduct

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
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
    RecycleViewItemClick {

    private lateinit var toolbarBinding: ToolbarWithTwoMenusDeleteAndWishlistBinding
    private lateinit var product: GetCartModel
    private val userId by lazy { context?.let { AppUtils(it).getUserId() } }
    private lateinit var shoppingBegProductAdapter: ShoppingBegProductAdapter

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        toolbarBinding = viewBinding.include

        //when user click on shop now button in shopping beg without product layout
        viewBinding.btnShopNow.setOnClickListener {
            findNavController().navigate(R.id.action_global_home2)
        }
        initViews()
    }

    private fun initViews() {
        toolbar()

        getCart()

        with(viewBinding) {
            placeOrder.setOnClickListener {
                val action =
                    ShoppingBegWithProductFragmentDirections.actionShoppingBegWithProductFragmentToShoppingBegSelectAddressFragment(
                        product
                    )

                findNavController().navigate(action)
            }
        }
    }

    private fun getCart() {

        userId?.let { userId ->
            viewModel.getCart(userId).observe(viewLifecycleOwner) {
                when (it) {
                    is Resource.Loading -> showLoading()

                    is Resource.Success -> {
                        stopShowingLoading()

                        val productCount = it.value.result?.size
                        Log.e("TAG", "getCart: $productCount")

                        if (productCount == 0) {
                            showEmptyCart()
                        } else {
                            product = it.value
                            setItems(it.value.result)
                        }
                    }

                    is Resource.Failure -> Log.e("TAG", "getCart: $it")
                }
            }
        }
    }

    private fun setItems(product: List<GetCartModel.Result?>?) {
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
        userId?.let { userId ->
            viewModel.clearCart(userId).observe(viewLifecycleOwner) {
                when (it) {
                    is Resource.Loading -> showLoading()

                    is Resource.Success -> {
                        stopShowingLoading()

                        toast(it.value.message)

                        //send user to empty cart
                        showEmptyCart()
                    }

                    is Resource.Failure -> Log.e("cart", "clearCart: $it")
                }
            }
        }
    }

    private fun showEmptyCart() {
        viewBinding.lytWithoutProduct.visibility = View.VISIBLE
        viewBinding.lytWithProduct.visibility = View.GONE

        toolbarBinding.delete.visibility = View.GONE
    }

    override fun getFragmentBinding(
        inflater: LayoutInflater,
        container: ViewGroup?
    ) = FragmentShoppingBegWithProductBinding.inflate(inflater, container, false)

    override fun getViewModel() = ProductViewModel::class.java

    override fun getFragmentRepository() =
        ProductRepository(remoteDataSource.getBaseUrl().create(ProductApi::class.java))

    override fun onItemClickWithName(name: String, position: Int) {
        when (name) {
            "remove" -> {
                val productId = product.result?.get(position)?.product?.id

                if (productId != null) {
                    userId?.let { userId ->
                        viewModel.deleteSingleWish(productId, userId).observe(viewLifecycleOwner) {
                            when (it) {
                                is Resource.Loading -> showLoading()

                                is Resource.Success -> {
                                    stopShowingLoading()

                                    toast(it.value.message)

                                    //update list
                                    val productList: MutableList<GetCartModel.Result> =
                                        product.result as MutableList
                                    productList.removeAt(position)
                                    shoppingBegProductAdapter.updateList(productList)
                                    //if size is 0 then show user to empty cart
                                    if (position == 0) {
                                        showEmptyCart()
                                    }
                                }

                                is Resource.Failure -> Log.e("TAG", "onItemClickWithName: $it")
                            }
                        }
                    }
                }
            }
            else -> updateCart(name, position)
        }
    }

    private fun updateCart(productId: String, qty: Int) {
        userId?.let { viewModel.updateCart(it, productId, qty.toString()) }
            ?.observe(viewLifecycleOwner) {
                when(it){
                    is Resource.Loading -> showLoading()

                    is Resource.Success -> {
                        stopShowingLoading()

                        toast(it.value.message)

                        getCart()
                    }

                    is Resource.Failure -> {
                        stopShowingLoading()
                        toast("try again")
                    }
                }
            }
    }
}