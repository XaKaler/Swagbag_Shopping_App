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

        viewBinding = FragmentMenBinding.bind(view)
        toolbarBinding = viewBinding.include

        initViews()
        mainActivity.showToolbar()
    }

    private fun initViews() {

       // setUpNavigation()

        val data: ArrayList<DummyModel>? = DummyData().getDummyData()

        val categoryData: ArrayList<DummyModel>? = DummyData().getDummyCategory()

        val dataTwo: ArrayList<DummyModel>? = DummyData().getTwoDummyData()

        val dataSlider: ArrayList<DummySlider> = DummyData().getDummySlider()

        // handle toolbar actions
        toolbarActions()


        setAutoImageSlider(dataSlider)


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
/*
    private fun setUpNavigation() {
        with(navigationBinding){
            imgHome.setOnClickListener{}
            imgCancel.setOnClickListener{
              //  viewBinding.drawerLayout.closeDrawer(GravityCompat.END)
            }

            // set master category
            rvCategory.apply{
                layoutManager = LinearLayoutManager(context)
                adapter = DummyData().getDummyCategory()?.let { NavigationMenuAdapter(context, it) }
            }

            // set category item
            rvCategoryItems.apply{
                layoutManager = LinearLayoutManager(context)
                adapter = DummyData().getDummyData()?.let { NavigationMenuAdapter(context, it) }
            }

        }
    }*/

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

    private fun toolbarActions() {
        with(toolbarBinding) {
            tvTitle.text = getText(R.string.men)

            // click listeners
            imgBack.setOnClickListener {
               findNavController().popBackStack()
            }

            imgSearch.setOnClickListener {
                findNavController().navigate(R.id.action_menFragment_to_searchFragment)
            }

            imgWishlist.setOnClickListener {
               findNavController().navigate(R.id.action_menFragment_to_wishlistWithProductFragment)
               /* if (!viewBinding.drawerLayout.isDrawerOpen(GravityCompat.END))
                    viewBinding.drawerLayout.openDrawer(GravityCompat.END)
                else
                    viewBinding.drawerLayout.closeDrawer(GravityCompat.START)*/
            }

            imgCart.setOnClickListener {
                findNavController().navigate(R.id.action_menFragment_to_shoppingBegWithoutProductFragment)
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

    private fun setAutoImageSlider(data: ArrayList<DummySlider>) {
        with(viewBinding) {


            menSliderView.autoCycleDirection = SliderView.LAYOUT_DIRECTION_LTR;

            // below method is used to
            // setadapter to sliderview.
            context?.let { AutoImageSliderAdapter(it, data) }
                ?.let { menSliderView.setSliderAdapter(it) }


            // below method is use to set
            // scroll time in seconds.
            menSliderView.scrollTimeInSec = 5

            // to set it scrollable automatically
            // we use below method.
            menSliderView.isAutoCycle = true

            menSliderView.setIndicatorAnimation(IndicatorAnimationType.THIN_WORM)
            menSliderView.setSliderTransformAnimation(SliderAnimations.SIMPLETRANSFORMATION)
            menSliderView.autoCycleDirection = SliderView.AUTO_CYCLE_DIRECTION_BACK_AND_FORTH

            // to start auto cycle below method is used.
            menSliderView.startAutoCycle()
        }
    }

    override fun onSingleItemClickListener(position: Int) {
        findNavController().navigate(R.id.action_menFragment_to_productDetailsFragment)
    }

}