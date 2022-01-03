package com.shopping.swagbag.common.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import com.bumptech.glide.Glide
import com.shopping.swagbag.R
import com.shopping.swagbag.common.FreeData
import com.shopping.swagbag.databinding.SingleAutoImageSliderBinding
import com.shopping.swagbag.databinding.SingleProductImageSliderBinding
import com.shopping.swagbag.dummy.DummyData
import com.shopping.swagbag.dummy.DummyModel
import com.shopping.swagbag.dummy.SingleProductImageModel
import com.shopping.swagbag.dummy.SingleProductModel
import com.smarteist.autoimageslider.SliderViewAdapter

class ProductImageSliderAdapter(private val context: Context, private val data: List<SingleProductImageModel>)
    : SliderViewAdapter<ProductImageSliderAdapter.MyViewHolder>() {

    inner class MyViewHolder(private val viewBinding: SingleProductImageSliderBinding)
        : SliderViewAdapter.ViewHolder(viewBinding.root){

        fun bind(singleData: SingleProductImageModel){
            Glide
                .with(context)
                .load(singleData.mainImage)
                .centerCrop()
                .error(R.drawable.ic_launcher_foreground)
                .placeholder(R.drawable.ic_swagbug_logo)
                .into(viewBinding.imgSlider)
            }
        }

    override fun getCount() = data.size

    override fun onCreateViewHolder(parent: ViewGroup?): MyViewHolder {
        return MyViewHolder(SingleProductImageSliderBinding.inflate(LayoutInflater.from(context), parent, false))
    }

    override fun onBindViewHolder(viewHolder: MyViewHolder?, position: Int) {
        viewHolder?.bind(data[position])
    }
}