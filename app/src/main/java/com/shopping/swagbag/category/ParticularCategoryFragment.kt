package com.shopping.swagbag.category

import android.content.Context
import android.graphics.drawable.ColorDrawable
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.activity.addCallback
import androidx.core.content.ContextCompat
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import com.bumptech.glide.Glide
import com.google.gson.Gson
import com.shopping.swagbag.R
import com.shopping.swagbag.common.GridSpaceItemDecoration
import com.shopping.swagbag.common.RecycleViewItemClick
import com.shopping.swagbag.common.adapter.AllTimeSliderAdapter
import com.shopping.swagbag.common.adapter.BestProductAdapter
import com.shopping.swagbag.common.adapter.CategoryToBegAdapter
import com.shopping.swagbag.common.base.BaseFragment
import com.shopping.swagbag.common.model.AllTimeSliderModel
import com.shopping.swagbag.common.model.BestProductModel
import com.shopping.swagbag.common.model.TopTrendingModel
import com.shopping.swagbag.databinding.FragmentParticularCategoryBinding
import com.shopping.swagbag.home.HomeDirections
import com.shopping.swagbag.home.TopTrendingAdapter
import com.shopping.swagbag.main_activity.MainActivity
import com.shopping.swagbag.products.ProductSearchParameters
import com.shopping.swagbag.products.product_details.ProductDetailsFragmentDirections
import com.shopping.swagbag.service.Resource
import com.shopping.swagbag.service.apis.CategoryApi

class ParticularCategoryFragment :
    BaseFragment<FragmentParticularCategoryBinding,
            CategoryViewModel,
            CategoryRepository>(FragmentParticularCategoryBinding::inflate),
