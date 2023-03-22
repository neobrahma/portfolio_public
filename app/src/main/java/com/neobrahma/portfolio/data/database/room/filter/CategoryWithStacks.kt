package com.neobrahma.portfolio.data.database.room.filter

import androidx.room.Embedded
import androidx.room.Junction
import androidx.room.Relation
import com.neobrahma.portfolio.data.database.room.table.Category
import com.neobrahma.portfolio.data.database.room.table.Stack
import com.neobrahma.portfolio.data.database.room.table.Task
import com.neobrahma.portfolio.data.database.room.table.crossref.ProjectStackCrossRef

data class CategoryWithStacks(
    @Embedded val category: Category,
    @Relation(
        parentColumn = "categoryId",
        entityColumn = "categoryId"
    )
    val stacks: List<Stack>
)