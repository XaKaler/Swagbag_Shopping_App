package com.shopping.swagbag.common.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import com.bumptech.glide.Glide
import com.shopping.swagbag.databinding.SingleAutoImageSliderBinding
import com.shopping.swagbag.dummy.DummySlider
import com.smarteist.autoimageslider.SliderViewAdapter

class ProductAutoImageSliderAdapter(
    private val context: Context,
    private val image: List<Int>
) : SliderViewAdapter<ProductAutoImageSliderAdapter.MyViewHolder>() {

    inner class MyViewHolder(private val viewBinding: SingleAutoImageSliderBinding) :
        SliderViewAdapter.ViewHolder(viewBinding.root) {

        fun bind(singleData: Int) {
            viewBinding.imgAutoSlider.setImageResource(singleData)
        }
    }

    override fun getCount() = image.size

    override fun onCreateViewHolder(parent: ViewGroup?): MyViewHolder {
        return MyViewHolder(
            SingleAutoImageSliderBinding.inflate(
                LayoutInflater.from(context),
                parent,
                false
            )
        )
    }

    override fun onBindViewHolder(viewHolder: MyViewHolder?, position: Int) {
        viewHolder?.bind(image[position])
    }
}