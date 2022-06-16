package com.shopping.swagbag.products.product_details

import android.annotation.SuppressLint
import android.content.Context
import android.graphics.PorterDuff
import android.util.Log
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.core.graphics.toColorInt
import androidx.recyclerview.widget.RecyclerView
import com.shopping.swagbag.R
import com.shopping.swagbag.common.RecycleViewItemClick
import com.shopping.swagbag.databinding.SingleProductColorBinding


class ProductColorAdapter(
    private val context: Context,
    private val data: List<ProductOptionModel>,
    private val itemClick: RecycleViewItemClick
) :
    RecyclerView.Adapter<ProductColorAdapter.ProductViewHolder>() {

    var selectedPosition =-1

    inner class ProductViewHolder(private val viewBinding: SingleProductColorBinding) :
        RecyclerView.ViewHolder(viewBinding.root) {

        @SuppressLint("NotifyDataSetChanged")
        fun bind(singleData: ProductOptionModel, position: Int, itemClick: RecycleViewItemClick){
            with(viewBinding){
                // set color
                Log.e("color", singleData.toString())

                val newColor = singleData.value.toColorInt()
                color.setColorFilter(newColor, PorterDuff.Mode.SRC_ATOP)

                // select when user click
                if(selectedPosition == position){
                    singleColor.setBackgroundResource(R.drawable.circle_outline_black)
                }
                else{
                    singleColor.setBackgroundResource(0)
                }

                itemView.setOnClickListener{
                    selectedPosition = position
                    notifyDataSetChanged()
                    itemClick.onItemClickWithName("Color", position)
                }
            }
        }

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ProductViewHolder {
        return ProductViewHolder(
            SingleProductColorBinding.inflate(
                LayoutInflater.from(context),
                parent,
                false
            )
        )
    }

    override fun onBindViewHolder(holder: ProductViewHolder, position: Int) {
        holder.bind(data[position], position, itemClick)
    }

    override fun getItemCount()= data.size
}