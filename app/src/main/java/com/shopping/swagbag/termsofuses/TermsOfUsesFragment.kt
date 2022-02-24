package com.shopping.swagbag.termsofuses

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
import com.shopping.swagbag.databinding.FragmentTermsOfUsesBinding
import com.shopping.swagbag.databinding.ToolbarWithNoMenuWhiteBgBinding
import com.shopping.swagbag.service.Resource
import com.shopping.swagbag.utils.SettingViewModel


class TermsOfUsesFragment :
    BaseFragment<FragmentTermsOfUsesBinding, SettingViewModel, SettingRepository>(
        FragmentTermsOfUsesBinding::inflate
    ) {

    private lateinit var toolbarBinding: ToolbarWithNoMenuWhiteBgBinding

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        toolbarBinding = viewBinding.include

        initViews()


    }

    private fun initViews() {
        setToolbar()

        getTermsOfUse()
    }

    private fun getTermsOfUse() {
        viewModel.settings().observe(viewLifecycleOwner){
            when(it){
                is Resource.Loading -> showLoading()

                is Resource.Success -> {
                    stopShowingLoading()

                    viewBinding.termsOfUse.text = it.value.result[7].value
                }

                is Resource.Failure -> Log.e("TAG", "getTermsOfUse: $it", )
            }
        }
    }

    private fun setToolbar() {
        with(toolbarBinding) {
            // set title
            tvTitle.text = getString(R.string.terms_of_uses)

            // back button click
            imgBack.setOnClickListener {
                findNavController().popBackStack()
            }
        }
    }

    override fun getFragmentBinding(
        inflater: LayoutInflater,
        container: ViewGroup?
    ) = FragmentTermsOfUsesBinding.inflate(inflater, container, false)

    override fun getViewModel() = SettingViewModel::class.java

    override fun getFragmentRepository() =
        SettingRepository(remoteDataSource.getBaseUrl().create(SettingApi::class.java))
}