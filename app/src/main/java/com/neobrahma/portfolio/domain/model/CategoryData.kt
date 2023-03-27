package com.neobrahma.portfolio.domain.model

data class CategoryData(
    val categoryId: Int,
    val label: String,
    val stacks: List<StackData>
)
