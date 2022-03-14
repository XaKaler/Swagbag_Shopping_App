package com.shopping.swagbag.user.shoppingbeg.withproduct

import android.content.Context
import android.util.Log
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.shopping.swagbag.common.RecycleViewItemClick
import com.shopping.swagbag.databinding.SingleProductOfShoppingBegBinding


class ShoppingBegProductAdapter(
    private val context: Context,
    private var data: List<GetCartModel.Result>,
    private val itemClick: RecycleViewItemClick
) :
    RecyclerView.Adapter<ShoppingBegProductAdapter.ShoppingBegViewHolder>() {

    inner class ShoppingBegViewHolder(private val viewBinding: SingleProductOfShoppingBegBinding) :
        RecyclerView.ViewHolder(viewBinding.root) {

        fun bind(singleData: GetCartModel.Result, position: Int, itemClick: RecycleViewItemClick){
            with(viewBinding){
                // set image
                Glide.with(context)
                    .load(singleData.product.file[0].location)
                    .into(productImage)

                Log.e("TAG", "bind: ${singleData.product.file[0]}", )

                // set text
                productName.text = singleData.product.name
                productDetails.text = singleData.product.shortDesc
                sellerName.text = singleData.product.vendor
                newPrice.text = singleData.product.sellingPrice.toString()

                val discount = "${singleData.product.discountedPrice}%Off"
                off.text = discount

                val quantityCount = "Qty: ${singleData.quantity}"
                size.text = quantityCount

                remove.setOnClickListener{
                    itemClick.onItemClickWithName("remove", position)
                }
            }
        }

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ShoppingBegViewHolder {
        return ShoppingBegViewHolder(
            SingleProductOfShoppingBegBinding.inflate(
                LayoutInflater.from(context),
                parent,
                false
            )
        )
    }

    override fun onBindViewHolder(holder: ShoppingBegViewHolder, position: Int) {
        holder.bind(data[position], position, itemClick)
    }

    override fun getItemCount()= data.size

    fun updateList(updateData: List<GetCartModel.Result>){
        data = updateData
        notifyDataSetChanged()
    }
}