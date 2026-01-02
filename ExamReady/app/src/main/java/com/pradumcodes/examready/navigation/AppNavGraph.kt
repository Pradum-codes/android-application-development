package com.pradumcodes.examready.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import com.pradumcodes.examready.screens.FormScreen
import com.pradumcodes.examready.screens.HomeScreen
import com.pradumcodes.examready.screens.SummaryScreen

@Composable
fun AppNavGraph() {
    val navController = rememberNavController()

    NavHost(
        navController = navController,
        startDestination = "home"
    ) {
        composable("home") {
            HomeScreen(navController)
        }
        composable(
            "form/{name}",
            arguments = listOf(navArgument("name") { type = NavType.StringType })
        ) {
            FormScreen(
                navController,
                it.arguments?.getString("name") ?: ""
            )
        }
        composable("summary/{result}") {
            SummaryScreen(it.arguments?.getString("result") ?: "")
        }
    }
}
