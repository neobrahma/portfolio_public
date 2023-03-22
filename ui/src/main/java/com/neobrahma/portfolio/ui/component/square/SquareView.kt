package com.neobrahma.portfolio.ui.component.square

import androidx.compose.foundation.Canvas
import androidx.compose.foundation.layout.size
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.geometry.Size
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp

@Composable
fun SquareView(arrayColor: FloatArray) {
    val white = Color(android.graphics.Color.HSVToColor(arrayColor))
    Canvas(modifier = Modifier.size(16.dp)) {
        val size = Size(size.width, size.height)
        val offset = Offset(0f, 0f)
        drawRect(
            color = white,
            topLeft = offset,
            size = size
        )
    }
}