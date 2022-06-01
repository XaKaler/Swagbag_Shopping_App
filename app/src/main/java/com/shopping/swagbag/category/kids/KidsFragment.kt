package com.shopping.swagbag.category.kids

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
import com.shopping.swagbag.databinding.FragmentKidsBinding
import com.shopping.swagbag.dummy.DummyChild
import com.shopping.swagbag.dummy.DummyData
import com.shopping.swagbag.dummy.DummyModel
import com.shopping.swagbag.dummy.DummySlider
import java.util.ArrayList

class KidsFragment : Fragment(R.layout.fragment_kids), RecycleItemClickListener {

    private lateinit var viewBinding: FragmentKidsBinding
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

        viewBinding = FragmentKidsBinding.bind(view)

        initViews()

        mainActivity.showToolbarAndBottomNavigation()
    }


    override fun onSingleItemClickListener(position: Int) {
        findNavController().navigate(R.id.action_kidsFragment_to_productDetailsFragment)
    }

    override fun itemClickWithName(name: String) {
        TODO("Not yet implemented")
    }

    private fun initViews() {
        val data: ArrayList<DummyModel>? = DummyData().getDummyData()

        val categoryData: ArrayList<DummyModel>? = DummyData().getDummyCategory()

        val dataTwo: ArrayList<DummyModel>? = DummyData().getTwoDummyData()

        val dataSlider: ArrayList<DummySlider> = DummyData().getDummySlider()

        val dataChild: ArrayList<DummyChild> = DummyData().getDummyChild()

        setAutoImageSlider(dataSlider)

        if(dataChild != null){
            setCategoryToBeg(dataChild)
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
}


    private fun setCollageData(data: ArrayList<DummyModel>) {
        with(viewBinding) {
            rvCollage.apply {
                layoutManager = GridLayoutManager(context, 2)
                //adapter = BestProductAdapter(context, data, this@KidsFragment)
            }
        }
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


    private fun setCard9(data: ArrayList<DummyModel>) {
        with(viewBinding) {
            rvKidsPicks.apply {
                layoutManager = GridLayoutManager(context,2)
                //adapter = BestProductAdapter(context, data, this@KidsFragment)
            }
        }
    }

    private fun setCategoryToBeg(data: ArrayList<DummyChild>) {
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
                //adapter = BestProductAdapter(context, data, this@KidsFragment)
            }
        }
    }

    private fun setCard6(data: ArrayList<DummyModel>) {
        with(viewBinding) {
            recyclerView.apply {
                layoutManager = LinearLayoutManager(context, LinearLayoutManager.HORIZONTAL, false)
                //adapter =  AllTimeSliderAdapter(context, data, this@KidsFragment)
            }
        }
    }

    private fun setCard5(data: ArrayList<DummyModel>) {
        with(viewBinding) {
            rvMenNewArrivals.apply {
                layoutManager = GridLayoutManager(context, 2)
                //adapter = BestProductAdapter(context, data, this@KidsFragment)
            }
        }
    }

    private fun setCard12(data: ArrayList<DummyModel>) {
        with(viewBinding) {
            rvBest.apply {
                layoutManager = GridLayoutManager(context, 2)
                //adapter = BestProductAdapter(context, data, this@KidsFragment)
            }
        }
    }


}