package com.shopping.swagbag.common.adapter

import com.shopping.swagbag.common.FreeData
import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.shopping.swagbag.R
import com.shopping.swagbag.databinding.SingleBestProductsBinding
import com.shopping.swagbag.databinding.SingleMostWantedBinding
import com.shopping.swagbag.dummy.DummyModel

class MostWantedProductAdapter(
    private val context: Context,
    private val data: List<DummyModel>
) :
    RecyclerView.Adapter<MostWantedProductAdapter.MostWantedProductViewHolder>() {

    inner class MostWantedProductViewHolder(private val viewBinding: SingleMostWantedBinding) :
        RecyclerView.ViewHolder(viewBinding.root) {

            fun bind(singleData: DummyModel){
                with(viewBinding){
                    // set imgae
                    Glide.with(context)
                        .load(singleData.image)
                        .error(R.drawable.ic_launcher_foreground)
                        .placeholder(R.drawable.ic_swagbug_logo)
                        .into(imgMostWanted)

                    // set text
                    tvMostWanted.text = singleData.name.toString()
                    tvMostWantedDetails.text = singleData.details
                }
            }

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MostWantedProductViewHolder {
        return MostWantedProductViewHolder(SingleMostWantedBinding.inflate(LayoutInflater.from(context), parent, false))
    }

    override fun onBindViewHolder(holder: MostWantedProductViewHolder, position: Int) {
        holder.bind(data[position])
    }

    override fun getItemCount()= data.size
}