RecycleViewItemClick{

    private lateinit var categoryData: ParticularCategoryModel
    private lateinit var mainActivity: MainActivity
    private lateinit var categoryName: String


    override fun onAttach(context: Context) {
        super.onAttach(context)

        mainActivity = context as MainActivity

        if(mainActivity !is MainActivity) {
            Log.e("TAG", "onAttach: is instance of main actvity", )
        }
        else{
            Log.e("TAG", "onAttach:not is instance of main actvity", )
        }


    }
    
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        // handle back pressed
        requireActivity().onBackPressedDispatcher.addCallback(viewLifecycleOwner) {
            findNavController().navigate(R.id.action_global_home2)
        }

        initViews()
        mainActivity.showToolbarAndBottomNavigation()
    }

    private fun initViews() {
        // set foreground color color of screen to white
        // and remove after data get from api
        viewBinding.scrlParticularCategory.foreground =
            context?.let { it1 ->
                ContextCompat.getColor(
                    it1,
                    R.color.white
                )
            }?.let { it2 -> ColorDrawable(it2) }

        getCategoryData()
    }

    private fun getCategoryData() {
        val args: ParticularCategoryFragmentArgs by navArgs()
        val categoryId = args.categoryId

        for(singleCategory in mainActivity.getMasterCategories()){
            if(categoryId == singleCategory.id)
                categoryName = singleCategory.slug
        }

        Log.e("TAG", "getCategoryData: $categoryId", )

        viewModel.particularCategory(categoryId).observe(viewLifecycleOwner){
            when(it){
                is Resource.Loading -> showLoading()

                is Resource.Success -> {
                    stopShowingLoading()

                    // remove foreground color so
                    viewBinding.scrlParticularCategory.foreground = null

                    val result = it.value.result
                    categoryData = it.value
                   setTopTrending(result.section)
                    showOfferImages()
                    setCategoryToBeg(result.category)
                    setBestOffer(result.featured)
                    setDealOfTheDay(result.deals)
                    setFeatureBrands(result.brand)


                }

                is Resource.Failure -> Log.e("TAG", "getCategoryData: $it", )
            }
        }

    }

    override fun getFragmentBinding(
        inflater: LayoutInflater,
        container: ViewGroup?
    ) = FragmentParticularCategoryBinding.inflate(inflater, container, false)

    override fun getViewModel() = CategoryViewModel::class.java

    override fun getFragmentRepository() =
        CategoryRepository(remoteDataSource.getBaseUrl().create(CategoryApi::class.java))


    private fun setTopTrending(data: List<ParticularCategoryModel.Result.Section>) {
        val topTrendingData = ArrayList<TopTrendingModel>()

        for (singleData in data) {
            singleData.run {
                topTrendingData.add(
                    TopTrendingModel(
                        active,
                        brand,
                        category,
                        createdDate,
                        deleted,
                        file,
                        id,
                        masterCategory,
                        product,
                        section,
                        updateDate,
                        url,
                        v
                    )
                )
            }
        }

        with(viewBinding) {
            rvTopTrending.apply {
                layoutManager = GridLayoutManager(context, 2)
                addItemDecoration(GridSpaceItemDecoration(20))
                adapter =
                    TopTrendingAdapter(context, topTrendingData, object : RecycleViewItemClick {
                        override fun onItemClickWithName(name: String, position: Int) {
                            when (name) {
                                "brand" -> {
                                    mainActivity.hideToolbarAndBottomNavigation()
                                    val productSearchParameters = ProductSearchParameters(
                                        "",
                                        data[position].brand,
                                        "",
                                        "",
                                        "",
                                        "",
                                        "",
                                        data[position].masterCategory,
                                        ""
                                    )
                                    val action = ParticularCategoryFragmentDirections.actionParticularCategoryFragmentToProductsFragment(
                                        Gson().toJson(
                                            productSearchParameters,
                                            ProductSearchParameters::class.java
                                        )
                                    )
                                    findNavController().navigate(action)
                                }

                                "category" -> {
                                    mainActivity.hideToolbarAndBottomNavigation()
                                    val productSearchParameters = ProductSearchParameters(
                                        "",
                                        "",
                                        "",
                                        data[position].category,
                                        "",
                                        "",
                                        "",
                                        data[position].masterCategory,
                                        ""
                                    )
                                    val action = ParticularCategoryFragmentDirections.actionParticularCategoryFragmentToProductsFragment(
                                        Gson().toJson(
                                            productSearchParameters,
                                            ProductSearchParameters::class.java
                                        )
                                    )
                                    findNavController().navigate(action)
                                }

                                "product" -> {
                                    mainActivity.hideToolbarAndBottomNavigation()

                                    val action =
                                        ParticularCategoryFragmentDirections.actionGlobalProductDetailsFragment(
                                            data[position].product
                                        )
                                    findNavController().navigate(action)
                                }
                            }
                        }
                    })
            }
        }
    }

    private fun showOfferImages() {
        with(viewBinding) {
            context?.let {
                Glide.with(it)
                    .load("https://uae.swagbag.com/img/down1.png")
                    .centerCrop()
                    .into(homeImg3)

                Glide.with(it)
                    .load("https://uae.swagbag.com/img/down2.jpg")
                    .centerCrop()
                    .into(homeImg5)


            }
        }
    }

    private fun setCategoryToBeg(data: List<ParticularCategoryModel.Result.Category>) {
        val master = ArrayList<CategoryToBegModel>()

        for (item in data) {
            master.add(
                CategoryToBegModel(
                    item.description,
                    item.file,
                    item.id,
                    item.name,
                    item.descriptionAfterContent,
                )
            )
        }

        Log.e("TAG", "setCategoryToBeg: $data", )
        with(viewBinding) {
            rvCategoryToBag.apply {
                layoutManager = GridLayoutManager(context, 3)
                adapter = CategoryToBegAdapter(context, master, object : RecycleViewItemClick{
                    override fun onItemClickWithName(name: String, position: Int) {
                        when (name) {
                            "products" -> {
                                mainActivity.hideToolbarAndBottomNavigation()
                                val productSearchParameters = ProductSearchParameters(
                                    "", "", "", categoryData.result.category[position].id,
                                    "", "", "", categoryName, ""
                                )

                                val action = ParticularCategoryFragmentDirections.actionGlobalProductsFragment(
                                    Gson().toJson(productSearchParameters, ProductSearchParameters::class.java))
                                findNavController().navigate(action)
                            }
                        }
                    }
                })
            }
        }

    }

    private fun setBestOffer(data: List<ParticularCategoryModel.Result.Featured>) {
        val bestProductModel = ArrayList<BestProductModel>()

        for (item in data) {
            val file = ArrayList<BestProductModel.File>()

            for (fileItem in item.file) {
               file.add(
                   BestProductModel.File(fileItem.location)
               )
            }

            bestProductModel.add(
                BestProductModel(
                    html2Text(item.desc),
                    item.shortDesc,
                    file,
                    item.id,
                    item.name
                )
            )
        }

        Log.e("TAG", "setBestOffer: $bestProductModel", )

        with(viewBinding) {
            rvBestOffer.apply {
                layoutManager = GridLayoutManager(context, 2)
                adapter = BestProductAdapter(context, bestProductModel, this@ParticularCategoryFragment)
            }
        }

    }

    private fun setDealOfTheDay(data: List<ParticularCategoryModel.Result.Deal>) {
        val bestProductModel = ArrayList<BestProductModel>()

        for (item in data) {
            val file = ArrayList<BestProductModel.File>()
            for (fileItem in item.file) {
                file.add(BestProductModel.File(
                    fileItem.location
                ))
            }

            bestProductModel.add(
                BestProductModel(
                    html2Text(item.desc),
                    item.shortDesc,
                    file,
                    item.id,
                    item.name
                )
            )
        }

        with(viewBinding) {
            rvPromotedBrands.apply {
                layoutManager = GridLayoutManager(context, 2)
                adapter = BestProductAdapter(context, bestProductModel, this@ParticularCategoryFragment )
            }
        }
    }

    private fun setFeatureBrands(data: List<ParticularCategoryModel.Result.Brand>) {
        val allTimeSliderModel = ArrayList<AllTimeSliderModel>()

        for (item in data) {
            allTimeSliderModel.add(
                AllTimeSliderModel(
                    item.desc,
                    item.file.toString(),
                    item.id,
                    "",
                    item.name
                )
            )
        }

        with(viewBinding) {
            rvKidsPicks.apply {
                layoutManager = LinearLayoutManager(context, LinearLayoutManager.HORIZONTAL, false)
                adapter = AllTimeSliderAdapter(context, allTimeSliderModel, this@ParticularCategoryFragment)
            }
        }
    }

    override fun onItemClickWithName(name: String, position: Int) {
        mainActivity.hideToolbarAndBottomNavigation()
        val action = ProductDetailsFragmentDirections.actionGlobalProductDetailsFragment(name)
        findNavController().navigate(action)
    }
}