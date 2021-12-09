package com.marcelo.marvelapp.data.pagging

import androidx.paging.PagingSource
import com.android.marcelo.testings.MainCoroutinesRule
import com.android.marcelo.testings.model.CharacterFactory
import com.marcelo.core.data.repository.interfaces.CharactersRemoteDataSource
import com.marcelo.core.domain.model.Character
import com.marcelo.marvelapp.data.response.DataWrapperResponse
import com.marcelo.marvelapp.factoryresponse.DataWrapperResponseFactory
import com.nhaarman.mockitokotlin2.any
import com.nhaarman.mockitokotlin2.whenever
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.test.runBlockingTest
import org.junit.Assert.assertEquals
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith
import org.mockito.Mock
import org.mockito.junit.MockitoJUnitRunner
import java.lang.RuntimeException

@ExperimentalCoroutinesApi
@RunWith(MockitoJUnitRunner::class)
class CharactersPagingSourceTest {

    @ExperimentalCoroutinesApi
    @get:Rule
    var mainCoroutineRole = MainCoroutinesRule()

    @Mock
    lateinit var remoteDataSource: CharactersRemoteDataSource<DataWrapperResponse>

    private val dataWrapperResponseFactory = DataWrapperResponseFactory()

    private val characterFactory = CharacterFactory()

    private lateinit var charactersPagingSource: CharactersPagingSource

    @Before
    fun setup() {
        charactersPagingSource = CharactersPagingSource(remoteDataSource, "")
    }

    @Test
    fun `should return a success load when load is called`() = runBlockingTest {
        // Arrange
        whenever(remoteDataSource.fetchCharacters(any()))
            .thenReturn(dataWrapperResponseFactory.create())

        // Act
        val result = charactersPagingSource.load(
            PagingSource.LoadParams.Refresh(
                null,
                loadSize = 2,
                false
            )
        )

        // Assert
        val expected = listOf(
            characterFactory.create(CharacterFactory.Hero.ThreeDMan),
            characterFactory.create(CharacterFactory.Hero.ABomb)
        )
        assertEquals(
            PagingSource.LoadResult.Page(
                data = expected,
                prevKey = null,
                nextKey = 20
            ),
            result
        )
    }

    @Test
    fun `should return a error load result when load is called`() = runBlockingTest {
       // Arrange
        val exception = RuntimeException()
        whenever(remoteDataSource.fetchCharacters(any()))
            .thenThrow(exception)

        // Act
        val result = charactersPagingSource.load(
            PagingSource.LoadParams.Refresh(
                key = null,
                loadSize = 2,
                placeholdersEnabled = false
            )
        )

        // Assert
        assertEquals(PagingSource.LoadResult.Error<Int, Character>(exception),
        result)
    }
}