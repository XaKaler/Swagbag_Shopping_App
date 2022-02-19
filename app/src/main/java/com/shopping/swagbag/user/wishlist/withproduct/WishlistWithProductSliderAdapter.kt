package com.shopping.swagbag.user.wishlist.withproduct

import android.content.Context
import android.util.Log
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.core.content.ContextCompat
import androidx.recyclerview.widget.RecyclerView
import com.shopping.swagbag.R
import com.shopping.swagbag.databinding.SingleCategoryOfWishlistWithItemsBinding
import com.shopping.swagbag.dummy.UserAddress

class WishlistWithProductSliderAdapter(
    private val context: Context,
    private val data: List<UserAddress>
) :
    RecyclerView.Adapter<WishlistWithProductSliderAdapter.MyViewHolder>() {

    var selectedPosition = -1

    inner class MyViewHolder(private val viewBinding: SingleCategoryOfWishlistWithItemsBinding) :
        RecyclerView.ViewHolder(viewBinding.root) {

        fun bind(singleData: UserAddress, position: Int) {
            with(viewBinding) {
                categoryName.text = singleData.address

                Log.e("TAG", "bind: ${singleData.address}")

                if (selectedPosition == position) {
                    categoryName.setBackgroundResource(R.drawable.rec_outline_15_black)
                    categoryName.setTextColor(ContextCompat.getColor(context, R.color.black))
                } else {
                    categoryName.setBackgroundResource(R.drawable.rec_outline_15)
                    categoryName.setTextColor(
                        ContextCompat.getColor(
                            context,
                            R.color.davys_grey
                        )
                    )
                }

                itemView.setOnClickListener {
                    selectedPosition = position
                    notifyDataSetChanged()
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