package com.shopping.swagbag.common.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.shopping.swagbag.databinding.SingleBulletListBinding
import com.shopping.swagbag.databinding.SingleSliderProductsBinding
import com.shopping.swagbag.dummy.DummyModel
import com.shopping.swagbag.dummy.UserAddress

class OfferDetailsAdapter(
    private val context: Context,
    private val data: List<UserAddress>
) :
    RecyclerView.Adapter<OfferDetailsAdapter.BestProductViewHolder>() {

    inner class BestProductViewHolder(private val viewBinding: SingleBulletListBinding) :
        RecyclerView.ViewHolder(viewBinding.root) {

            fun bind(singleData: UserAddress, position: Int){
                with(viewBinding){
                    // set image

                }
            }

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): BestProductViewHolder {
        return BestProductViewHolder(SingleBulletListBinding.inflate(LayoutInflater.from(context), parent, false))
    }

    override fun onBindViewHolder(holder: BestProductViewHolder, position: Int) {
        holder.bind(data[position], position)
    }

    override fun getItemCount()= data.size
}