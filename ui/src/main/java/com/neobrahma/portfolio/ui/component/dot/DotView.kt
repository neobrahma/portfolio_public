package com.neobrahma.portfolio.ui.component.dot

import android.graphics.BlurMaskFilter
import android.graphics.LinearGradient
import android.graphics.Paint
import android.graphics.Shader
import androidx.compose.foundation.Canvas
import androidx.compose.foundation.layout.size
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.geometry.Size
import androidx.compose.ui.graphics.drawscope.drawIntoCanvas
import androidx.compose.ui.graphics.nativeCanvas
import androidx.compose.ui.platform.LocalDensity
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.unit.dp
import com.neobrahma.portfolio.ui.R

@Composable
fun DotView(
    state: DotStateView,
    colors: List<Int>,
    modifier: Modifier = Modifier
        .size(48.dp, 116.dp)
) {
    val white = colorResource(id = R.color.white)
    val paddingStroke = with(LocalDensity.current) { 2.dp.toPx() }

    Canvas(modifier = modifier) {
        val canvasWidth = size.width
        val canvasHeight = size.height

        val middleX = canvasWidth / 2f
        val middleY = canvasHeight / 2f

        val rectWith = canvasWidth / 30

        val offset: Offset
        val size: Size

        when (state) {
            DotStateView.START -> {
                offset = Offset(x = (canvasWidth - rectWith) / 2, y = middleY)
                size = Size(rectWith, canvasHeight / 2f)
            }
            DotStateView.END -> {
                offset = Offset(x = (canvasWidth - rectWith) / 2, y = 0f)
                size = Size(rectWith, canvasHeight / 2f)
            }
            else -> {
                offset = Offset(x = (canvasWidth - rectWith) / 2, y = 0f)
                size = Size(rectWith, canvasHeight)
            }
        }

        if (state != DotStateView.POINT) {
            drawIntoCanvas {

                val paint = Paint().apply {
                    color = colors[0]
                    strokeWidth = paddingStroke * 3
                    style = Paint.Style.STROKE
                    maskFilter = BlurMaskFilter(16f, BlurMaskFilter.Blur.NORMAL)
                    if (colors.size > 1) {

                        val tabColor = intArrayOf(colors[0], colors[1], colors[1])
                        val tabPosition = floatArrayOf(0f, 0.5f, 1f)

                        shader = LinearGradient(
                            offset.x + (size.width / 2),
                            offset.y,
                            offset.x + (size.width / 2),
                            offset.y + size.height,
                            tabColor,
                            tabPosition,
                            Shader.TileMode.MIRROR
                        )
                    }
                }

                it.nativeCanvas.drawLine(
                    offset.x + (size.width / 2),
                    offset.y,
                    offset.x + (size.width / 2),
                    offset.y + size.height,
                    paint
                )
            }
            drawRect(
                color = white,
                topLeft = offset,
                size = size
            )
        }

        drawCircle(
            color = white,
            center = Offset(x = middleX, y = middleY),
            radius = canvasWidth / 5
        )
    }
}