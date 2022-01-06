package com.shopping.swagbag.coupons

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.shopping.swagbag.common.adapter.OfferDetailsAdapter
import com.shopping.swagbag.databinding.SingleCouponBinding
import com.shopping.swagbag.dummy.DummyData
import com.shopping.swagbag.dummy.DummyModel


class CouponAdapter(
    private val context: Context,
    private val data: List<DummyModel>
) :
    RecyclerView.Adapter<CouponAdapter.MyViewHolder>() {

    inner class MyViewHolder(private val viewBinding: SingleCouponBinding) :
        RecyclerView.ViewHolder(viewBinding.root) {

        fun bind(singleData: DummyModel){
            with(viewBinding){
                details.setOnClickListener{
                    details.visibility = View.GONE
                    hide.visibility = View.VISIBLE
                    rvOfferDetails.visibility = View.VISIBLE
                }
                hide.setOnClickListener{
                    hide.visibility = View.GONE
                    details.visibility = View.VISIBLE
                    rvOfferDetails.visibility = View.GONE
                }

                rvOfferDetails.apply {
                    layoutManager = LinearLayoutManager(context)
                    adapter = OfferDetailsAdapter(context, DummyData().getUserAddress())
                }
            }
        }

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {
        return MyViewHolder(
            SingleCouponBinding.inflate(
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