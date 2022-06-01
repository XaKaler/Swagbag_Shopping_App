package com.shopping.swagbag.account

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.View
import androidx.navigation.findNavController
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.shopping.swagbag.BuildConfig
import com.shopping.swagbag.R
import com.shopping.swagbag.common.RecycleViewItemClick
import com.shopping.swagbag.databinding.FragmentAccountBinding
import com.shopping.swagbag.databinding.ToolbarWithNoMenuBinding


class AccountFragment : Fragment(R.layout.fragment_account) {

    private lateinit var viewBinding: FragmentAccountBinding
    private lateinit var toolbarBinding: ToolbarWithNoMenuBinding

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        viewBinding = FragmentAccountBinding.bind(view)
        toolbarBinding = viewBinding.includeToolbar

        setToolbar()

        //set version name
        val versionName = BuildConfig.VERSION_NAME
        viewBinding.versionName.text = "Version $versionName"

        setAccountMenuItems()
    }

    private fun setToolbar() {
        with(toolbarBinding){
            tvTitle.text = "Account"
            imgBack.setOnClickListener{
                findNavController().popBackStack()
            }
        }
    }

    private fun setAccountMenuItems() {
        val menuList = accountMenuList

        with(viewBinding){
            rvMyAccountTabs.apply{
                layoutManager  = LinearLayoutManager(context)
                adapter = AccountMenuAdapter(context, menuList, object: RecycleViewItemClick{
                    override fun onItemClickWithName(name: String, position: Int) {
                        when(name){
                            "My Account" -> {findNavController().navigate(R.id.action_accountFragment_to_myAccountFragment)}
                            "Orders" -> findNavController().navigate(R.id.action_global_orderWithItemsFragment)
                            "Swagbag Wallet" -> {findNavController().navigate(R.id.action_accountFragment_to_walletFragment)}
                            "My Profile" -> findNavController().navigate(R.id.action_global_profileFragment)
                            "My Address Book" -> findNavController().navigate(R.id.action_global_viewUserDetailsFragment)
                        }
                    }
                })
            }
        }
    }

}