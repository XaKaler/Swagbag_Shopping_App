package com.shopping.swagbag.helpcenter.with_order

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.View
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import androidx.recyclerview.widget.LinearLayoutManager
import com.shopping.swagbag.R
import com.shopping.swagbag.databinding.FragmentHelpCenterWithOrderBinding
import com.shopping.swagbag.databinding.ToolbarWithNoMenuWhiteBgBinding
import com.shopping.swagbag.dummy.DummyData

class HelpCenterWithOrderFragment : Fragment(R.layout.fragment_help_center_with_order) {

    private lateinit var viewBinding: FragmentHelpCenterWithOrderBinding
    private lateinit var toolbarBinding: ToolbarWithNoMenuWhiteBgBinding

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        viewBinding = FragmentHelpCenterWithOrderBinding.bind(view)
        toolbarBinding = viewBinding.include

        initViews()


    }

    private fun initViews() {
        with(viewBinding){
            rvOrderProduct.apply{
                layoutManager = LinearLayoutManager(context)
                adapter = DummyData().getTwoDummyData()?.let { HelpCenterAdapter(context, it) }
            }
        }
        setToolbar()

        getArgument()
    }

    private fun getArgument() {
        val args : HelpCenterWithOrderFragmentArgs by navArgs()
        val product = args.product
    }

    private fun setToolbar() {
        with(toolbarBinding){
            // set title
            tvTitle.text = getString(R.string.help_center)

            // back button click
            imgBack.setOnClickListener{
                findNavController().popBackStack()
            }
        }
    }
}