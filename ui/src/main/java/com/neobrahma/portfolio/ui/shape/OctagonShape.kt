package com.neobrahma.portfolio.ui.shape

import android.graphics.PointF
import androidx.compose.ui.geometry.Size
import androidx.compose.ui.graphics.Outline
import androidx.compose.ui.graphics.Path
import androidx.compose.ui.graphics.Shape
import androidx.compose.ui.unit.Density
import androidx.compose.ui.unit.LayoutDirection
import com.neobrahma.portfolio.computePositionOnCircle
import com.neobrahma.portfolio.convertValueToDegree

class OctagonShape : Shape {

    override fun createOutline(
        size: Size,
        layoutDirection: LayoutDirection,
        density: Density
    ): Outline {

        val pointStart = PointF(size.width / 2, 0f)
        val pointCenter = PointF(size.width / 2, size.height / 2)

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

        return Outline.Generic(path)
    }
}