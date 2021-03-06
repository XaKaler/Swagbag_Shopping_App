package com.shopping.swagbag.products.product_details

import android.content.Intent
import android.graphics.Paint
import android.graphics.drawable.ColorDrawable
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.content.ContextCompat
import androidx.lifecycle.Observer
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import androidx.recyclerview.widget.LinearLayoutManager
import com.google.gson.Gson
import com.shopping.swagbag.R
import com.shopping.swagbag.common.RecycleViewItemClick
import com.shopping.swagbag.common.adapter.ProductImageSliderAdapter
import com.shopping.swagbag.common.base.BaseFragment
import com.shopping.swagbag.databinding.FragmentProductDetailsBinding
import com.shopping.swagbag.dummy.DummyData
import com.shopping.swagbag.products.*
import com.shopping.swagbag.service.apis.ProductApi
import com.shopping.swagbag.service.Resource
import com.shopping.swagbag.user.shoppingbeg.withproduct.AddToCartProductOptionModel
import com.shopping.swagbag.utils.AppUtils
import com.smarteist.autoimageslider.SliderView
import kotlinx.android.synthetic.main.single_card_10.*
import org.json.JSONArray
import kotlin.properties.Delegates


class ProductDetailsFragment : BaseFragment<
        FragmentProductDetailsBinding,
        ProductViewModel,
        ProductRepository
        >(FragmentProductDetailsBinding::inflate),
    View.OnClickListener,
    RecycleViewItemClick {

    private lateinit var product: ProductDetailModel

    private var sizeList = ArrayList<ProductOptionModel>()
    private var colorList = ArrayList<ProductOptionModel>()

    private var finalPrice: Float = 0.0F
    private var finalSize: ProductOptionModel? = null
    private var finalColor: ProductOptionModel? = null
    private lateinit var jsArray: JSONArray

    private var sellingPrice: Float = 0.0F

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        initViews()
    }

    private fun initViews() {
        with(viewBinding) {
            // set foreground color color of screen to white
            // and remove after data get from api
            productDetailsMainLayout.foreground =
                context?.let { it1 ->
                    ContextCompat.getColor(
                        it1,
                        R.color.white
                    )
                }?.let { it2 -> ColorDrawable(it2) }

            /*// make text strike threw
            oldRate.paintFlags = oldRate.paintFlags or Paint.STRIKE_THRU_TEXT_FLAG
            oldRate1.paintFlags = oldRate1.paintFlags or Paint.STRIKE_THRU_TEXT_FLAG
            textView59.paintFlags = textView59.paintFlags or Paint.STRIKE_THRU_TEXT_FLAG
            textView590.paintFlags = textView590.paintFlags or Paint.STRIKE_THRU_TEXT_FLAG*/

            sizeChart.setOnClickListener {
                val action =
                    ProductDetailsFragmentDirections.actionProductDetailsFragmentToProductDetailsSizeChart(
                        gsonToJsonString(product, ProductDetailModel::class.java)
                    )
                findNavController().navigate(action)
            }

            viewEmiPlan.setOnClickListener {
                findNavController().navigate(R.id.action_productDetailsFragment_to_productDetailsEMIPlansFragment)
            }

        }

        getArgument()

        handleClickListeners()

        setAllCustomerReviewImages()

        setUserReview()
    }

    private fun getArgument() {
        val args: ProductDetailsFragmentArgs by navArgs()
        val productsName = args.productName

        if (this@ProductDetailsFragment::product.isInitialized)
            setData()
        else
            getProductDetails(productsName)
    }

    private fun setData() {
        try{
            with(viewBinding) {
                // remove foreground color so data can visible to user
                productDetailsMainLayout.foreground = null

                // set product details
                productName.text = product.result.name

                sellingPrice = product.result.price
                //finalPrice = sellingPrice
                newRate.text = sellingPrice.toString()

                if(product.result.discountedPrice == null || product.result.discountedPrice == 0)
                    off.visibility = View.GONE
                else {
                    off.visibility = View.VISIBLE
                    val discount = "(${product.result.discountedPrice}%Off)"
                    off.text = discount
                }
                //sellerName.text = it.value.vendor
                productInDetail.text = html2Text(product.result.details)
                description.text = html2Text(product.result.desc)
                additionalDescription.text = html2Text(product.result.additionalDescription)

                setViewSimilar(product.related)
                setAutoImageSlider(product.result.file)
                setProductSmallImages(product.result.file)

                //set user review
                val review = product.review
                if (review.isNotEmpty())
                    setUserReview()
                else {
                    viewBinding.review.visibility = View.GONE
                    viewBinding.topRating.root.visibility = View.GONE
                }

                // set product options like color or size
                val optionsList = product.result.options

                if (optionsList.isNotEmpty()) {
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
                        } else if (optionName == "Colour") {
                            viewBinding.colors.root.visibility = View.VISIBLE
                            colorList = stringToList(optionValue)
                            setProductColor(colorList)
                        }
                    }
                } else viewBinding.optionCard.visibility = View.GONE

            }
        }catch(e: Exception){
            Log.e("TAG", "setData: $e", )
        }

    }

    private fun getProductDetails(productsName: String) {
        Log.e("product", "Product name is : $productsName")

        viewModel
            .productDetails(productsName)
            .observe(viewLifecycleOwner) {
                when (it) {
                    is Resource.Loading -> showLoading()

                    is Resource.Success -> {
                        stopShowingLoading()

                        product = it.value
                        setData()
                       // Log.e("product detail", "getProductDetails: $it")

                    }

                    is Resource.Failure -> {
                        stopShowingLoading()
                        toast("try again")
                        findNavController().popBackStack()
                        Log.e("TAG", "getProductDetails: $it")
                    }
                }
            }
    }

    private fun stringToList(optionValue: String): ArrayList<ProductOptionModel> {
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
        list.add(ProductOptionModel(value, price, sku, qty))
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
            imgShareBackground.setOnClickListener(this@ProductDetailsFragment)
            btnRateProduct.setOnClickListener{rateProduct()}
        }
    }

    private fun rateProduct() {
        val action = ProductDetailsFragmentDirections.actionProductDetailsFragmentToProductRatingFragment(
            Gson().toJson(product, ProductDetailModel::class.java)
        )

        findNavController().navigate(action)
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
            R.id.wishlist -> addToWishlist()
            R.id.addToBeg -> {
                when (viewBinding.addToBeg.text) {
                    "Go to beg" -> findNavController().navigate(R.id.action_global_shoppingBegWithProductFragment)
                    else -> getUserSelectedOptions()
                }
            }

            R.id.imgWishlistBackground -> {
                if (context?.let { AppUtils(it).isUserLoggedIn() } == true)
                    findNavController().navigate(R.id.action_global_wishlistWithProductFragment)
                else
                    findNavController().navigate(R.id.action_global_signInFragment)

            }
            R.id.imgBackBackground -> findNavController().popBackStack()
            R.id.imgBegBackground -> {
                if (context?.let { AppUtils(it).isUserLoggedIn() } == true)
                    findNavController().navigate(R.id.action_global_shoppingBegWithProductFragment)
                else
                    findNavController().navigate(R.id.action_global_signInFragment)
            }
            R.id.imgShareBackground -> shareProduct()
        }
    }

    private fun shareProduct() {
        val sharingIntent = Intent(Intent.ACTION_SEND)
        sharingIntent.type = "text/plain"
        val shareBody =
            "https://uae.swagbag.com/${product.result.masterCategory[0]}/product/${product.result.slug}"
        sharingIntent.putExtra(Intent.EXTRA_SUBJECT, "Subject Here")
        sharingIntent.putExtra(Intent.EXTRA_TEXT, shareBody)
        startActivity(Intent.createChooser(sharingIntent, "Share via"))
    }

    private fun getUserSelectedOptions() {
        // add product size or color
        val optionList = ArrayList<AddToCartProductOptionModel>()

        if (optionList.isEmpty()) {
            if (sizeList.isEmpty() && colorList.isEmpty()) {
                //optionList.add(AddToCartProductOptionModel("", "", ""))
                checkCart(optionList)
            } else if (sizeList.isEmpty() && colorList.isNotEmpty()) {
                if (finalColor == null)
                    toast("Choose color")
                else {
                    optionList.add(
                        AddToCartProductOptionModel(
                            "Color",
                            finalColor!!.value,
                            finalColor!!.price
                        )
                    )
                    checkCart(optionList)
                }
            } else if (sizeList.isNotEmpty() && colorList.isEmpty()) {
                if (finalSize == null)
                    toast("Choose size")
                else {
                    optionList.add(
                        AddToCartProductOptionModel(
                            "Size",
                            finalSize!!.value,
                            finalSize!!.price
                        )
                    )
                    checkCart(optionList)
                }
            } else if (sizeList.isNotEmpty() || colorList.isNotEmpty()) {
                // add size
                if (finalSize == null || finalColor == null) {
                    if (finalSize == null)
                        toast("Choose size")
                    else {
                        optionList.add(
                            AddToCartProductOptionModel(
                                "Size",
                                finalSize!!.value,
                                finalSize!!.price
                            )
                        )
                    }

                    // add color
                    if (finalColor == null)
                        toast("Choose color")
                    else
                        optionList.add(
                            AddToCartProductOptionModel(
                                "Color",
                                finalColor!!.value,
                                finalColor!!.price
                            )
                        )
                }
                else
                    checkCart(optionList)
            }
        } else
            checkCart(optionList)

    }

    private fun checkCart(optionList: ArrayList<AddToCartProductOptionModel>) {
        val isUserLogIn = context?.let { AppUtils(it).isUserLoggedIn() }

        if (isUserLogIn == true) {
            val userId = context?.let { AppUtils(it).getUserId() }
            if (userId != null) {
                viewModel.getCart(userId).observe(viewLifecycleOwner) {
                    when (it) {
                        is Resource.Loading -> showLoading()

                        is Resource.Success -> {
                            stopShowingLoading()

                            val cartProductList = it.value.result

                            if (cartProductList != null) {
                                var isFound = false
                                for (singleProduct in cartProductList) {
                                    if (singleProduct.product.id == product.result.id) {
                                        updateCart(singleProduct.id, singleProduct.quantity)
                                        isFound = true
                                        break
                                    }
                                }
                                if (!isFound)
                                    addToCart(userId, optionList)
                            } else
                                addToCart(userId, optionList)
                        }
                        is Resource.Failure -> {
                            stopShowingLoading()
                            toast("try again")
                            Log.e("TAG", "checkCart: $it")
                        }
                    }
                }
            }
        } else {
            findNavController().navigate(R.id.action_global_signInFragment)
        }

    }

    private fun updateCart(cartId: String, quantity: Int) {

        val productId = product.result.id
        val qty = quantity + 1

        Log.e(
            "TAG",
            "updateCart: product id  : \n" +
                    "cart id : $cartId" +
                    "product id : $productId\n" +
                    "qty : $qty",
        )

        viewModel.updateCart(cartId, productId, qty.toString()).observe(viewLifecycleOwner) {
            when (it) {
                is Resource.Loading -> showLoading()

                is Resource.Success -> {
                    stopShowingLoading()
                    toast(it.value.message)
                    viewBinding.addToBeg.text = "Go to beg"
                }

                is Resource.Failure -> {
                    stopShowingLoading()
                    toast("try again")
                    Log.e("cart", "updateCart: $it")
                }
            }
        }
    }

    private fun addToCart(userId: String, optionList: ArrayList<AddToCartProductOptionModel>)
    {

        jsArray = JSONArray(optionList.toArray())

        Log.e("option", optionList.toString())

        viewModel
            .addToCart("1", product.result.id, userId, JSONArray(optionList.toArray()))
            .observe(
                viewLifecycleOwner
            ) {
                when (it) {
                    is Resource.Loading -> showLoading()

                    is Resource.Success -> {
                        stopShowingLoading()

                        //set text on button

                        if (it.value.message != "Out of stock")
                            viewBinding.addToBeg.text = "Go to beg"
                        toast(it.value.message)
                    }

                    is Resource.Failure -> {
                        stopShowingLoading()
                        tryAgain()
                        findNavController().popBackStack()
                        Log.e("TAG", "addToCart: $it")
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
                when (it) {
                    is Resource.Loading -> showLoading()

                    is Resource.Success -> {
                        stopShowingLoading()
                        toast(it.value.message)
                    }

                    is Resource.Failure -> Log.e("wishlist", "addToWishlist: ${it.toString()}")
                }
            })
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

                    finalSize = sizeList[position]

                    //set new price
                    if (colorList.isEmpty() || finalColor == null) {
                        finalPrice = sellingPrice + sizeList[position].price.toFloat()

                        Log.e(
                            "size",
                            "color list is empty or final color is null  \n " +
                                    "final color is null: ${finalColor == null} \n" +
                                    "color list is empty ${colorList.isEmpty()}\n" +
                                    "selling price is : $sellingPrice\n" +
                                    "size price is : ${sizeList[position].price}\n" +
                                    "final price is : ${sellingPrice + sizeList[position].price.toFloat()}",
                        )

                    } else {
                        finalPrice = sellingPrice + sizeList[position].price.toFloat() + finalColor!!.price.toFloat()

                        Log.e(
                            "size",
                            "size else -> color list is empty or final color is null  \n " +
                                    "final color is null: ${finalColor == null} \n" +
                                    "color list is empty ${colorList.isEmpty()}\n" +
                                    "selling price is : $sellingPrice\n" +
                                    "final color price is : ${finalColor!!.price}\n" +
                                    "size price is : ${sizeList[position].price}\n" +
                                    "final price is : ${sellingPrice + sizeList[position].price.toFloat() + finalColor!!.price.toFloat()}",
                        )
                    }

                    size.text = finalSize!!.value

                    Log.e("price", "final price after select size: $finalPrice")

                    newRate.text = finalPrice.toString()

                }
            }
            "Color" -> {
                finalColor = colorList[position]

                if (finalSize == null || sizeList.isEmpty()) {
                    finalPrice = sellingPrice + colorList[position].price.toFloat()
                    Log.e(
                        "color",
                        "size list is empty or final color is null  \n " +
                                "final size is null: ${finalSize == null} \n" +
                                "size list is empty ${sizeList.isEmpty()}\n" +
                                "selling price is : $sellingPrice\n" +
                                "color price is : ${colorList[position].price}\n" +
                                "final price is : ${sellingPrice + colorList[position].price.toFloat()}",
                    )

                } else {
                    finalPrice = sellingPrice + finalSize!!.price.toFloat() + colorList[position].price.toFloat()

                    Log.e(
                        "color",
                        "color else -> size list is empty or final color is null  \n " +
                                "final size is null: ${finalSize == null} \n" +
                                "size list is empty ${sizeList.isEmpty()}\n" +
                                "selling price is : $sellingPrice\n" +
                                "final size price is : ${finalSize!!.price}\n" +
                                "color price is : ${colorList[position].price}\n" +
                                "final price is : ${sellingPrice + finalSize!!.price.toFloat() + colorList[position].price.toFloat() }",
                    )
                }


                viewBinding.newRate.text = finalPrice.toString()
                Log.e("price", "final price after select color: $finalPrice")

            }
            else -> {
                val action = ProductDetailsFragmentDirections.actionProductDetailsFragmentSelf(name)
                findNavController().navigate(action)
            }
        }
    }

}