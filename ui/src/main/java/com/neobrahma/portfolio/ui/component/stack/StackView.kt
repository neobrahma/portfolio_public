package com.neobrahma.portfolio.ui.component.stack

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.neobrahma.portfolio.ui.R
import com.neobrahma.portfolio.ui.component.stack.StackUI
import com.neobrahma.portfolio.ui.shape.OctagonShape

@Composable
fun StackView(uiModel: StackUI) {
    Column(
        modifier = Modifier.width(80.dp),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Image(
            painter = painterResource(id = uiModel.idDrawable),
            contentDescription = null,
            modifier = Modifier
                .size(64.dp)
                .clip(OctagonShape())
        )
        Text(
            text = uiModel.label,
            style = MaterialTheme.typography.body2,
            color = colorResource(id = R.color.orange),
            modifier = Modifier.padding(vertical = 8.dp),
            textAlign = TextAlign.Center,
            maxLines = 1,
            overflow = TextOverflow.Ellipsis
        )
    }
}