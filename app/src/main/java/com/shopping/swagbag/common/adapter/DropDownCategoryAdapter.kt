package com.shopping.swagbag.common.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.shopping.swagbag.databinding.SingleCategoryDropDownBinding
import com.shopping.swagbag.dummy.DummyCategoryModel
import com.shopping.swagbag.dummy.DummyData
import com.shopping.swagbag.dummy.DummyModel
import java.util.ArrayList
import kotlin.math.max

class DropDownCategoryAdapter(
    private val context: Context,
    private val data: ArrayList<DummyModel>
) : RecyclerView.Adapter<DropDownCategoryAdapter.MyViewHolder>() {

    val map = mutableMapOf<Int, Boolean>()

    inner class MyViewHolder(private val viewBinding: SingleCategoryDropDownBinding) :
        RecyclerView.ViewHolder(viewBinding.root) {

        fun bind(singleData: DummyModel, position: Int) {
            setMap()
            with(viewBinding) {
                tvCatName.text = singleData.name

                // click listener
                singleCategory.setOnClickListener {
                    setSubCategory(position, viewBinding)
                }
            }
        }

        private fun setMap() {
            for (i in 0..data.size) {
                map[i] = false
            }
        }

        private fun setSubCategory(position: Int, viewBinding: SingleCategoryDropDownBinding) {
            with(viewBinding) {
            for (i in 0..data.size) {
                if (position == i && map[position] == false) {
                    map[position] = true
                    view.visibility = View.GONE
                    imgCatArrow.rotation = max(180f, 180f)
                    subCatCard.visibility = View.VISIBLE
                }
                else if(position == i && map[position]  == true){
                    map[position] = false
                    view.visibility = View.VISIBLE
                    imgCatArrow.rotation = max(0f, 0f)
                    subCatCard.visibility = View.GONE
                }
            }
        }

            with(viewBinding) {
                rvCatSubMenu.apply {
                    layoutManager = LinearLayoutManager(context)
                    adapter = DummyData().getDummyData()?.let { SubCategoryAdapter(context, it) }
                }
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int) = MyViewHolder(
        SingleCategoryDropDownBinding.inflate(LayoutInflater.from(context), parent, false)
    )

    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {
        data[position].let { holder.bind(it, position) }
    }

    override fun getItemCount() = data.size
}