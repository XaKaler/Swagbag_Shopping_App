package com.shopping.swagbag.home

import android.content.Context
import android.graphics.Bitmap
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.bumptech.glide.integration.webp.decoder.WebpDrawable
import com.bumptech.glide.integration.webp.decoder.WebpDrawableTransformation
import com.bumptech.glide.load.Transformation
import com.bumptech.glide.load.resource.bitmap.CenterCrop
import com.bumptech.glide.load.resource.bitmap.CircleCrop
import com.bumptech.glide.load.resource.bitmap.FitCenter
import com.shopping.swagbag.common.RecycleViewItemClick
import com.shopping.swagbag.common.model.TopTrendingModel
import com.shopping.swagbag.databinding.SingleTopTrendingBinding


class TopTrendingAdapter(
    private val context: Context,
    private val data: List<TopTrendingModel>,
    private val itemClick: RecycleViewItemClick
) :
    RecyclerView.Adapter<TopTrendingAdapter.MyViewHolder>() {

    inner class MyViewHolder(private val viewBinding: SingleTopTrendingBinding) :
        RecyclerView.ViewHolder(viewBinding.root) {

        fun bind(singleData: TopTrendingModel,position: Int, itemClick: RecycleViewItemClick){
            with(viewBinding){
                // set image
                val circleCrop: Transformation<Bitmap> = FitCenter()
                Glide
                    .with(context)
                    .load(singleData.file)
                    .optionalTransform(circleCrop)
                    .optionalTransform(WebpDrawable::class.java, WebpDrawableTransformation(circleCrop))
                    .into(viewBinding.topTrendingImg)
            }

            itemView.setOnClickListener {
                var navigateTo = ""

                singleData.run{
                    if(brand.isNotEmpty())
                        navigateTo = "brand"
                    else if(category.isNotEmpty())
                        navigateTo = "category"
                    else if(product.isNotEmpty())
                        navigateTo = "product"
                }

                itemClick.onItemClickWithName(navigateTo, position)
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {
        return MyViewHolder(
            SingleTopTrendingBinding.inflate(
                LayoutInflater.from(context),
                parent,
                false
            )
        )
    }

    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {
        holder.bind(data[position],position, itemClick)
    }

    override fun getItemCount()= data.size
}