package com.neobrahma.portfolio.ui.theme

import androidx.compose.material.MaterialTheme
import androidx.compose.runtime.Composable

@Composable
fun PortfolioTheme(
    content: @Composable () -> Unit
) {
    MaterialTheme(
        colors = DarkColors,
        typography = portfolioTypography,
        content = content
    )
}