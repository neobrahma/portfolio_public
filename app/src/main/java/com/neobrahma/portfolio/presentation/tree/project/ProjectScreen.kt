package com.neobrahma.portfolio.presentation.tree.project

import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.navigation.NavController
import com.neobrahma.portfolio.presentation.tree.HomeViewModel
import com.neobrahma.portfolio.presentation.tree.TreeView

@Composable
fun ProjectScreen(
    navController: NavController,
    viewModel: HomeViewModel, modifier: Modifier
) {
    val uiState by viewModel.uiStateProject.collectAsState()
    TreeView(modifier, navController, uiState)
}