package com.neobrahma.portfolio.data.repository

import com.neobrahma.portfolio.data.FilterDataSource
import kotlinx.coroutines.runBlocking
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith
import org.mockito.Mock
import org.mockito.junit.MockitoJUnitRunner
import org.mockito.kotlin.verify

@RunWith(MockitoJUnitRunner::class)
class FilterRepositoryImplTest {

    @Mock
    private lateinit var dataSource: FilterDataSource

    private lateinit var repository: FilterRepositoryImpl

    @Before
    fun setup() {
        repository = FilterRepositoryImpl(dataSource)
    }

    @Test
    fun whenSetItemsFilter_DSShouldCallGetItemsFilter() {
        runBlocking {
            repository.getItemsFilter()
            verify(dataSource).getItemsFilter()
        }
    }

    @Test
    fun whenUpdateStack_DSShouldCallUpdateStack() {
        runBlocking {
            val stackId = 0
            val isFavorite = true
            repository.updateStack(stackId, isFavorite)
            verify(dataSource).updateStack(stackId, isFavorite)
        }
    }

}