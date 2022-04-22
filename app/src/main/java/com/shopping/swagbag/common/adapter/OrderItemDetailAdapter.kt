package com.shopping.swagbag.common.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.shopping.swagbag.R
import com.shopping.swagbag.common.RecycleViewItemClick
import com.shopping.swagbag.databinding.SingleShippingViewOrderDetailsBinding
import com.shopping.swagbag.user.order.with_items.OrderModel
import kotlinx.android.synthetic.main.single_shipping_view_order_details.view.*

class OrderItemDetailAdapter(
    private val context: Context,
    private val data: OrderModel.OrderModelItem,
    private val itemClick: RecycleViewItemClick
) :
    RecyclerView.Adapter<OrderItemDetailAdapter.MyViewHolder>() {

    inner class MyViewHolder(private val viewBinding: SingleShippingViewOrderDetailsBinding) :
        RecyclerView.ViewHolder(viewBinding.root) {

            fun bind(singleData: OrderModel.OrderModelItem, position: Int, itemClick: RecycleViewItemClick){
                with(viewBinding){
                    // set image
                    Glide.with(context)
                        .load(singleData.products[position].product.file[0].location)
                        .error(R.drawable.ic_launcher_foreground)
                        .placeholder(R.drawable.ic_swagbug_logo)
                        .into(productImg)

                    // set text
                    productName.text = singleData.products[position].productname
                    productDesc.text = singleData.products[position].product.shortDesc

                    val price = singleData.products[position].price.toFloat()*singleData.products[position].quantity
                    productPrice.text = price.toString()

                    //orderStatus.text = singleData.status

                    itemView.setOnClickListener{
                        itemClick.onItemClickWithName("detail", position)
                    }

                }
            }

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {
        return MyViewHolder(SingleShippingViewOrderDetailsBinding.inflate(LayoutInflater.from(context), parent, false))
    }

    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {
        holder.bind(data, position, itemClick)
    }

    override fun getItemCount()= data.products.size
}