package com.shopping.swagbag.wishlist.withproduct

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.shopping.swagbag.R
import com.shopping.swagbag.common.RecycleItemClickListener
import com.shopping.swagbag.databinding.SingleCategoryOfWishlistWithItemsBinding
import com.shopping.swagbag.databinding.SingleSliderProductsBinding
import com.shopping.swagbag.dummy.DummyModel
import com.shopping.swagbag.dummy.UserAddress

class WishlistWithProductSliderAdapter(
    private val context: Context,
    private val data: List<UserAddress>
) :
    RecyclerView.Adapter<WishlistWithProductSliderAdapter.MyViewHolder>() {

    inner class MyViewHolder(private val viewBinding: SingleCategoryOfWishlistWithItemsBinding) :
        RecyclerView.ViewHolder(viewBinding.root) {

            fun bind(singleData: UserAddress, position: Int){
                with(viewBinding){
                    if(singleData.address == "All"){
                        categoryName.setTextColor(context.resources.getColor(R.color.red_light))
                        categoryName.setBackgroundResource(R.drawable.red_rec_outline_15)
                    }

                    categoryName.text = singleData.address

                    categoryName.setOnClickListener{
                        for(i in 0..data.size){
                            if(i!=position){
                                categoryName.setTextColor(context.resources.getColor(R.color.sonic_silver))
                                categoryName.setBackgroundResource(R.drawable.rec_outline_15)
                            }
                            else{
                                categoryName.setTextColor(context.resources.getColor(R.color.red_light))
                                categoryName.setBackgroundResource(R.drawable.red_rec_outline_15)
                            }
                        }
                    }
                }
            }

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {
        return MyViewHolder(SingleCategoryOfWishlistWithItemsBinding.inflate(LayoutInflater.from(context), parent, false))
    }

    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {
        holder.bind(data[position], position)
    }

    override fun getItemCount()= data.size
}