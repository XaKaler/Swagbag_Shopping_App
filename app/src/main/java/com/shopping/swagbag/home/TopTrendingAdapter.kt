package com.shopping.swagbag.home

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.shopping.swagbag.R
import com.shopping.swagbag.category.ParticularCategoryModel
import com.shopping.swagbag.common.model.TopTrendingModel
import com.shopping.swagbag.databinding.SingleTopTrendingBinding
import com.shopping.swagbag.dummy.DummyModel
import com.shopping.swagbag.dummy.TopTrending

class TopTrendingAdapter(
    private val context: Context,
    private val data: List<TopTrendingModel>
) :
    RecyclerView.Adapter<TopTrendingAdapter.MyViewHolder>() {

    inner class MyViewHolder(private val viewBinding: SingleTopTrendingBinding) :
        RecyclerView.ViewHolder(viewBinding.root) {

        fun bind(singleData: TopTrendingModel){
            with(viewBinding){
                // set image
                Glide
                    .with(context)
                    .load(singleData.file)
                    .into(viewBinding.topTrendingImg)
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
        holder.bind(data[position])
    }

    override fun getItemCount()= data.size
}