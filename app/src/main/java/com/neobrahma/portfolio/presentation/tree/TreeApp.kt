package com.neobrahma.portfolio.presentation.tree

import androidx.compose.material.Surface
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import com.neobrahma.portfolio.presentation.tree.client.ClientScreen
import com.neobrahma.portfolio.presentation.tree.companies.CompaniesScreen
import com.neobrahma.portfolio.presentation.tree.company.CompanyScreen
import com.neobrahma.portfolio.presentation.tree.project.ProjectClientScreen
import com.neobrahma.portfolio.presentation.tree.project.ProjectScreen

@Composable
fun TreeApp(
    modifier: Modifier,
    viewModel: HomeViewModel = hiltViewModel()
) {
    val navController = rememberNavController()

    Surface {
        NavHost(navController = navController, startDestination = "home") {
            composable(
                route = "home"
            )
            {
                CompaniesScreen(navController, viewModel, modifier)
            }
            composable(
                route = "company/{companyId}",
                arguments = listOf(navArgument("companyId") { type = NavType.IntType })
            ) {
                CompanyScreen(
                    navController,
                    it.arguments?.getInt("companyId") ?: 0,
                    viewModel,
                    modifier
                )
            }
            composable(
                route = "company/{companyId}/client/{clientId}",
                arguments = listOf(
                    navArgument("companyId") { type = NavType.IntType },
                    navArgument("clientId") { type = NavType.IntType })
            ) {
                ClientScreen(
                    navController,
                    it.arguments?.getInt("companyId") ?: 0,
                    it.arguments?.getInt("clientId") ?: 0,
                    viewModel,
                    modifier
                )
            }
            composable(
                route = "company/{companyId}/client/{clientId}/project/{projectId}",
                arguments = listOf(
                    navArgument("companyId") { type = NavType.IntType },
                    navArgument("clientId") { type = NavType.IntType },
                    navArgument("projectId") { type = NavType.IntType },
                )
            ) {
                ProjectClientScreen(
                    navController,
                    it.arguments?.getInt("companyId") ?: 0,
                    it.arguments?.getInt("clientId") ?: 0,
                    it.arguments?.getInt("projectId") ?: 0,
                    viewModel,
                    modifier
                )
            }
            composable(
                route = "company/{companyId}/project/{projectId}",
                arguments = listOf(
                    navArgument("companyId") { type = NavType.IntType },
                    navArgument("projectId") { type = NavType.IntType },
                )
            ) {
                ProjectScreen(
                    navController,
                    it.arguments?.getInt("companyId") ?: 0,
                    it.arguments?.getInt("projectId") ?: 0,
                    viewModel,
                    modifier
                )
            }
        }
    }
}