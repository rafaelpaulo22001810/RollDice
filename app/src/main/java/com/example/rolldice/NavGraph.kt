package com.example.rolldice

import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable

@Composable
fun NavGraph (navController: NavHostController){
    NavHost(navController = navController, startDestination = Screens.Roll.route){
        composable(route = Screens.Roll.route){
            DiceWithButtonAndImage(navController = navController)
        }
        composable(route = Screens.DiceResult.route){
            DiceResult(navController = navController)
        }
    }
}
