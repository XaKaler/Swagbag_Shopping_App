package com.shopping.swagbag.products.product_details

import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.bumptech.glide.Glide
import com.shopping.swagbag.R
import com.shopping.swagbag.common.adapter.ProductImageSliderAdapter
import com.shopping.swagbag.databinding.FragmentProductDetailsBinding
import com.shopping.swagbag.dummy.DummyData
import com.shopping.swagbag.dummy.SingleProductImageModel
import com.shopping.swagbag.dummy.SingleProductModel
import com.shopping.swagbag.products.Card10Adapter
import com.shopping.swagbag.products.ReviewAdapter
import com.shopping.swagbag.products.ReviewImagesAdapter
import com.smarteist.autoimageslider.SliderView

class ProductDetailsFragment : Fragment(R.layout.fragment_product_details), View.OnClickListener {

    private lateinit var viewBinding: FragmentProductDetailsBinding

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        viewBinding = FragmentProductDetailsBinding.bind(view)

        initViews()
    }

    private fun initViews() {
        with(viewBinding){
            sizeChart.setOnClickListener{
                findNavController().navigate(R.id.action_productDetailsFragment_to_productDetailsSizeChart)
            }

            viewEmiPlan.setOnClickListener{
                findNavController().navigate(R.id.action_productDetailsFragment_to_productDetailsEMIPlansFragment)
            }

            wishlist.setOnClickListener{
                findNavController().navigate(R.id.action_productDetailsFragment_to_wishlistWithProductFragment)
            }

            addToBeg.setOnClickListener{
                findNavController().navigate(R.id.action_productDetailsFragment_to_shoppingBegWithProductFragment)
            }


        }

        val data = DummyData().getSingleProductImage()
        val singleProduct = DummyData().getSingleProduct()

        handleClickListeners()

        setAutoImageSlider(data)

        setProductSmallImages(singleProduct)

        setAllCustomerReviewImages()

        setUserReview()

        setViewSimilar()
    }

    private  fun handleClickListeners(){
        with(viewBinding){
            imgBack.setOnClickListener(this@ProductDetailsFragment)
            wishlist.setOnClickListener(this@ProductDetailsFragment)
            addToBeg.setOnClickListener(this@ProductDetailsFragment)
            imgBeg.setOnClickListener(this@ProductDetailsFragment)
        }
    }

    private fun setViewSimilar() {
        with(viewBinding){
            rvViewSimilar.apply{
                layoutManager = LinearLayoutManager(context, LinearLayoutManager.HORIZONTAL, false)
                adapter = DummyData().getDummyData()?.let { Card10Adapter(context, it) }
            }
        }
    }

    private fun setUserReview() {
        with(viewBinding){
            rvUserReview.apply{
                layoutManager = LinearLayoutManager(context)
                adapter = DummyData().getCategory()?.let { ReviewAdapter(context, it) }
            }
        }
    }

    private fun setAllCustomerReviewImages() {
        with(viewBinding) {
            rvReviewImages.apply {
                layoutManager = LinearLayoutManager(context, LinearLayoutManager.HORIZONTAL, false)
                adapter = ReviewImagesAdapter(context, DummyData().getSingleProductImage())
            }
        }
    }

    private fun setProductSmallImages(data: SingleProductModel) {
        with(viewBinding) {
            context?.let {
                Glide
                    .with(it)
                    .load(data.image1)
                    .fitCenter()
                    .into(viewBinding.roundedImageView6)


                Glide
                    .with(it)
                    .load(data.image2)
                    .fitCenter()
                    .into(viewBinding.roundedImageView5)


                Glide
                    .with(it)
                    .load(data.image3)
                    .fitCenter()
                    .into(viewBinding.roundedImageView4)


                Glide
                    .with(it)
                    .load(data.image4)
                    .fitCenter()
                    .into(viewBinding.roundedImageView7)


                Glide
                    .with(it)
                    .load(data.image5)
                    .fitCenter()
                    .into(viewBinding.roundedImageView)


            }
        }
    }


    private fun setAutoImageSlider(data: ArrayList<SingleProductImageModel>) {
        with(viewBinding) {
            sliderView.autoCycleDirection = SliderView.LAYOUT_DIRECTION_LTR;

            // below method is used to
            // setadapter to sliderview.
            context?.let { ProductImageSliderAdapter(it, data) }
                ?.let { sliderView.setSliderAdapter(it) }

            // below method is use to set
            // scroll time in seconds.
            //sliderView.scrollTimeInSec = 5

            // to set it scrollable automatically
            // we use below method.
            // sliderView.isAutoCycle = true

            sliderView.setIndicatorAnimation(com.smarteist.autoimageslider.IndicatorView.animation.type.IndicatorAnimationType.THIN_WORM)
            sliderView.setSliderTransformAnimation(com.smarteist.autoimageslider.SliderAnimations.SIMPLETRANSFORMATION)
            sliderView.autoCycleDirection =
                com.smarteist.autoimageslider.SliderView.AUTO_CYCLE_DIRECTION_BACK_AND_FORTH

            // to start auto cycle below method is used.
            //sliderView.startAutoCycle()
        }
    }

    override fun onClick(v: View?) {
        when(v?.id){
            R.id.wishlist -> findNavController().navigate(R.id.action_productDetailsFragment_to_wishlistWithoutProductFragment)
            R.id.addToBeg -> findNavController().navigate(R.id.action_productDetailsFragment_to_shoppingBegWithoutProductFragment)

            R.id.imgBack -> findNavController().popBackStack()
            R.id.imgBeg -> findNavController().navigate(R.id.action_productDetailsFragment_to_shoppingBegWithoutProductFragment)


        }
    }

}