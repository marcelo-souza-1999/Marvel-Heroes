package com.marcelo.core.usecase.interfaces

import androidx.paging.PagingConfig
import androidx.paging.PagingData
import com.marcelo.core.domain.model.Character
import kotlinx.coroutines.flow.Flow

interface GetCharactersUseCase {
    operator fun invoke(params: GetCharactersParams): Flow<PagingData<Character>>

    data class GetCharactersParams(val query: String, val pagingConfig: PagingConfig)
}