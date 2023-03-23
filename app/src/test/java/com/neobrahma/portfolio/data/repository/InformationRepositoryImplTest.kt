package com.neobrahma.portfolio.data.repository

import com.neobrahma.portfolio.data.InformationDataSource
import com.neobrahma.portfolio.domain.repository.InformationRepository
import kotlinx.coroutines.runBlocking
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith
import org.mockito.Mock
import org.mockito.junit.MockitoJUnitRunner
import org.mockito.kotlin.verify

@RunWith(MockitoJUnitRunner::class)
class InformationRepositoryImplTest {

    @Mock
    private lateinit var dataSource: InformationDataSource

    private lateinit var repository: InformationRepository

    @Before
    fun setup() {
        repository = InformationRepositoryImpl(dataSource)
    }

    @Test
    fun whenGetStackStatistical_DSShouldCallGetStackStatistical() {
        runBlocking {
            repository.getStackStatistical()
            verify(dataSource).getStackStatistical()
        }
    }

}