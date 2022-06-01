package com.shopping.swagbag.account

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.shopping.swagbag.common.RecycleViewItemClick
import com.shopping.swagbag.databinding.SingleAccountTabBinding


class AccountMenuAdapter(
    private val context: Context,
    private val data: List<AccountMenu>,
    private val itemClick: RecycleViewItemClick
) :
    RecyclerView.Adapter<AccountMenuAdapter.MyViewHolder>() {

    inner class MyViewHolder(private val viewBinding: SingleAccountTabBinding) :
        RecyclerView.ViewHolder(viewBinding.root) {

        fun bind(singleData: AccountMenu, position: Int, itemClick: RecycleViewItemClick) {
            with(viewBinding) {
                singleData.apply {
                    menuIcon.setImageResource(icon)
                    tvMenuName.text = menuName

                    if(data.size-1 == position)
                        bottomLine.visibility = View.GONE

                    itemView.setOnClickListener {
                        itemClick.onItemClickWithName(menuName, position)
                    }
                }
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {
        return MyViewHolder(
            SingleAccountTabBinding.inflate(
                LayoutInflater.from(context),
                parent,
                false
            )
        )
    }

    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {
        holder.bind(data[position], position, itemClick)
    }

    override fun getItemCount() = data.size
}