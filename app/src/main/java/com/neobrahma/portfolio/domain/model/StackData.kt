package com.neobrahma.portfolio.domain.model

data class StackData(
    val stackId: Int,
    val label: String,
    val iconId: String,
    val categoryId: Int,
    val isFavorite: Boolean = false
)
