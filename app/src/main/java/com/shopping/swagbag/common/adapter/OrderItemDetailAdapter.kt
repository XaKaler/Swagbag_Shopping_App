package com.shopping.swagbag.common.adapter

import android.app.Activity
import com.shopping.swagbag.common.FreeData
import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.navigation.findNavController
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.shopping.swagbag.R
import com.shopping.swagbag.common.RecycleItemClickListener
import com.shopping.swagbag.databinding.SingleBestProductsBinding
import com.shopping.swagbag.databinding.SingleShippingViewOrderDetailsBinding
import com.shopping.swagbag.databinding.SingleSliderProductsBinding
import com.shopping.swagbag.dummy.DummyModel

class OrderItemDetailAdapter(
    private val context: Context,
    private val data: List<DummyModel>,
    private val itemClickListener: RecycleItemClickListener
) :
    RecyclerView.Adapter<OrderItemDetailAdapter.MyViewHolder>() {

    inner class MyViewHolder(private val viewBinding: SingleShippingViewOrderDetailsBinding) :
        RecyclerView.ViewHolder(viewBinding.root) {

            fun bind(singleData: DummyModel, position: Int, itemClickListener: RecycleItemClickListener){
                with(viewBinding){
                    // set image
                    Glide.with(context)
                        .load(singleData.image)
                        .error(R.drawable.ic_launcher_foreground)
                        .placeholder(R.drawable.ic_swagbug_logo)
                        .into(productImg)

                    // set text
                    tvProductCompnayName.text = singleData.name.toString()
                    tvProductName.text = singleData.details

                    singleProduct.setOnClickListener{
                        itemClickListener.onSingleItemClickListener(position)
                    }

                }
            }

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {
        return MyViewHolder(SingleShippingViewOrderDetailsBinding.inflate(LayoutInflater.from(context), parent, false))
    }

    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {
        holder.bind(data[position], position, itemClickListener)
    }

    override fun getItemCount()= data.size
}