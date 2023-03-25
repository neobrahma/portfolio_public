package com.neobrahma.portfolio.data.database.room.filter

import androidx.room.Dao
import androidx.room.Query
import com.neobrahma.portfolio.data.database.room.table.*
import com.neobrahma.portfolio.data.database.room.table.crossref.ProjectStackCrossRef

@Dao
interface FilterTestDao {

    @Query("SELECT * FROM stack")
    suspend fun getStacks(): List<Stack>

}