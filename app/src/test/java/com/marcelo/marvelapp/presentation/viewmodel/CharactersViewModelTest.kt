package com.marcelo.marvelapp.presentation.viewmodel

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import androidx.paging.PagingData
import com.marcelo.core.domain.model.Character
import com.marcelo.core.usecase.interfaces.GetCharactersUseCase
import com.nhaarman.mockitokotlin2.any
import com.nhaarman.mockitokotlin2.whenever
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.count
import kotlinx.coroutines.flow.flowOf
import kotlinx.coroutines.runBlocking
import kotlinx.coroutines.test.TestCoroutineDispatcher
import kotlinx.coroutines.test.resetMain
import kotlinx.coroutines.test.runBlockingTest
import kotlinx.coroutines.test.setMain
import org.junit.Assert.assertEquals
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.rules.TestWatcher
import org.junit.runner.Description
import org.junit.runner.RunWith
import org.mockito.Mock
import org.mockito.junit.MockitoJUnitRunner
import java.lang.RuntimeException

@RunWith(MockitoJUnitRunner::class)
class CharactersViewModelTest {

    @get:Rule
    val instantExecutor = InstantTaskExecutorRule()

    @ExperimentalCoroutinesApi
    val testDispatcher: TestCoroutineDispatcher = TestCoroutineDispatcher()

    @Mock
    lateinit var getCharactersUseCase: GetCharactersUseCase

    private lateinit var charactersViewModel: CharactersViewModel

    private val pagingDataCharacters = PagingData.from(
        listOf(
            Character(
                "3-D Man 1", "https://i.annihiddl.us/u/prod/marvel/i/mg/c/e0/535fecbbb9784.jpg"
            ),
            Character(
                "A-Bomb (HAS) 1",
                "https://i.annsddddihil.us/u/prod/marvel/i/mg/3/20/5232158de5b16.jpg"
            )
        )
    )

    @ExperimentalCoroutinesApi
    @Before
    fun setup() {
        Dispatchers.setMain(testDispatcher)
        charactersViewModel = CharactersViewModel(getCharactersUseCase)
    }

    @ExperimentalCoroutinesApi
    @Test
    fun `should validate the paging data object values when calling charactersPagingData`() =
        runBlockingTest {

            whenever(
                getCharactersUseCase.invoke(any())
            ).thenReturn(
                flowOf(
                    pagingDataCharacters
                )
            )

            val result = charactersViewModel.charactersPagingData("")

            assertEquals(1, result.count())
        }

    @ExperimentalCoroutinesApi
    @Test
    fun `should throw an exception when the calling to the use case return an exception`() =
        runBlockingTest {
            whenever(getCharactersUseCase.invoke(any()))
                .thenThrow(RuntimeException())

            charactersViewModel.charactersPagingData("")
        }
}