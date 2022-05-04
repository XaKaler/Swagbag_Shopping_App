package com.shopping.swagbag.user.shoppingbeg.withproduct

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.shopping.swagbag.common.RecycleViewItemClick
import com.shopping.swagbag.databinding.SingleUserAddressBinding
import com.shopping.swagbag.user.address.address_list.AllAddressModel


class UserAddressAdapter(
    private val context: Context,
    private val data: List<AllAddressModel.Result>,
    private val itemClick: RecycleViewItemClick
) :
    RecyclerView.Adapter<UserAddressAdapter.MyViewHolder>() {

    var selectedPosition =-1

    inner class MyViewHolder(private val viewBinding: SingleUserAddressBinding) :
        RecyclerView.ViewHolder(viewBinding.root) {

        fun bind(singleData: AllAddressModel.Result, position: Int, itemClick: RecycleViewItemClick){
            with(viewBinding){
                userName.text = singleData.contactName
                addressType.text = singleData.title

                val fullAddress = "${singleData.address}\n${singleData.city}, ${singleData.state}\n${singleData.pincode}\n\n${singleData.contactMobile}"
                address.text = fullAddress

                userAddress.isChecked = selectedPosition == position

                userAddress.setOnClickListener{
                    selectedPosition = position
                    notifyDataSetChanged()
                    itemClick.onItemClickWithName("select", position)
                }


                //click listeners
                btnEdit.setOnClickListener{
                    itemClick.onItemClickWithName("edit", position)
                }
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {
        return MyViewHolder(
            SingleUserAddressBinding.inflate(
                LayoutInflater.from(context),
                parent,
                false
            )
        )
    }

    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {
        holder.bind(data[position] , position, itemClick)
    }

    override fun getItemCount()= data.size
}