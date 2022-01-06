package com.shopping.swagbag.category.kids

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.View
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import com.shopping.swagbag.R
import com.shopping.swagbag.common.RecycleItemClickListener
import com.shopping.swagbag.common.adapter.*
import com.shopping.swagbag.databinding.FragmentKidsBinding
import com.shopping.swagbag.databinding.ToolbarWithThreeMenusBinding
import com.shopping.swagbag.dummy.DummyChild
import com.shopping.swagbag.dummy.DummyData
import com.shopping.swagbag.dummy.DummyModel
import com.shopping.swagbag.dummy.DummySlider
import com.smarteist.autoimageslider.IndicatorView.animation.type.IndicatorAnimationType
import com.smarteist.autoimageslider.SliderAnimations
import com.smarteist.autoimageslider.SliderView
import java.util.ArrayList

class KidsFragment : Fragment(R.layout.fragment_kids), RecycleItemClickListener {

    private lateinit var viewBinding: FragmentKidsBinding
    private lateinit var toolbarBinding: ToolbarWithThreeMenusBinding

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        viewBinding = FragmentKidsBinding.bind(view)
        toolbarBinding = viewBinding.include

        initViews()


    }


    override fun onSingleItemClickListener(position: Int) {
        findNavController().navigate(R.id.action_kidsFragment_to_productDetailsFragment)
    }

    private fun initViews() {
        val data: ArrayList<DummyModel>? = DummyData().getDummyData()

        val categoryData: ArrayList<DummyModel>? = DummyData().getDummyCategory()

        val dataTwo: ArrayList<DummyModel>? = DummyData().getTwoDummyData()

        val dataSlider: ArrayList<DummySlider> = DummyData().getDummySlider()

        val dataChild: ArrayList<DummyChild> = DummyData().getDummyChild()

        setAutoImageSlider(dataSlider)

        if (categoryData != null) {
            setCategoryDropDown(categoryData)
        }

        if(dataChild != null){
            setCard8(dataChild)
        }

        if (data != null) {
            setCard6(data)

            setCard7(data)
        }

        if (dataTwo != null) {
            setCard5(dataTwo)

            setCard12(dataTwo)

            setCard9(dataTwo)

            setCollageData(dataTwo)


        }
        toolbar()
    }

    private fun toolbar() {
        with(toolbarBinding){
            // set title
            tvTitle.text = getString(R.string.kids)


            // click listeners
            imgBack.setOnClickListener {
                findNavController().popBackStack()
            }

            imgSearch.setOnClickListener {
                findNavController().navigate(R.id.action_kidsFragment_to_searchFragment)
            }

            imgWishlist.setOnClickListener {
                findNavController().navigate(R.id.action_kidsFragment_to_wishlistWithProductFragment)
            }

            imgCart.setOnClickListener {
                findNavController().navigate(R.id.action_kidsFragment_to_shoppingBegWithoutProductFragment2)
            }

        }
    }

    private fun setCollageData(data: ArrayList<DummyModel>) {
        with(viewBinding) {
            rvCollage.apply {
                layoutManager = GridLayoutManager(context, 2)
                adapter = BestProductAdapter(context, data, this@KidsFragment)
            }
        }
    }

    private fun setCard9(data: ArrayList<DummyModel>) {
        with(viewBinding) {
            rvKidsPicks.apply {
                layoutManager = GridLayoutManager(context,2)
                adapter = BestProductAdapter(context, data, this@KidsFragment)
            }
        }
    }

    private fun setCard8(data: ArrayList<DummyChild>) {
        with(viewBinding) {
            rvCategoryToBag.apply {
                layoutManager = GridLayoutManager(context, 2)
                adapter = CategoryToBegChildAdapter(context, data)
            }
        }

    }

    private fun setCard7(data: ArrayList<DummyModel>) {
        with(viewBinding) {
            rvRecommendForYou.apply {
                layoutManager = GridLayoutManager(context, 2)
                adapter = BestProductAdapter(context, data, this@KidsFragment)
            }
        }
    }

    private fun setCard6(data: ArrayList<DummyModel>) {
        with(viewBinding) {
            recyclerView.apply {
                layoutManager = LinearLayoutManager(context, LinearLayoutManager.HORIZONTAL, false)
                adapter =  AllTimeSliderAdapter(context, data, this@KidsFragment)
            }
        }
    }

    private fun setCard5(data: ArrayList<DummyModel>) {
        with(viewBinding) {
            rvMenNewArrivals.apply {
                layoutManager = GridLayoutManager(context, 2)
                adapter = BestProductAdapter(context, data, this@KidsFragment)
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

    private fun setCard12(data: ArrayList<DummyModel>) {
        with(viewBinding) {
            rvBest.apply {
                layoutManager = GridLayoutManager(context, 2)
                adapter = BestProductAdapter(context, data, this@KidsFragment)
            }
        }
    }

    private fun setAutoImageSlider(data: ArrayList<DummySlider>) {
        with(viewBinding) {
            kidsSliderView.autoCycleDirection = SliderView.LAYOUT_DIRECTION_LTR;

            // below method is used to
            // setadapter to sliderview.
            context?.let { AutoImageSliderAdapter(it, data) }
                ?.let { kidsSliderView.setSliderAdapter(it) }


            // below method is use to set
            // scroll time in seconds.
            kidsSliderView.scrollTimeInSec = 5

            // to set it scrollable automatically
            // we use below method.
            kidsSliderView.isAutoCycle = true

            kidsSliderView.setIndicatorAnimation(IndicatorAnimationType.THIN_WORM)
            kidsSliderView.setSliderTransformAnimation(SliderAnimations.SIMPLETRANSFORMATION)
            kidsSliderView.autoCycleDirection = SliderView.AUTO_CYCLE_DIRECTION_BACK_AND_FORTH

            // to start auto cycle below method is used.
            kidsSliderView.startAutoCycle()
        }
    }

}