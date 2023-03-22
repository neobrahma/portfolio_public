package com.neobrahma.portfolio.ui.component.background

import android.graphics.BlurMaskFilter
import android.graphics.Paint
import android.graphics.PointF
import android.graphics.RectF
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
import com.neobrahma.portfolio.ui.R

@Composable
fun BackgroundItemView(
    modifier: Modifier
) {
    val colorLine = colorResource(id = R.color.white)
    val paddingStroke = with(LocalDensity.current) { 2.dp.toPx() }
    val sizeImage = with(LocalDensity.current) { 24.dp.toPx() }

    Canvas(modifier = modifier) {

        val width = size.width
        val height = size.height

        val paddingVertical = paddingStroke * 4
        val paddingHorizontal = paddingStroke * 4
        val rectBackground = RectF(
            paddingHorizontal,
            paddingVertical,
            width - paddingHorizontal,
            height - paddingVertical
        )

        val list: ArrayList<PointF> = arrayListOf()
        list.add(PointF(rectBackground.left + sizeImage, rectBackground.top))
        list.add(PointF(rectBackground.right - sizeImage, rectBackground.top))
        list.add(PointF(rectBackground.right, rectBackground.top + sizeImage))
        list.add(PointF(rectBackground.right, rectBackground.bottom - sizeImage))
        list.add(PointF(rectBackground.right - sizeImage, rectBackground.bottom))
        list.add(PointF(rectBackground.left + sizeImage, rectBackground.bottom))
        list.add(PointF(rectBackground.left, rectBackground.bottom - sizeImage))
        list.add(PointF(rectBackground.left, rectBackground.top + sizeImage))

        val startPointF = list[0]
        val path = Path()
        path.moveTo(startPointF.x, startPointF.y)
        for (index in 1 until list.size) {
            val pointF = list[index]
            path.lineTo(pointF.x, pointF.y)
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