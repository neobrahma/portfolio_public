package com.neobrahma.portfolio.presentation.filter.filter

import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.GridItemSpan
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Scaffold
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import com.neobrahma.portfolio.R
import com.neobrahma.portfolio.presentation.filter.filter.model.StackUi
import com.neobrahma.portfolio.ui.component.background.SelectedStackView
import com.neobrahma.portfolio.ui.shape.OctagonShape

@Composable
fun FilterScreen() {
    Scaffold(
        content = {
            Filter(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(it)
            )
        }
    )
}

@Composable
fun Filter(
    modifier: Modifier,
    viewModel: FilterViewModel = hiltViewModel()
) {
    val uiState by viewModel.initFilter.collectAsState(initial = emptyList())

    LazyVerticalGrid(
        columns = GridCells.Fixed(3),
        modifier = modifier
    ) {
        uiState.forEach {
            item(span = { GridItemSpan(3) }) {
                HeaderCategory(it.label)
            }
            items(items = it.stacks, span = { GridItemSpan(1) }) {
                Stack(
                    Modifier.clickable {
                        viewModel.updateStack(it.id, !it.isFavorite)
                    },
                    it
                )
            }
        }
    }
}

@Composable
fun HeaderCategory(label: String) {
    Text(
        text = label,
        color = colorResource(id = R.color.white),
        modifier = Modifier.padding(8.dp),
        style = MaterialTheme.typography.subtitle1,
    )
}

@Composable
fun Stack(modifier: Modifier, uiModel: StackUi) {
    Column(
        modifier = modifier,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Box {
            Image(
                painter = painterResource(id = uiModel.idDrawable),
                contentDescription = null,
                modifier = Modifier
                    .size(96.dp)
                    .clip(OctagonShape())
            )
            if (uiModel.isFavorite) {
                SelectedStackView(
                    modifier = Modifier
                        .size(96.dp)
                )
            }
        }

        Text(
            text = uiModel.label,
            color = colorResource(id = R.color.orange),
            modifier = Modifier.padding(8.dp),
            style = MaterialTheme.typography.body2,
        )
    }
}