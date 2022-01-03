package com.shopping.swagbag.common.adapter

import com.shopping.swagbag.common.FreeData
import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.shopping.swagbag.R
import com.shopping.swagbag.databinding.SingleBestProductsBinding
import com.shopping.swagbag.databinding.SingleSliderProductsBinding
import com.shopping.swagbag.dummy.DummyModel

class AllTimeSliderAdapter(
    private val context: Context,
    private val data: List<DummyModel>
) :
    RecyclerView.Adapter<AllTimeSliderAdapter.BestProductViewHolder>() {

    inner class BestProductViewHolder(private val viewBinding: SingleSliderProductsBinding) :
        RecyclerView.ViewHolder(viewBinding.root) {

            fun bind(singleData: DummyModel){
                with(viewBinding){
                    // set imgae
                    Glide.with(context)
                        .load(singleData.image)
                        .error(R.drawable.ic_launcher_foreground)
                        .placeholder(R.drawable.ic_swagbug_logo)
                        .into(imgBestProduct)

                    // set text
                    tvBestProductName.text = singleData.name.toString()
                    tvBestProductDetails.text = singleData.details
                }
            }

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): BestProductViewHolder {
        return BestProductViewHolder(SingleSliderProductsBinding.inflate(LayoutInflater.from(context), parent, false))
    }

    override fun onBindViewHolder(holder: BestProductViewHolder, position: Int) {
        holder.bind(data[position])
    }

    override fun getItemCount()= data.size
}