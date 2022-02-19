package com.shopping.swagbag.user.shipping

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.View
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.shopping.swagbag.R
import com.shopping.swagbag.common.adapter.InvoiceAdapter
import com.shopping.swagbag.databinding.FragmentGetInvoiceBinding
import com.shopping.swagbag.databinding.ToolbarWithNoMenuWhiteBgBinding
import com.shopping.swagbag.dummy.DummyData

class GetInvoiceFragment : Fragment(R.layout.fragment_get_invoice) {

    private lateinit var viewBinding: FragmentGetInvoiceBinding
    private lateinit var toolbarBinding: ToolbarWithNoMenuWhiteBgBinding

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        viewBinding = FragmentGetInvoiceBinding.bind(view)
        toolbarBinding = viewBinding.include

        initViews()


    }

    private fun initViews() {
        with(viewBinding){
            rvInvoice.apply {
                layoutManager = LinearLayoutManager(context)
                adapter = DummyData().getDummyData()?.let { InvoiceAdapter(context, it) }
            }

            helpCenter.setOnClickListener{
                findNavController().navigate(R.id.action_getInvoiceFragment_to_helpCenterWithoutOrder)
            }
        }

        toolbar()
    }


    private fun toolbar() {
        with(toolbarBinding){
            // set title
            tvTitle.text = getString(R.string.order_details)

            // back button click
            imgBack.setOnClickListener{
                findNavController().popBackStack()
            }
        }
    }

}