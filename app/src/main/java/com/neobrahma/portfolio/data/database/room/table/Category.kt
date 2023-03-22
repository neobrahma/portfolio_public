package com.neobrahma.portfolio.data.database.room.table

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class Category(
    @PrimaryKey val categoryId: Int,
    val label: String
)