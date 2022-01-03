package com.shopping.swagbag.termsofuses

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.fragment.findNavController
import com.shopping.swagbag.R
import com.shopping.swagbag.databinding.FragmentContactUsBinding
import com.shopping.swagbag.databinding.FragmentTermsOfUsesBinding
import com.shopping.swagbag.databinding.ToolbarWithNoMenuWhiteBgBinding


class TermsOfUsesFragment : Fragment(R.layout.fragment_terms_of_uses) {

    private lateinit var viewBinding: FragmentTermsOfUsesBinding
    private lateinit var toolbarBinding: ToolbarWithNoMenuWhiteBgBinding

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        viewBinding = FragmentTermsOfUsesBinding.bind(view)
        toolbarBinding = viewBinding.include

        initViews()


    }

    private fun initViews() {
        setToolbar()
    }

    private fun setToolbar() {
        with(toolbarBinding){
            // set title
            tvTitle.text = getString(R.string.terms_of_uses)

            // back button click
            imgBack.setOnClickListener{
                findNavController().popBackStack()
            }
        }
    }
}