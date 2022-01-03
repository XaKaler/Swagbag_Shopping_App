package com.shopping.swagbag.wishlist.withoutproduct

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import com.shopping.swagbag.R
import com.shopping.swagbag.common.adapter.FeatureProductAdapter
import com.shopping.swagbag.databinding.*
import com.shopping.swagbag.dummy.DummyData


class WishlistWithoutProductFragment : Fragment(R.layout.fragment_wishtlist_without_product) {

    private lateinit var viewBinding: FragmentWishtlistWithoutProductBinding
    private lateinit var toolbarBinding: ToolbarWithTwoMenusBinding

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        viewBinding = FragmentWishtlistWithoutProductBinding.bind(view)
        toolbarBinding = viewBinding.include

        initViews()


    }

    private fun initViews() {
        setToolbar()

        setItemViewed()
    }

    private fun setItemViewed() {
        with(viewBinding){
            rvItemViewed.apply{
                layoutManager = GridLayoutManager(context, 2)
                adapter = DummyData().getDummyData()?.let { FeatureProductAdapter(context, it) }
            }
        }
    }

    private fun setToolbar() {
        with(toolbarBinding){
            // set title
            tvTitle.text = getString(R.string.wishlist)

            // back button click
            imgBack.setOnClickListener{
                findNavController().popBackStack()
            }
        }
    }

}