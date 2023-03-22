package com.neobrahma.portfolio.presentation.tree.client

import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.navigation.NavController
import com.neobrahma.portfolio.presentation.tree.HomeViewModel
import com.neobrahma.portfolio.presentation.tree.TreeView

@Composable
fun ClientScreen(
    navController: NavController,
    companyId: Int,
    clientId: Int,
    viewModel: HomeViewModel, modifier: Modifier
) {
    val uiState by viewModel.uiStateClient.collectAsState()

    LaunchedEffect(Unit) {
        viewModel.initClientScreen(companyId, clientId)
    }

    TreeView(modifier, navController, uiState)
}