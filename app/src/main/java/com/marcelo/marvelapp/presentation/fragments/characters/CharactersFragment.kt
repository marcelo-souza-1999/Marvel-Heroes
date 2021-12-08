package com.marcelo.marvelapp.presentation.fragments.characters

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import com.marcelo.core.domain.model.Character
import com.marcelo.marvelapp.R
import com.marcelo.marvelapp.databinding.FragmentCharactersBinding
import com.marcelo.marvelapp.presentation.fragments.characters.adapter.CharactersAdapter
import com.marcelo.marvelapp.presentation.viewmodel.CharactersViewModel
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch

@AndroidEntryPoint
class CharactersFragment : Fragment() {

    private var _binding: FragmentCharactersBinding? = null
    private val binding: FragmentCharactersBinding get() = _binding!!

    private val viewModel: CharactersViewModel by viewModels()

    private val charactersAdapter = CharactersAdapter()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ) = FragmentCharactersBinding.inflate(
        inflater,
        container,
        false
    ).apply {
        _binding = this
    }.root

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        initCharactersAdapter()
        setupViewModel()
    }

    private fun initCharactersAdapter() {
        with(binding.rvCharacters) {
            setHasFixedSize(true)
            adapter = charactersAdapter
        }
    }

    private fun setupViewModel() {
        lifecycleScope.launch{
            viewModel.charactersPagingData("").collect {pagingData ->
                charactersAdapter.submitData(pagingData)
            }
        }
    }
}