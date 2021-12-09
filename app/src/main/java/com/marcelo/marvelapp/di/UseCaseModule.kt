package com.marcelo.marvelapp.di

import com.marcelo.core.usecase.GetCharactersUseCaseImpl
import com.marcelo.core.usecase.interfaces.GetCharactersUseCase
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ViewModelComponent

@Module
@InstallIn(ViewModelComponent::class)
interface UseCaseModule {

    @Binds
    fun bindCharactersUseCase(useCase: GetCharactersUseCaseImpl): GetCharactersUseCase
}