package com.neobrahma.portfolio.data

import com.neobrahma.portfolio.domain.model.CategoryData
import kotlinx.coroutines.flow.Flow

interface FilterDataSource {

    fun getItemsFilter(): Flow<List<CategoryData>>

    suspend fun updateStack(stackId: Int, isFavorite: Boolean)

}