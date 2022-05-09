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
import java.text.SimpleDateFormat
import java.util.*

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
                    orderId.text = singleData.orderid.toString()

                    //set cancel and return button text according to order status
                    when (singleData.status) {
                        "delivered" -> {
                            showReturnOrCancelButton(context.getString(R.string.returns))
                            setStatus("Delivered")
                        }
                        "pending_payment" -> {
                            setStatus("Pending payment")
                            hideReturnOrCancelButton()
                        }
                        "return_in_process" -> {
                            setStatus("Return in process")
                            hideReturnOrCancelButton()
                        }
                        "cancel" -> {
                            setStatus("Cancel")
                            hideReturnOrCancelButton()
                        }
                        "processing" -> {
                            orderStatus.text = "Processing"

                            // The order cancellation time that is available from the Settings API,
                            // if the same time has elapsed since the order has been placed,
                            // then the user can cancel the order.
                            // User can cancel order only in processing status
                            try {
                                val formatedServerDat =
                                    GeneralFunction.convertServerDateToUserTimeZoneTask(
                                        singleData.createdDate,
                                        "yyyy-MM-dd'T'HH:mm:ss.SSS",
                                        "hh:mm:ss_yyyy.MM.dd"
                                    )

                                val df = SimpleDateFormat("hh:mm:ss_yyyy.MM.dd")
                                val date1: Date = Date()
                                val date2: Date = df.parse(formatedServerDat)
                                val diff: Long = date1.time - date2.time

                                val cancelInMilli = cancelTime.toInt() * 60 * 1000
                                val cancelDiff = cancelInMilli - diff

                                Log.e(
                                    "cancel",
                                    "Order date is : $date2\n" +
                                            "Current date is : $date1\n" +
                                            "cancel time is : $cancelInMilli\n" +
                                            "diff is : $cancelDiff\n " +
                                            "is greater : ${cancelInMilli > diff}"
                                )

                                if (cancelInMilli > diff)
                                    showReturnOrCancelButton(context.getString(R.string.cancel))
                                else
                                    hideReturnOrCancelButton()


                            } catch (e: Exception) {
                                Log.e("TEST", "Exception", e)
                            }
                        }
                    }

                    // button click
                    cancel.setOnClickListener {
                        Log.e("order", "cancel or return button: ${cancel.text}")
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

        private fun hideReturnOrCancelButton() {
            with(viewBinding){
                cancel.text = ""
                cancel.background = null
            }
        }

        private fun setStatus(status: String) {
            viewBinding.orderStatus.text = status
        }

        private fun showReturnOrCancelButton(buttonText: String) {
            with(viewBinding){
                cancel.text = buttonText
                cancel.background =
                    ContextCompat.getDrawable(context, R.drawable.rec_white_5)
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