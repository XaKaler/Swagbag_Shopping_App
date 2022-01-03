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

class Card16Adapter(
    private val context: Context,
    private val data: List<DummyModel>
) :
    RecyclerView.Adapter<Card16Adapter.MyViewHolder>() {

    inner class MyViewHolder(private val viewBinding: SingleCard16Binding) :
        RecyclerView.ViewHolder(viewBinding.root) {

        fun bind(singleData: DummyModel){
            with(viewBinding){

                // set text
                tvProductCategory.text = singleData.name

                Glide.with(context)
                    .load(singleData.image)
                    .error(R.drawable.ic_launcher_foreground)
                    .placeholder(R.drawable.ic_swagbug_logo)
                    .into(productImage)
            }
        }

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {
        return MyViewHolder(
            SingleCard16Binding.inflate(
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