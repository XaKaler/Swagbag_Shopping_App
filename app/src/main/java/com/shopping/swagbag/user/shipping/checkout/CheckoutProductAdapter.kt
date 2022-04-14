package com.shopping.swagbag.user.shipping.checkout

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.shopping.swagbag.R
import com.shopping.swagbag.databinding.*
import com.shopping.swagbag.dummy.DummyModel
import com.shopping.swagbag.user.shoppingbeg.withproduct.GetCartModel

class CheckoutProductAdapter(
    private val context: Context,
    private val data: List<GetCartModel.Result>
) :
    RecyclerView.Adapter<CheckoutProductAdapter.MyViewHolder>() {

    inner class MyViewHolder(private val viewBinding: SingleChekcoutProductBinding) :
        RecyclerView.ViewHolder(viewBinding.root) {

        fun bind(singleData: GetCartModel.Result){
            with(viewBinding){
                val nameProduct = "${singleData.product.name} x ${singleData.quantity}"
                productName.text = nameProduct
                val price = "${context.getString(R.string.Rs)}${singleData.price*singleData.quantity}"
                totalPrice.text = price
            }
        }

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {
        return MyViewHolder(
            SingleChekcoutProductBinding.inflate(
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