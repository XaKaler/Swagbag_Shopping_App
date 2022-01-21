package com.shopping.swagbag.products.product_details

import android.annotation.SuppressLint
import android.app.Activity
import android.content.Context
import android.graphics.Color
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.content.ContextCompat
import androidx.navigation.findNavController
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.shopping.swagbag.R
import com.shopping.swagbag.databinding.SingleProductBinding
import com.shopping.swagbag.databinding.SingleProductSizeBinding
import com.shopping.swagbag.dummy.DummyModel
import com.shopping.swagbag.dummy.ProductFilter

class ProductSizeAdapter(
    private val context: Context,
    private val data: List<ProductFilter>
) :
    RecyclerView.Adapter<ProductSizeAdapter.ProductViewHolder>() {

    var selectedPosition =-1

    inner class ProductViewHolder(private val viewBinding: SingleProductSizeBinding) :
        RecyclerView.ViewHolder(viewBinding.root) {

        @SuppressLint("NotifyDataSetChanged")
        fun bind(singleData: ProductFilter, position: Int){
            with(viewBinding){
                if(position == 0){
                    sizeOutline.setImageResource(R.drawable.circle_outline_black)
                    sizeType.setTextColor(ContextCompat.getColor(context, R.color.black))
                }
                if(selectedPosition == position){
                   sizeOutline.setImageResource(R.drawable.circle_outline_black)
                    sizeType.setTextColor(ContextCompat.getColor(context, R.color.black))
                }
                else{
                    sizeOutline.setImageResource(R.drawable.circle_outline)
                    sizeType.setTextColor(ContextCompat.getColor(context, R.color.davys_grey))
                }

                singleSize.setOnClickListener{
                    selectedPosition = position
                    notifyDataSetChanged()
                }
            }
        }

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ProductViewHolder {
        return ProductViewHolder(
            SingleProductSizeBinding.inflate(
                LayoutInflater.from(context),
                parent,
                false
            )
        )
    }

    override fun onBindViewHolder(holder: ProductViewHolder, position: Int) {
        holder.bind(data[position], position)
    }

    override fun getItemCount()= data.size
}