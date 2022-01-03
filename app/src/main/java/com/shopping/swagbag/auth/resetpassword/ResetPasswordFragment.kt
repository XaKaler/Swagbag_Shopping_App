package com.shopping.swagbag.auth.resetpassword

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.fragment.findNavController
import com.shopping.swagbag.R
import com.shopping.swagbag.databinding.FragmentResetPasswordBinding
import com.shopping.swagbag.databinding.ToolbarWithNoMenuBinding

class ResetPassword : Fragment(R.layout.fragment_reset_password), View.OnClickListener {

    private lateinit var viewBinding: FragmentResetPasswordBinding
    private lateinit var toolbarBinding: ToolbarWithNoMenuBinding

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        viewBinding = FragmentResetPasswordBinding.bind(view)
        toolbarBinding = viewBinding.include

        initViews()


    }

    private fun initViews() {
        setToolbar()

        //clickListeners
        with(viewBinding){
        btnSendOtp.setOnClickListener(this@ResetPassword)
        }
    }

    private fun setToolbar() {
        with(toolbarBinding){
            // set title
            tvTitle.text = getString(R.string.reset_password)

            // back button click
            imgBack.setOnClickListener{
                findNavController().popBackStack()
            }
        }
    }

    override fun onClick(v: View?) {
        when(v?.id){

            R.id.btnSendOtp -> findNavController().navigate(R.id.action_resetPassword_to_verificationCodeFragment)
        }
    }

}