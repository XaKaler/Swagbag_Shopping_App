package com.shopping.swagbag.common.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.shopping.swagbag.R
import com.shopping.swagbag.databinding.SingleCategoryDropDownBinding
import com.shopping.swagbag.dummy.DummyData
import com.shopping.swagbag.dummy.DummyModel
import java.util.*
import kotlin.math.max

class DropDownCategoryAdapter(
    private val context: Context,
    private val data: ArrayList<DummyModel>
) : RecyclerView.Adapter<DropDownCategoryAdapter.MyViewHolder>() {

    var selectedPosition = -1
    val map = mutableMapOf<Int, Boolean>()
    var subCategoryVisible = false

    inner class MyViewHolder(private val viewBinding: SingleCategoryDropDownBinding) :
        RecyclerView.ViewHolder(viewBinding.root) {

        private val dataSize: Int = data.size

        fun bind(singleData: DummyModel, position: Int) {
            setMap()
            with(viewBinding) {

                if(position == dataSize-1){
                    //view.visibility = View.GONE
                }

                tvCatName.text = singleData.name
                Glide
                    .with(context)
                    .load(singleData.image)
                    .error(R.drawable.ic_launcher_foreground)
                    .placeholder(R.drawable.logo)
                    .into(catIcon)

                if (selectedPosition == position) {
                    with(viewBinding) {
                        rvCatSubMenu.apply {
                            layoutManager = LinearLayoutManager(context)
                            adapter =
                                DummyData().getDummyData()?.let { SubCategoryAdapter(context, it) }
                        }
                    }
                    if (subCategoryVisible) {
                        imgCatArrow.rotation = max(0f, 0f)
                        rvCatSubMenu.visibility = View.GONE
                    } else {
                        imgCatArrow.rotation = max(180f, 180f)
                        rvCatSubMenu.visibility = View.VISIBLE
                    }
                } else {
                    imgCatArrow.rotation = max(0f, 0f)
                    rvCatSubMenu.visibility = View.GONE
                }
                // click listener
                singleCategory.setOnClickListener {
                    selectedPosition = position
                    // setSubCategory(position, viewBinding)
                    notifyDataSetChanged()
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
                   // view.visibility = View.GONE
                    imgCatArrow.rotation = max(180f, 180f)
                    rvCatSubMenu.visibility = View.VISIBLE
                }
                else if(position == i && map[position]  == true){
                    map[position] = false
                   // view.visibility = View.VISIBLE
                    imgCatArrow.rotation = max(0f, 0f)
                    rvCatSubMenu.visibility = View.GONE
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