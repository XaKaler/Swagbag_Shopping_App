package com.shopping.swagbag.user.address.address_list

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.Observer
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.google.gson.Gson
import com.shopping.swagbag.R
import com.shopping.swagbag.common.RecycleViewItemClick
import com.shopping.swagbag.common.base.BaseFragment
import com.shopping.swagbag.databinding.FragmentAllAddressBinding
import com.shopping.swagbag.databinding.ToolbarWithNoMenuWhiteBgBinding
import com.shopping.swagbag.service.Resource
import com.shopping.swagbag.service.apis.UserApi
import com.shopping.swagbag.user.auth.UserRepository
import com.shopping.swagbag.user.auth.UserViewModel
import com.shopping.swagbag.utils.AppUtils

class AllAddressFragment : BaseFragment<
        FragmentAllAddressBinding,
        UserViewModel,
        UserRepository>(FragmentAllAddressBinding::inflate), RecycleViewItemClick {

    private lateinit var toolbarBinding: ToolbarWithNoMenuWhiteBgBinding
    private lateinit var addresses: AllAddressModel
    private lateinit var addressAdapter: AddressAdapter

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        toolbarBinding = viewBinding.include

        initViews()


    }

    private fun initViews() {
        with(viewBinding){
            addNewAddress.setOnClickListener{
                val action = AllAddressFragmentDirections.actionViewUserDetailsFragmentToAddUserDetailsFragment("")
                findNavController().navigate(action)
            }

            btnSelect.setOnClickListener{
                findNavController().navigate(R.id.action_viewUserDetailsFragment_to_home2)
            }

            btnCancel.setOnClickListener{
                findNavController().navigate(R.id.action_viewUserDetailsFragment_to_home2)
            }

        }
        setToolbar()

        getAllAddresses()
    }

    private fun getAllAddresses() {
        val appUtils = context?.let { AppUtils(it) }

            val userId = appUtils!!.getUserId()

            viewModel.allAddress(userId).observe(viewLifecycleOwner, Observer {
                when(it){
                    is Resource.Loading -> showLoading()

                    is Resource.Success -> {

                        stopShowingLoading()

                        addresses = it.value
                        addresses.result?.let { it1 -> setAddresses(it1) }
                    }

                    is Resource.Failure -> Log.e("TAG", "getAllAddresses: $it", )
                }
            })
    }

    private fun setAddresses(allAddressesList: List<AllAddressModel.Result?>) {
        with(viewBinding){
            rvUserDetails.apply{
                layoutManager = LinearLayoutManager(context)
                addressAdapter = AddressAdapter(context, allAddressesList, object: RecycleViewItemClick{
                    override fun onItemClickWithName(name: String, position: Int) {
                        when(name){
                            "edit" -> {
                                val action =
                                    AllAddressFragmentDirections.actionViewUserDetailsFragmentToAddUserDetailsFragment(
                                        Gson().toJson(addresses.result!![position], AllAddressModel.Result::class.java)
                                    )
                                findNavController().navigate(action)
                            }
                            "delete" -> deleteAddress(position)
                            "default" -> setDefaultAddress(position)
                        }
                    }
                })

                adapter = addressAdapter
            }
        }
    }

    private fun setDefaultAddress(position: Int) {

    }

    private fun setToolbar() {
        with(toolbarBinding) {
            // set title
            tvTitle.text = getString(R.string.addresses)

            // back button click
            imgBack.setOnClickListener {
                findNavController().popBackStack()
            }
        }
    }

    override fun getFragmentBinding(
        inflater: LayoutInflater,
        container: ViewGroup?
    ) = FragmentAllAddressBinding.inflate(inflater, container, false)

    override fun getViewModel() = UserViewModel::class.java

    override fun getFragmentRepository() =
        UserRepository(remoteDataSource.getBaseUrl().create(UserApi::class.java))

    override fun onItemClickWithName(name: String, position: Int) {
        when(name){
            "edit" -> {
                val action =
                    AllAddressFragmentDirections.actionViewUserDetailsFragmentToAddUserDetailsFragment(
                        Gson().toJson(addresses.result!![position], AllAddressModel.Result::class.java)
                    )
                findNavController().navigate(action)
            }
            "delete" -> deleteAddress(position)
        }
    }

    private fun deleteAddress(position: Int) {
        val addressId = addresses.result?.get(position)?.id

        Log.e("addressId", "deleteAddress: $addressId", )

        if (addressId != null) {
            viewModel.deleteAddress(addressId).observe(viewLifecycleOwner, Observer {
                when(it){
                    is Resource.Loading -> showLoading()

                    is Resource.Success -> {
                        stopShowingLoading()

                        val addressList: MutableList<AllAddressModel.Result> = addresses.result as MutableList
                        addressList.removeAt(position)

                        // update address list
                        addressAdapter.updateAddress(addressList)
                    }

                    is Resource.Failure -> Log.e("address", "deleteAddress: $it", )
                }
            })
        }

    }
}