package com.neobrahma.portfolio.presentation.tree.client

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
fun ClientScreen(
    navController: NavController,
    viewModel: HomeViewModel,
    modifier: Modifier
) {
    val uiStateClient by viewModel.uiStateClient.collectAsState()

    when (val uiState = uiStateClient) {
        is ClientUiState.DisplayTree -> {
            DisplayTreeView(
                modifier,
                navController,
                uiState.tree,
                viewModel
            )
        }
        is ClientUiState.DisplayLoader -> {
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
                        is Action.Navigate -> {
                            viewModel.initProjectClientScreen(
                                action.ids[0],
                                action.ids[1],
                                action.ids[2]
                            )
                            navController.navigate(action.route)
                        }
                        is Action.PopBackStack -> {
                            navController.popBackStack()
                        }
                        is Action.PopUpTo -> {
                            navController.navigate(action.route) {
                                popUpTo(action.route)
                                launchSingleTop = true
                            }
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