package com.neobrahma.portfolio.presentation.tree

import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material.Surface
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.neobrahma.portfolio.presentation.tree.client.ClientScreen
import com.neobrahma.portfolio.presentation.tree.companies.CompaniesScreen
import com.neobrahma.portfolio.presentation.tree.company.CompanyScreen
import com.neobrahma.portfolio.presentation.tree.project.*

@Composable
fun TreeApp(
    modifier: Modifier = Modifier.fillMaxSize(),
    viewModel: HomeViewModel = hiltViewModel()
) {
    val navController = rememberNavController()

    Surface {
        NavHost(
            navController = navController,
            startDestination = CompaniesDestination.destination
        ) {
            composable(
                route = CompaniesDestination.destination
            )
            {
                CompaniesScreen(navController, viewModel, modifier)
            }
            composable(
                route = CompanyDestination.destination
            ) {
                CompanyScreen(
                    navController,
                    viewModel,
                    modifier
                )
            }
            composable(
                route = ClientDestination.destination
            ) {
                ClientScreen(
                    navController,
                    viewModel,
                    modifier
                )
            }
            composable(
                route = ProjectClientDestination.destination
            ) {
                ProjectClientScreen(
                    navController,
                    viewModel,
                    modifier
                )
            }
            composable(
                route = ProjectDestination.destination
            ) {
                ProjectScreen(
                    navController,
                    viewModel,
                    modifier
                )
            }
        }
    }
}