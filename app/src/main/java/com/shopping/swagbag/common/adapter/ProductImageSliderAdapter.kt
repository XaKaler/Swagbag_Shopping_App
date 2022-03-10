package com.shopping.swagbag.common.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import com.bumptech.glide.Glide
import com.shopping.swagbag.databinding.SingleProductImageSliderBinding
import com.shopping.swagbag.products.product_details.ProductDetailModel
import com.smarteist.autoimageslider.SliderViewAdapter

class ProductImageSliderAdapter(
    private val context: Context,
    private val data: List<ProductDetailModel.Result.File>
    ) : SliderViewAdapter<ProductImageSliderAdapter.MyViewHolder>() {

    inner class MyViewHolder(private val viewBinding: SingleProductImageSliderBinding)
        : SliderViewAdapter.ViewHolder(viewBinding.root){

        fun bind(singleData: ProductDetailModel.Result.File){
            Glide
                .with(context)
                .load(singleData.location)
                .centerCrop()
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