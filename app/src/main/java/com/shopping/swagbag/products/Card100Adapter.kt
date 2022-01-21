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
import com.shopping.swagbag.databinding.SingleCard10Binding
import com.shopping.swagbag.databinding.SingleProductBinding
import com.shopping.swagbag.dummy.DummyModel

class Card10Adapter(
    private val context: Context,
    private val data: List<DummyModel>
) :
    RecyclerView.Adapter<Card10Adapter.MyViewHolder>() {

    inner class MyViewHolder(private val viewBinding: SingleCard10Binding) :
        RecyclerView.ViewHolder(viewBinding.root) {

        fun bind(singleData: DummyModel){
            with(viewBinding){
                // set imgae
                Glide.with(context)
                    .load(singleData.image)
                    .into(imgFeatureBrand)

                // set text
                productName.text = singleData.name.toString()
                productDetails.text = singleData.details
            }
        }

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {
        return MyViewHolder(
            SingleCard10Binding.inflate(
                LayoutInflater.from(context),
                parent,
                false
            )
        )
    }

    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {
        holder.bind(data[position])
        if(position == 0){
            val padding: Int = context.resources.getDimensionPixelOffset(com.shopping.swagbag.R.dimen.screen_padding_15)
            holder.itemView.setPadding(padding, 0,0,0)
        }
    }

    override fun getItemCount()= data.size
}