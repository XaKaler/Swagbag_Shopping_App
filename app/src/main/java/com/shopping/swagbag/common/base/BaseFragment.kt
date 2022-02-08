package com.shopping.swagbag.common.base

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModel
import androidx.viewbinding.ViewBinding
import com.shopping.swagbag.service.RemoteDataSource

abstract class BaseFragment<VB : ViewBinding, VM : ViewModel, R : BaseRepository>(
    private val bindingInflater: (inflater: LayoutInflater) -> VB
) : Fragment() {

    private var binding: VB? = null
    val viewBinding: VB
        get() = binding as VB


    protected lateinit var viewModel: VM
    protected val remoteDataSource = RemoteDataSource()


    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = bindingInflater.invoke(inflater)

        if (binding == null) {
            throw IllegalArgumentException("Binding cannot be null")
        }

        return viewBinding.root
    }

    abstract fun getFragmentBinding(inflater: LayoutInflater, container: ViewGroup?): VB

    abstract fun getViewModel(): Class<VM>

    abstract fun getFragmentRepository(): R


}