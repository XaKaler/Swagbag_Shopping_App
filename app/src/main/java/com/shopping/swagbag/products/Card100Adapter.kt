package com.shopping.swagbag.products

import android.app.Activity
import android.content.Context
import android.graphics.Paint
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.navigation.findNavController
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.shopping.swagbag.R
import com.shopping.swagbag.common.RecycleItemClick
import com.shopping.swagbag.common.RecycleItemClickListener
import com.shopping.swagbag.common.adapter.AutoImageSliderAdapter
import com.shopping.swagbag.common.adapter.BestOfferAdapter
import com.shopping.swagbag.common.adapter.ProductAutoImageSliderAdapter
import com.shopping.swagbag.common.adapter.ProductImageAdapter
import com.shopping.swagbag.databinding.SingleBestOffersBinding
import com.shopping.swagbag.databinding.SingleCard10Binding
import com.shopping.swagbag.databinding.SingleProductBinding
import com.shopping.swagbag.dummy.DummyData
import com.shopping.swagbag.dummy.DummyModel
import com.shopping.swagbag.dummy.DummySlider
import com.shopping.swagbag.products.product_details.ProductDetailModel
import com.smarteist.autoimageslider.IndicatorView.animation.type.IndicatorAnimationType
import com.smarteist.autoimageslider.SliderAnimations
import com.smarteist.autoimageslider.SliderView

class Card10Adapter(
    private val context: Context,
    private val data: List<ProductDetailModel.Related>,
    private val itemClick: RecycleItemClickListener

) :
    RecyclerView.Adapter<Card10Adapter.MyViewHolder>() {

    inner class MyViewHolder(private val viewBinding: SingleCard10Binding) :
        RecyclerView.ViewHolder(viewBinding.root) {

        fun bind(singleData: ProductDetailModel.Related,position: Int, itemClick: RecycleItemClickListener){
            with(viewBinding){
                oldRate.paintFlags = oldRate.paintFlags or Paint.STRIKE_THRU_TEXT_FLAG
                textView59.paintFlags = textView59.paintFlags or Paint.STRIKE_THRU_TEXT_FLAG

                // set
                Glide
                    .with(context)
                    .load(singleData.file[0].location)
                    .into(imgFeatureBrand)


                // set text
                productName.text = singleData.name
                productDetails.text = singleData.shortDesc
                newRate.text = singleData.sellingPrice.toString()
                oldRate.text = singleData.price.toString()

                val discount = "(${singleData.discountedPrice}%off)"
                oldRate.text = discount

                // click listener
                itemView.setOnClickListener{
                    itemClick.itemClickWithName(singleData.name)
                }
            }
        }

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {
        return MyViewHolder(
            SingleCard10Binding.inflate(
                LayoutInflater.from(context),
                parent,
                false
            )
        )
    }

    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {
        holder.bind(data[position], position, itemClick)
        if(position == 0){
            val padding: Int = context.resources.getDimensionPixelOffset(com.shopping.swagbag.R.dimen.screen_padding_15)
            holder.itemView.setPadding(padding, 0,0,0)
        }

    }

    override fun getItemCount()= data.size
}