package com.shopping.swagbag.category.travel

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.View
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import com.shopping.swagbag.R
import com.shopping.swagbag.common.RecycleItemClickListener
import com.shopping.swagbag.common.adapter.*
import com.shopping.swagbag.databinding.FragmentTravelBinding
import com.shopping.swagbag.databinding.ToolbarWithThreeMenusBinding
import com.shopping.swagbag.dummy.*
import com.smarteist.autoimageslider.IndicatorView.animation.type.IndicatorAnimationType
import com.smarteist.autoimageslider.SliderAnimations
import com.smarteist.autoimageslider.SliderView
import java.util.ArrayList


class TravelFragment : Fragment(R.layout.fragment_travel), RecycleItemClickListener {

    private lateinit var viewBinding: FragmentTravelBinding
    private lateinit var toolbarBinding: ToolbarWithThreeMenusBinding

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        viewBinding = FragmentTravelBinding.bind(view)
        toolbarBinding = viewBinding.include

        initViews()
    }


    private fun initViews() {
        toolbar()

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

    private fun toolbar() {
        with(toolbarBinding) {
            // set title
            tvTitle.text = getString(R.string.travel)

            // click listeners
            imgBack.setOnClickListener {
                findNavController().popBackStack()
            }

            imgSearch.setOnClickListener {
                findNavController().navigate(R.id.action_travelFragment_to_searchFragment)
            }

            imgWishlist.setOnClickListener {
                findNavController().navigate(R.id.action_travelFragment_to_wishlistWithProductFragment)
            }

            imgCart.setOnClickListener {
                findNavController().navigate(R.id.action_travelFragment_to_shoppingBegWithoutProductFragment)
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
                adapter = BestProductAdapter(context, data, this@TravelFragment)
            }
        }
    }

    private fun setLast(data: ArrayList<DummyModel>) {
        with(viewBinding) {
            rvAppliances.apply {
                layoutManager = GridLayoutManager(context, 2)
                adapter = BestProductAdapter(context, data, this@TravelFragment)
            }
        }
    }

    override fun onSingleItemClickListener(position: Int) {
        findNavController().navigate(R.id.action_travelFragment_to_productDetailsFragment)
    }
}