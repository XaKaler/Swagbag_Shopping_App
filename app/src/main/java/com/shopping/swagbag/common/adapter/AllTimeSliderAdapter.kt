package com.shopping.swagbag.common.adapter

import android.app.Activity
import com.shopping.swagbag.common.FreeData
import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.navigation.findNavController
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.shopping.swagbag.common.RecycleItemClickListener
import com.shopping.swagbag.databinding.SingleBestProductsBinding
import com.shopping.swagbag.databinding.SingleSliderProductsBinding
import com.shopping.swagbag.dummy.DummyModel
import android.R
import com.shopping.swagbag.home.HomeModel


class AllTimeSliderAdapter(
    private val context: Context,
    private val data: List<HomeModel.Result.RandomCategory>,
    private val itemClick: RecycleItemClickListener
) :
    RecyclerView.Adapter<AllTimeSliderAdapter.BestProductViewHolder>() {

    inner class BestProductViewHolder(private val viewBinding: SingleSliderProductsBinding) :
        RecyclerView.ViewHolder(viewBinding.root) {

            fun bind(singleData: HomeModel.Result.RandomCategory, itemClick: RecycleItemClickListener, position: Int){
                with(viewBinding){
                    // set image
                    Glide.with(context)
                        .load(singleData.file)
                        .into(imgBestProduct)

                    // set text
                    tvBestProductName.text = singleData.name
                    tvBestProductDetails.text = singleData.description

                    newId.setOnClickListener{
                       itemClick.onSingleItemClickListener(position)
                    }
                }
            }

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): BestProductViewHolder {
        return BestProductViewHolder(SingleSliderProductsBinding.inflate(LayoutInflater.from(context), parent, false))
    }

    override fun onBindViewHolder(holder: BestProductViewHolder, position: Int) {
        holder.bind(data[position],itemClick = itemClick, position)

       /* if(position == 0){
            val padding: Int = context.resources.getDimensionPixelOffset(com.shopping.swagbag.R.dimen.screen_padding_15)
            holder.itemView.setPadding(padding, 0,0,0)
        }*/
    }

    override fun getItemCount()= data.size
}