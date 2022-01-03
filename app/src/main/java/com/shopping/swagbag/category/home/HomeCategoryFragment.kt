package com.shopping.swagbag.category.home

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import com.shopping.swagbag.R
import com.shopping.swagbag.common.adapter.*
import com.shopping.swagbag.databinding.FragmentHomeCategoryBinding
import com.shopping.swagbag.databinding.FragmentPetsBinding
import com.shopping.swagbag.databinding.ToolbarWithThreeMenusBinding
import com.shopping.swagbag.dummy.DummyChild
import com.shopping.swagbag.dummy.DummyData
import com.shopping.swagbag.dummy.DummyModel
import com.shopping.swagbag.dummy.DummySlider
import com.smarteist.autoimageslider.IndicatorView.animation.type.IndicatorAnimationType
import com.smarteist.autoimageslider.SliderAnimations
import com.smarteist.autoimageslider.SliderView
import java.util.ArrayList

class HomeFragment : Fragment(R.layout.fragment_home_category) {

    private lateinit var viewBinding: FragmentHomeCategoryBinding
    private lateinit var toolbarBinding: ToolbarWithThreeMenusBinding

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        viewBinding = FragmentHomeCategoryBinding.bind(view)
        toolbarBinding = viewBinding.include

        initViews()
    }

    private fun initViews() {
        setToolbar()

        val data: ArrayList<DummyModel>? = DummyData().getDummyData()

        val categoryData: ArrayList<DummyModel>? = DummyData().getDummyCategory()

        val dataTwo: ArrayList<DummyModel>? = DummyData().getTwoDummyData()

        val dataSlider: ArrayList<DummySlider> = DummyData().getDummySlider()

        val dataChild: ArrayList<DummyChild> = DummyData().getDummyTravel()

        setAutoImageSlider(dataSlider)

        if (categoryData != null) {
            setCategoryDropDown(categoryData)
            setCategoryToBeg(categoryData)
        }



        if (data != null) {
            setMostPopular(data)

            setRecommendForYou(data)
        }

        if (dataTwo != null) {
          //  setMostWanted(dataTwo)

            setNewArrival(dataTwo)

            setLast(dataTwo)

            setSecondLast(dataTwo)
        }
    }

    private fun setToolbar() {
        with(toolbarBinding) {
            // set title
            tvTitle.text = getString(R.string.home)

            // back button click
            imgBack.setOnClickListener {
                findNavController().popBackStack()
            }
        }
    }

    private fun setAutoImageSlider(data: ArrayList<DummySlider>) {
        with(viewBinding) {

            homeSliderView.autoCycleDirection = SliderView.LAYOUT_DIRECTION_LTR;

            // below method is used to
            // setadapter to sliderview.
            context?.let { AutoImageSliderAdapter(it, data) }
                ?.let { homeSliderView.setSliderAdapter(it) }


            // below method is use to set
            // scroll time in seconds.
            homeSliderView.scrollTimeInSec = 5

            // to set it scrollable automatically
            // we use below method.
            homeSliderView.isAutoCycle = true

            homeSliderView.setIndicatorAnimation(IndicatorAnimationType.THIN_WORM)
            homeSliderView.setSliderTransformAnimation(SliderAnimations.SIMPLETRANSFORMATION)
            homeSliderView.autoCycleDirection = SliderView.AUTO_CYCLE_DIRECTION_BACK_AND_FORTH

            // to start auto cycle below method is used.
            homeSliderView.startAutoCycle()
        }
    }

    /*private fun setMostWanted(data: ArrayList<DummyModel>) {
        with(viewBinding) {
            rvMostWanted.apply {
                layoutManager = LinearLayoutManager(context, LinearLayoutManager.HORIZONTAL, false)
                adapter =  Card11Adapter(context, data)
            }
        }
    }*/

    private fun setCategoryDropDown(data: ArrayList<DummyModel>?) {
        with(viewBinding) {
            rvCateDropDown.apply {
                layoutManager = LinearLayoutManager(context)
                adapter = data?.let { DropDownCategoryAdapter(context, it) }
            }
        }
    }

    private fun setNewArrival(data: ArrayList<DummyModel>) {
        with(viewBinding) {
            rvNewArrivals.apply {
                layoutManager = GridLayoutManager(context, 2)
                adapter = BestProductAdapter(context, data)
            }
        }
    }

    private fun setMostPopular(data: ArrayList<DummyModel>) {
        with(viewBinding) {
            rvMostPopular.apply {
                layoutManager = GridLayoutManager(context, 2)
                adapter = BestProductAdapter(context, data)
            }
        }
    }

    private fun setRecommendForYou(data: ArrayList<DummyModel>) {
        with(viewBinding) {
            rvRecommendForYou.apply {
                layoutManager = GridLayoutManager(context, 2)
                adapter = BestProductAdapter(context, data)
            }
        }
    }

    private fun setCategoryToBeg(data: ArrayList<DummyModel>) {
        with(viewBinding) {
            rvCategoryToBag.apply {
                layoutManager = GridLayoutManager(context, 3)
                adapter = CategoryToBegHomeAdapter(context, data)
            }
        }

    }

    private fun setSecondLast(data: ArrayList<DummyModel>) {
        with(viewBinding) {
            rvKidsPicks.apply {
                layoutManager = GridLayoutManager(context, 2)
                adapter = BestProductAdapter(context, data)
            }
        }
    }

    private fun setLast(data: ArrayList<DummyModel>) {
        with(viewBinding) {
            rvCollage.apply {
                layoutManager = GridLayoutManager(context, 2)
                adapter = BestProductAdapter(context, data)
            }
        }
    }

}