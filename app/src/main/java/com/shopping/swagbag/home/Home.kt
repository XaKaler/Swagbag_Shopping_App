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
import com.shopping.swagbag.service.Resource
import com.smarteist.autoimageslider.IndicatorView.animation.type.IndicatorAnimationType
import com.smarteist.autoimageslider.SliderAnimations
import com.smarteist.autoimageslider.SliderView

class Home :
    BaseFragment<FragmentHomeBinding, ProductViewModel, ProductRepository>(FragmentHomeBinding::inflate),
    RecycleItemClickListener {

    private lateinit var viewBinding2: HomeBinding
    private lateinit var activity: AppCompatActivity
    private lateinit var mainActivity: MainActivity

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

        viewBinding2 = viewBinding.include

        initViews()

        mainActivity.showToolbar()
        mainActivity.setCategorySlider()

    }

    private fun initViews() {
        activity = context as AppCompatActivity

        getHomeData()

        setCategorySlider()

        showOfferImages()


    }

    private fun getHomeData() {
        viewModel.getHome().observe(viewLifecycleOwner){
            when(it){
                is Resource.Loading ->  showLoading()

                is Resource.Success -> {
                    stopShowingLoading()

                    Log.e("TAG", "getHomeData: $it", )
                }

                is Resource.Failure -> Log.e("TAG", "getHomeData: $it", )
            }
        }
    }

    private fun showOfferImages() {
        with(viewBinding2) {
            context?.let {
                // set on image1
                Glide.with(it)
                    .load("https://swagbag-space.fra1.digitaloceanspaces.com/1640079281564elvys.webp")
                    .into(homeImg1)

                // set on image2
                Glide.with(it)
                    .load("https://swagbag-space.fra1.digitaloceanspaces.com/1640080145210noqvi.webp")
                    .into(homeImg2)

                // set on image3
                Glide.with(it)
                    .load("https://swagbag-space.fra1.digitaloceanspaces.com/1640072900495xoipv.webp")
                    .into(homeImg3)

             /*   // set on image4
                Glide.with(it)
                    .load("https://swagbag-space.fra1.digitaloceanspaces.com/1639983624004mlj8x.png")
                    .into(homeImg4)*/


            }
        }
    }

    private fun setCategorySlider() {

        with(viewBinding2) {
            Log.e("TAG", "setCategorySlider: inside with")
            val data: ArrayList<DummyModel>? = DummyData().getDummyData()

            val categoryData: ArrayList<DummyModel>? = DummyData().getDummyCategory()

            val dataTwo: ArrayList<DummyModel>? = DummyData().getTwoDummyData()

            val dataSlider: ArrayList<DummySlider> = DummyData().getDummySlider( )
/*
            // category slider
            rvCategorySlider.layoutManager =
                LinearLayoutManager(context, LinearLayoutManager.HORIZONTAL, false)

            rvCategorySlider.adapter =
                context?.let { DummyData().getDummyCategory()
                    ?.let { it1 -> CategorySliderAdapter(context = it, it1, this@Home) } }*/

            Log.e("TAG", "setCategorySlider: ${DummyData().getDummyCategory()}", )

            //set auto image slider
            if (data != null) {
                setAutoImageSlider(dataSlider)


                // set deals data
                setSpecialDeal(data)

                // set top treding
                setTopTrending(data)



                // set category to beg
                if (categoryData != null) {
                    setCategoryToBeg(categoryData)
                }

                // best offer
                setBestOffer(data)
            }


            // set best products
            if (dataTwo != null) {
                setBestProducts(dataTwo)


                // set deal of the day
                setDealOfTheDay(dataTwo)


                // set fearue product
                setFeatureProduct(dataTwo)


                // set feature brands
                setFeatureBrands(dataTwo)

                // set kids picks
                setKidsPicks(dataTwo)

                // set collage data
                setCollageData(dataTwo)
            }
        }
    }

    private fun setCollageData(data: ArrayList<DummyModel>) {
        with(viewBinding2) {
            rvCollage.apply {
                layoutManager = GridLayoutManager(context, 2)
                adapter = BestProductAdapter(context, data, this@Home)
            }
        }
    }

    private fun setTopTrending(data: ArrayList<DummyModel>) {
        with(viewBinding2) {
            rvTopTrending.apply {
                addItemDecoration(GridSpaceItemDecoration(20))
                layoutManager = GridLayoutManager(context, 2)
                adapter = TopTrendingAdapter(context, DummyData().getTopTrending())
            }
        }
    }

    private fun setKidsPicks(data: ArrayList<DummyModel>) {
        with(viewBinding2) {
            rvKidsPicks.apply {
                layoutManager = GridLayoutManager(context, 2)
                adapter = BestProductAdapter(context, data, this@Home)
            }
        }
    }

    private fun setFeatureBrands(data: ArrayList<DummyModel>) {
        with(viewBinding2) {
            rvPromotedBrands.apply {
                layoutManager = GridLayoutManager(context, 2)
                adapter = BestProductAdapter(context, data, this@Home)
            }
        }
    }

    private fun setCategoryToBeg(data: ArrayList<DummyModel>) {
        with(viewBinding2) {
            rvCategoryToBag.apply {
                layoutManager = GridLayoutManager(context, 3)
                adapter = CategoryToBegAdapter(context, data)
            }
        }

    }

    private fun setBestOffer(data: ArrayList<DummyModel>) {
        with(viewBinding2) {
            rvBestOffer.apply {
                isNestedScrollingEnabled = false
                layoutManager = LinearLayoutManager(context, LinearLayoutManager.HORIZONTAL, false)
                adapter = AllTimeSliderAdapter(context, data, this@Home)
            }
        }

    }

    private fun setFeatureProduct(data: ArrayList<DummyModel>) {
        with(viewBinding2) {
            rvFeatureBrands.apply {
                layoutManager = GridLayoutManager(context, 2)
                adapter = BestProductAdapter(context, data, this@Home)
            }
        }
    }

    private fun setDealOfTheDay(data: ArrayList<DummyModel>) {
        with(viewBinding2) {
            rvDealOfDay.apply {
                layoutManager = GridLayoutManager(context, 2)
                adapter = BestProductAdapter(context, data, this@Home)
            }
        }
    }

    private fun setBestProducts(data: List<DummyModel>) {
        with(viewBinding2) {
            rvBest.apply {
                layoutManager = GridLayoutManager(context, 2)
                adapter = BestProductAdapter(context, data, this@Home)
            }
        }
    }

    private fun setSpecialDeal(data: ArrayList<DummyModel>) {
        with(viewBinding2) {
            rvDeals.apply {
                //isNestedScrollingEnabled = false
                layoutManager = LinearLayoutManager(context, LinearLayoutManager.HORIZONTAL, false)
                adapter = AllTimeSliderAdapter(context, data, this@Home)
            }
        }
    }

    private fun setAutoImageSlider(data: ArrayList<DummySlider>) {
        with(viewBinding2) {
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