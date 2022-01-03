package com.shopping.swagbag.privacypolicy

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.fragment.findNavController
import com.shopping.swagbag.R
import com.shopping.swagbag.databinding.FragmentContactUsBinding
import com.shopping.swagbag.databinding.FragmentPrivacyPolicyBinding
import com.shopping.swagbag.databinding.ToolbarWithNoMenuWhiteBgBinding

class PrivacyPolicyFragment : Fragment(R.layout.fragment_privacy_policy) {

    private lateinit var viewBinding: FragmentPrivacyPolicyBinding
    private lateinit var toolbarBinding: ToolbarWithNoMenuWhiteBgBinding

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        viewBinding = FragmentPrivacyPolicyBinding.bind(view)
        toolbarBinding = viewBinding.include

        initViews()


    }

    private fun initViews() {
        setToolbar()
    }

    private fun setToolbar() {
        with(toolbarBinding){
            // set title
            tvTitle.text = getString(R.string.privacy_policy)

            // back button click
            imgBack.setOnClickListener{
                findNavController().popBackStack()
            }
        }
    }
}