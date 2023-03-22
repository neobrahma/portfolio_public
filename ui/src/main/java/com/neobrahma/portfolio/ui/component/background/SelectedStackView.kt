package com.neobrahma.portfolio.ui.component.background

import android.graphics.BlurMaskFilter
import android.graphics.Paint
import android.graphics.PointF
import androidx.compose.foundation.Canvas
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Path
import androidx.compose.ui.graphics.asAndroidPath
import androidx.compose.ui.graphics.drawscope.Stroke
import androidx.compose.ui.graphics.drawscope.drawIntoCanvas
import androidx.compose.ui.graphics.nativeCanvas
import androidx.compose.ui.platform.LocalDensity
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.unit.dp
import com.neobrahma.portfolio.computePositionOnCircle
import com.neobrahma.portfolio.convertValueToDegree
import com.neobrahma.portfolio.ui.R

@Composable
fun SelectedStackView(
    modifier : Modifier
){
    val colorLine = colorResource(id = R.color.white)
    val paddingStroke = with(LocalDensity.current) { 2.dp.toPx() }

    Canvas(modifier = modifier) {

        val width = size.width
        val height = size.height

        val pointStart = PointF(width / 2, 0f)
        val pointCenter = PointF(width / 2, height / 2)

        val side = 8
        val deltaAngle = 360 / side
        val startAngle = deltaAngle / 2

        val path = Path()

        for (i in 0 until side) {
            val angle = startAngle + deltaAngle * i
            val pointF = computePositionOnCircle(
                convertValueToDegree(angle),
                pointStart,
                pointCenter
            )

            if (i == 0) {
                path.moveTo(pointF.x, pointF.y)
            } else {
                path.lineTo(pointF.x, pointF.y)
            }
        }

        path.close()

        drawIntoCanvas {
            val paint = Paint().apply {
                color = 0xFFD7225E.toInt()
                strokeWidth = paddingStroke * 3
                style = Paint.Style.STROKE
                maskFilter = BlurMaskFilter(16f, BlurMaskFilter.Blur.NORMAL)
            }

            it.nativeCanvas.drawPath(path.asAndroidPath(), paint)
        }

        drawPath(path = path, color = colorLine, style = Stroke(width = paddingStroke))
    }
}