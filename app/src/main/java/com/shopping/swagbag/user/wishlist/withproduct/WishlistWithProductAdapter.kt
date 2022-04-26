package com.shopping.swagbag.user.wishlist.withproduct

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.shopping.swagbag.common.RecycleViewItemClick
import com.shopping.swagbag.databinding.SingleWislistItemBinding

class WishlistWithProductAdapter(
    private val context: Context,
    private var data: List<GetWishlistModel.Result>,
    private val itemClick: RecycleViewItemClick
) :
    RecyclerView.Adapter<WishlistWithProductAdapter.MyViewHolder>() {

    inner class MyViewHolder(private val viewBinding: SingleWislistItemBinding) :
        RecyclerView.ViewHolder(viewBinding.root) {

        fun bind(
            singleData: GetWishlistModel.Result,
            position: Int,
            itemClick: RecycleViewItemClick
        ) {
            with(viewBinding) {
                // set image
                Glide.with(context)
                    .load(singleData.product.file[0].location)
                    .into(productImg)

                // set text
                productName.text = singleData.product.name
                productPrice.text = singleData.product.price
                productDetails.text = singleData.product.shortDesc

                moveToBag.setOnClickListener {
                    itemClick.onItemClickWithName("moveToBeg", position)
                }

                cancel.setOnClickListener{
                    itemClick.onItemClickWithName("remove", position)
                }
                }
            }

    }

    fun updateData(updatedWishlist: List<GetWishlistModel.Result>){
        data = updatedWishlist
        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {
        return MyViewHolder(SingleWislistItemBinding.inflate(LayoutInflater.from(context), parent, false))
    }

    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {
        holder.bind(data[position], position, itemClick)
    }

    override fun getItemCount()= data.size
}