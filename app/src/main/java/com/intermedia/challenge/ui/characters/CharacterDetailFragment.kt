package com.intermedia.challenge.ui.characters

import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.intermedia.challenge.databinding.FragmentCharacterDetailBinding
import com.intermedia.challenge.databinding.ViewApparitionItemBinding
import org.koin.android.viewmodel.ext.android.sharedViewModel

class CharacterDetailFragment : Fragment() {

    private lateinit var binding: FragmentCharacterDetailBinding
    private val viewModel: CharactersViewModel by sharedViewModel()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentCharacterDetailBinding.inflate(inflater, container, false).apply {
            lifecycleOwner = viewLifecycleOwner
        }

        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.character = viewModel.selected.value

        setUpAparitions()
    }

    private fun setUpAparitions() {
        viewModel.selected.value!!.comics.appearances.forEach {

            val inflater =
                context?.getSystemService(Context.LAYOUT_INFLATER_SERVICE) as LayoutInflater

            val itemBinding: ViewApparitionItemBinding = ViewApparitionItemBinding.inflate(inflater)
            itemBinding.appearance = it

            binding.layoutComics.addView(itemBinding.root)
        }

    }

}