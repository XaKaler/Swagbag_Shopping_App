package com.shopping.swagbag.aboutus

import android.content.Context
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.fragment.findNavController
import com.shopping.swagbag.main_activity.MainActivity
import com.shopping.swagbag.R
import com.shopping.swagbag.service.apis.SettingApi
import com.shopping.swagbag.settings.SettingRepository
import com.shopping.swagbag.common.base.BaseFragment
import com.shopping.swagbag.databinding.FragmentAboutUsBinding
import com.shopping.swagbag.databinding.ToolbarWithNoMenuWhiteBgBinding
import com.shopping.swagbag.utils.SettingViewModel

class AboutUs :
    BaseFragment<FragmentAboutUsBinding, SettingViewModel, SettingRepository>(FragmentAboutUsBinding::inflate) {

    private lateinit var mainActivity: MainActivity
    private lateinit var toolbarBinding: ToolbarWithNoMenuWhiteBgBinding

    override fun onAttach(context: Context) {
        super.onAttach(context)
        mainActivity = context as MainActivity

        if (mainActivity !is MainActivity) {
            Log.e("TAG", "onAttach: is instance of main actvity")
        } else {
            Log.e("TAG", "onAttach:not is instance of main actvity")
        }
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        toolbarBinding = viewBinding.include

        initViews()


    }

    private fun initViews() {
        setToolbar()

        getAboutUs()
    }

    private fun getAboutUs() {/*
        viewModel.settings().observe(viewLifecycleOwner){
            when(it){
                is Resource.Loading -> showLoading()

                is Resource.Success -> {
                    stopShowingLoading()

                    for (singleResult in it.value.result) {
                        if (singleResult.name == "About")
                            viewBinding.aboutUs.text = html2Text(singleResult.value)
                    }
                }

                is Resource.Failure -> Log.e("TAG", "getAboutUs: $it", )
            }
        }*/

        val aboutUs = mainActivity.getSettingResult("About")
        viewBinding.aboutUs.text = html2Text(aboutUs)
    }

    private fun setToolbar() {
        with(toolbarBinding) {
            // set title
            tvTitle.text = getString(R.string.about_us)

            // back button click
            imgBack.setOnClickListener {
                findNavController().popBackStack()
            }
        }
    }

    override fun getFragmentBinding(
        inflater: LayoutInflater,
        container: ViewGroup?
    ) = FragmentAboutUsBinding.inflate(inflater, container, false)

    override fun getViewModel() = SettingViewModel::class.java

    override fun getFragmentRepository() =
        SettingRepository(remoteDataSource.getBaseUrl().create(SettingApi::class.java))
}