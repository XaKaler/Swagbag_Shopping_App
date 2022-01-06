package com.shopping.swagbag.wishlist.withproduct

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.shopping.swagbag.R
import com.shopping.swagbag.databinding.FragmentWishlistWithProductBinding
import com.shopping.swagbag.databinding.FragmentWishtlistWithoutProductBinding
import com.shopping.swagbag.databinding.ToolbarWithTwoMenusDeleteAndCartBinding
import com.shopping.swagbag.dummy.DummyData


class WishlistWithProductFragment : Fragment(R.layout.fragment_wishlist_with_product) {

    private lateinit var viewBinding: FragmentWishlistWithProductBinding
    private lateinit var toolbarBinding: ToolbarWithTwoMenusDeleteAndCartBinding

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        viewBinding = FragmentWishlistWithProductBinding.bind(view)
        toolbarBinding = viewBinding.include

        initViews()
    }

    private fun initViews() {
        setCategory()

        setWishlistProduct()

        toolbar()
    }

    private fun setWishlistProduct() {
        with(viewBinding){
            rvProducts.apply{
                layoutManager = LinearLayoutManager(context)
                adapter = DummyData().getDummyData()
                    ?.let { WishlistWithProductAdapter(context, it) }
            }
        }
    }

    private fun setCategory() {
        with(viewBinding){
            rvCategory.apply{
                layoutManager = LinearLayoutManager(context, LinearLayoutManager.HORIZONTAL, false)
                adapter = WishlistWithProductSliderAdapter(context, DummyData().getWishListWithItemCategory())
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