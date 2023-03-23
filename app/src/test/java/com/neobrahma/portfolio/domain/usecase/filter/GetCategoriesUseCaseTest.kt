package com.neobrahma.portfolio.domain.usecase.filter

import com.neobrahma.portfolio.domain.repository.FilterRepository
import kotlinx.coroutines.runBlocking
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith
import org.mockito.Mock
import org.mockito.junit.MockitoJUnitRunner
import org.mockito.kotlin.verify

@RunWith(MockitoJUnitRunner::class)
class GetCategoriesUseCaseTest {

    @Mock
    private lateinit var repository: FilterRepository

    private lateinit var useCase: GetCategoriesUseCase

    @Before
    fun setup() {
        useCase = GetCategoriesUseCase(repository)
    }

    @Test
    fun whenInvokeUseCase_callGetItemsFilter() {
        runBlocking {
            useCase.invoke()
            verify(repository).getItemsFilter()
        }
    }

}