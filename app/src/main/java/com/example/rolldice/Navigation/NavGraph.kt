package com.example.rolldice.Navigation

import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.example.rolldice.Card
import com.example.rolldice.DiceResult
import com.example.rolldice.DiceResultWithIncrement
import com.example.rolldice.DiceResultWithTextField
import com.example.rolldice.DiceWithButtonAndImage
import com.example.rolldice.Page6
import com.example.rolldice.Pages
import com.example.rolldice.Screens
import com.example.rolldice.ShakeDetector

@Composable
fun NavGraph(navController: NavHostController, mShakeDetector: ShakeDetector?) {
    NavHost(navController = navController, startDestination = Screens.Roll.route+ "?result={result}") {
        composable(route = Screens.Roll.route + "?result={result}") { navBackStack ->
            val resultShow: Int = navBackStack.arguments?.getString("result")?.toIntOrNull() ?: 1
            DiceWithButtonAndImage(navController = navController, resultShow = resultShow, mShakeDetector = mShakeDetector)
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
