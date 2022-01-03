package com.shopping.swagbag.common.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.shopping.swagbag.R
import com.shopping.swagbag.common.FreeData
import com.shopping.swagbag.databinding.SingleDealOfTheDayBinding
import com.shopping.swagbag.dummy.DummyModel

class DealOfTheDayAdapter(
    private val context: Context,
    private val data: List<DummyModel>
) : RecyclerView.Adapter<DealOfTheDayAdapter.DealOfTheDayViewHolder>() {

    inner class DealOfTheDayViewHolder(val viewBinding: SingleDealOfTheDayBinding) :
        RecyclerView.ViewHolder(viewBinding.root) {

            fun bind(singleData: DummyModel){
                with(viewBinding){
                    // set image
                    Glide.with(context)
                        .load(singleData.image)
                        .error(R.drawable.ic_launcher_foreground)
                        .placeholder(R.drawable.ic_swagbug_logo)
                        .into(imgBestProduct)

                    // set Text
                    tvDealOfTheDayDetails.text = singleData.details
                    tvDealOfTheDayBrandName.text = singleData.name
                }
            }
    }

    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): DealOfTheDayViewHolder {
        return DealOfTheDayViewHolder(SingleDealOfTheDayBinding.inflate(LayoutInflater.from(context), parent, false))
    }

    override fun onBindViewHolder(
        holder: DealOfTheDayViewHolder,
        position: Int
    ) {
        holder.bind(data[position])
    }

    override fun getItemCount()= data.size
}