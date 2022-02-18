package com.shopping.swagbag.user.auth.resetpassword

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.View
import androidx.navigation.fragment.findNavController
import com.shopping.swagbag.R
import com.shopping.swagbag.databinding.FragmentCreatePasswordBinding
import com.shopping.swagbag.databinding.ToolbarWithNoMenuBinding

class CreatePasswordFragment : Fragment(R.layout.fragment_create_password), View.OnClickListener {

    private lateinit var viewBinding: FragmentCreatePasswordBinding
    private lateinit var toolbarBinding: ToolbarWithNoMenuBinding

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        viewBinding = FragmentCreatePasswordBinding.bind(view)
        toolbarBinding = viewBinding.include

        initViews()


    }

    private fun initViews() {
        setToolbar()

        with(viewBinding){
            save.setOnClickListener(this@CreatePasswordFragment)
        }
    }

    private fun setToolbar() {
        with(toolbarBinding){
            // set title
            tvTitle.text = getString(R.string.create_password)

            // back button click
            imgBack.setOnClickListener{
                findNavController().popBackStack()
            }
        }
    }

    override fun onClick(v: View?) {
        when(v?.id){
            R.id.save->{
                findNavController().navigate(R.id.action_createPasswordFragment_to_home2)
            }
        }
    }

}