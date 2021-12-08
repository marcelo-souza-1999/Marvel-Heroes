package com.marcelo.marvelapp.presentation.fragments.characters.adapter

import android.view.ViewGroup
import androidx.paging.LoadState
import androidx.paging.LoadStateAdapter
import com.marcelo.marvelapp.presentation.fragments.characters.viewholder.CharactersLoadMoreStateViewHolder

class CharactersLoadStateAdapter(
    private val retry: () -> Unit
) : LoadStateAdapter<CharactersLoadMoreStateViewHolder>(){

    override fun onCreateViewHolder(
        parent: ViewGroup,
        loadState: LoadState
    ) = CharactersLoadMoreStateViewHolder.create(parent, retry)


    override fun onBindViewHolder(
        holder: CharactersLoadMoreStateViewHolder,
        loadState: LoadState
    ) = holder.bind(loadState)
}