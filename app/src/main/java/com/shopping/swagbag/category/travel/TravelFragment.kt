package com.shopping.swagbag.category.travel

import android.content.Context
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.View
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import com.shopping.swagbag.main_activity.MainActivity
import com.shopping.swagbag.R
import com.shopping.swagbag.common.RecycleItemClickListener
import com.shopping.swagbag.common.adapter.*
import com.shopping.swagbag.databinding.FragmentTravelBinding
import com.shopping.swagbag.dummy.*
import java.util.ArrayList


class TravelFragment : Fragment(R.layout.fragment_travel), RecycleItemClickListener {

    private lateinit var viewBinding: FragmentTravelBinding
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

        viewBinding = FragmentTravelBinding.bind(view)

        initViews()
        mainActivity.showToolbarAndBottomNavigation()
    }


    private fun initViews() {

        val data: ArrayList<DummyModel>? = DummyData().getDummyData()

        val categoryData: ArrayList<DummyModel>? = DummyData().getDummyCategory()

        val dataTwo: ArrayList<DummyModel>? = DummyData().getTwoDummyData()

        val dataSlider: ArrayList<DummySlider> = DummyData().getDummySlider()

        val dataChild: ArrayList<DummyChild> = DummyData().getDummyChild()


        if (categoryData != null) {
           setCategoryToBeg(DummyData().getDummyTravel())
            
        }

        if (data != null) {
        }

        if (dataTwo != null) {
            setRecommendForYou(dataTwo)
            //  setMostWanted(dataTwo)
            setLast(dataTwo)

            setSecondLast(dataTwo)
        }

        setAutoImageSlider(dataSlider)
    }


    private fun setAutoImageSlider(data: ArrayList<DummySlider>) {
        with(viewBinding) {
            sliderView.autoCycleDirection = com.smarteist.autoimageslider.SliderView.LAYOUT_DIRECTION_LTR;

            // below method is used to
            // set adapter to sliderview.
            /*context?.let { AutoImageSliderAdapter(it, data) }
                ?.let { sliderView.setSliderAdapter(it) }*/

            // below method is use to set
            // scroll time in seconds.
            sliderView.scrollTimeInSec = 5

            // to set it scrollable automatically
            // we use below method.
            sliderView.isAutoCycle = true

            sliderView.setIndicatorAnimation(com.smarteist.autoimageslider.IndicatorView.animation.type.IndicatorAnimationType.THIN_WORM)
            sliderView.setSliderTransformAnimation(com.smarteist.autoimageslider.SliderAnimations.SIMPLETRANSFORMATION)
            sliderView.autoCycleDirection = com.smarteist.autoimageslider.SliderView.AUTO_CYCLE_DIRECTION_BACK_AND_FORTH

            // to start auto cycle below method is used.
            sliderView.startAutoCycle()
        }
    }


    private fun setRecommendForYou(data: ArrayList<DummyModel>) {
        with(viewBinding) {
            rvRecommendForYou.apply {
                layoutManager = LinearLayoutManager(context)
                adapter = TravelCard2Adapter(context, data)
            }
        }
    }

    private fun setCategoryToBeg(data: ArrayList<DummyChild>) {
        with(viewBinding) {
            rvCategoryToBeg.apply {
                layoutManager = GridLayoutManager(context, 3)
                adapter = CategoryToBegTravelAdapter(context, data)
            }
        }

    }

    private fun setSecondLast(data: ArrayList<DummyModel>) {
        with(viewBinding) {
            rvAccessories.apply {
                layoutManager = GridLayoutManager(context, 2)
                //adapter = BestProductAdapter(context, data, this@TravelFragment)
            }
        }
    }

    private fun setLast(data: ArrayList<DummyModel>) {
        with(viewBinding) {
            rvAppliances.apply {
                layoutManager = GridLayoutManager(context, 2)
                //adapter = BestProductAdapter(context, data, this@TravelFragment)
            }
        }
    }

    override fun onSingleItemClickListener(position: Int) {
        findNavController().navigate(R.id.action_travelFragment_to_productDetailsFragment)
    }

    override fun itemClickWithName(name: String) {
        TODO("Not yet implemented")
    }
}