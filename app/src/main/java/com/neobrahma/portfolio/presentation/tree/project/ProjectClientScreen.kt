package com.neobrahma.portfolio.presentation.tree.project

import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.navigation.NavController
import com.neobrahma.portfolio.presentation.tree.HomeViewModel
import com.neobrahma.portfolio.presentation.tree.TreeView

@Composable
fun ProjectClientScreen(
    navController: NavController,
    viewModel: HomeViewModel, modifier: Modifier
) {
    val uiState by viewModel.uiStateProjectClient.collectAsState()
    TreeView(modifier, navController, uiState)
}