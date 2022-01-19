package com.shopping.swagbag

import android.content.Context
import android.view.View
import android.view.ViewGroup
import android.widget.BaseExpandableListAdapter
import android.widget.ExpandableListView
import com.shopping.swagbag.dummy.DummyModel
import android.graphics.Typeface

import android.widget.TextView

import android.view.LayoutInflater
import com.shopping.swagbag.databinding.SingleCategoryDropDownBinding


class ExpandableListAdapter(
    val context: Context,
    private val listHeader: List<DummyModel>,
    private val listChild: HashMap<DummyModel, List<DummyModel>>,
    val expandableListView: ExpandableListView
) : BaseExpandableListAdapter() {

    override fun getGroupCount() = listHeader.size

    override fun getChildrenCount(groupPosition: Int): Int = listChild[listHeader[groupPosition]]!!.size

    override fun getGroup(groupPosition: Int) {
        listHeader[groupPosition]
    }

    override fun getChild(groupPosition: Int, childPosition: Int) {
        listChild[listHeader[groupPosition]]!![childPosition]
    }

    override fun getGroupId(groupPosition: Int): Long {
        return groupPosition.toLong()
    }

    override fun getChildId(groupPosition: Int, childPosition: Int): Long {
        return childPosition.toLong()
    }

    override fun hasStableIds(): Boolean {
        return false
    }

    override fun getGroupView(
        groupPosition: Int,
        isExpanded: Boolean,
        convertView: View?,
        parent: ViewGroup?
    ): View {

        val headerTitle: DummyModel = getGroup(groupPosition) as DummyModel

        if (convertView == null) {
            val layoutInflater: LayoutInflater = context.getSystemService(Context.LAYOUT_INFLATER_SERVICE) as LayoutInflater
            //convertView = layoutInflater.inflate(R.layout.single_category_drop_down, null)

        }

        val lblListHeader = convertView
            ?.findViewById(R.id.tvCatName) as TextView
        lblListHeader.setTypeface(null, Typeface.BOLD)
        lblListHeader.text = headerTitle.name

        return convertView
    }

    override fun getChildView(
        groupPosition: Int,
        childPosition: Int,
        isLastChild: Boolean,
        convertView: View?,
        parent: ViewGroup?
    ): View {


        val headerTitle: DummyModel = getChild(groupPosition, childPosition) as DummyModel

        if (convertView == null) {
            val layoutInflater: LayoutInflater = context.getSystemService(Context.LAYOUT_INFLATER_SERVICE) as LayoutInflater
           // convertView = layoutInflater.inflate(R.layout.single_category_drop_down_menu, null)

        }

        val lblListHeader = convertView
            ?.findViewById(R.id.tvCatName) as TextView
        lblListHeader.setTypeface(null, Typeface.BOLD)
        lblListHeader.text = headerTitle.name

        return convertView
    }

    override fun isChildSelectable(groupPosition: Int, childPosition: Int): Boolean {
        return true
    }
}