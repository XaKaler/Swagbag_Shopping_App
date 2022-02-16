package com.shopping.swagbag.products.product_details

import android.annotation.SuppressLint
import android.app.Activity
import android.content.Context
import android.graphics.Color
import android.graphics.Typeface
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.content.ContextCompat
import androidx.navigation.findNavController
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.shopping.swagbag.R
import com.shopping.swagbag.common.RecycleItemClick
import com.shopping.swagbag.databinding.SingleProductBinding
import com.shopping.swagbag.databinding.SingleProductColorBinding
import com.shopping.swagbag.databinding.SingleProductSizeBinding
import com.shopping.swagbag.dummy.DummyModel
import com.shopping.swagbag.dummy.ProductFilter

class ProductColorAdapter(
    private val context: Context,
    private val data: List<ProductFilter>,
    private val itemClick: RecycleItemClick
) :
    RecyclerView.Adapter<ProductColorAdapter.ProductViewHolder>() {

    var selectedPosition =-1

    inner class ProductViewHolder(private val viewBinding: SingleProductColorBinding) :
        RecyclerView.ViewHolder(viewBinding.root) {

        @SuppressLint("NotifyDataSetChanged")
        fun bind(singleData: ProductFilter, position: Int, itemClick: RecycleItemClick){
            with(viewBinding){
                // set color
                //color.setBackgroundColor(singleData.)

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