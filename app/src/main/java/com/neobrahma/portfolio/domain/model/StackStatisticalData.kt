package com.neobrahma.portfolio.domain.model

data class StackStatisticalData(
    val label: String,
    val stacks: List<Pair<String, Int>>,
)