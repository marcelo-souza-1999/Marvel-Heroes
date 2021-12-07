package com.marcelo.marvelapp.data.network.remote

import com.marcelo.core.data.repository.interfaces.CharactersRemoteDataSource
import com.marcelo.marvelapp.data.network.interfaces.MarvelAPI
import com.marcelo.marvelapp.data.response.DataWrapperResponse
import javax.inject.Inject

class RetrofitCharactersDataSource @Inject constructor(
    private val marvelApi: MarvelAPI
) : CharactersRemoteDataSource<DataWrapperResponse> {

    override suspend fun fetchCharacters(queries: Map<String, String>): DataWrapperResponse {
        return marvelApi.getCharacters(queries)
    }
}