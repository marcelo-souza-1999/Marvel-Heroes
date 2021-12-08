package com.marcelo.marvelapp.presentation.fragments.characters.viewholder

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.core.view.isVisible
import androidx.paging.LoadState
import androidx.recyclerview.widget.RecyclerView
import com.marcelo.marvelapp.databinding.ItemCharacterLoadMoreStatesBinding

class CharactersLoadMoreStateViewHolder(
    itemBinding: ItemCharacterLoadMoreStatesBinding,
    retry: () -> Unit
): RecyclerView.ViewHolder(itemBinding.root) {

    private val binding = ItemCharacterLoadMoreStatesBinding.bind(itemView)
    private val progressBarLoadMore = binding.progressLoadingMore
    private val textErrorLoadMore = binding.btnTryAgain.also {
        it.setOnClickListener{
            retry()
        }
    }

    fun bind(loadState: LoadState) {
        progressBarLoadMore.isVisible = loadState is LoadState.Loading
        textErrorLoadMore.isVisible = loadState is LoadState.Error
    }

    companion object {
        fun create(parent: ViewGroup, retry: () -> Unit): CharactersLoadMoreStateViewHolder {
            val itemBinding = ItemCharacterLoadMoreStatesBinding
                .inflate(LayoutInflater.from(parent.context), parent, false)

            return CharactersLoadMoreStateViewHolder(itemBinding, retry)
        }
    }
}