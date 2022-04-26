package com.shopping.swagbag.common.adapter

import android.app.Activity
import android.content.Context
import android.util.Log
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.navigation.findNavController
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.shopping.swagbag.R
import com.shopping.swagbag.common.RecycleItemClickListener
import com.shopping.swagbag.common.RecycleViewItemClick
import com.shopping.swagbag.common.model.AllTimeSliderModel
import com.shopping.swagbag.databinding.SingleSliderProductsBinding
import com.shopping.swagbag.products.product_details.ProductDetailsFragmentDirections


class AllTimeSliderAdapter(
    private val context: Context,
    private val data: List<AllTimeSliderModel>,
    private val itemClick: RecycleViewItemClick
) :
    RecyclerView.Adapter<AllTimeSliderAdapter.BestProductViewHolder>() {

    inner class BestProductViewHolder(private val viewBinding: SingleSliderProductsBinding) :
        RecyclerView.ViewHolder(viewBinding.root) {

            fun bind(singleData: AllTimeSliderModel ,itemClick: RecycleViewItemClick, position: Int){
                with(viewBinding){
                    // set image
                    Glide.with(context)
                        .load(singleData.file)
                        .error(R.drawable.glide_error)
                        .placeholder(R.drawable.glide_error)
                        .fitCenter()
                        .into(imgBestProduct)

                    Log.e("TAG", "all time slider bind name ${singleData.name}  \nand file is: ${singleData.file}", )

                    // set text
                    tvBestProductName.text = singleData.name
                    tvBestProductDetails.text = singleData.description

                    itemView.setOnClickListener{
                        itemClick.onItemClickWithName(singleData.name, position)
                    }
                }
            }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): BestProductViewHolder {
        return BestProductViewHolder(SingleSliderProductsBinding.inflate(LayoutInflater.from(context), parent, false))
    }

    override fun onBindViewHolder(holder: BestProductViewHolder, position: Int) {
        holder.bind(data[position] ,itemClick ,position)
    }

    override fun getItemCount()= data.size
}