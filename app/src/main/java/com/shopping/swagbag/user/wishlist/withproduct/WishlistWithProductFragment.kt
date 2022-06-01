package com.shopping.swagbag.user.wishlist.withproduct

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
import com.shopping.swagbag.service.apis.ProductApi
import com.shopping.swagbag.products.ProductRepository
import com.shopping.swagbag.products.ProductViewModel
import com.shopping.swagbag.service.Resource
import com.shopping.swagbag.utils.AppUtils
import org.json.JSONArray


class WishlistWithProductFragment : BaseFragment<
        FragmentWishlistWithProductBinding,
        ProductViewModel,
        ProductRepository
        >(FragmentWishlistWithProductBinding::inflate),
    RecycleViewItemClick {

    private lateinit var toolbarBinding: ToolbarWithTwoMenusDeleteAndCartBinding
    private lateinit var wistListProduct: GetWishlistModel
    private lateinit var wishlistProductAdapter: WishlistWithProductAdapter
   // private val appUtils = context?.let { AppUtils(it) }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        toolbarBinding = viewBinding.include

        //when user can see wishlist without product
        viewBinding.btnShopNow.setOnClickListener {
            findNavController().navigate(R.id.action_global_home2)
        }

        initViews()
    }

    private fun initViews() {
        setCategory()
        toolbar()
            getWishlistProduct()
    }

    private fun getWishlistProduct() {

            val userId = context?.let { AppUtils(it).getUserId() }

            Log.e("userId", "check user id: $userId", )

            if (userId != null) {
                viewModel.getWish(userId).observe(viewLifecycleOwner, Observer {
                    when (it) {
                        is Resource.Loading -> showLoading()

                        is Resource.Success -> {
                            stopShowingLoading()

                            // if user has not any product in wishlist then send user to empty wishlist screen
                            if (it.value.result.isEmpty()) {
                                showEmptyWishlist()
                            } else {
                                wistListProduct = it.value
                                Log.e("TAG", "getWishlistProduct: $wistListProduct")
                                setWishlistProduct(it.value.result)
                            }
                        }

                        is Resource.Failure -> {
                            stopShowingLoading()
                            toast("try again!")
                            findNavController().popBackStack()
                            Log.e("TAG", "setWishlistProduct: $it")
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

    private fun showEmptyWishlist(){
        viewBinding.lytWithProduct.visibility = View.GONE
        viewBinding.lytWithoutProduct.visibility = View.VISIBLE

        toolbarBinding.delete.visibility = View.GONE
    }

    private fun toolbar() {
        with(toolbarBinding){
            // set title
            tvTitle.text = getString(R.string.wishlist)

            //click listeners
            imgBack.setOnClickListener {
                findNavController().popBackStack()
            }

            delete.setOnClickListener {
                clearWishlist()
            }

            imgCart.setOnClickListener {
                findNavController().navigate(R.id.action_wishlistWithProductFragment_to_shoppingBegWithProductFragment)
            }

        }
    }

    private fun clearWishlist() {
        val isUserLogIn = context?.let { AppUtils(it).isUserLoggedIn() }

        Log.e("TAG", "is User logged in: $isUserLogIn")

        if (isUserLogIn == true) {
            val userId = context?.let { AppUtils(it).getUserId() }

            Log.e("userId", "check user id: $userId")

            if (userId != null) {
                viewModel.clearWishlist(userId).observe(viewLifecycleOwner) {
                    when (it) {
                        is Resource.Loading -> showLoading()

                        is Resource.Success -> {
                            stopShowingLoading()
                            toast(it.value.message)
                            showEmptyWishlist()
                        }

                        is Resource.Failure -> Log.e("TAG", "clearWishlist: $it")
                    }
                }
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

    override fun onItemClickWithName(name: String, position: Int) {
        when (name) {
            "moveToBeg" -> addToBeg(position)
            "remove" -> removeFromWishlist(position)
        }
    }

    private fun removeFromWishlist(position: Int) {
        val isUserLogIn = context?.let { AppUtils(it).isUserLoggedIn() }
        val userId = context?.let { AppUtils(it).getUserId() }

        if (isUserLogIn==true && userId != null) {

            viewModel.deleteSingleWish(wistListProduct.result[position].product.id, userId)
                .observe(viewLifecycleOwner, Observer {
                    when (it) {
                        is Resource.Loading -> showLoading()

                        is Resource.Success -> {
                            stopShowingLoading()

                            //toast("")

                            val allDataList: MutableList<GetWishlistModel.Result> =
                                wistListProduct.result as MutableList
                            allDataList.removeAt(position)
                            wishlistProductAdapter.updateData(allDataList)

                            if (position==0 && allDataList.isEmpty()) {
                                showEmptyWishlist()
                            }
                            Log.e("wishlist", "removeFromWishlist: $allDataList")
                        }

                        is Resource.Failure -> Log.e("TAG", "removeFromWishlist: $it")
                    }
                })
        }
    }

    private fun addToBeg(position: Int) {
        val isUserLogIn = context?.let { AppUtils(it).isUserLoggedIn() }
        val userId = context?.let { AppUtils(it).getUserId() }

        val jsonArray = JSONArray()

        if (isUserLogIn == true && userId != null) {
            viewModel.addToCart("1", wistListProduct.result[position].product.id, userId, jsonArray)
                .observe(viewLifecycleOwner) {
                    when (it) {
                        is Resource.Loading -> showLoading()

                        is Resource.Success -> {
                            stopShowingLoading()
                            toast(it.value.message)
                            removeFromWishlist(position)
                        }

                        is Resource.Failure -> {
                            stopShowingLoading()
                            toast("try again!")
                        }
                    }
                }
        }
    }
}