package com.shopping.swagbag.cards.savedcards.with_cards

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.shopping.swagbag.R
import com.shopping.swagbag.databinding.FragmentAddCardBinding
import com.shopping.swagbag.databinding.FragmentSavedCardsWithCardsBinding
import com.shopping.swagbag.databinding.ToolbarWithNoMenuWhiteBgBinding
import com.shopping.swagbag.dummy.DummyData


class SavedCardsWithCards : Fragment(R.layout.fragment_saved_cards_with_cards) {

    private lateinit var viewBinding: FragmentSavedCardsWithCardsBinding
    private lateinit var toolbarBinding: ToolbarWithNoMenuWhiteBgBinding

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        viewBinding = FragmentSavedCardsWithCardsBinding.bind(view)
        toolbarBinding = viewBinding.include

        initViews()


    }

    private fun initViews() {
        with(viewBinding){
            addNewCard.setOnClickListener{
                findNavController().navigate(R.id.action_savedCardsWithCards_to_addCardFragment)
            }
        }

        setToolbar()

        setCards()
    }

    private fun setCards() {
        with(viewBinding){
            rvCards.apply{
                layoutManager = LinearLayoutManager(context)
                adapter = DummyData().getDummyData()?.let { CardAdapter(context, it) }
            }
        }
    }

    private fun setToolbar() {
        with(toolbarBinding){
            // set title
            tvTitle.text = getString(R.string.saved_cards)

            // back button click
            imgBack.setOnClickListener{
                findNavController().popBackStack()
            }
        }
    }
}