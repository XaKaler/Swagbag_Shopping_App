package com.shopping.swagbag.category.travel

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.viewbinding.ViewBinding
import com.shopping.swagbag.R
import com.shopping.swagbag.common.adapter.*
import com.shopping.swagbag.databinding.FragmentTravelBinding
import com.shopping.swagbag.databinding.ToolbarWithThreeMenusBinding
import com.shopping.swagbag.dummy.*
import com.smarteist.autoimageslider.IndicatorView.animation.type.IndicatorAnimationType
import com.smarteist.autoimageslider.SliderAnimations
import com.smarteist.autoimageslider.SliderView
import java.util.ArrayList


class TravelFragment : Fragment(R.layout.fragment_travel) {

    private lateinit var viewBinding: FragmentTravelBinding
    private lateinit var toolbarBinding: ToolbarWithThreeMenusBinding

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        viewBinding = FragmentTravelBinding.bind(view)
        toolbarBinding = viewBinding.include

        initViews()
    }


    private fun initViews() {
        setToolbar()

        val data: ArrayList<DummyModel>? = DummyData().getDummyData()

        val categoryData: ArrayList<DummyModel>? = DummyData().getDummyCategory()

        val dataTwo: ArrayList<DummyModel>? = DummyData().getTwoDummyData()

        val dataSlider: ArrayList<DummySlider> = DummyData().getDummySlider()

        val dataChild: ArrayList<DummyChild> = DummyData().getDummyChild()

        setAutoImageSlider(dataSlider)

        if (categoryData != null) {
            setCategoryDropDown(categoryData)
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
    }

    private fun setToolbar() {
        with(toolbarBinding) {
            // set title
            tvTitle.text = getString(R.string.travel)

            // back button click
            imgBack.setOnClickListener {
                findNavController().popBackStack()
            }
        }
    }

    private fun setAutoImageSlider(data: ArrayList<DummySlider>) {
        with(viewBinding) {

            travelSliderView.autoCycleDirection = SliderView.LAYOUT_DIRECTION_LTR;

            // below method is used to
            // setadapter to sliderview.
            context?.let { AutoImageSliderAdapter(it, data) }
                ?.let { travelSliderView.setSliderAdapter(it) }


            // below method is use to set
            // scroll time in seconds.
            travelSliderView.scrollTimeInSec = 5

            // to set it scrollable automatically
            // we use below method.
            travelSliderView.isAutoCycle = true

            travelSliderView.setIndicatorAnimation(IndicatorAnimationType.THIN_WORM)
            travelSliderView.setSliderTransformAnimation(SliderAnimations.SIMPLETRANSFORMATION)
            travelSliderView.autoCycleDirection = SliderView.AUTO_CYCLE_DIRECTION_BACK_AND_FORTH

            // to start auto cycle below method is used.
            travelSliderView.startAutoCycle()
        }
    }

    private fun setCategoryDropDown(data: ArrayList<DummyModel>?) {
        with(viewBinding) {
            rvCateDropDown.apply {
                layoutManager = LinearLayoutManager(context)
                adapter = data?.let { DropDownCategoryAdapter(context, it) }
            }
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
                adapter = BestProductAdapter(context, data)
            }
        }
    }

    private fun setLast(data: ArrayList<DummyModel>) {
        with(viewBinding) {
            rvAppliances.apply {
                layoutManager = GridLayoutManager(context, 2)
                adapter = BestProductAdapter(context, data)
            }
        }
    }
}