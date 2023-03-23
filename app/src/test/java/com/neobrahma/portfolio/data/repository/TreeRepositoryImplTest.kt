package com.neobrahma.portfolio.data.repository

import com.neobrahma.portfolio.data.TreeDataSource
import com.neobrahma.portfolio.domain.repository.TreeRepository
import kotlinx.coroutines.runBlocking
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith
import org.mockito.Mock
import org.mockito.junit.MockitoJUnitRunner
import org.mockito.kotlin.verify

@RunWith(MockitoJUnitRunner::class)
class TreeRepositoryImplTest {

    @Mock
    private lateinit var dataSource: TreeDataSource

    private lateinit var repository: TreeRepository

    @Before
    fun setup() {
        repository = TreeRepositoryImpl(dataSource)
    }

    @Test
    fun whenGetCompanies_DSShouldCallGetCompanies() {
        runBlocking {
            repository.getCompanies()
            verify(dataSource).getCompanies()
        }
    }
}