package com.marcelo.core.usecase

import androidx.paging.PagingConfig
import androidx.paging.PagingSource
import com.android.marcelo.testings.MainCoroutinesRule
import com.android.marcelo.testings.model.CharacterFactory
import com.android.marcelo.testings.pagingsource.PagingSourceFactory
import com.marcelo.core.data.repository.interfaces.CharactersRepository
import com.marcelo.core.usecase.interfaces.GetCharactersUseCase
import com.nhaarman.mockitokotlin2.times
import com.nhaarman.mockitokotlin2.verify
import com.nhaarman.mockitokotlin2.whenever
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.count
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.test.runBlockingTest
import org.junit.Assert.assertEquals
import org.junit.Assert.assertNotNull
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith
import org.mockito.Mock
import org.mockito.junit.MockitoJUnitRunner

@ExperimentalCoroutinesApi
@RunWith(MockitoJUnitRunner::class)
class GetCharactersUseCaseImplTest {

    @ExperimentalCoroutinesApi
    @get:Rule
    var mainCoroutineRole = MainCoroutinesRule()

    @Mock
    lateinit var repository: CharactersRepository

    private lateinit var getCharactersUseCase: GetCharactersUseCase

    private val hero = CharacterFactory().create(CharacterFactory.Hero.ABomb)

    private val fakePagingSource = PagingSourceFactory().create(listOf(hero))

    @Before
    fun setup() {
        getCharactersUseCase = GetCharactersUseCaseImpl(repository)
    }

    @Test
    fun `should validate flow paging data creation when invoke from use case is called`() =
        runBlockingTest {
            whenever(repository.getCharacters(""))
                .thenReturn(fakePagingSource)

            val result = getCharactersUseCase
                .invoke(GetCharactersUseCase.GetCharactersParams("", PagingConfig(20)))

            verify(repository, times(1)).getCharacters("")

            assertNotNull(result.first())
        }
}