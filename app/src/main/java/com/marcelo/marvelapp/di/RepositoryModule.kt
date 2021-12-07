package com.marcelo.marvelapp.di

import com.marcelo.core.data.repository.interfaces.CharactersRemoteDataSource
import com.marcelo.core.data.repository.interfaces.CharactersRepository
import com.marcelo.marvelapp.data.network.remote.RetrofitCharactersDataSource
import com.marcelo.marvelapp.data.repository.CharactersRepositoryImpl
import com.marcelo.marvelapp.data.response.DataWrapperResponse
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@Module
@InstallIn(SingletonComponent::class)
interface RepositoryModule {

    @Binds
    fun bindCharacterRepository(repository: CharactersRepositoryImpl): CharactersRepository

    @Binds
    fun bindRemoteDataSource(
        dataSource: RetrofitCharactersDataSource
    ): CharactersRemoteDataSource<DataWrapperResponse>
}