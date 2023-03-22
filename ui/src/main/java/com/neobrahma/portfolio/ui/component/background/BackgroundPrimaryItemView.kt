package com.neobrahma.portfolio.ui.component.background

import android.graphics.BlurMaskFilter
import android.graphics.Paint
import android.graphics.PointF
import android.graphics.RectF
import androidx.compose.foundation.Canvas
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.Path
import androidx.compose.ui.graphics.asAndroidPath
import androidx.compose.ui.graphics.drawscope.Stroke
import androidx.compose.ui.graphics.drawscope.drawIntoCanvas
import androidx.compose.ui.graphics.nativeCanvas
import androidx.compose.ui.res.dimensionResource
import com.neobrahma.portfolio.computePositionOnCircle
import com.neobrahma.portfolio.convertValueToDegree
import com.neobrahma.portfolio.ui.R
import kotlin.math.abs

@Composable
fun BackgroundPrimaryItemView(
    modifier: Modifier,
    colorLine : Int = 0xFFD7225E.toInt(),
    drawBackArrow : Boolean = false,
    drawGoArrow : Boolean = false
) {
    val sizeView = dimensionResource(R.dimen.stroke_96)
    val paddingStroke = dimensionResource(R.dimen.stroke_2)

    Canvas(modifier = modifier) {
        val width = size.width
        val height = size.height

        val paddingVertical = (height - sizeView.toPx()) / 2
        val paddingHorizontal = 0f

        val middleY = height / 2

        val pointStart = PointF(paddingHorizontal + middleY, paddingVertical)
        val pointCenter = PointF(paddingHorizontal + middleY, middleY)

        val listShape: ArrayList<PointF> = arrayListOf()
        val listBackground: ArrayList<PointF> = arrayListOf()

        //to draw the octagon
        val side = 8
        val deltaAngle = 360 / side
        val startAngle = deltaAngle / 2

        for (i in 0 until side) {
            val angle = startAngle + deltaAngle * i
            listShape.add(
                computePositionOnCircle(
                    convertValueToDegree(angle),
                    pointStart,
                    pointCenter
                )
            )
        }

        //translation to have space to draw the back arrow and go arrow
        if(drawGoArrow || drawBackArrow) {
            val deltaTranslationX = listShape[1].x - (paddingStroke.toPx() * (3 + 2) * 3)
            listShape.forEach {
                it.x = it.x - deltaTranslationX
            }
        }

        val rectF = RectF(
            listShape[1].x,
            paddingVertical,
            width - listShape[1].x,
            height - paddingVertical
        )

        val marginX = abs(listShape[0].x - listShape[1].x)

        listBackground.add(listShape[0])
        listBackground.add(PointF(rectF.right - marginX, listShape[0].y))
        listBackground.add(PointF(rectF.right, listShape[6].y))
        listBackground.add(PointF(rectF.right, listShape[5].y))
        listBackground.add(PointF(rectF.right - marginX, listShape[4].y))
        listBackground.add(listShape[4])

        val path = Path()
        initPath(path, listShape)
        path.close()
        initPath(path, listBackground)

        val pathArrow = Path()
        val padding = (listShape[1].x / 2) + (paddingStroke.toPx() * 3)
        //arrow back
        if (drawBackArrow) {
            pathArrow.moveTo(listShape[0].x - padding, listShape[0].y)
            pathArrow.lineTo(listShape[1].x - padding, listShape[1].y)
            pathArrow.close()

            pathArrow.moveTo(listShape[2].x - padding, listShape[2].y)
            pathArrow.lineTo(listShape[3].x - padding, listShape[3].y)
            pathArrow.close()
        }

        //arrow open
        if (drawGoArrow) {
            pathArrow.moveTo((rectF.right - marginX) + padding, listShape[7].y)
            pathArrow.lineTo(rectF.right + padding, listShape[6].y)
            pathArrow.close()

            pathArrow.moveTo(rectF.right + padding, listShape[5].y)
            pathArrow.lineTo((rectF.right - marginX) + padding, listShape[4].y)
            pathArrow.close()
        }

        drawIntoCanvas {
            val paint = Paint().apply {
                color = colorLine
                strokeWidth = paddingStroke.toPx() * 3
                style = Paint.Style.STROKE
                maskFilter = BlurMaskFilter(16f, BlurMaskFilter.Blur.NORMAL)
            }
            it.nativeCanvas.drawPath(path.asAndroidPath(), paint)

            val paintArrow = Paint().apply {
                color = 0xFFFFFFFF.toInt()
                strokeWidth = paddingStroke.toPx() * 3
                style = Paint.Style.STROKE
                strokeCap = Paint.Cap.BUTT
                maskFilter = BlurMaskFilter(16f, BlurMaskFilter.Blur.NORMAL)
            }
            it.nativeCanvas.drawPath(pathArrow.asAndroidPath(), paintArrow)
        }

        drawPath(
            path,
            Color.White,
            style = Stroke(width = paddingStroke.toPx())
        )

        drawPath(
            pathArrow,
            Color.White,
            style = Stroke(width = paddingStroke.toPx())
        )
    }
}

private fun initPath(path: Path, list: List<PointF>) {
    val startPointF = list[0]
    path.moveTo(startPointF.x, startPointF.y)
    for (index in 1 until list.size) {
        val pointF = list[index]
        path.lineTo(pointF.x, pointF.y)
    }
}