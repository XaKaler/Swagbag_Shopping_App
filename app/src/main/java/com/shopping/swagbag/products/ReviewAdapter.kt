package com.shopping.swagbag.products

import android.app.Activity
import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.navigation.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.shopping.swagbag.R
import com.shopping.swagbag.common.adapter.BestOfferAdapter
import com.shopping.swagbag.databinding.SingleBestOffersBinding
import com.shopping.swagbag.databinding.SingleProductBinding
import com.shopping.swagbag.databinding.SingleReviewBinding
import com.shopping.swagbag.dummy.DummyCategoryModel
import com.shopping.swagbag.dummy.DummyData
import com.shopping.swagbag.dummy.DummyModel
import com.shopping.swagbag.dummy.SingleProductModel

class ReviewAdapter(
    private val context: Context,
    private val data: List<DummyCategoryModel>
) :
    RecyclerView.Adapter<ReviewAdapter.MyViewHolder>() {

    inner class MyViewHolder(private val viewBinding: SingleReviewBinding) :
        RecyclerView.ViewHolder(viewBinding.root) {

        fun bind(singleData: DummyCategoryModel){
            with(viewBinding){
                rvReviewImages.apply {
                    layoutManager = LinearLayoutManager(context, LinearLayoutManager.HORIZONTAL, false)
                    adapter = ReviewImagesAdapter(context, DummyData().getSingleProductImage())
                }
            }
        }

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {
        return MyViewHolder(
            SingleReviewBinding.inflate(
                LayoutInflater.from(context),
                parent,
                false
            )
        )
    }

    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {
        holder.bind(data[position])
    }

    override fun getItemCount()= 1
}