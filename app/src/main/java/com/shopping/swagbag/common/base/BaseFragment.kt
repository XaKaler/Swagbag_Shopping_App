package com.shopping.swagbag.common.base

import android.app.Activity
import android.content.Context
import android.content.res.Resources
import android.os.Build
import android.os.Bundle
import android.view.Gravity
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.inputmethod.InputMethodManager
import android.widget.ArrayAdapter
import android.widget.Toast
import androidx.annotation.RequiresApi
import androidx.appcompat.widget.ListPopupWindow
import androidx.core.text.HtmlCompat
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.viewbinding.ViewBinding
import com.shopping.swagbag.R
import com.shopping.swagbag.common.ProgressDialogFragment
import com.shopping.swagbag.service.RemoteDataSource

abstract class BaseFragment<VB : ViewBinding, VM : ViewModel, BR : BaseRepository>(
    private val bindingInflater: (inflater: LayoutInflater) -> VB
) : Fragment() {

    private var binding: VB? = null
    val viewBinding: VB
        get() = binding as VB

    protected lateinit var viewModel: VM
    protected val remoteDataSource = RemoteDataSource()
    private lateinit var progressDialog: ProgressDialogFragment

    val screenWidth = Resources.getSystem().displayMetrics.widthPixels
    val screenHeight = Resources.getSystem().displayMetrics.heightPixels

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

    abstract fun getFragmentRepository(): BR

    fun showLoading() {
        val manager = requireActivity().supportFragmentManager
        progressDialog = ProgressDialogFragment.newInstance()
        progressDialog.isCancelable = false
        progressDialog.show(manager, "progress")
    }

    fun stopShowingLoading() {
        if (this::progressDialog.isInitialized) {
            progressDialog.dismiss()
        }
    }

    fun toast(text: String) {
        Toast.makeText(context, text, Toast.LENGTH_LONG).show()
    }

    fun tryAgain(){
        toast("try again")
    }

    fun html2Text(desc: String) =
        android.text.Html.fromHtml(desc, HtmlCompat.FROM_HTML_MODE_LEGACY).toString()

    fun <T> openListDialog(
        ancView: View?, vList: Array<T>, isWrapContent: Boolean, onSelected: (value: T) -> Unit
    ) {
        hideKeyboard()
        context?.let {
            ListPopupWindow(it).apply {
                setDropDownGravity(Gravity.CENTER_HORIZONTAL)
                anchorView = ancView
                width = if (isWrapContent) ListPopupWindow.WRAP_CONTENT
                else screenWidth - R.dimen.dimen_64.asDimen(it)
                height = ListPopupWindow.WRAP_CONTENT
                isModal = true
                setAdapter(
                    ArrayAdapter(
                        it, R.layout.dropdown, R.id.tvDropDown, vList
                    )
                )
                setOnItemClickListener { _, _, position, _ ->
                    onSelected(vList[position])
                    dismiss()
                }
                show()
            }
        }
    }

    private fun Fragment.hideKeyboard() {
        view?.let { activity?.hideKeyboard(it) }
    }

    private fun Context.hideKeyboard(view: View) {
        val inputMethodManager = getSystemService(Activity.INPUT_METHOD_SERVICE) as InputMethodManager
        inputMethodManager.hideSoftInputFromWindow(view.windowToken, 0)
    }

    private fun Int.asDimen(context: Context) = context.resources.getDimensionPixelSize(this)

}