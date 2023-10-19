package com.example.rolldice

import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable

@Composable
fun NavGraph(navController: NavHostController) {
    NavHost(navController = navController, startDestination = Screens.Roll.route+ "?result={result}") {
        composable(route = Screens.Roll.route + "?result={result}") { navBackStack ->
            val resultShow: Int = navBackStack.arguments?.getString("result")?.toIntOrNull() ?: 1
            DiceWithButtonAndImage(navController = navController, resultShow = resultShow)
        }
        composable(route = Screens.DiceResult.route + "?result={result}") { navBackStack ->
            val resultShow: Int = navBackStack.arguments?.getString("result")?.toIntOrNull() ?: 1
            DiceResult(navController = navController, resultShow = resultShow)
        }
        composable(route = Screens.Card.route) {
            Card(navController = navController)
        }
        composable(route = Screens.Increment.route + "?result={result}") { navBackStack ->
            val resultShow: Int = navBackStack.arguments?.getString("result")?.toIntOrNull() ?: 1
            DiceResultWithIncrement(navController = navController, resultShow = resultShow)
        }
        composable(route = Screens.Pages.route + "?result={result}"){ navBackStack ->
            val resultShow: Int = navBackStack.arguments?.getString("result")?.toIntOrNull() ?: 1
            Pages(navController = navController, resultShow = resultShow)
        }
        composable(route = Screens.DiceResultWithTextField.route + "?result={result}"){ navBackStack ->
            val resultShow: Int = navBackStack.arguments?.getString("result")?.toIntOrNull() ?: 1
            DiceResultWithTextField(navController = navController, resultShow = resultShow)
        }
        composable(route = Screens.DiceResultReturn.route + "?result={result}"){ navBackStack ->
            val resultShow: Int = navBackStack.arguments?.getString("result")?.toIntOrNull() ?: 1
            Page6(navController = navController, resultShow = resultShow)
        }
    }
}
