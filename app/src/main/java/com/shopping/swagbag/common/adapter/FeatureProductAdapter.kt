
package com.shopping.swagbag.common.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.shopping.swagbag.R
import com.shopping.swagbag.common.FreeData
import com.shopping.swagbag.databinding.SingleFeatureBrandsBinding
import com.shopping.swagbag.dummy.DummyModel

class FeatureProductAdapter(private val context: Context, private val data: List<DummyModel>)
    : RecyclerView.Adapter<FeatureProductAdapter.FeatureProductViewHolder>() {

    inner class FeatureProductViewHolder(private val viewBinding: SingleFeatureBrandsBinding)
        : RecyclerView.ViewHolder(viewBinding.root){

        fun bind(singleData: DummyModel){
            Glide
                .with(context)
                //.load(singleData.thumbnailUrl)
                .load(singleData.image)
                .error(R.drawable.ic_launcher_foreground)
                .placeholder(R.drawable.logo)
                .into(viewBinding.imgFeatureBrand)

            viewBinding.tvFeatureProductOfferName.text = singleData.details.toString()
            viewBinding.tvProductWork.text = singleData.name.toString()
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): FeatureProductViewHolder {
        return FeatureProductViewHolder(SingleFeatureBrandsBinding.inflate(LayoutInflater.from(context), parent, false))
    }

    override fun onBindViewHolder(holder: FeatureProductViewHolder, position: Int) {
        holder.bind(data[position])
    }

    override fun getItemCount() = data.size
}