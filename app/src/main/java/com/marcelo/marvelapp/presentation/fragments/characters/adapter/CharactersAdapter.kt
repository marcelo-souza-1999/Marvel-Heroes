package com.marcelo.marvelapp.presentation.fragments.characters.adapter

import android.view.ViewGroup
import androidx.paging.PagingDataAdapter
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import com.marcelo.core.domain.model.Character
import com.marcelo.marvelapp.presentation.fragments.characters.viewholder.CharactersViewHolder

class CharactersAdapter : PagingDataAdapter<Character, CharactersViewHolder>(diffCalback) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CharactersViewHolder {
        return CharactersViewHolder.create(parent)
    }

    override fun onBindViewHolder(holder: CharactersViewHolder, position: Int) {
        getItem(position)?.let {character ->
            holder.bind(character)
        }
    }

    companion object {
        private val diffCalback = object: DiffUtil.ItemCallback<Character>() {

            override fun areItemsTheSame(
                oldItem: Character,
                newItem: Character
            ): Boolean {
                return oldItem.name == newItem.name
            }

            override fun areContentsTheSame(
                oldItem: Character,
                newItem: Character
            ): Boolean {
                return oldItem == newItem
            }
        }
    }
}