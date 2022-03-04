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
    private val data: List<GiftCardModel.Result>
) :
    RecyclerView.Adapter<CouponAdapter.MyViewHolder>() {

    inner class MyViewHolder(private val viewBinding: SingleCouponBinding) :
        RecyclerView.ViewHolder(viewBinding.root) {

        fun bind(singleData: GiftCardModel.Result){
            with(viewBinding){
                tvOff.text = singleData.coupon
                val minAmount = "Rs. ${singleData.minimumAmount}"
                tvMinimumPurchase.text = minAmount
                tvExpiryDate.text = singleData.expDate
                desc.text = singleData.desc

                details.setOnClickListener{
                    details.visibility = View.GONE
                    hide.visibility = View.VISIBLE
                    desc.visibility = View.VISIBLE
                }
                hide.setOnClickListener{
                    hide.visibility = View.GONE
                    details.visibility = View.VISIBLE
                    desc.visibility = View.GONE
                }

              /*  rvOfferDetails.apply {
                    layoutManager = LinearLayoutManager(context)
                    adapter = OfferDetailsAdapter(context, DummyData().getUserAddress())
                }*/
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