package com.neobrahma.portfolio.ui.component.item.primary

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.size
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.constraintlayout.compose.ConstraintLayout
import com.neobrahma.portfolio.ui.R
import com.neobrahma.portfolio.ui.component.background.BackgroundPrimaryItemView
import com.neobrahma.portfolio.ui.shape.OctagonShape

@Composable
fun PrimaryDetailView(ui: PrimaryItemUI) {
    ConstraintLayout(
        modifier = Modifier
            .height(116.dp)
            .fillMaxWidth()
    ) {
        val (iconRef, titleRef, valueRef, testRef, backgroundRef) = createRefs()
        val paddingStart = 27.dp //2 * 3
        Image(
            painter = painterResource(id = ui.idDrawable),
            contentDescription = null,
            modifier = Modifier
                .size(96.dp)
                .clip(OctagonShape())
                .constrainAs(iconRef) {
                    top.linkTo(parent.top)
                    bottom.linkTo(parent.bottom)
                    start.linkTo(parent.start, paddingStart)
                }
        )
        Text(
            text = ui.header,
            color = colorResource(id = R.color.white),
            style = MaterialTheme.typography.subtitle2,
            modifier = Modifier
                .constrainAs(titleRef) {
                    start.linkTo(iconRef.end, 2.dp)
                    top.linkTo(parent.top, 4.dp)
                    bottom.linkTo(testRef.top)
                }
        )
        Text(
            text = ui.title,
            color = colorResource(id = R.color.white),
            style = MaterialTheme.typography.subtitle1,
            modifier = Modifier
                .constrainAs(testRef) {
                    start.linkTo(iconRef.end, 2.dp)
                    top.linkTo(titleRef.bottom)
                    bottom.linkTo(valueRef.top)
                }
        )
        Text(
            text = ui.footer,
            color = colorResource(id = R.color.white),
            style = MaterialTheme.typography.subtitle2,
            modifier = Modifier
                .constrainAs(valueRef) {
                    start.linkTo(iconRef.end, 2.dp)
                    top.linkTo(testRef.bottom)
                    bottom.linkTo(parent.bottom, 4.dp)
                }
        )

        BackgroundPrimaryItemView(
            Modifier
                .fillMaxWidth()
                .constrainAs(backgroundRef) {
                    top.linkTo(parent.top)
                    bottom.linkTo(parent.bottom)
                    start.linkTo(parent.start)
                    end.linkTo(parent.end)
                },
            ui.color,
            ui.isClosable,
            ui.isOpenable
        )
    }
}