package com.shopping.swagbag.category.pets

import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import com.shopping.swagbag.R
import com.shopping.swagbag.common.adapter.*
import com.shopping.swagbag.databinding.FragmentPetsBinding
import com.shopping.swagbag.databinding.ToolbarWithThreeMenusBinding
import com.shopping.swagbag.dummy.DummyChild
import com.shopping.swagbag.dummy.DummyData
import com.shopping.swagbag.dummy.DummyModel
import com.shopping.swagbag.dummy.DummySlider
import com.smarteist.autoimageslider.IndicatorView.animation.type.IndicatorAnimationType
import com.smarteist.autoimageslider.SliderAnimations
import com.smarteist.autoimageslider.SliderView
import java.util.*

class PetsFragment : Fragment(R.layout.fragment_pets) {

    private lateinit var viewBinding: FragmentPetsBinding
    private lateinit var toolbarBinding: ToolbarWithThreeMenusBinding

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        viewBinding = FragmentPetsBinding.bind(view)
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
        }

        if (dataChild != null) {
            setCategoryToBeg(dataChild)
        }

        if (data != null) {
            setMostPopular(data)

            setRecommendForYou(data)
        }

        if (dataTwo != null) {
            setMostWanted(dataTwo)

            setNewArrival(dataTwo)

            setLast(dataTwo)

            setSecondLast(dataTwo)
        }
    }

    private fun setToolbar() {
        with(toolbarBinding) {
            // set title
            tvTitle.text = getString(R.string.pets)

            // back button click
            imgBack.setOnClickListener {
                findNavController().popBackStack()
            }
        }
    }

    private fun setAutoImageSlider(data: ArrayList<DummySlider>) {
        with(viewBinding) {


            petsSliderView.autoCycleDirection = SliderView.LAYOUT_DIRECTION_LTR;

            // below method is used to
            // setadapter to sliderview.
            context?.let { AutoImageSliderAdapter(it, data) }
                ?.let { petsSliderView.setSliderAdapter(it) }


            // below method is use to set
            // scroll time in seconds.
            petsSliderView.scrollTimeInSec = 5

            // to set it scrollable automatically
            // we use below method.
            petsSliderView.isAutoCycle = true

            petsSliderView.setIndicatorAnimation(IndicatorAnimationType.THIN_WORM)
            petsSliderView.setSliderTransformAnimation(SliderAnimations.SIMPLETRANSFORMATION)
            petsSliderView.autoCycleDirection = SliderView.AUTO_CYCLE_DIRECTION_BACK_AND_FORTH

            // to start auto cycle below method is used.
            petsSliderView.startAutoCycle()
        }
    }

    private fun setMostWanted(data: ArrayList<DummyModel>) {
        with(viewBinding) {
            rvMostWanted.apply {
                layoutManager = GridLayoutManager(context, 2)
                adapter =  BestProductAdapter(context, data)
            }
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
                layoutManager = LinearLayoutManager(context, LinearLayoutManager.HORIZONTAL, false)
                adapter = AllTimeSliderAdapter(context, data)
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

    private fun setCategoryToBeg(data: ArrayList<DummyChild>) {
        with(viewBinding) {
            rvCategoryToBag.apply {
                layoutManager = GridLayoutManager(context, 2)
                adapter = CategoryToBegPetsAdapter(context, data)
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