package com.shopping.swagbag.wishlist.withproduct

import android.app.Activity
import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.navigation.findNavController
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.shopping.swagbag.R
import com.shopping.swagbag.common.RecycleItemClickListener
import com.shopping.swagbag.databinding.SingleCategoryOfWishlistWithItemsBinding
import com.shopping.swagbag.databinding.SingleSliderProductsBinding
import com.shopping.swagbag.databinding.SingleWislistItemBinding
import com.shopping.swagbag.dummy.DummyModel
import com.shopping.swagbag.dummy.SingleProductModel
import com.shopping.swagbag.dummy.UserAddress

class WishlistWithProductAdapter(
    private val context: Context,
    private val data: List<DummyModel>
) :
    RecyclerView.Adapter<WishlistWithProductAdapter.MyViewHolder>() {

    inner class MyViewHolder(private val viewBinding: SingleWislistItemBinding) :
        RecyclerView.ViewHolder(viewBinding.root) {

            fun bind(singleData: DummyModel, position: Int){
                with(viewBinding){
                    // set image
                    Glide.with(context)
                        .load(singleData.image)
                        .error(R.drawable.ic_launcher_foreground)
                        .placeholder(R.drawable.ic_swagbug_logo)
                        .into(productImg)

                    // set text
                    tvProductCompnayName.text = singleData.name.toString()
                    tvProductName.text = singleData.details

                    moveToBag.setOnClickListener{
                        val activity = context as Activity
                        activity.findNavController(R.id.moveToBag).navigate(R.id.action_wishlistWithProductFragment_to_shoppingBegWithProductFragment)
                    }
                }
            }

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {
        return MyViewHolder(SingleWislistItemBinding.inflate(LayoutInflater.from(context), parent, false))
    }

    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {
        holder.bind(data[position], position)
    }

    override fun getItemCount()= data.size
}