package com.shopping.swagbag.privacypolicy

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.fragment.findNavController
import com.shopping.swagbag.R
import com.shopping.swagbag.SettingApi
import com.shopping.swagbag.SettingRepository
import com.shopping.swagbag.common.base.BaseFragment
import com.shopping.swagbag.databinding.FragmentPrivacyPolicyBinding
import com.shopping.swagbag.databinding.ToolbarWithNoMenuWhiteBgBinding
import com.shopping.swagbag.service.Resource
import com.shopping.swagbag.utils.SettingViewModel
import kotlinx.android.synthetic.main.fragment_privacy_policy.*

class PrivacyPolicyFragment :
    BaseFragment<FragmentPrivacyPolicyBinding, SettingViewModel, SettingRepository>(
        FragmentPrivacyPolicyBinding::inflate
    ) {

    private lateinit var toolbarBinding: ToolbarWithNoMenuWhiteBgBinding

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        toolbarBinding = viewBinding.include

        initViews()


    }

    private fun initViews() {
        setToolbar()

        getPrivacyPolicy()
    }

    private fun getPrivacyPolicy() {
        viewModel.settings().observe(viewLifecycleOwner){
            when(it){
                is Resource.Loading -> showLoading()

                is Resource.Success -> {
                    stopShowingLoading()

                    viewBinding.privacyPolicy.text = html2Text(it.value.result[6].value)
                }

                is Resource.Failure -> Log.e("TAG", "getPrivacyPolicy: $it", )
            }
        }
    }

    private fun setToolbar() {
        with(toolbarBinding) {
            // set title
            tvTitle.text = getString(R.string.privacy_policy)

            // back button click
            imgBack.setOnClickListener {
                findNavController().popBackStack()
            }
        }
    }

    override fun getFragmentBinding(
        inflater: LayoutInflater,
        container: ViewGroup?
    ) = FragmentPrivacyPolicyBinding.inflate(inflater, container, false)

    override fun getViewModel() = SettingViewModel::class.java

    override fun getFragmentRepository() =
        SettingRepository(remoteDataSource.getBaseUrl().create(SettingApi::class.java))
}