package com.neobrahma.portfolio.domain.usecase.information

import com.neobrahma.portfolio.domain.repository.InformationRepository
import kotlinx.coroutines.runBlocking
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith
import org.mockito.Mock
import org.mockito.junit.MockitoJUnitRunner
import org.mockito.kotlin.verify

@RunWith(MockitoJUnitRunner::class)
class GetStackStatisticalUseCaseTest {
    @Mock
    private lateinit var repository: InformationRepository

    private lateinit var useCase: GetStackStatisticalUseCase

    @Before
    fun setup() {
        useCase = GetStackStatisticalUseCase(repository)
    }

    @Test
    fun whenInvokeUseCase_callGetStackStatistical() {
        runBlocking {
            useCase.invoke()
            verify(repository).getStackStatistical()
        }
    }
}