package com.shopping.swagbag.auth.resetpassword

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.fragment.findNavController
import com.shopping.swagbag.R
import com.shopping.swagbag.databinding.FragmentVerificationCodeBinding
import com.shopping.swagbag.databinding.ToolbarWithNoMenuBinding

class VerificationCodeFragment : Fragment(R.layout.fragment_verification_code), View.OnClickListener {

    private lateinit var viewBinding: FragmentVerificationCodeBinding
    private lateinit var toolbarBinding: ToolbarWithNoMenuBinding

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        viewBinding = FragmentVerificationCodeBinding.bind(view)
        toolbarBinding = viewBinding.include


        initViews()
    }

    private fun initViews() {
        setToolbar()

        with(viewBinding){
            submit.setOnClickListener(this@VerificationCodeFragment)
        }
    }

    private fun setToolbar() {
        with(toolbarBinding){
            tvTitle.text = getString(R.string.verification_code)

            imgBack.setOnClickListener{
                findNavController().popBackStack()
            }
        }
    }

    override fun onClick(v: View?) {
        when(v?.id){
            R.id.submit->{findNavController().navigate(R.id.action_verificationCodeFragment_to_createPasswordFragment)}
        }
    }
}