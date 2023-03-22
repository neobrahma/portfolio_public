package com.neobrahma.portfolio.domain.repository

import com.neobrahma.portfolio.domain.model.CategoryData
import kotlinx.coroutines.flow.Flow

interface FilterRepository {
    fun getItemsFilter(): Flow<List<CategoryData>>

    suspend fun updateStack(stackId: Int, isFavorite: Boolean)
}