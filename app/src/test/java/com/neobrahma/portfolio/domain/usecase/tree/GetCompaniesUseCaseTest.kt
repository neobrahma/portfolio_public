package com.neobrahma.portfolio.domain.usecase.tree

import com.neobrahma.portfolio.domain.repository.TreeRepository
import kotlinx.coroutines.runBlocking
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith
import org.mockito.Mock
import org.mockito.junit.MockitoJUnitRunner
import org.mockito.kotlin.verify

@RunWith(MockitoJUnitRunner::class)
class GetCompaniesUseCaseTest {

    @Mock
    private lateinit var repository: TreeRepository

    private lateinit var useCase: GetCompaniesUseCase

    @Before
    fun setup() {
        useCase = GetCompaniesUseCase(repository)
    }

    @Test
    fun whenInvokeUseCase_callGetCompanies() {
        runBlocking {
            useCase.invoke()
            verify(repository).getCompanies()
        }
    }

}