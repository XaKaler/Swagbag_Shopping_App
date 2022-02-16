package com.shopping.swagbag.products.product_details

import android.graphics.Paint
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.text.HtmlCompat
import androidx.lifecycle.Observer
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import androidx.recyclerview.widget.LinearLayoutManager
import com.shopping.swagbag.R
import com.shopping.swagbag.common.RecycleItemClick
import com.shopping.swagbag.common.RecycleItemClickListener
import com.shopping.swagbag.common.adapter.ProductImageSliderAdapter
import com.shopping.swagbag.common.base.BaseFragment
import com.shopping.swagbag.databinding.FragmentProductDetailsBinding
import com.shopping.swagbag.dummy.DummyData
import com.shopping.swagbag.products.*
import com.shopping.swagbag.service.Resource
import com.shopping.swagbag.utils.AppUtils
import com.smarteist.autoimageslider.SliderView
import kotlin.math.log

class ProductDetailsFragment : BaseFragment<
        FragmentProductDetailsBinding,
        ProductViewModel,
        ProductRepository
        >(FragmentProductDetailsBinding::inflate),
    View.OnClickListener,
    RecycleItemClick,
    RecycleItemClickListener {

    private lateinit var product: ProductDetailModel

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        initViews()
    }

    private fun initViews() {
        with(viewBinding) {

            // make text strike threw
            oldRate.paintFlags = oldRate.paintFlags or Paint.STRIKE_THRU_TEXT_FLAG
            oldRate1.paintFlags = oldRate1.paintFlags or Paint.STRIKE_THRU_TEXT_FLAG
            textView59.paintFlags = textView59.paintFlags or Paint.STRIKE_THRU_TEXT_FLAG
            textView590.paintFlags = textView590.paintFlags or Paint.STRIKE_THRU_TEXT_FLAG

            sizeChart.setOnClickListener {
                findNavController().navigate(R.id.action_productDetailsFragment_to_productDetailsSizeChart)
            }

            viewEmiPlan.setOnClickListener {
                findNavController().navigate(R.id.action_productDetailsFragment_to_productDetailsEMIPlansFragment)
            }

            wishlist.setOnClickListener {
                findNavController().navigate(R.id.action_productDetailsFragment_to_wishlistWithProductFragment)
            }

            addToBeg.setOnClickListener {
                findNavController().navigate(R.id.action_productDetailsFragment_to_shoppingBegWithProductFragment)
            }

            /*floatingActionButton.setOnClickListener{
                nestedScrollView.smoothScrollTo(0,0)
            }*/

            val args: ProductDetailsFragmentArgs by navArgs()
            val productsName = args.productName
            getProductDetails(productsName)
        }

        val data = DummyData().getSingleProductImage()
        val singleProduct = DummyData().getSingleProduct()

        handleClickListeners()

        setAllCustomerReviewImages()

        setUserReview()

        //setProductSize()
    }

    private fun getProductDetails(productsName: String) {

        //@todo add product name in productDetails function
        viewModel
            .productDetails("iffalcon-108-cm-43-inches-4k-ultra-hd-certified-android-smart-led-tv-43u61-black-2021-model")
            .observe(viewLifecycleOwner, Observer {
                when (it) {
                    is Resource.Loading -> showLoading()

                    is Resource.Success -> {
                        stopShowingLoading()

                        Log.e("TAG", "getProductDetails: ${it.value.result.id}")

                        with(viewBinding) {
                            product = it.value
                            // set product details
                            productName.text = it.value.result.name
                            oldRate.text = it.value.result.price.toString()
                            newRate.text = it.value.result.sellingPrice.toString()
                            newRate1.text = it.value.result.sellingPrice.toString()
                            val discount = "(${it.value.result.discountedPrice}%Off)"
                            off.text = discount
                            //sellerName.text = it.value.vendor
                            productInDetail.text = html2Text(it.value.result.desc)

                            setViewSimilar(it.value.related)
                            setAutoImageSlider(it.value.result.file)
                            setProductSmallImages(it.value.result.file)
                            setProductColor()

                            // set product options
                            //@todo get product size and color in manner
                            val optionsList = it.value.result.options
                            for (option in optionsList.indices) {

                                val optionName = optionsList[option].name
                                val optionValue = optionsList[option].value

                                if (optionName == "Size") {

                                    Log.e("TAG", "getProductDetails: $optionValue")

                                    viewBinding.txtSize.visibility = View.VISIBLE
                                    viewBinding.rvSize.visibility = View.VISIBLE

                                    val sizeList = stringToList(optionValue)

                                    Log.e("TAG", "size list : ${stringToList(optionValue)}")
                                    //option is coming in string form seperated with comma
                                    //so we have exclude it


                                    //setProductSize(option.value)
                                } else if (optionName == "Color") {
                                    //setProductColor()
                                }
                            }

                        }
                    }

                    is Resource.Failure -> Log.e("TAG", "getProductDetails: $it")
                }
            })
    }

    private fun stringToList(optionValue: String): List<String> {
        val list = ArrayList<String>()
        val stringSize = optionValue.length
        var singleValue: String = ""

        for (i in 0 until stringSize) {

            Log.e("TAG", "i value: ${optionValue[i]}\n")

            val singleChar = optionValue[i]

            singleValue = if (singleChar == ',') {
                list.add(singleValue)
                ""
            } else {
                "$singleValue$singleChar"
            }
        }

        return list
    }

    private fun html2Text(desc: String) =
        android.text.Html.fromHtml(desc, HtmlCompat.FROM_HTML_MODE_LEGACY).toString()

    private fun setProductColor() {
        with(viewBinding.colors) {
            rvColors.apply {
                layoutManager = LinearLayoutManager(context, LinearLayoutManager.HORIZONTAL, false)
                adapter = ProductColorAdapter(
                    context,
                    DummyData().getProductSize(),
                    this@ProductDetailsFragment
                )
            }
        }
    }

    private fun setProductSize(sizes: List<String>) {
        with(viewBinding) {
            rvSize.apply {
                layoutManager = LinearLayoutManager(context, LinearLayoutManager.HORIZONTAL, false)
                adapter = ProductSizeAdapter(
                    context,
                    DummyData().getProductSize(),
                    this@ProductDetailsFragment
                )
            }
        }
    }

    private fun handleClickListeners() {
        with(viewBinding) {
            imgBackBackground.setOnClickListener(this@ProductDetailsFragment)
            wishlist.setOnClickListener(this@ProductDetailsFragment)
            addToBeg.setOnClickListener(this@ProductDetailsFragment)
            imgBegBackground.setOnClickListener(this@ProductDetailsFragment)
        }
    }

    private fun setViewSimilar(relatedProduct: List<ProductDetailModel.Related>) {
        with(viewBinding) {
            rvViewSimilar.apply {
                layoutManager = LinearLayoutManager(context, LinearLayoutManager.HORIZONTAL, false)
                adapter = Card10Adapter(context, relatedProduct, this@ProductDetailsFragment)
            }
        }
    }

    private fun setUserReview() {
        with(viewBinding) {
            rvUserReview.apply {
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

    private fun setProductSmallImages(data: List<ProductDetailModel.Result.File>) {
        with(viewBinding) {
            rvProductImage.apply {
                layoutManager = LinearLayoutManager(context, LinearLayoutManager.VERTICAL, false)
                adapter = SingleProductImagesAdatper(context, data)
            }
        }
    }


    private fun setAutoImageSlider(data: List<ProductDetailModel.Result.File>) {
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
        when (v?.id) {
            R.id.wishlist -> {
                addToWishlist()
            }
            R.id.addToBeg -> {
                addToCart()
            }

            R.id.imgBack -> findNavController().popBackStack()
            R.id.imgBeg -> findNavController().navigate(R.id.action_productDetailsFragment_to_shoppingBegWithoutProductFragment)
        }
    }

    private fun addToCart() {

    }

    private fun addToWishlist() {
        val productId = product.result.id
        val appUtils = context?.let { AppUtils(it) }

        if (appUtils!!.isUserLoggedIn()) {
            val userId = appUtils.getUserId()

            viewModel.addToWishList(productId, userId).observe(viewLifecycleOwner, Observer {
                when(it){
                    is Resource.Loading -> showLoading()

                    is Resource.Success -> {
                        stopShowingLoading()
                        toast(it.value.message)
                    }

                    is Resource.Failure -> Log.e("wishlist", "addToWishlist: ${it.toString()}", )
                }
            })
        }

        else{
            //@todo if user is not logged in then save product in local database
        }
    }

    override fun getFragmentBinding(
        inflater: LayoutInflater,
        container: ViewGroup?
    ) = FragmentProductDetailsBinding.inflate(inflater, container, false)

    override fun getViewModel() = ProductViewModel::class.java

    override fun getFragmentRepository() =
        ProductRepository(remoteDataSource.getBaseUrl().create(ProductApi::class.java))

    override fun onItemClick(name: String, position: Int) {
        viewBinding.size.text = name
    }

    override fun onItemClickWithView(position: Int, view: View) {
        TODO("Not yet implemented")
    }

    override fun onSingleItemClickListener(position: Int) {
        TODO("Not yet implemented")
    }

    override fun itemClickWithName(name: String) {
        val action = ProductDetailsFragmentDirections.actionOpenProductDetails("name")
        findNavController().navigate(action)
        //getProductDetails(name)
        // viewBinding.nestedScrollView.fullScroll(ScrollView.FOCUS_UP)
    }

}