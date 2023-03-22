package com.neobrahma.portfolio.ui.component.item.primary

import androidx.compose.foundation.layout.Row
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import com.neobrahma.portfolio.ui.component.dot.DotUI
import com.neobrahma.portfolio.ui.component.dot.DotView

@Composable
fun PrimaryItemView(
    modifier: Modifier = Modifier,
    dotUI: DotUI,
    primaryItemUI: PrimaryItemUI
) {
    Row(modifier = modifier) {
        DotView(
            state = dotUI.stateDote,
            colors = dotUI.colors
        )
        PrimaryDetailView(primaryItemUI)
    }
}