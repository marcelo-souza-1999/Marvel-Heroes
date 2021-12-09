package com.marcelo.core.usecase

import androidx.paging.Pager
import androidx.paging.PagingData
import com.marcelo.core.data.repository.interfaces.CharactersRepository
import com.marcelo.core.domain.model.Character
import com.marcelo.core.usecase.base.PagingUseCase
import com.marcelo.core.usecase.interfaces.GetCharactersUseCase
import com.marcelo.core.usecase.interfaces.GetCharactersUseCase.GetCharactersParams
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class GetCharactersUseCaseImpl @Inject constructor(
    private val charactersRepository: CharactersRepository
) : PagingUseCase<GetCharactersParams, Character>(),
    GetCharactersUseCase{

    override fun createFlowObservable(params: GetCharactersParams): Flow<PagingData<Character>> {
        return Pager(config = params.pagingConfig) {
            charactersRepository.getCharacters(params.query)
        }.flow
    }
}