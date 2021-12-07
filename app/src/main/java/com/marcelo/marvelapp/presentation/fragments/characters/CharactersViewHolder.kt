package com.marcelo.marvelapp.presentation.fragments.characters

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.marcelo.core.domain.model.Character
import com.marcelo.marvelapp.R
import com.marcelo.marvelapp.databinding.ItemCharacterBinding

class CharactersViewHolder(
    itemCharacterBinding: ItemCharacterBinding
): RecyclerView.ViewHolder(itemCharacterBinding.root) {

    private val txtName = itemCharacterBinding.txtNameCharacter
    private val imgCharacter = itemCharacterBinding.imgCharacter

    fun bind(character: Character) {
        txtName.text = character.name
        Glide.with(itemView)
            .load(character.imageUrl)
            .fallback(R.drawable.ic_img_loading_error)
            .into(imgCharacter)
    }

    companion object {
        fun create (parent: ViewGroup): CharactersViewHolder {
            val inflater = LayoutInflater.from(parent.context)
            val itemBinding = ItemCharacterBinding.inflate(inflater, parent, false)
            return CharactersViewHolder(itemBinding)
        }
    }
}