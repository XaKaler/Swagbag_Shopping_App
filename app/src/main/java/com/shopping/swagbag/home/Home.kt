package com.shopping.swagbag.home

import android.content.Context
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.activity.addCallback
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.GridLayoutManager
import com.bumptech.glide.Glide
import com.google.gson.Gson
import com.shopping.swagbag.category.CategoryToBegModel
import com.shopping.swagbag.common.GridSpaceItemDecoration
import com.shopping.swagbag.common.RecycleViewItemClick
import com.shopping.swagbag.common.adapter.AutoImageSliderAdapter
import com.shopping.swagbag.common.adapter.BestProductAdapter
import com.shopping.swagbag.common.adapter.CategoryToBegAdapter
import com.shopping.swagbag.common.base.BaseFragment
import com.shopping.swagbag.common.model.BestProductModel
import com.shopping.swagbag.common.model.TopTrendingModel
import com.shopping.swagbag.databinding.FragmentHomeBinding
import com.shopping.swagbag.main_activity.MainActivity
import com.shopping.swagbag.products.ProductRepository
import com.shopping.swagbag.products.ProductSearchParameters
import com.shopping.swagbag.products.ProductViewModel
import com.shopping.swagbag.products.product_details.ProductDetailsFragmentDirections
import com.shopping.swagbag.service.Resource
import com.shopping.swagbag.service.apis.ProductApi
import com.smarteist.autoimageslider.IndicatorView.animation.type.IndicatorAnimationType
import com.smarteist.autoimageslider.SliderAnimations
import com.smarteist.autoimageslider.SliderView
import kotlin.system.exitProcess


class Home :
    BaseFragment<FragmentHomeBinding, ProductViewModel, ProductRepository>(FragmentHomeBinding::inflate),
