package com.neobrahma.portfolio.data.database.room.table

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class Stack(
    @PrimaryKey val stackId: Int,
    val label: String,
    val iconId: String,
    val categoryId: Int,
    val isFiltered: Boolean
)