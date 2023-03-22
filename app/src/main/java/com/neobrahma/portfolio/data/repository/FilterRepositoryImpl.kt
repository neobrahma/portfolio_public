package com.neobrahma.portfolio.data.repository

import com.neobrahma.portfolio.data.FilterDataSource
import com.neobrahma.portfolio.domain.model.CategoryData
import com.neobrahma.portfolio.domain.repository.FilterRepository
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class FilterRepositoryImpl @Inject constructor(
    private val filterDataSource: FilterDataSource,
) : FilterRepository {

    override fun getItemsFilter(): Flow<List<CategoryData>> {
        return filterDataSource.getItemsFilter()
    }

    override suspend fun updateStack(stackId: Int, isFavorite: Boolean) {
        filterDataSource.updateStack(stackId, isFavorite)
    }
}