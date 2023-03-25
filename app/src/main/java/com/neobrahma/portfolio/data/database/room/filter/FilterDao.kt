package com.neobrahma.portfolio.data.database.room.filter

import androidx.room.Dao
import androidx.room.Query
import androidx.room.Transaction
import kotlinx.coroutines.flow.Flow

@Dao
interface FilterDao {

    @Transaction
    @Query("SELECT * FROM category")
    fun getAllCategoryWithStacks(): Flow<List<CategoryWithStacks>>

    @Query("UPDATE stack SET isFiltered = :isFavorite WHERE stackId = :stackId")
    suspend fun updateStackState(stackId: Int, isFavorite: Boolean)
}