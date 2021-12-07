package com.marcelo.core.data.repository.interfaces

import androidx.paging.PagingSource
import com.marcelo.core.domain.model.Character

interface CharactersRepository {

    fun getCharacters(query: String): PagingSource<Int, Character>
}