package com.shopping.swagbag.category

import android.content.Context
import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.GridLayoutManager
import com.shopping.swagbag.MainActivity
import com.shopping.swagbag.R
import com.shopping.swagbag.databinding.FragmentCategoryBinding
import com.shopping.swagbag.databinding.ToolbarWithNoMenuWhiteBgBinding
import com.shopping.swagbag.dummy.DummyData

class CategoryFragment : Fragment(R.layout.fragment_category) {

    private lateinit var viewBinding: FragmentCategoryBinding
    private lateinit var toolbarBinding: ToolbarWithNoMenuWhiteBgBinding
    private lateinit var mainActivity: MainActivity


    override fun onAttach(context: Context) {
        super.onAttach(context)
        mainActivity = context as MainActivity
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        viewBinding = FragmentCategoryBinding.bind(view)
        toolbarBinding = viewBinding.include

        initViews()
        mainActivity.showToolbar()

    }

    private fun initViews() {
        setToolbar()

        setCategoryData()
    }

    private fun setCategoryData() {
        with(viewBinding) {
            rvCategory.apply{
                layoutManager = GridLayoutManager(context, 2)
                adapter = DummyData().getDummyCategory()?.let { DummyData().getDummyCategory()
                    ?.let { it1 -> CategoryAdapter(context, it1, mainActivity) } }
            }
        }
    }

    private fun setToolbar() {
        with(toolbarBinding) {
            // set title
            tvTitle.text = getString(R.string.category)

            // back button click
            imgBack.setOnClickListener {
                findNavController().popBackStack()
            }
        }
    }

}