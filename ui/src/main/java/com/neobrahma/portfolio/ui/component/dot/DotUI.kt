package com.neobrahma.portfolio.ui.component.dot

data class DotUI(
    val colors: List<Int>,
    var stateDote: DotStateView = DotStateView.ALL
)

enum class DotStateView {
    START, END, ALL, POINT
}