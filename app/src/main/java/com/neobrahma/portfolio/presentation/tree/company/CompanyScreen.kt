package com.neobrahma.portfolio.presentation.tree.company

import androidx.compose.foundation.clickable
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.navigation.NavController
import com.neobrahma.portfolio.presentation.tree.HomeViewModel
import com.neobrahma.portfolio.presentation.tree.model.Action
import com.neobrahma.portfolio.presentation.tree.model.Tree
import com.neobrahma.portfolio.ui.component.LoaderView
import com.neobrahma.portfolio.ui.component.item.primary.PrimaryItemView

@Composable
fun CompanyScreen(
    navController: NavController,
    viewModel: HomeViewModel,
    modifier: Modifier
) {
    val uiStateCompany by viewModel.uiStateCompany.collectAsState()

    when (val uiState = uiStateCompany) {
        is CompanyUiState.DisplayTree -> {
            DisplayTreeView(
                modifier,
                navController,
                uiState.tree,
                viewModel
            )
        }
        is CompanyUiState.DisplayLoader -> {
            LoaderView(modifier)
        }
    }
}

@Composable
fun DisplayTreeView(
    modifier: Modifier,
    navController: NavController,
    tree: List<Tree.PrimaryItem>,
    viewModel: HomeViewModel
) {
    LazyColumn(
        modifier = modifier
    ) {
        items(tree) {
            PrimaryItemView(
                Modifier.clickable {
                    when (val action = it.action) {
                        is Action.NavigateFromCompany -> {
                            if (action.isClient) {
                                viewModel.initClientScreen(action.ids[0], action.ids[1])
                            } else {
                                viewModel.initProjectScreen(action.ids[0], action.ids[1])
                            }
                            navController.navigate(action.route)
                        }
                        is Action.PopBackStack -> {
                            navController.popBackStack()
                        }
                        else -> {

                        }
                    }
                },
                it.dotUI,
                it.primaryItemUI
            )
        }
    }
}