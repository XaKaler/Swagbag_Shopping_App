package com.shopping.swagbag.common.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import com.bumptech.glide.Glide
import com.shopping.swagbag.R
import com.shopping.swagbag.common.FreeData
import com.shopping.swagbag.databinding.SingleAutoImageSliderBinding
import com.shopping.swagbag.dummy.DummyData
import com.shopping.swagbag.dummy.DummyModel
import com.shopping.swagbag.dummy.DummySlider
import com.smarteist.autoimageslider.SliderViewAdapter

class AutoImageSliderAdapter(private val context: Context, private val data: List<DummySlider>)
    : SliderViewAdapter<AutoImageSliderAdapter.MyViewHolder>() {

    inner class MyViewHolder(private val viewBinding: SingleAutoImageSliderBinding)
        : SliderViewAdapter.ViewHolder(viewBinding.root){

        fun bind(singleData: DummySlider){
            Glide
                .with(context)
                .load(singleData.image)
                .centerInside()
                .into(viewBinding.imgAutoSlider)
            }
        }

    override fun getCount() = data.size

    override fun onCreateViewHolder(parent: ViewGroup?): MyViewHolder {
        return MyViewHolder(SingleAutoImageSliderBinding.inflate(LayoutInflater.from(context), parent, false))
    }

    override fun onBindViewHolder(viewHolder: MyViewHolder?, position: Int) {
        viewHolder?.bind(data[position])
    }
}