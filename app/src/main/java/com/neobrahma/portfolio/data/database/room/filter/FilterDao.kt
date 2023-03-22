package com.neobrahma.portfolio.data.database.room.filter

import androidx.room.*
import com.neobrahma.portfolio.data.database.room.table.*
import com.neobrahma.portfolio.data.database.room.table.crossref.ProjectStackCrossRef
import kotlinx.coroutines.flow.Flow

@Dao
interface FilterDao {

    @Transaction
    @Query("SELECT * FROM category")
    fun getAllCategoryWithStacks(): Flow<List<CategoryWithStacks>>

    @Query("UPDATE stack SET isFiltered = :isFavorite WHERE stackId = :stackId")
    suspend fun updateStackState(stackId : Int, isFavorite : Boolean)
}