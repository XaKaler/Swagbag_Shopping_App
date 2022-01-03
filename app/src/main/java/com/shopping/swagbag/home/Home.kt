package com.shopping.swagbag.home

import android.app.Activity
import android.content.Context
import android.os.Bundle
import android.util.Log
import android.view.Gravity
import android.view.View
import androidx.appcompat.app.ActionBarDrawerToggle
import androidx.appcompat.app.AppCompatActivity
import androidx.drawerlayout.widget.DrawerLayout
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import com.bumptech.glide.Glide
import com.shopping.swagbag.MainActivity
import com.shopping.swagbag.R
import com.shopping.swagbag.ShowToolbar
import com.shopping.swagbag.common.FreeData
import com.shopping.swagbag.common.RecycleItemClickListener
import com.shopping.swagbag.common.adapter.*
import com.shopping.swagbag.databinding.FragmentHomeBinding
import com.shopping.swagbag.databinding.HomeBinding
import com.shopping.swagbag.dummy.DummyData
import com.shopping.swagbag.dummy.DummyModel
import com.shopping.swagbag.dummy.DummySlider
import com.shopping.swagbag.service.RetrofitSingleton
import com.smarteist.autoimageslider.IndicatorView.animation.type.IndicatorAnimationType
import com.smarteist.autoimageslider.SliderAnimations
import com.smarteist.autoimageslider.SliderView
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class Home : Fragment(R.layout.fragment_home),RecycleItemClickListener {

    private lateinit var viewBinding: FragmentHomeBinding
    private lateinit var viewBinding2: HomeBinding
    private lateinit var activity: AppCompatActivity
    private lateinit var mainActivity: MainActivity

    override fun onAttach(context: Context) {
        super.onAttach(context)
        mainActivity = context as MainActivity

    }


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        viewBinding = FragmentHomeBinding.bind(view)
        viewBinding2 = viewBinding.include

        initViews()

        mainActivity.showToolbar()
    }

    private fun initViews() {
        activity = context as AppCompatActivity
        setCategorySlider()

        showOfferImages()


    }

    private fun showOfferImages() {
        with(viewBinding2) {
            context?.let {
                // set on image1
                Glide.with(it)
                    .load("https://m.media-amazon.com/images/G/31/AMS/IN/970X250-_desktop_banner.jpg")

                    .error(R.drawable.ic_launcher_foreground)
                    .placeholder(R.drawable.ic_swagbug_logo)
                    .into(homeImg1)

                // set on image2
                Glide.with(it)
                    .load("https://m.media-amazon.com/images/I/61Y6exteCHL._SX3000_.jpg")
                    .error(R.drawable.ic_launcher_foreground)
                    .placeholder(R.drawable.ic_swagbug_logo)
                    .into(homeImg2)

                // set on image3
                Glide.with(it)
                    .load("https://images.unsplash.com/photo-1615233500570-c5d7576b4262?ixlib=rb-1.2.1&ixid=MnwxMjA3fDB8MHxwaG90by1wYWdlfHx8fGVufDB8fHx8&auto=format&fit=crop&w=387&q=80")
                    .error(R.drawable.ic_launcher_foreground)
                    .placeholder(R.drawable.ic_swagbug_logo)
                    .into(homeImg3)

                // set on image4
                Glide.with(it)
                    .load("https://images.unsplash.com/photo-1505691723518-36a5ac3be353?ixlib=rb-1.2.1&ixid=MnwxMjA3fDB8MHxwaG90by1wYWdlfHx8fGVufDB8fHx8&auto=format&fit=crop&w=870&q=80")
                    .error(R.drawable.ic_launcher_foreground)
                    .placeholder(R.drawable.ic_swagbug_logo)
                    .into(homeImg4)


            }
        }
    }

    private fun setCategorySlider() {

        with(viewBinding2) {
            Log.e("TAG", "setCategorySlider: inside with")
            val data: ArrayList<DummyModel>? = DummyData().getDummyData()

            val categoryData: ArrayList<DummyModel>? = DummyData().getDummyCategory()

            val dataTwo: ArrayList<DummyModel>? = DummyData().getTwoDummyData()



            val dataSlider: ArrayList<DummySlider> = DummyData().getDummySlider()

            // category slider
            rvCategorySlider.layoutManager =
                LinearLayoutManager(context, LinearLayoutManager.HORIZONTAL, false)

            rvCategorySlider.adapter =
                context?.let { DummyData().getDummyCategory()
                    ?.let { it1 -> CategorySliderAdapter(context = it, it1, this@Home) } }

            Log.e("TAG", "setCategorySlider: ${DummyData().getDummyCategory()}", )

            //set auto image slider
            if (data != null) {
                setAutoImageSlider(dataSlider)


                // set deals data
                setSpecialDeal(data)

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
                adapter = CollageAdapter(context, data)
            }
        }
    }

    private fun setKidsPicks(data: ArrayList<DummyModel>) {
        with(viewBinding2) {
            rvKidsPicks.apply {
                layoutManager = GridLayoutManager(context, 2)
                adapter = KidsWearPicksAdapter(context, data)
            }
        }
    }

    private fun setFeatureBrands(data: ArrayList<DummyModel>) {
        with(viewBinding2) {
            rvPromotedBrands.apply {
                layoutManager = GridLayoutManager(context, 2)
                adapter = FeatureProductAdapter(context, data)
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
                layoutManager = LinearLayoutManager(context, LinearLayoutManager.HORIZONTAL, false)
                adapter = AllTimeSliderAdapter(context, data)
            }
        }

    }

    private fun setFeatureProduct(data: ArrayList<DummyModel>) {
        with(viewBinding2) {
            rvFeatureBrands.apply {
                layoutManager = GridLayoutManager(context, 2)
                adapter = FeatureProductAdapter(context, data)
            }
        }
    }

    private fun setDealOfTheDay(data: ArrayList<DummyModel>) {
        with(viewBinding2) {
            rvDealOfDay.apply {
                layoutManager = GridLayoutManager(context, 2)
                adapter = DealOfTheDayAdapter(context, data)
            }
        }
    }

    private fun setBestProducts(data: List<DummyModel>) {
        with(viewBinding2) {
            rvBest.apply {
                layoutManager = GridLayoutManager(context, 2)
                adapter = BestProductAdapter(context, data)
            }
        }
    }

    private fun setSpecialDeal(data: ArrayList<DummyModel>) {
        with(viewBinding2) {
            rvDeals.apply {
                //isNestedScrollingEnabled = false
                layoutManager = LinearLayoutManager(context, LinearLayoutManager.HORIZONTAL, false)
                adapter = AllTimeSliderAdapter(context, data)
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
        when(position){
            0 -> {
                mainActivity.hideToolbar()
               // findNavController().navigate(R.id.action_home2_to_menFragment)
            }

            1->{
                mainActivity.hideToolbar()
                //findNavController().navigate(R.id.action_home2_to_womenFragment)
            }

            2->{
                mainActivity.hideToolbar()
               // findNavController().navigate(R.id.action_home2_to_kidsFragment)
            }

            3->{
                mainActivity.hideToolbar()
               // findNavController().navigate(R.id.action_home2_to_petsFragment)
            }
            4->{
                mainActivity.hideToolbar()
               // findNavController().navigate(R.id.action_home2_to_homeFragment)
            }

            5->{
                mainActivity.hideToolbar()
               // findNavController().navigate(R.id.action_home2_to_travelFragment)
            }
        }
    }
}