package com.neobrahma.portfolio.ui.component.item.task

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.width
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.constraintlayout.compose.ConstraintLayout
import androidx.constraintlayout.compose.Dimension
import com.neobrahma.portfolio.ui.R
import com.neobrahma.portfolio.ui.component.background.BackgroundItemView
import com.neobrahma.portfolio.ui.component.dot.DotUI
import com.neobrahma.portfolio.ui.component.dot.DotView

@Composable
fun TaskItemView(dotUI: DotUI, content: TaskItemUI) {
    ConstraintLayout(
        modifier = Modifier
            .fillMaxWidth()
    ) {
        val (dotRef, backgroundRef, titleRef, descriptionRef) = createRefs()

        val startGuideline = createGuidelineFromStart(80.dp)
        val endGuideline = createGuidelineFromEnd(32.dp)
        val topGuideline = createGuidelineFromTop(16.dp)
        val bottomGuideline = createGuidelineFromBottom(24.dp)

        DotView(
            state = dotUI.stateDote,
            colors = dotUI.colors,
            modifier = Modifier
                .width(48.dp)
                .constrainAs(dotRef) {
                    start.linkTo(parent.start)
                    top.linkTo(parent.top)
                    bottom.linkTo(parent.bottom)
                    height = Dimension.fillToConstraints
                }
        )

        Text(
            text = content.title,
            color = colorResource(id = R.color.white),
            style = MaterialTheme.typography.subtitle1,
            modifier = Modifier
                .constrainAs(titleRef) {
                    start.linkTo(startGuideline)
                    top.linkTo(topGuideline)
                }
        )

        BackgroundItemView(
            modifier = Modifier
                .constrainAs(backgroundRef) {
                    top.linkTo(parent.top)
                    bottom.linkTo(parent.bottom)
                    start.linkTo(dotRef.end)
                    end.linkTo(parent.end)
                    width = Dimension.fillToConstraints
                    height = Dimension.fillToConstraints
                }
        )

        Column(
            modifier = Modifier
                .constrainAs(descriptionRef) {
                    start.linkTo(startGuideline)
                    end.linkTo(endGuideline)
                    top.linkTo(titleRef.bottom, 8.dp)
                    bottom.linkTo(bottomGuideline)
                    width = Dimension.fillToConstraints
                }
        ) {
            content.description.forEach {
                Text(
                    text = it,
                    color = colorResource(id = R.color.orange),
                    style = MaterialTheme.typography.subtitle2,
                )
            }
        }


    }
}