package com.shopping.swagbag.user.order.with_items

import android.content.Context
import android.util.Log
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.core.content.ContextCompat
import androidx.recyclerview.widget.RecyclerView
import com.shopping.swagbag.R
import com.shopping.swagbag.common.RecycleViewItemClick
import com.shopping.swagbag.common.base.GeneralFunction
import com.shopping.swagbag.databinding.SingleOrderItemBinding

class OrderItemsAdapter(
    private val context: Context,
    private var data: List<OrderModel.OrderModelItem>,
    private val cancelTime: String,
    private val itemClick: RecycleViewItemClick
) :
    RecyclerView.Adapter<OrderItemsAdapter.BestProductViewHolder>() {

    inner class BestProductViewHolder(private val viewBinding: SingleOrderItemBinding) :
        RecyclerView.ViewHolder(viewBinding.root) {

            fun bind(singleData: OrderModel.OrderModelItem, position: Int, itemClick: RecycleViewItemClick){
                with(viewBinding){
                    //orderStatus.text = singleData.status
                    orderDate.text =
                        GeneralFunction.convertServerDateToUserTimeZoneTask(singleData.createdDate)

                    // val orderPrice = singleData.products[position].price *singleData.products[position].quantity
                    totalPrice.text = singleData.price.toString()

                    //set cancel and return button text according to order status
                    when(singleData.status){
                        "delivered" -> {
                            orderStatus.text = context.getString(R.string.returns)
                            cancel.text = context.getString(R.string.returns)
                            cancel.background = ContextCompat.getDrawable(context, R.drawable.rec_white_5)
                        }
                        "pending_payment" -> orderStatus.text = "Pending payment"
                        "return_in_process" -> orderStatus.text = "Return in process"
                        "cancel" -> orderStatus.text = "Cancel"
                        "processing" -> {
                            orderStatus.text = "Processing"
                        }
                    }

                    // button click
                    cancel.setOnClickListener {
                        Log.e("order", "cancel or return button: ${cancel.text}", )
                        if (cancel.text == "Cancel")
                            itemClick.onItemClickWithName("cancel", position)
                        else
                            itemClick.onItemClickWithName("return", position)
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