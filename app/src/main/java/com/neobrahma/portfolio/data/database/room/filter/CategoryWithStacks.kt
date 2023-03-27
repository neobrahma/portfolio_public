package com.neobrahma.portfolio.data.database.room.filter

import androidx.room.Embedded
import androidx.room.Relation
import com.neobrahma.portfolio.data.database.room.table.Category
import com.neobrahma.portfolio.data.database.room.table.Stack

data class CategoryWithStacks(
    @Embedded val category: Category,
    @Relation(
        parentColumn = "categoryId",
        entityColumn = "categoryId"
    )
    val stacks: List<Stack>
)