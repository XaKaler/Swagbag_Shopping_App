package com.shopping.swagbag.account.myaccount

import android.annotation.SuppressLint
import android.app.AlertDialog
import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.AdapterView
import android.widget.ArrayAdapter
import android.widget.Spinner
import androidx.navigation.fragment.findNavController
import com.shopping.swagbag.R
import com.shopping.swagbag.common.base.BaseFragment
import com.shopping.swagbag.databinding.FragmentMyAccountBinding
import com.shopping.swagbag.databinding.ToolbarWithNoMenuBinding
import com.shopping.swagbag.main_activity.MainActivity
import com.shopping.swagbag.service.apis.UserApi
import com.shopping.swagbag.user.auth.UserRepository
import com.shopping.swagbag.user.auth.UserViewModel
import com.shopping.swagbag.utils.AppUtils


class MyAccountFragment :
    BaseFragment<FragmentMyAccountBinding, UserViewModel, UserRepository>(FragmentMyAccountBinding::inflate) {

    private lateinit var toolbarBinding: ToolbarWithNoMenuBinding
    private lateinit var mainActivity: MainActivity
    private var deactivateReason = ""
    private var deactivateReasonList = ArrayList<String>()

    override fun onAttach(context: Context) {
        super.onAttach(context)
        mainActivity = context as MainActivity
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        toolbarBinding = viewBinding.includeToolbar

        //make deactivate reason list
        deactivateReasonList.add("Select deactivate reason")
        for (singleReason in mainActivity.getDeactivateAccountReason())
            deactivateReasonList.add(singleReason.name)

        setToolbar()
        setData()
    }

    private fun setToolbar() {
        with(toolbarBinding) {
            tvTitle.text = "My Account"
            imgBack.setOnClickListener { findNavController().popBackStack() }
        }
    }

    @SuppressLint("SetTextI18n")
    private fun setData() {
        val appUtils = context?.let { AppUtils(it) }
        val user = appUtils?.getUser()?.result

        with(viewBinding) {
            user?.apply {
                tvYourDetails.text =
                    "First name: $fname\n" +
                            "Last name: $lname\n" +
                            "Email Address: $email\n" +
                            "Phone Number: $mobile"
            }

            btnEditProfile.setOnClickListener { findNavController().navigate(R.id.action_global_profileFragment) }
            btnEditProfileAddress.setOnClickListener { findNavController().navigate(R.id.action_global_viewUserDetailsFragment) }
            btnEditProfileCommunicationPreference.setOnClickListener {
                findNavController().navigate(
                    R.id.action_myAccountFragment_to_communicationPreferencesFragment
                )
            }
            btnDeactvateAccount.setOnClickListener { showReasonDialog() }
        }
    }

    private fun deactivateAccount() {
        val userId = context?.let { AppUtils(it).getUserId() }

        if(deactivateReason == "" || deactivateReason == "Select deactivate reason")
            toast("Select deactivate reason")
        else
            userId?.let { viewModel.deactivateAccount(it, deactivateReason) }
    }

    private fun showReasonDialog() {
        val alertDialogBuilder = AlertDialog.Builder(context)

        val layoutInflater: LayoutInflater = layoutInflater
        val view = layoutInflater.inflate(R.layout.dialog_with_spinner, null)
        alertDialogBuilder.setView(view)

        //set data in spinner
        val spinner = view.findViewById<Spinner>(R.id.deactivateReasonSpinner)
        val arrayAdapter =
            context?.let {
                ArrayAdapter(
                    it,
                    android.R.layout.simple_spinner_item,
                    deactivateReasonList
                )
            }
        arrayAdapter?.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
        spinner.adapter = arrayAdapter
        spinner.onItemSelectedListener = object : AdapterView.OnItemSelectedListener {
            override fun onItemSelected(
                parent: AdapterView<*>?,
                view: View?,
                position: Int,
                id: Long
            ) {
                deactivateReason = deactivateReasonList[position]
                //toast(deactivateReasonList[position])
            }

            override fun onNothingSelected(parent: AdapterView<*>?) {
                TODO("Not yet implemented")
            }
        }

        //set positive button
        alertDialogBuilder.setPositiveButton(
            "Ok"
        ) { dialog, _ ->
            run {
                toast(deactivateReason)
                dialog.dismiss()
                deactivateAccount()
            }
        }

        //set negative button
        alertDialogBuilder.setNegativeButton("Cancel") { dialog, _ ->
            run {
                dialog.dismiss()
            }
        }

        val alertDialog = alertDialogBuilder.create()
        alertDialog.show()
    }

    override fun getFragmentBinding(
        inflater: LayoutInflater,
        container: ViewGroup?
    ) = FragmentMyAccountBinding.inflate(inflater, container, false)

    override fun getViewModel() = UserViewModel::class.java

    override fun getFragmentRepository() =
        UserRepository(remoteDataSource.getBaseUrl().create(UserApi::class.java))
}