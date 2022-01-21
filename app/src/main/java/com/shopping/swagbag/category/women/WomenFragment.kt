package com.shopping.swagbag.category.women

import android.content.Context
import android.os.Bundle
import android.util.Log
import android.view.View
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import com.shopping.swagbag.MainActivity
import com.shopping.swagbag.R
import com.shopping.swagbag.common.RecycleItemClickListener
import com.shopping.swagbag.common.adapter.*
import com.shopping.swagbag.databinding.FragmentWomenBinding
import com.shopping.swagbag.databinding.ToolbarWithThreeMenusBinding
import com.shopping.swagbag.dummy.DummyData
import com.shopping.swagbag.dummy.DummyModel
import com.shopping.swagbag.dummy.DummySlider
import com.smarteist.autoimageslider.IndicatorView.animation.type.IndicatorAnimationType
import com.smarteist.autoimageslider.SliderAnimations
import com.smarteist.autoimageslider.SliderView
import java.util.ArrayList

class WomenFragment : Fragment(R.layout.fragment_women), RecycleItemClickListener {

    private lateinit var viewBinding: FragmentWomenBinding
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

        viewBinding = FragmentWomenBinding.bind(view)
        toolbarBinding = viewBinding.include

        initViews()
        mainActivity.showToolbar()


    }

    override fun onSingleItemClickListener(position: Int) {
        findNavController().navigate(R.id.action_womenFragment_to_productDetailsFragment)
    }

    private fun initViews() {
        val data: ArrayList<DummyModel>? = DummyData().getDummyData()

        val categoryData: ArrayList<DummyModel>? = DummyData().getDummyCategory()

        val dataTwo: ArrayList<DummyModel>? = DummyData().getTwoDummyData()

        val dataSlider: ArrayList<DummySlider> = DummyData().getDummySlider()

        setAutoImageSlider(dataSlider)

        if (categoryData != null) {
            setCategoryDropDown(categoryData)
            setCategoryToBeg(categoryData)
        }

        if (data != null) {
            setCard3(data)

            setRecommended(data)
        }

        if (dataTwo != null) {

            setCollageData(dataTwo)

            setCardOne(dataTwo)

            setCard2(dataTwo)

            setCard4(dataTwo)

        }
        toolbar()
    }

    private fun toolbar() {
        with(toolbarBinding){
            // set title
            tvTitle.text = getString(R.string.womens)


            // click listeners
            imgBack.setOnClickListener {
                findNavController().popBackStack()
            }

            imgSearch.setOnClickListener {
                findNavController().navigate(R.id.action_womenFragment_to_searchFragment)
            }

            imgWishlist.setOnClickListener {
                findNavController().navigate(R.id.action_womenFragment_to_wishlistWithProductFragment)
            }

            imgCart.setOnClickListener {
                findNavController().navigate(R.id.action_womenFragment_to_shoppingBegWithoutProductFragment)
            }

        }
    }

    private fun setCard4(data: ArrayList<DummyModel>) {
        with(viewBinding) {
            rvKidsPicks.apply {
                layoutManager = GridLayoutManager(context, 2)
                adapter = BestProductAdapter(context, data, this@WomenFragment)
            }
        }
    }

    private fun setCollageData(data: ArrayList<DummyModel>) {
        with(viewBinding) {
            rvCollage.apply {
                layoutManager = GridLayoutManager(context, 2)
                adapter = BestProductAdapter(context, data, this@WomenFragment)
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
                adapter = BestProductAdapter(context, data, this@WomenFragment)
            }
        }
    }

    private fun setCard3(data: ArrayList<DummyModel>) {
        with(viewBinding) {
            recyclerView.apply {
                layoutManager = LinearLayoutManager(context, LinearLayoutManager.HORIZONTAL, false)
                adapter =  AllTimeSliderAdapter(context, data, this@WomenFragment)
            }
        }
    }

    private fun setCard2(data: ArrayList<DummyModel>) {
        with(viewBinding) {
            rvMenNewArrivals.apply {
                layoutManager = GridLayoutManager(context, 2)
                adapter = BestProductAdapter(context, data, this@WomenFragment)
            }
        }
    }

    private fun toolbarActions() {
        with(toolbarBinding) {
            tvTitle.text = getText(R.string.men)
            imgBack.setOnClickListener {
                findNavController().popBackStack()
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

    private fun setCardOne(data: ArrayList<DummyModel>) {
        with(viewBinding) {
            rvBest.apply {
                layoutManager = GridLayoutManager(context, 2)
                adapter = BestProductAdapter(context, data, this@WomenFragment)
            }
        }
    }

    private fun setAutoImageSlider(data: ArrayList<DummySlider>) {
        with(viewBinding) {


            womenSliderView.autoCycleDirection = SliderView.LAYOUT_DIRECTION_LTR;

            // below method is used to
            // setadapter to sliderview.
            context?.let { AutoImageSliderAdapter(it, data) }
                ?.let { womenSliderView.setSliderAdapter(it) }


            // below method is use to set
            // scroll time in seconds.
            womenSliderView.scrollTimeInSec = 5

            // to set it scrollable automatically
            // we use below method.
            womenSliderView.isAutoCycle = true

            womenSliderView.setIndicatorAnimation(IndicatorAnimationType.THIN_WORM)
            womenSliderView.setSliderTransformAnimation(SliderAnimations.SIMPLETRANSFORMATION)
            womenSliderView.autoCycleDirection = SliderView.AUTO_CYCLE_DIRECTION_BACK_AND_FORTH

            // to start auto cycle below method is used.
            womenSliderView.startAutoCycle()
        }
    }
}