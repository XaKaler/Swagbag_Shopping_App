package com.shopping.swagbag.user.order.with_items

import android.app.Activity
import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.findNavController
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.shopping.swagbag.R
import com.shopping.swagbag.common.RecycleViewItemClick
import com.shopping.swagbag.databinding.SingleOrderItemBinding
import com.shopping.swagbag.user.shoppingbeg.withproduct.GetCartModel

class OrderItemsAdapter(
    private val context: Context,
    private var data: List<OrderModel.OrderModelItem>,
    private val itemClick: RecycleViewItemClick
) :
    RecyclerView.Adapter<OrderItemsAdapter.BestProductViewHolder>() {

    inner class BestProductViewHolder(private val viewBinding: SingleOrderItemBinding) :
        RecyclerView.ViewHolder(viewBinding.root) {

            fun bind(singleData: OrderModel.OrderModelItem, position: Int, itemClick: RecycleViewItemClick){
                with(viewBinding){
                    orderStatus.text = singleData.status
                    orderDate.text = singleData.createdDate
                    totalPrice.text = singleData.finalprice

                    cancel.setOnClickListener {
                        itemClick.onItemClickWithName("cancel", position)
                    }

                    view.setOnClickListener {
                        itemClick.onItemClickWithName("view", position)
                    }
                }
            }

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): BestProductViewHolder {
        return BestProductViewHolder(SingleOrderItemBinding.inflate(LayoutInflater.from(context), parent, false))
    }

    override fun onBindViewHolder(holder: BestProductViewHolder, position: Int) {
        holder.bind(data[position], position, itemClick)
    }

    override fun getItemCount()= data.size

    fun updateList(updateData: MutableList<OrderModel.OrderModelItem>){
        data = updateData
        notifyDataSetChanged()
    }
}