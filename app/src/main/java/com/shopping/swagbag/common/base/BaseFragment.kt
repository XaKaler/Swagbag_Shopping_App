package com.shopping.swagbag.common.base

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.viewbinding.ViewBinding
import com.shopping.swagbag.common.ProgressDialogFragment
import com.shopping.swagbag.service.RemoteDataSource

abstract class BaseFragment<VB : ViewBinding, VM : ViewModel, R : BaseRepository>(
    private val bindingInflater: (inflater: LayoutInflater) -> VB
) : Fragment() {

    private var binding: VB? = null
    val viewBinding: VB
        get() = binding as VB

    protected lateinit var viewModel: VM
    protected val remoteDataSource = RemoteDataSource()
    private lateinit var progressDialog: ProgressDialogFragment

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = bindingInflater.invoke(inflater)

        val factory = ViewModelFactory(getFragmentRepository())
        viewModel = ViewModelProvider(this, factory)[getViewModel()]

        if (binding == null) {
            throw IllegalArgumentException("Binding cannot be null")
        }

        return viewBinding.root
    }

    abstract fun getFragmentBinding(inflater: LayoutInflater, container: ViewGroup?): VB

    abstract fun getViewModel(): Class<VM>

    abstract fun getFragmentRepository(): R


    fun showLoading() {
        showLoading(getString(com.shopping.swagbag.R.string.please_wait), getString(com.shopping.swagbag.R.string.loading))
    }

    fun showLoading(title: String, message: String) {
        val manager = requireActivity().supportFragmentManager
        progressDialog = ProgressDialogFragment.newInstance(title, message)
        progressDialog.isCancelable = false
        progressDialog.show(manager, "progress")
    }

    fun stopShowingLoading() {
        if(this::progressDialog.isInitialized) {
            progressDialog.dismiss()
        }
    }

    fun toast(text: String) {
        Toast.makeText(context, text, Toast.LENGTH_LONG).show()
    }

}