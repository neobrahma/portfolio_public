package com.neobrahma.portfolio.ui.component.item.stack

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import androidx.constraintlayout.compose.ConstraintLayout
import androidx.constraintlayout.compose.Dimension
import com.neobrahma.portfolio.ui.R
import com.neobrahma.portfolio.ui.component.background.BackgroundItemView
import com.neobrahma.portfolio.ui.component.dot.DotUI
import com.neobrahma.portfolio.ui.component.dot.DotView
import com.neobrahma.portfolio.ui.component.stack.StackView

@Composable
fun StackItemView(dotUI: DotUI, content: List<List<StackItemUI>>) {
    ConstraintLayout(
        modifier = Modifier
            .fillMaxWidth()
    ) {
        val (dotRef, backgroundRef, descriptionRef) = createRefs()

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
                    top.linkTo(topGuideline)
                    bottom.linkTo(bottomGuideline)
                    width = Dimension.fillToConstraints
                }
        ) {
            content.forEach { list ->
                Row {
                    list.forEach {
                        Column(
                            modifier = Modifier.weight(it.stacks.size.toFloat(), fill = true),
                            horizontalAlignment = Alignment.CenterHorizontally
                        ) {
                            Text(
                                text = it.title,
                                color = colorResource(id = R.color.white),
                                style = MaterialTheme.typography.subtitle1,
                                maxLines = 1,
                                overflow = TextOverflow.Ellipsis
                            )
                            LazyRow(
                                modifier = Modifier.padding(0.dp, 8.dp, 0.dp, 0.dp)
                            ) {
                                items(it.stacks) { stack ->
                                    StackView(stack)
                                }
                            }
                        }
                    }
                }
            }
        }
    }
}