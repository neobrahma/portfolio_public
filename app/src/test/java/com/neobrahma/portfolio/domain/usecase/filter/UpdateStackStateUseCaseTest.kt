package com.neobrahma.portfolio.domain.usecase.filter

import com.neobrahma.portfolio.domain.repository.FilterRepository
import kotlinx.coroutines.runBlocking
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith
import org.mockito.Mock
import org.mockito.junit.MockitoJUnitRunner
import org.mockito.kotlin.verify

@RunWith(MockitoJUnitRunner::class)
class UpdateStackStateUseCaseTest {

    @Mock
    private lateinit var repository: FilterRepository

    private lateinit var useCase: UpdateStackStateUseCase

    @Before
    fun setup() {
        useCase = UpdateStackStateUseCase(repository)
    }

    @Test
    fun whenInvokeUseCase_callUpdateStack() {
        val stackId = 0
        val isFavorite = false

        runBlocking {
            useCase.invoke(stackId, isFavorite)
            verify(repository).updateStack(stackId, isFavorite)
        }
    }

}