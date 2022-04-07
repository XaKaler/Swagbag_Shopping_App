package com.shopping.swagbag.home

import android.content.Context
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.app.AppCompatActivity
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import com.bumptech.glide.Glide
import com.shopping.swagbag.MainActivity
import com.shopping.swagbag.category.CategoryToBegModel
import com.shopping.swagbag.common.GridSpaceItemDecoration
import com.shopping.swagbag.common.RecycleViewItemClick
import com.shopping.swagbag.common.adapter.AllTimeSliderAdapter
import com.shopping.swagbag.common.adapter.AutoImageSliderAdapter
import com.shopping.swagbag.common.adapter.BestProductAdapter
import com.shopping.swagbag.common.adapter.CategoryToBegAdapter
import com.shopping.swagbag.common.base.BaseFragment
import com.shopping.swagbag.common.model.AllTimeSliderModel
import com.shopping.swagbag.common.model.BestProductModel
import com.shopping.swagbag.databinding.FragmentHomeBinding
import com.shopping.swagbag.dummy.DummyModel
import com.shopping.swagbag.products.ProductApi
import com.shopping.swagbag.products.ProductRepository
import com.shopping.swagbag.products.ProductViewModel
import com.shopping.swagbag.products.product_details.ProductDetailsFragmentDirections
import com.shopping.swagbag.service.Resource
import com.smarteist.autoimageslider.IndicatorView.animation.type.IndicatorAnimationType
import com.smarteist.autoimageslider.SliderAnimations
import com.smarteist.autoimageslider.SliderView

class Home :
    BaseFragment<FragmentHomeBinding, ProductViewModel, ProductRepository>(FragmentHomeBinding::inflate),
RecycleViewItemClick{

    private lateinit var activity: AppCompatActivity
    private lateinit var mainActivity: MainActivity
    private lateinit var homeResult: HomeModel

    override fun onAttach(context: Context) {
        super.onAttach(context)

        mainActivity = context as MainActivity

        if(mainActivity !is MainActivity) {
            Log.e("TAG", "onAttach: is instance of main activity", )
        }
        else{
            Log.e("TAG", "onAttach:not is instance of main actvity", )
        }


    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        initViews()

        mainActivity.showToolbar()
        mainActivity.setMasterCategorySlider()

        Log.e("TAG", "onViewCreated: ", )

    }

    private fun initViews() {
        activity = context as AppCompatActivity

        if(this::homeResult.isInitialized)
            setData()
        else
            getHomeData()

    }

    fun setData(){
        setAutoImageSlider(homeResult.result.slider)
        //DummyData().getDummyData()?.let { it1 -> setTopTrending(it1) }
        setCategoryToBeg(homeResult.result.masterCategory)
        setDealOfTheDay(homeResult.result.deals)
        setBestOffer(homeResult.result.randomCategory)
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

                 is Resource.Failure -> Log.e("TAG", "getHomeData: $it", )
             }
         }


  /*      homeResult = mainActivity.getHomeResult()
        setAutoImageSlider(homeResult.result.slider)
        //DummyData().getDummyData()?.let { it1 -> setTopTrending(it1) }
        setCategoryToBeg(homeResult.result.masterCategory)
        setDealOfTheDay(homeResult.result.deals)
        setBestOffer(homeResult.result.randomCategory)
        setFeatureBrands(homeResult.result.featured)
        showOfferImages()*/

    }

    private fun showOfferImages() {
        with(viewBinding) {
            context?.let {
                Glide.with(it)
                    .load("https://swagbag-space.fra1.digitaloceanspaces.com/1640072900495xoipv.webp")
                    .into(homeImg3)

                Glide.with(it)
                    .load("https://swagbag-space.fra1.digitaloceanspaces.com/1640072900495xoipv.webp")
                    .into(homeImg5)


            }
        }
    }

    private fun setTopTrending(data: ArrayList<DummyModel>) {
        with(viewBinding) {
            rvTopTrending.apply {
                addItemDecoration(GridSpaceItemDecoration(20))
                layoutManager = GridLayoutManager(context, 2)
               // adapter = TopTrendingAdapter(context, DummyData().getTopTrending())
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
                adapter = CategoryToBegAdapter(context, master)
            }
        }

    }

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

        Log.e("TAG", "setBestOffer: $allTimeSliderModel", )

        with(viewBinding) {
            rvBestOffer.apply {
                layoutManager = LinearLayoutManager(context, LinearLayoutManager.HORIZONTAL, false)
                adapter = AllTimeSliderAdapter(context, allTimeSliderModel, this@Home)
            }
        }

    }

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
                    item.name
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
                    item.name
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

    override fun onItemClickWithName(tag: String, position: Int) {
        mainActivity.hideToolbar()
        val action = ProductDetailsFragmentDirections.actionGlobalProductDetailsFragment(tag)
        findNavController().navigate(action)
    }

}
