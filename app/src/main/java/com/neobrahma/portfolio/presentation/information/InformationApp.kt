package com.neobrahma.portfolio.presentation.information

import androidx.compose.material.Surface
import androidx.compose.runtime.Composable
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.neobrahma.portfolio.presentation.information.info.InformationScreen

@Composable
fun InformationApp() {
    val navController = rememberNavController()
    Surface {
        NavHost(navController = navController, startDestination = "filter") {
            composable(route = "filter")
            {
                InformationScreen()
            }
        }
    }
}