package com.marcelo.marvelapp.data.repository

import androidx.paging.PagingSource
import com.marcelo.core.data.repository.interfaces.CharactersRemoteDataSource
import com.marcelo.core.data.repository.interfaces.CharactersRepository
import com.marcelo.core.domain.model.Character
import com.marcelo.marvelapp.data.response.DataWrapperResponse
import javax.inject.Inject

class CharactersRepositoryImpl @Inject constructor(
    private val remoteDataSource: CharactersRemoteDataSource<DataWrapperResponse>
): CharactersRepository{

    override fun getCharacters(query: String): PagingSource<Int, Character> {
        return CharactersPaging()
    }
}