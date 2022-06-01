package com.shopping.swagbag.user.wallet

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.fragment.findNavController
import com.shopping.swagbag.common.base.BaseFragment
import com.shopping.swagbag.databinding.FragmentWalletBinding
import com.shopping.swagbag.databinding.ToolbarWithNoMenuBinding
import com.shopping.swagbag.service.apis.UserApi
import com.shopping.swagbag.user.auth.UserRepository
import com.shopping.swagbag.user.auth.UserViewModel

class WalletFragment :
    BaseFragment<FragmentWalletBinding, UserViewModel, UserRepository>(FragmentWalletBinding::inflate) {

    private lateinit var toolbarBinding: ToolbarWithNoMenuBinding

    override fun getFragmentBinding(
        inflater: LayoutInflater,
        container: ViewGroup?
    ) = FragmentWalletBinding.inflate(inflater, container, false)

    override fun getViewModel() = UserViewModel::class.java

    override fun getFragmentRepository() = UserRepository(remoteDataSource.getBaseUrl().create(UserApi::class.java))

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        toolbarBinding  = viewBinding.includeToolbar

        setToolbar()
    }

    private fun setToolbar() {
        with(toolbarBinding){
            tvTitle.text = "Wallet"
            imgBack.setOnClickListener { findNavController().popBackStack() }
        }
    }

}