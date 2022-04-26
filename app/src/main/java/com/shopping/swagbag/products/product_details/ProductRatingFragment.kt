package com.shopping.swagbag.products.product_details

import android.os.Bundle
import android.text.Editable
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import com.bumptech.glide.Glide
import com.google.gson.Gson
import com.shopping.swagbag.R
import com.shopping.swagbag.common.base.BaseFragment
import com.shopping.swagbag.databinding.FragmentProductRatingBinding
import com.shopping.swagbag.products.ProductRepository
import com.shopping.swagbag.products.ProductViewModel
import com.shopping.swagbag.service.Resource
import com.shopping.swagbag.service.apis.ProductApi
import com.shopping.swagbag.utils.AppUtils


class ProductRatingFragment : BaseFragment<
        FragmentProductRatingBinding,
        ProductViewModel,
        ProductRepository>(FragmentProductRatingBinding::inflate) {

    private lateinit var product: ProductDetailModel

    override fun getFragmentBinding(
        inflater: LayoutInflater,
        container: ViewGroup?
    ) = FragmentProductRatingBinding.inflate(inflater, container, false)

    override fun getViewModel() = ProductViewModel::class.java

    override fun getFragmentRepository() = ProductRepository(
        remoteDataSource.getBaseUrl().create(
            ProductApi::class.java
        )
    )

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        initViews()
    }

    private fun initViews() {
        setToolbar()

        getArgument()

        handleNotReviewing()

        handleUserReview()
    }

    private fun setToolbar(){
        val toolbarBinding = viewBinding.toolbar
        with(toolbarBinding){
            imgBack.setOnClickListener{
                findNavController().popBackStack()
            }
            tvTitle.text = "Review Product"
        }
    }

    private fun handleUserReview() {
        with(viewBinding) {
            btnContinue.setOnClickListener {
                //first get detail that user enter
                val appUtils = context?.let { it1 -> AppUtils(it1) }

                if (appUtils!!.isUserLoggedIn()) {
                    val userId = appUtils.getUserId()
                    val productId = product.result.id
                    val mobile = appUtils.getUser()?.result?.mobile
                    val userComment = edtComment.text.toString()
                    val name = edtName.text.toString()
                    val email = edtEmail.text.toString()
                    val rating = ratingBar.rating.toString()

                    // check user enter all details or not
                    if (userComment.isNotEmpty() && name.isNotEmpty() && email.isNotEmpty() && rating.isNotEmpty()) {
                        addUserReview(userId, productId, name, email, mobile!!, rating, userComment)
                    } else
                        toast("Please fill all details")
                } else
                    findNavController().navigate(R.id.action_global_signInFragment)
            }
        }
    }

    private fun addUserReview(
        userId: String,
        productId: String,
        name: String,
        email: String,
        mobile: String,
        rating: String,
        userComment: String
    ) {
        viewModel.addReview(userId, productId, name, email, mobile, rating, userComment).observe(viewLifecycleOwner){
            when(it){
                is Resource.Loading -> showLoading()

                is Resource.Success -> {
                    stopShowingLoading()
                    toast(it.value.message)

                    with(viewBinding){
                        edtComment.setText("")
                        edtName.setText("")
                        edtEmail.setText("")
                        ratingBar.rating = 0.0F
                    }
                }

                is Resource.Failure -> {
                    stopShowingLoading()
                    Log.e("rating", "$it")
                }
            }
        }
    }

    private fun getArgument() {
        val args: ProductRatingFragmentArgs by navArgs()
        product = Gson().fromJson(args.product, ProductDetailModel::class.java)
        setData()
    }

    private fun setData() {
        with(viewBinding) {
            //set image
            context?.let {
                Glide.with(it)
                    .load(product.result.file[0].location)
                    .into(imgProduct)
            }

            Log.e("rating", "${product.result.file[0]}")

            //set name
            productName.text = product.result.name
        }
    }

    private fun handleNotReviewing() {
        viewBinding.btnContinueShopping.setOnClickListener {
            findNavController().popBackStack()
        }
    }


}