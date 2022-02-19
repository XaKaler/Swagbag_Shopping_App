package com.shopping.swagbag.user.wishlist.withoutproduct

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.View
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.GridLayoutManager
import com.shopping.swagbag.R
import com.shopping.swagbag.common.adapter.FeatureProductAdapter
import com.shopping.swagbag.databinding.*
import com.shopping.swagbag.dummy.DummyData


class WishlistWithoutProductFragment : Fragment(R.layout.fragment_wishtlist_without_product) {

    private lateinit var viewBinding: FragmentWishtlistWithoutProductBinding
    private lateinit var toolbarBinding: ToolbarWithTwoMenusDeleteAndCartBinding

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        viewBinding = FragmentWishtlistWithoutProductBinding.bind(view)
        toolbarBinding = viewBinding.include

        initViews()



    }

    private fun initViews() {
        with(viewBinding){
            btnShopNow.setOnClickListener{
                findNavController().navigate(R.id.action_wishlistWithoutProductFragment_to_home2)
            }
        }

        toolbar()

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

    private fun toolbar() {
        with(toolbarBinding){
            // set title
            tvTitle.text = getString(R.string.wishlist)

            //click listeners
            imgBack.setOnClickListener{
                findNavController().popBackStack()
            }

            delete.setOnClickListener{}

            imgCart.setOnClickListener{
                findNavController().navigate(R.id.action_wishlistWithoutProductFragment_to_shoppingBegWithoutProductFragment)
            }

        }
    }

}