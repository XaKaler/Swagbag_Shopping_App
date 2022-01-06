package com.shopping.swagbag.cards.add_cards

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.fragment.findNavController
import com.shopping.swagbag.R
import com.shopping.swagbag.databinding.FragmentAddCardBinding
import com.shopping.swagbag.databinding.FragmentCreatePasswordBinding
import com.shopping.swagbag.databinding.ToolbarWithNoMenuBinding
import com.shopping.swagbag.databinding.ToolbarWithNoMenuWhiteBgBinding


class AddCardFragment : Fragment(R.layout.fragment_add_card) {

    private lateinit var viewBinding: FragmentAddCardBinding
    private lateinit var toolbarBinding: ToolbarWithNoMenuWhiteBgBinding

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        viewBinding = FragmentAddCardBinding.bind(view)
        toolbarBinding = viewBinding.include

        initViews()


    }

    private fun initViews() {
        with(viewBinding){
            btnSave.setOnClickListener{
                findNavController().navigate(R.id.action_addCardFragment_to_savedCardsWithCards)
            }
        }

        setToolbar()
    }

    private fun setToolbar() {
        with(toolbarBinding){
            // set title
            tvTitle.text = getString(R.string.add_card)

            // back button click
            imgBack.setOnClickListener{
                findNavController().popBackStack()
            }
        }
    }
}