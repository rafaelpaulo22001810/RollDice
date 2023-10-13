package com.example.rolldice

import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable

@Composable
fun NavGraph(navController: NavHostController) {
    NavHost(navController = navController, startDestination = Screens.Roll.route) {
        composable(route = Screens.Roll.route) {
            DiceWithButtonAndImage(navController = navController)
        }
        composable(route = Screens.DiceResult.route + "?result={result}") { navBackStack ->
            var resultShow: Int = navBackStack.arguments?.getString("result")?.toIntOrNull() ?: 1
            DiceResult(navController = navController, resultShow = resultShow)
        }
        composable(route = Screens.Card.route) {
            Card(navController = navController)
        }
        composable(route = Screens.Increment.route + "?result={result}") { navBackStack ->
            var resultShow: Int = navBackStack.arguments?.getString("result")?.toIntOrNull() ?: 1
            DiceResultWithIncrement(navController = navController, resultShow = resultShow)
        }
        composable(route = Screens.Pages.route + "?result={result}"){ navBackStack ->
            var resultShow: Int = navBackStack.arguments?.getString("result")?.toIntOrNull() ?: 1
            Pages(navController = navController, resultShow = resultShow)
        }
    }
}
