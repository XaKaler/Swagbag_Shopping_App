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
import com.shopping.swagbag.R
import com.shopping.swagbag.category.CategoryToBegModel
import com.shopping.swagbag.common.GridSpaceItemDecoration
import com.shopping.swagbag.common.RecycleItemClickListener
import com.shopping.swagbag.common.adapter.AllTimeSliderAdapter
import com.shopping.swagbag.common.adapter.AutoImageSliderAdapter
import com.shopping.swagbag.common.adapter.BestProductAdapter
import com.shopping.swagbag.common.adapter.CategoryToBegAdapter
import com.shopping.swagbag.common.base.BaseFragment
import com.shopping.swagbag.databinding.FragmentHomeBinding
import com.shopping.swagbag.databinding.HomeBinding
import com.shopping.swagbag.dummy.DummyData
import com.shopping.swagbag.dummy.DummyModel
import com.shopping.swagbag.dummy.DummySlider
import com.shopping.swagbag.products.ProductApi
import com.shopping.swagbag.products.ProductRepository
import com.shopping.swagbag.products.ProductViewModel
import com.shopping.swagbag.products.product_details.ProductDetailModel
import com.shopping.swagbag.service.Resource
import com.smarteist.autoimageslider.IndicatorView.animation.type.IndicatorAnimationType
import com.smarteist.autoimageslider.SliderAnimations
import com.smarteist.autoimageslider.SliderView

class Home :
    BaseFragment<FragmentHomeBinding, ProductViewModel, ProductRepository>(FragmentHomeBinding::inflate),
    RecycleItemClickListener {

    private lateinit var activity: AppCompatActivity
    private lateinit var mainActivity: MainActivity

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
        mainActivity.setCategorySlider()

    }

    private fun initViews() {
        activity = context as AppCompatActivity

        getHomeData()

        showOfferImages()


    }

    private fun getHomeData() {
        viewModel.getHome().observe(viewLifecycleOwner){
            when(it){
                is Resource.Loading ->  showLoading()

                is Resource.Success -> {
                    stopShowingLoading()

                    Log.e("TAG", "getHomeData: $it")
                    val result = it.value.result
                    setAutoImageSlider(result.slider)
                    DummyData().getDummyData()?.let { it1 -> setTopTrending(it1) }
                    setCategoryToBeg(result.masterCategory)
                    setDealOfTheDay(result.deals)
                    setBestOffer(result.randomCategory)
                    //setFeatureBrands(result.featured)
                }

                is Resource.Failure -> Log.e("TAG", "getHomeData: $it", )
            }
        }
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
                adapter = TopTrendingAdapter(context, DummyData().getTopTrending())
            }
        }
    }

    private fun setFeatureBrands(data: ArrayList<DummyModel>) {
        with(viewBinding) {
            rvPromotedBrands.apply {
                layoutManager = GridLayoutManager(context, 2)
                //adapter = BestProductAdapter(context, data, this@Home)
            }
        }
    }

    // transforming lists
 /*   private fun List<HomeModel.Result.MasterCategory>.transform(): List<CategoryToBegModel> {
        return this.map {
            it.transform()
        }
    }

    // Example use
    private fun listTransform(squares: List<HomeModel.Result.MasterCategory>): List<CategoryToBegModel> {
        return squares.transform()
    }*/

    private fun setCategoryToBeg(data: List<HomeModel.Result.MasterCategory>) {
        val master = ArrayList<CategoryToBegModel>()
      //  master.addAll(data as CategoryToBegModel)

        with(viewBinding) {
            rvCategoryToBag.apply {
                layoutManager = GridLayoutManager(context, 3)
                adapter = CategoryToBegAdapter(context, data)
            }
        }

    }

    private fun setBestOffer(data: List<HomeModel.Result.RandomCategory>) {
        with(viewBinding) {
            rvBestOffer.apply {
                layoutManager = LinearLayoutManager(context, LinearLayoutManager.HORIZONTAL, false)
                adapter = AllTimeSliderAdapter(context, data, this@Home)
            }
        }

    }

    private fun setDealOfTheDay(data: List<HomeModel.Result.Deal>) {
        with(viewBinding) {
            rvDealOfDay.apply {
                layoutManager = GridLayoutManager(context, 2)
                adapter = BestProductAdapter(context, data, this@Home)
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

    override fun onSingleItemClickListener(position: Int) {
        mainActivity.hideToolbar()
        findNavController().navigate(R.id.action_home2_to_productDetailsFragment)
    }

    override fun itemClickWithName(name: String) {
        TODO("Not yet implemented")
    }

    override fun getFragmentBinding(
        inflater: LayoutInflater,
        container: ViewGroup?
    ) = FragmentHomeBinding.inflate(inflater, container, false)

    override fun getViewModel() = ProductViewModel::class.java

    override fun getFragmentRepository() =
        ProductRepository(remoteDataSource.getBaseUrl().create(ProductApi::class.java))

}
