package com.shopping.swagbag.auth.signin

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.fragment.findNavController
import com.shopping.swagbag.R
import com.shopping.swagbag.databinding.FragmentCouponsBinding
import com.shopping.swagbag.databinding.FragmentSignInBinding
import com.shopping.swagbag.databinding.ToolbarWithNoMenuWhiteBgBinding

class SignInFragment : Fragment(R.layout.fragment_sign_in), View.OnClickListener {

    private lateinit var viewBinding: FragmentSignInBinding

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        viewBinding = FragmentSignInBinding.bind(view)

        initViews()


    }

    private fun initViews() {
        with(viewBinding){
            signUp.setOnClickListener(this@SignInFragment)
            btnSignIn.setOnClickListener(this@SignInFragment)
            forgotPassword.setOnClickListener(this@SignInFragment)
        }
    }

    override fun onClick(v: View?) {
        when(v?.id){
            R.id.signUp -> findNavController().navigate(R.id.action_signInFragment_to_signUpFragment)
            R.id.forgotPassword -> findNavController().navigate(R.id.action_signInFragment_to_resetPassword)
            R.id.btnSignIn -> findNavController().navigate(R.id.action_signInFragment_to_profileFragment)
        }
    }

}