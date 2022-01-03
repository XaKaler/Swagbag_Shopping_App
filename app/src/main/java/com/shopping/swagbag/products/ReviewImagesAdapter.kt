package com.shopping.swagbag.products

import android.app.Activity
import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.navigation.findNavController
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.shopping.swagbag.R
import com.shopping.swagbag.common.adapter.BestOfferAdapter
import com.shopping.swagbag.databinding.SingleBestOffersBinding
import com.shopping.swagbag.databinding.SingleProductBinding
import com.shopping.swagbag.databinding.SingleReiviewImageBinding
import com.shopping.swagbag.dummy.DummyModel
import com.shopping.swagbag.dummy.SingleProductImageModel

class ReviewImagesAdapter(
    private val context: Context,
    private val data: List<SingleProductImageModel>
) :
    RecyclerView.Adapter<ReviewImagesAdapter.MyViewHolder>() {

    inner class MyViewHolder(private val viewBinding: SingleReiviewImageBinding) :
        RecyclerView.ViewHolder(viewBinding.root) {

        fun bind(singleData: SingleProductImageModel){
            with(viewBinding){
                // set imgae
                Glide.with(context)
                    .load(singleData.mainImage)
                    .error(R.drawable.ic_launcher_foreground)
                    .placeholder(R.drawable.ic_swagbug_logo)
                    .into(reviewImage)
            }
        }

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {
        return MyViewHolder(
            SingleReiviewImageBinding.inflate(
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