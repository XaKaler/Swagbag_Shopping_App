package com.shopping.swagbag.common.adapter

import com.shopping.swagbag.common.FreeData
import android.content.Context
import android.util.Log
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.shopping.swagbag.R
import com.shopping.swagbag.databinding.SingleDealAndPopularBinding
import com.shopping.swagbag.dummy.DummyModel

class DealAndPopularAdapter(
    private val context: Context,
    private val data: List<DummyModel>
) :
    RecyclerView.Adapter<DealAndPopularAdapter.DealAndPopularViewHolder>() {

    inner class DealAndPopularViewHolder(private val viewBinding: SingleDealAndPopularBinding) :
        RecyclerView.ViewHolder(viewBinding.root) {

            fun bind(singleData: DummyModel){
                with(viewBinding){
                    // set image
                    Glide.with(context)
                        .load(singleData.image)
                        .error(R.drawable.ic_launcher_foreground)
                        .placeholder(R.drawable.ic_swagbug_logo)
                        .into(dealCardImg)

                    dealCardTitle.text = singleData.name

                    dealCardDetails.text = singleData.details
                }
            }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): DealAndPopularViewHolder {
        return DealAndPopularViewHolder(SingleDealAndPopularBinding.inflate(LayoutInflater.from(context), parent, false))
    }

    override fun onBindViewHolder(holder: DealAndPopularViewHolder, position: Int) {
        holder.bind(data[position])
    }

    override fun getItemCount() = data.size
}