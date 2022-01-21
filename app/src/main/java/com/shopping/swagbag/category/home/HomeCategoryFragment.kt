package com.shopping.swagbag.category.home

import android.content.Context
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.View
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import com.shopping.swagbag.MainActivity
import com.shopping.swagbag.R
import com.shopping.swagbag.common.RecycleItemClickListener
import com.shopping.swagbag.common.adapter.*
import com.shopping.swagbag.databinding.FragmentHomeCategoryBinding
import com.shopping.swagbag.databinding.ToolbarWithThreeMenusBinding
import com.shopping.swagbag.dummy.DummyChild
import com.shopping.swagbag.dummy.DummyData
import com.shopping.swagbag.dummy.DummyModel
import com.shopping.swagbag.dummy.DummySlider
import com.smarteist.autoimageslider.IndicatorView.animation.type.IndicatorAnimationType
import com.smarteist.autoimageslider.SliderAnimations
import com.smarteist.autoimageslider.SliderView
import java.util.ArrayList

class HomeFragment : Fragment(R.layout.fragment_home_category), RecycleItemClickListener {

    private lateinit var viewBinding: FragmentHomeCategoryBinding
    private lateinit var toolbarBinding: ToolbarWithThreeMenusBinding
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

        viewBinding = FragmentHomeCategoryBinding.bind(view)
        toolbarBinding = viewBinding.include

        initViews()
        mainActivity.showToolbar()
    }

    private fun initViews() {
        toolbar()

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

    private fun toolbar() {
        with(toolbarBinding) {
            // set title
            tvTitle.text = getString(R.string.home)

            // click listeners
            imgBack.setOnClickListener {
                findNavController().popBackStack()
            }

            imgSearch.setOnClickListener {
                findNavController().navigate(R.id.action_homeFragment_to_searchFragment)
            }

            imgWishlist.setOnClickListener {
                findNavController().navigate(R.id.action_homeFragment_to_wishlistWithProductFragment)
            }

            imgCart.setOnClickListener {
                findNavController().navigate(R.id.action_homeFragment_to_shoppingBegWithoutProductFragment)
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
                adapter = BestProductAdapter(context, data, this@HomeFragment)
            }
        }
    }

    private fun setMostPopular(data: ArrayList<DummyModel>) {
        with(viewBinding) {
            rvMostPopular.apply {
                layoutManager = GridLayoutManager(context, 2)
                adapter = BestProductAdapter(context, data, this@HomeFragment)
            }
        }
    }

    private fun setRecommendForYou(data: ArrayList<DummyModel>) {
        with(viewBinding) {
            rvRecommendForYou.apply {
                layoutManager = GridLayoutManager(context, 2)
                adapter = BestProductAdapter(context, data, this@HomeFragment)
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
                adapter = BestProductAdapter(context, data, this@HomeFragment)
            }
        }
    }

    private fun setLast(data: ArrayList<DummyModel>) {
        with(viewBinding) {
            rvCollage.apply {
                layoutManager = GridLayoutManager(context, 2)
                adapter = BestProductAdapter(context, data, this@HomeFragment)
            }
        }
    }

    override fun onSingleItemClickListener(position: Int) {
        findNavController().navigate(R.id.action_homeFragment_to_productDetailsFragment)
    }

}