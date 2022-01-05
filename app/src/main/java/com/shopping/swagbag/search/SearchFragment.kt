package com.shopping.swagbag.search

import android.content.pm.PackageManager
import android.os.Bundle
import android.view.View
import androidx.core.app.ActivityCompat
import androidx.core.content.ContextCompat
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import com.shopping.swagbag.R
import com.shopping.swagbag.common.RecycleItemClickListener
import com.shopping.swagbag.common.adapter.AllTimeSliderAdapter
import com.shopping.swagbag.common.adapter.BestProductAdapter
import com.shopping.swagbag.databinding.*
import com.shopping.swagbag.dummy.DummyData

class SearchFragment : Fragment(R.layout.fragment_search), View.OnClickListener, RecycleItemClickListener {

    private lateinit var viewBinding: FragmentSearchBinding
    private lateinit var toolbarBinding: ToolbarWithNoMenuWhiteBgBinding
    private lateinit var searchBarBinding: SearchBarBinding

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        viewBinding = FragmentSearchBinding.bind(view)
        toolbarBinding = viewBinding.include
        searchBarBinding = viewBinding.searchBar

        initViews()
    }

    private fun initViews() {

        // click listeners
        with(searchBarBinding) {
            searchByImage.setOnClickListener(this@SearchFragment)
            searchByVoice.setOnClickListener(this@SearchFragment)
        }


        setToolbar()

        setNewArrivals()

        setPopularOnSwagbag()
    }

    private fun setPopularOnSwagbag() {
        with(viewBinding) {
            rvPopularOnSwagbag.apply {
                layoutManager = LinearLayoutManager(context, LinearLayoutManager.HORIZONTAL, false)
                adapter = DummyData().getDummyData()?.let { AllTimeSliderAdapter(context, it, this@SearchFragment) }
            }
        }
    }

    private fun setNewArrivals() {
        with(viewBinding) {
            rvNewArrivals.apply {
                layoutManager = GridLayoutManager(context, 2)
                adapter = DummyData().getTwoDummyData()?.let { BestProductAdapter(context, it, this@SearchFragment) }
            }
        }
    }

    private fun setToolbar() {
        with(toolbarBinding) {
            // set title
            tvTitle.text = getString(R.string.search)

            // back button click
            imgBack.setOnClickListener {
                findNavController().popBackStack()
            }
        }
    }

    override fun onClick(v: View?) {
        when (v?.id) {
            R.id.searchByImage -> findNavController().navigate(R.id.action_searchFragment_to_dialogSearchUsing)

            R.id.searchByVoice -> checkMicPermission()
        }
    }

    private fun checkMicPermission() {
        if (context?.let {
                ContextCompat.checkSelfPermission(
                    it,
                    android.Manifest.permission.RECORD_AUDIO
                )
            } == PackageManager.PERMISSION_DENIED) {
            activity?.let { ActivityCompat.requestPermissions(it, arrayOf(android.Manifest.permission.RECORD_AUDIO), 0) }
        }
        else{
            findNavController().navigate(R.id.action_searchFragment_to_search_using_microphone)
        }
    }

    override fun onSingleItemClickListener(position: Int) {
        findNavController().navigate(R.id.action_searchFragment_to_productDetailsFragment)
    }


}