RecycleViewItemClick{

    private lateinit var mainActivity: MainActivity
    private lateinit var homeResult: HomeModel

    override fun onAttach(context: Context) {
        super.onAttach(context)

        mainActivity = context as MainActivity

        if(mainActivity !is MainActivity) {
            Log.e("TAG", "onAttach: is instance of main activity")
        }
        else{
            Log.e("TAG", "onAttach:not is instance of main activity")
        }
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        // handle back pressed
        requireActivity().onBackPressedDispatcher.addCallback(viewLifecycleOwner) {
            exitProcess(0)
        }

        initViews()
    }

    private fun initViews() {
        mainActivity.showToolbarAndBottomNavigation()
        mainActivity.setMasterCategories()

        homeResult = mainActivity.getHome()
        setData()

    }

    private fun setData(){
        setAutoImageSlider(homeResult.result.slider)
        setTopTrending(homeResult.result.masterCategory)
        setCategoryToBeg(homeResult.result.masterCategory)
        setDealOfTheDay(homeResult.result.deals)
        //setBestOffer(homeResult.result.randomCategory)
        setFeatureBrands(homeResult.result.featured)
        showOfferImages()
    }

    private fun getHomeData() {
         viewModel.getHome().observe(viewLifecycleOwner){
             when(it){
                 is Resource.Loading ->  showLoading()

                 is Resource.Success -> {
                     stopShowingLoading()

                     Log.e("TAG", "getHomeData: $it")

                     homeResult = it.value

                     setData()
                 }

                 is Resource.Failure -> Log.e("TAG", "getHomeData: $it")
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
    private fun setTopTrending(sections: List<HomeModel.Result.MasterCategory>) {
        val topTrendingData = ArrayList<TopTrendingModel>()

        // collect data from section
        for (singleSection in sections) {
            singleSection.run {
                    topTrendingData.add(
                        TopTrendingModel(
                            active,
                            "",
                            "",
                            createdDate,
                            deleted,
                            file,
                            id,
                            "",
                            "",
                            6,
                            updateDate,
                            "",
                            v
                        )
                    )
                }
        }

        with(viewBinding) {
            rvTopTrending.apply {
                addItemDecoration(GridSpaceItemDecoration(20))
                layoutManager = GridLayoutManager(context, 2)
                adapter =
                    TopTrendingAdapter(context, topTrendingData, object : RecycleViewItemClick {
                        override fun onItemClickWithName(name: String, position: Int) {
                            mainActivity.hideToolbarAndBottomNavigation()
                            val productSearchParameters = ProductSearchParameters(
                                "",
                                "",
                                "",
                                "",
                                "",
                                "",
                                "",
                                sections[position].id,
                                ""
                            )
                            val action = HomeDirections.actionHome2ToProductsFragment(
                                Gson().toJson(
                                    productSearchParameters,
                                    ProductSearchParameters::class.java
                                )
                            )
                            findNavController().navigate(action)

                        }
                    })
            }
        }
    }

    private fun setCategoryToBeg(data: List<HomeModel.Result.MasterCategory>) {
        val master = ArrayList<CategoryToBegModel>()

        for (item in data) {/*
            val data1 = GsonBuilder().create().toJson(item)
            master.add(GsonBuilder().create().fromJson(data1, CategoryToBegModel::class.java))*/
            master.add(
                CategoryToBegModel(
                item.desc,
                item.file,
                item.id,
                item.name,
                item.shortDesc
            )
            )
        }

        with(viewBinding) {
            rvCategoryToBag.apply {
                layoutManager = GridLayoutManager(context, 3)
                adapter = CategoryToBegAdapter(context, master, object: RecycleViewItemClick{
                    override fun onItemClickWithName(name: String, position: Int) {
                        when(name){
                            "products" -> {
                                mainActivity.hideToolbarAndBottomNavigation()
                                val productSearchParameters = ProductSearchParameters(
                                    "",
                                    "",
                                    "",
                                    "",
                                    "",
                                    "",
                                    "",
                                    homeResult.result.masterCategory[position].slug,
                                    ""
                                )
                                val action = HomeDirections.actionHome2ToProductsFragment(Gson().toJson(productSearchParameters, ProductSearchParameters::class.java))
                                findNavController().navigate(action)
                            }
                        }
                    }
                })
            }
        }

    }
/*
    private fun setBestOffer(data: List<HomeModel.Result.RandomCategory>) {
        val allTimeSliderModel = ArrayList<AllTimeSliderModel>()
        for (item in data) {
            allTimeSliderModel.add(
                AllTimeSliderModel(
                    item.description,
                    item.file,
                    item.id,
                    item.master,
                    item.name
                )
            )
        }

        // Log.e("TAG", "setBestOffer: $allTimeSliderModel")

        with(viewBinding) {
            rvBestOffer.apply {
                layoutManager = LinearLayoutManager(context, LinearLayoutManager.HORIZONTAL, false)
                adapter = AllTimeSliderAdapter(context, allTimeSliderModel, this@Home)
            }
        }

    }*/

    private fun setDealOfTheDay(data: List<HomeModel.Result.Deal>) {
        val bestProductModel = ArrayList<BestProductModel>()

        for (item in data) {
            val file = ArrayList<BestProductModel.File>()
            for (fileItem in item.file) {
                file.add(
                    BestProductModel.File(
                        fileItem.location
                    )
                )
            }

            bestProductModel.add(
                BestProductModel(
                    html2Text(item.desc),
                    item.shortDesc,
                    file,
                    item.id,
                    item.slug
                )
            )
        }

        with(viewBinding) {
            rvPromotedBrands.apply {
                layoutManager = GridLayoutManager(context, 2)
                adapter = BestProductAdapter(context, bestProductModel, this@Home )
            }
        }
    }

    private fun setFeatureBrands(data: List<HomeModel.Result.Featured>) {
        val bestProductModel = ArrayList<BestProductModel>()

        for (item in data) {
            val file = ArrayList<BestProductModel.File>()
            for (fileItem in item.file) {
                file.add(
                    BestProductModel.File(
                        fileItem.location
                    )
                )
            }

            bestProductModel.add(
                BestProductModel(
                    html2Text(item.desc),
                    item.shortDesc,
                    file,
                    item.id,
                    item.slug
                )
            )
        }

        with(viewBinding) {
            rvKidsPicks.apply {
                layoutManager = GridLayoutManager(context, 2)
                adapter = BestProductAdapter(context, bestProductModel, this@Home)
            }
        }
    }

    private fun setAutoImageSlider(data: List<HomeModel.Result.Slider>) {
        with(viewBinding) {
            sliderView.autoCycleDirection = SliderView.LAYOUT_DIRECTION_LTR;

            // below method is used to
            // set adapter to sliderview.
            context?.let { AutoImageSliderAdapter(it, data) }
                ?.let { sliderView.setSliderAdapter(it) }

            // below method is use to set
            // scroll time in seconds.
            sliderView.scrollTimeInSec = 5

            // to set it scrollable automatically
            // we use below method.
            sliderView.isAutoCycle = true

            sliderView.setIndicatorAnimation(IndicatorAnimationType.THIN_WORM)
            sliderView.setSliderTransformAnimation(SliderAnimations.SIMPLETRANSFORMATION)
            sliderView.autoCycleDirection = SliderView.AUTO_CYCLE_DIRECTION_BACK_AND_FORTH

            // to start auto cycle below method is used.
            sliderView.startAutoCycle()
        }
    }

    override fun getFragmentBinding(
        inflater: LayoutInflater,
        container: ViewGroup?
    ) = FragmentHomeBinding.inflate(inflater, container, false)

    override fun getViewModel() = ProductViewModel::class.java

    override fun getFragmentRepository() =
        ProductRepository(remoteDataSource.getBaseUrl().create(ProductApi::class.java))

    override fun onItemClickWithName(name: String, position: Int) {
        mainActivity.hideToolbarAndBottomNavigation()
        val action = ProductDetailsFragmentDirections.actionGlobalProductDetailsFragment(name)
        findNavController().navigate(action)
    }

}
