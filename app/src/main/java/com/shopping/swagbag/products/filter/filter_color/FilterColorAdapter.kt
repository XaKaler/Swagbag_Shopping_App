package com.shopping.swagbag.products.filter.filter_color

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.shopping.swagbag.databinding.SingleFilterColorBinding
import com.shopping.swagbag.databinding.SingleFilterSizeBinding
import com.shopping.swagbag.dummy.DummyModel


class FilterColorAdapter(
    private val context: Context,
    private val data: List<DummyModel>
) :
    RecyclerView.Adapter<FilterColorAdapter.MyViewHolder>() {

    inner class MyViewHolder(private val viewBinding: SingleFilterColorBinding) :
        RecyclerView.ViewHolder(viewBinding.root) {

            fun bind(singleData: DummyModel){
                with(viewBinding){
                    //sizeType.text = singleData.filterName
                }
            }
    }

    private fun setButtonBackground(position: Int) {
        for(i in data.indices){

        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {
        return MyViewHolder(SingleFilterColorBinding.inflate(LayoutInflater.from(context), parent, false))
    }

    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {
        holder.bind(data[position])
    }

    override fun getItemCount()= data.size
}