package com.hynekbraun.randomanimalscompose1.presentation.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.NavType
import androidx.navigation.Navigation
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import com.hynekbraun.randomanimalscompose1.presentation.animal_detail.AnimalFactDetailScreen
import com.hynekbraun.randomanimalscompose1.presentation.animal_list.AnimalFactListScreen

@Composable
fun Navigation() {
    val navController = rememberNavController()
    NavHost(
        navController = navController,
        startDestination = NavScreen.ListScreen.route
    ) {
        composable(route = NavScreen.ListScreen.route) {
            AnimalFactListScreen(navController = navController)
        }
        composable(route = NavScreen.DetailScreen.route + "/{id}",
            arguments = listOf(
                navArgument("id") {
                    type = NavType.StringType
                    nullable = true
                }
            )
        ) { entry ->
            AnimalFactDetailScreen(id = entry.arguments?.getString("id"))

        }
    }

}