package com.neobrahma.portfolio.data.database

import com.neobrahma.portfolio.data.FilterDataSource
import com.neobrahma.portfolio.data.database.room.filter.FilterDao
import com.neobrahma.portfolio.domain.model.CategoryData
import com.neobrahma.portfolio.domain.model.StackData
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import javax.inject.Inject

class FilterLocalDataSourceImpl @Inject constructor(
    private val room: FilterDao
) : FilterDataSource {
    override fun getItemsFilter(): Flow<List<CategoryData>> {
        return room.getAllCategoryWithStacks().map {
            it.map {
                val stacks = it.stacks.map { stack ->
                    StackData(
                        stackId = stack.stackId,
                        label = stack.label,
                        iconId = stack.iconId,
                        categoryId = stack.categoryId,
                        isFavorite = stack.isFiltered
                    )
                }
                val category = it.category
                CategoryData(
                    categoryId = category.categoryId,
                    label = category.label,
                    stacks = stacks
                )
            }
        }
    }

    override suspend fun updateStack(stackId: Int, isFavorite: Boolean) {
        room.updateStackState(stackId, isFavorite)
    }
}