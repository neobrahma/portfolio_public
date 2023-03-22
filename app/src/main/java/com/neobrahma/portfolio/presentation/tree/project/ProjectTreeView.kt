package com.neobrahma.portfolio.presentation.tree

import androidx.compose.foundation.clickable
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.NavController
import com.neobrahma.portfolio.presentation.tree.model.Action
import com.neobrahma.portfolio.presentation.tree.model.Tree
import com.neobrahma.portfolio.presentation.tree.project.ProjectUiState
import com.neobrahma.portfolio.ui.component.LoaderView
import com.neobrahma.portfolio.ui.component.item.context.ContextItemView
import com.neobrahma.portfolio.ui.component.item.primary.PrimaryItemView
import com.neobrahma.portfolio.ui.component.item.stack.StackItemView
import com.neobrahma.portfolio.ui.component.item.task.TaskItemView

@Composable
fun TreeView(
    modifier: Modifier,
    navController: NavController,
    uiState: ProjectUiState
) {
    when (uiState) {
        is ProjectUiState.DisplayTree -> {
            DisplayTreeView(
                modifier,
                navController,
                uiState.tree
            )
        }
        else -> {
            LoaderView(modifier)
        }
    }
}

@Composable
fun DisplayTreeView(
    modifier: Modifier,
    navController: NavController,
    tree: List<Tree>
) {
    LazyColumn(
        modifier = modifier
    ) {
        items(tree) {
            when (it) {
                is Tree.PrimaryItem -> {
                    PrimaryItemView(
                        Modifier.clickable {
                            when (val action = it.action) {
                                is Action.Navigate -> {
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
                                else ->{

                                }
                            }
                        },
                        it.dotUI,
                        it.primaryItemUI
                    )
                }
                is Tree.ContextItem -> {
                    ContextItemView(it.dotUI, it.content)
                }
                is Tree.TaskItem -> {
                    TaskItemView(it.dotUI, it.content)
                }
                is Tree.StackItem -> {
                    StackItemView(it.dotUI, it.content)
                }
            }
        }
    }
}

