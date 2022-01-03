package com.shopping.swagbag.auth.signup

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.fragment.findNavController
import com.shopping.swagbag.R
import com.shopping.swagbag.databinding.FragmentSignInBinding
import com.shopping.swagbag.databinding.FragmentSignUpBinding


class SignUpFragment : Fragment(R.layout.fragment_sign_up), View.OnClickListener {

    private lateinit var viewBinding: FragmentSignUpBinding

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        viewBinding = FragmentSignUpBinding.bind(view)

        initViews()


    }

    private fun initViews() {
        with(viewBinding){
            signIn.setOnClickListener(this@SignUpFragment)
            btnSignUp.setOnClickListener(this@SignUpFragment)
        }
    }

    override fun onClick(v: View?) {
        when(v?.id){
            R.id.signIn -> findNavController().navigate(R.id.action_signUpFragment_to_signInFragment)
            R.id.btnSignUp -> findNavController().navigate(R.id.action_signUpFragment_to_profileFragment)
        }
    }
}