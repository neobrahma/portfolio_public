package com.neobrahma.portfolio.presentation.tree.company

import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.navigation.NavController
import com.neobrahma.portfolio.presentation.tree.HomeViewModel
import com.neobrahma.portfolio.presentation.tree.TreeView

@Composable
fun CompanyScreen(
    navController: NavController,
    companyId: Int,
    viewModel: HomeViewModel,
    modifier: Modifier
) {
    val uiState by viewModel.uiStateCompany.collectAsState()

    LaunchedEffect(Unit) {
        viewModel.initCompanyScreen(companyId)
    }

    TreeView(modifier, navController, uiState)
}