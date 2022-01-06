package com.shopping.swagbag.common.adapter

import android.app.Activity
import com.shopping.swagbag.common.FreeData
import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.navigation.findNavController
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.shopping.swagbag.R
import com.shopping.swagbag.common.RecycleItemClickListener
import com.shopping.swagbag.databinding.SingleBestProductsBinding
import com.shopping.swagbag.databinding.SingleGetInvoiceBinding
import com.shopping.swagbag.dummy.DummyModel

class InvoiceAdapter(
    private val context: Context,
    private val data: List<DummyModel>
) :
    RecyclerView.Adapter<InvoiceAdapter.MyViewHolder>() {

    inner class MyViewHolder(private val viewBinding: SingleGetInvoiceBinding) :
        RecyclerView.ViewHolder(viewBinding.root) {

            fun bind(singleData: DummyModel, position: Int){
                with(viewBinding){
                    // set image
                }
            }

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {
        return MyViewHolder(SingleGetInvoiceBinding.inflate(LayoutInflater.from(context), parent, false))
    }

    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {
        holder.bind(data[position], position)
    }

    override fun getItemCount()= data.size
}