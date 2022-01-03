package com.shopping.swagbag.common.adapter

import com.shopping.swagbag.common.FreeData
import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.shopping.swagbag.R
import com.shopping.swagbag.databinding.*
import com.shopping.swagbag.dummy.DummyModel

class Card11Adapter(
    private val context: Context,
    private val data: List<DummyModel>
) :
    RecyclerView.Adapter<Card11Adapter.MyViewHolder>() {

    inner class MyViewHolder(private val viewBinding: SingleCard11Binding) :
        RecyclerView.ViewHolder(viewBinding.root) {

        fun bind(singleData: DummyModel){
            with(viewBinding){

                // set text
                tvMostWanted.text = singleData.name
                tvMostWantedDetails.text = singleData.details

                Glide.with(context)
                    .load(singleData.image)
                    .error(R.drawable.ic_launcher_foreground)
                    .placeholder(R.drawable.ic_swagbug_logo)
                    .into(imgMostWanted)
            }
        }

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {
        return MyViewHolder(
            SingleCard11Binding.inflate(
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