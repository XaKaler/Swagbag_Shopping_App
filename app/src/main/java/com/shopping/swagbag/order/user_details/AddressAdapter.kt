package com.shopping.swagbag.order.user_details

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.shopping.swagbag.R
import com.shopping.swagbag.common.adapter.BestOfferAdapter
import com.shopping.swagbag.databinding.SingleUserDetailBinding
import com.shopping.swagbag.dummy.DummyModel




class AddressAdapter(
    private val context: Context,
    private val data: List<DummyModel>
) :
    RecyclerView.Adapter<AddressAdapter.MyViewHolder>() {

    inner class MyViewHolder(private val viewBinding: SingleUserDetailBinding) :
        RecyclerView.ViewHolder(viewBinding.root) {

        fun bind(singleData: DummyModel){
            with(viewBinding){

            }
        }

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {
        return MyViewHolder(
            SingleUserDetailBinding.inflate(
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