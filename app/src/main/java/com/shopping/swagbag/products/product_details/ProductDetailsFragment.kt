package com.shopping.swagbag.products.product_details

import android.graphics.Paint
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.Observer
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import androidx.recyclerview.widget.LinearLayoutManager
import com.shopping.swagbag.R
import com.shopping.swagbag.common.RecycleViewItemClick
import com.shopping.swagbag.common.adapter.ProductImageSliderAdapter
import com.shopping.swagbag.common.base.BaseFragment
import com.shopping.swagbag.databinding.FragmentProductDetailsBinding
import com.shopping.swagbag.dummy.DummyData
import com.shopping.swagbag.products.*
import com.shopping.swagbag.service.Resource
import com.shopping.swagbag.utils.AppUtils
import com.smarteist.autoimageslider.SliderView
import kotlin.properties.Delegates

class ProductDetailsFragment : BaseFragment<
        FragmentProductDetailsBinding,
        ProductViewModel,
        ProductRepository
        >(FragmentProductDetailsBinding::inflate),
    View.OnClickListener,
    RecycleViewItemClick {

    private lateinit var product: ProductDetailModel
    private lateinit var sizeList: List<ProductOptionModel>
    private lateinit var colorList: List<ProductOptionModel>
    private var finalPrice by Delegates.notNull<Int>()
    private var finalSize: String = ""
    private var finalColor: String = ""
    private var sellingPrice by Delegates.notNull<Int>()

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
                addToCart()
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
    }

    private fun getProductDetails(productsName: String) {

        //@todo add product name in productDetails function
        Log.e("product", "Product name is : $productsName", )

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
                            sellingPrice = it.value.result.sellingPrice
                            newRate.text = sellingPrice.toString()
                            val discount = "(${it.value.result.discountedPrice}%Off)"
                            off.text = discount
                            //sellerName.text = it.value.vendor
                            productInDetail.text = html2Text(it.value.result.desc)

                            setViewSimilar(it.value.related)
                            setAutoImageSlider(it.value.result.file)
                            setProductSmallImages(it.value.result.file)

                            // set product options
                            //@todo get product size and color in manner
                            val optionsList = it.value.result.options
                            for (option in optionsList.indices) {

                                val optionName = optionsList[option].name
                                val optionValue = optionsList[option].value

                                if (optionName == "Size") {
                                    with(viewBinding) {
                                        txtSize.visibility = View.VISIBLE
                                        rvSize.visibility = View.VISIBLE
                                        size.visibility = View.VISIBLE
                                        sizeChart.visibility = View.VISIBLE

                                        sizeList = stringToList(optionValue)
                                        setProductSize(sizeList)
                                    }
                                    //option is coming in string form seperated with comma
                                    //so we have exclude it
                                } else if (optionName == "Color") {
                                    viewBinding.colors.root.visibility = View.VISIBLE
                                    colorList = stringToList(optionValue)
                                    setProductColor(colorList)
                                }
                            }

                        }
                    }

                    is Resource.Failure -> Log.e("TAG", "getProductDetails: $it")
                }
            })
    }

    private fun stringToList(optionValue: String): List<ProductOptionModel> {
        //1. remove all white spaces from string
        //2. iterate string
        //3. value:price:sku:qty these are 4 parameters in string
        //make list of them

        val updatedOptionValue = optionValue.replace("\\s".toRegex(), "")

        Log.e("TAG", "Remove white space: $updatedOptionValue")

        val list = ArrayList<ProductOptionModel>()

        var colonCount = 0
        var value = ""
        var price = ""
        var sku = ""
        var qty = ""

        for (option in updatedOptionValue) {

            if (option == ':') colonCount += 1
            else if (option == ',') {
                colonCount = 0
                list.add(ProductOptionModel(value, price, sku, qty))

                Log.e("TAG", "option == ,: $list  ")

                value = ""
                price = ""
                sku = ""
                qty = ""
            } else {
                when (colonCount) {
                    0 -> value += option
                    1 -> price += option
                    2 -> sku += option
                    3 -> qty += option
                }
            }
        }
        return list
    }

    private fun setProductColor(colors: List<ProductOptionModel>) {
        with(viewBinding.colors) {
            rvColors.apply {
                layoutManager = LinearLayoutManager(context, LinearLayoutManager.HORIZONTAL, false)
                adapter = ProductColorAdapter(
                    context,
                    colors,
                    this@ProductDetailsFragment
                )
            }
        }
    }

    private fun setProductSize(sizes: List<ProductOptionModel>) {
        with(viewBinding) {
            rvSize.apply {
                layoutManager = LinearLayoutManager(context, LinearLayoutManager.HORIZONTAL, false)
                adapter = ProductSizeAdapter(
                    context,
                    sizes,
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
                SliderView.AUTO_CYCLE_DIRECTION_BACK_AND_FORTH

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
        val appUtils = context?.let { AppUtils(it) }

        if (appUtils!!.isUserLoggedIn()) {
            viewModel
                .addToCart("1", product.result.id, appUtils.getUserId(), "")
                .observe(
                    viewLifecycleOwner
                ) {
                    when(it){
                        is Resource.Loading -> showLoading()

                        is Resource.Success -> {
                            stopShowingLoading()

                            toast(it.value.message)
                        }

                        is Resource.Failure -> Log.e("TAG", "addToCart: $it", )
                    }
                }
        }
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

    override fun onItemClickWithName(name: String, position: Int) {
        when (name) {
            "Size" -> {
                with(viewBinding) {
                    Log.e("size", "size that user choose: $name", )
                    finalSize = sizeList[position].value
                    size.text = finalSize

                    //set new price
                    Log.e("price", "final price is : $finalPrice\nsize price is : ${sizeList[position].price}", )

                    finalPrice += sizeList[position].price.toInt()
                    newRate.text = finalPrice.toString()
                }
            }
            "Color" -> {
                finalColor = colorList[position].value

                finalPrice += colorList[position].price.toInt()
                viewBinding.newRate.text = finalPrice.toString()
            }
            else -> {
                val action = ProductDetailsFragmentDirections.actionProductDetailsFragmentSelf(name)
                findNavController().navigate(action)
            }
        }
    }

}