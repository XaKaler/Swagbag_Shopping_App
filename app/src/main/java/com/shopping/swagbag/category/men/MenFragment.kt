package com.shopping.swagbag.category.men

import android.content.Context
import android.os.Bundle
import android.util.Log
import android.view.View
import androidx.core.view.GravityCompat
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import com.shopping.swagbag.MainActivity
import com.shopping.swagbag.NavigationMenuAdapter
import com.shopping.swagbag.R
import com.shopping.swagbag.common.RecycleItemClickListener
import com.shopping.swagbag.common.adapter.*
import com.shopping.swagbag.databinding.FragmentMenBinding
import com.shopping.swagbag.databinding.NavigationDrawerBinding
import com.shopping.swagbag.databinding.ToolbarWithThreeMenusBinding
import com.shopping.swagbag.dummy.DummyData
import com.shopping.swagbag.dummy.DummyModel
import com.shopping.swagbag.dummy.DummySlider
import com.smarteist.autoimageslider.IndicatorView.animation.type.IndicatorAnimationType
import com.smarteist.autoimageslider.SliderAnimations
import com.smarteist.autoimageslider.SliderView
import java.util.*

class MenFragment : androidx.fragment.app.Fragment(R.layout.fragment_men), RecycleItemClickListener {

    private lateinit var viewBinding: FragmentMenBinding
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

        viewBinding = FragmentMenBinding.bind(view)

        initViews()
        mainActivity.showToolbar()
    }

    private fun initViews() {

       // setUpNavigation()

        val data: ArrayList<DummyModel>? = DummyData().getDummyData()

        val categoryData: ArrayList<DummyModel>? = DummyData().getDummyCategory()

        val dataTwo: ArrayList<DummyModel>? = DummyData().getTwoDummyData()

        val dataSlider: ArrayList<DummySlider> = DummyData().getDummySlider()

        if (categoryData != null) {
            setCategoryDropDown(categoryData)
            setCategoryToBeg(categoryData)
        }

        if (data != null) {
            setMostPopular(data)

            setRecommended(data)
        }

        if (dataTwo != null) {
            setCollageData(dataTwo)

            setMostWanted(dataTwo)

            setNewArrival(dataTwo)

            setSecondLastCard(dataTwo)

        }
    }

    private fun setSecondLastCard(data: ArrayList<DummyModel>) {
        with(viewBinding) {
            rvKidsPicks.apply {
                layoutManager = GridLayoutManager(context, 2)
                adapter = BestProductAdapter(context, data, this@MenFragment)
            }
        }
    }

    private fun setCollageData(data: ArrayList<DummyModel>) {
        with(viewBinding) {
            rvCollage.apply {
                layoutManager = GridLayoutManager(context, 2)
                adapter = BestProductAdapter(context, data, this@MenFragment)
            }
        }
    }

    private fun setCategoryToBeg(data: ArrayList<DummyModel>) {
        with(viewBinding) {
            rvCategoryToBag.apply {
                layoutManager = GridLayoutManager(context, 3)
                adapter = CategoryToBegAdapter(context, data)
            }
        }

    }

    private fun setRecommended(data: ArrayList<DummyModel>) {
        with(viewBinding) {
            rvRecommendForYou.apply {
                layoutManager = GridLayoutManager(context, 2)
                adapter = BestProductAdapter(context, data, this@MenFragment)
            }
        }
    }

    private fun setMostPopular(data: ArrayList<DummyModel>) {
        with(viewBinding) {
            recyclerView.apply {
                layoutManager = LinearLayoutManager(context, LinearLayoutManager.HORIZONTAL, false)
                adapter = DummyData().getDummyData()?.let { AllTimeSliderAdapter(context, it, this@MenFragment) }
            }
        }
    }

    private fun setNewArrival(data: ArrayList<DummyModel>) {
        with(viewBinding) {
            rvMenNewArrivals.apply {
                layoutManager = GridLayoutManager(context, 2)
                adapter = BestProductAdapter(context, data, this@MenFragment)
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

    private fun setMostWanted(data: ArrayList<DummyModel>) {
        with(viewBinding) {
            rvBest.apply {
                layoutManager = GridLayoutManager(context, 2)
                adapter = BestProductAdapter(context, data, this@MenFragment)
            }
        }
    }

    override fun onSingleItemClickListener(position: Int) {
        findNavController().navigate(R.id.action_menFragment_to_productDetailsFragment)
    }

}