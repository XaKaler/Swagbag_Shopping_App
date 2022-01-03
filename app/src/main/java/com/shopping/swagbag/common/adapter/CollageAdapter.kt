package com.shopping.swagbag.common.adapter

import android.content.Context
import android.util.Log
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.shopping.swagbag.R
import com.shopping.swagbag.common.FreeData
import com.shopping.swagbag.databinding.SingleDealAndPopularBinding
import com.shopping.swagbag.databinding.SingleWithImageCollaseBinding
import com.shopping.swagbag.dummy.DummyModel


class CollageAdapter(
    private val context: Context,
    private val data: List<DummyModel>
) :
    RecyclerView.Adapter<CollageAdapter.CollageViewHolder>() {

    inner class CollageViewHolder(private val viewBinding: SingleWithImageCollaseBinding) :
        RecyclerView.ViewHolder(viewBinding.root) {

        fun bind(singleData: DummyModel){
            with(viewBinding){
                // set collage image 1
                Glide.with(context)
                    .load(singleData.image)
                    .error(R.drawable.ic_launcher_foreground)
                    .placeholder(R.drawable.ic_swagbug_logo)
                    .into(imgCollage1)


                // set collage image 2
                Glide.with(context)
                    .load(singleData.image)
                    .error(R.drawable.ic_launcher_foreground)
                    .placeholder(R.drawable.ic_swagbug_logo)
                    .into(imgCollage2)


                // set collage image 3
                Glide.with(context)
                    .load(singleData.image)
                    .error(R.drawable.ic_launcher_foreground)
                    .placeholder(R.drawable.ic_swagbug_logo)
                    .into(imgCollage3)

                tvPerfectPicksProductType.text = singleData.name
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CollageViewHolder{
        return CollageViewHolder(
            SingleWithImageCollaseBinding.inflate(
                LayoutInflater.from(context),
                parent,
                false
            )
        )
    }

    override fun onBindViewHolder(holder: CollageViewHolder, position: Int) {
        holder.bind(data[position])
    }

    override fun getItemCount() = data.size
}