package com.neobrahma.portfolio.ui.component.camembert

import androidx.compose.foundation.Canvas
import androidx.compose.foundation.layout.size
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp

@Composable
fun Camembert(modifier: Modifier, camemberts: List<CamembertUi>) {
    Canvas(modifier = modifier.size(96.dp)) {
        var startAngle = 0f
        camemberts.forEach { camembert ->

            drawArc(
                color = Color(android.graphics.Color.HSVToColor(camembert.arrayColor)),
                startAngle = startAngle,
                sweepAngle = camembert.percent,
                useCenter = true
            )
            startAngle += camembert.percent
        }
    }
}